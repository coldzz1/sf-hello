package com.kele.tkmybatis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

/**
 * 原子操作:不会被线程调度所打断的操作 一旦这个操作开始执行 就会一直运行到结束 中间不会有任何线程切换
 *
 * 很多方法是没办法做到原子性操作的,并且在分布式架构下 实例多个部署导致jvm级别的锁没法起作用
 * 所以引入了分布式锁
 */
public class RedisUtils {

    @Autowired
    JedisPool jedisPool;

    protected long internalLockLeaseTime=30000;

    private String lock_key="redis_lock";//一个线程操作时的锁键

    private long timeout=999999;//一个线程获取锁的最长等待时间

    //设置加锁过期时间 目的是因为如果在加锁之后的逻辑出现异常，导致最后的释放锁的工作没用做，会导致锁永远不释放

    //setnx 和 expire两条指令不是原子指令 并且 expire命令是依赖与setnx的结果 所以不能用redis的事务来解决
    //而下面这个方式可以使得expire和setnx指令一起执行
    SetParams setParams=SetParams.setParams().nx().px(internalLockLeaseTime);


    /**
     * 设置一个随机数 在释放锁时会先去判断是否跟这个随机数一致 如果一致才会删除该锁 确保这个锁不会被其他线程所释放
     * 除非是因为这个锁到期了而被服务器自动释放
     * @param randomValue
     * @return
     */
    public boolean lock(String randomValue){
        Jedis redis = jedisPool.getResource();
        Long startTime=System.currentTimeMillis();

        //尝试占坑 如果能占到说明这个位置没有其他线程在使用

        try {
            while (true) {

                //尝试获取 执行下面逻辑的锁
                String set = redis.set(lock_key, randomValue, setParams);
                //如果成功 则可以进行下列操作
                if ("OK".equals(set)) {
                    return true;
                }

                long l = System.currentTimeMillis() - startTime;//获取的时间

                //如果超过最长等待时间就不再获取
                if (l > timeout) {
                    return false;
                }

                //休息0.1s进行再次获取
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }finally {
            redis.close();
        }

    }

    //在当前线程执行时对操作逻辑先获取锁
    //执行完后需要解锁  先去匹配相同randomValue 然后对其进行解锁
    public boolean unLock(String randomValue){
        Jedis redis = jedisPool.getResource();
        //这里存在情景是当执行判断出是自己的锁后,此时恰好锁已经失效了,别的线程已经获得了新的锁
        //这个时候在去进行删除此任务的锁 实际上是删除了别人加的锁md

        //根本原因就是这两个操作(判断是否是自己的锁 删除锁) 是分步的  不是原子性操作 所以需要通过Lua脚本保证多个指令的原子性操作
        if(redis.get(lock_key)==(randomValue)){
            redis.del(lock_key);
        }
        return false;
    }
}
