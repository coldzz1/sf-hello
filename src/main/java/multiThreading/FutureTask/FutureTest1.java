package multiThreading.FutureTask;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description: demo-learn
 * <p>
 * Created by ys on 2020/6/23 12:46
 */
public class FutureTest1 {

    static class Task implements Callable<Integer> {
        @Override
        public Integer call(){
            int i=0;
            for (; i < 10; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + "_"
                            + i);
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return i;
        }
    }
    public static void main(String[] args) {
        Task task = new Task();// 新建异步任务
        FutureTask<Integer> future = new FutureTask<Integer>(task) {
            // 异步任务执行完成，回调
            @Override
            protected void done() {
                try {
                    System.out.println("future.done():" + get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        };
        // 创建线程池（使用了预定义的配置）
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(future);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        // 可以取消异步任务
        // future.cancel(true);

        try {
            //在去调用FutureTask.get()方法时, 如果任务没有执行完,调用get()获取结果时会阻塞
            // 阻塞，等待异步任务执行完毕-获取异步任务的返回值
            System.out.println("future.get():" + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("终于轮到我了");
    }

    /*private Map<String, Connection> connectionPool = new HashMap<String, Connection>();
    private ReentrantLock lock = new ReentrantLock();

    public Connection getConnection(String key){
        try{
            lock.lock();
            if(connectionPool.containsKey(key)){
                return connectionPool.get(key);
            }
            else{
                //创建 Connection
                Connection conn = createConnection();
                connectionPool.put(key, conn);
                return conn;
            }
        }
        finally{
            lock.unlock();
        }
    }

    */



    //创建Connection（根据业务需求，自定义Connection）
    private Connection createConnection(){
        return null;
    }

    private ConcurrentHashMap<String,FutureTask<Connection>>connectionPool = new ConcurrentHashMap<String, FutureTask<Connection>>();

    public Connection getConnection(String key) throws Exception{
        FutureTask<Connection>connectionTask=connectionPool.get(key);
        if(connectionTask!=null){
            return connectionTask.get();
        }
        else{
            Callable<Connection> callable = new Callable<Connection>(){
                @Override
                public Connection call() throws Exception {
                    // TODO Auto-generated method stub
                    return createConnection();
                }
            };
            FutureTask<Connection>newTask = new FutureTask<Connection>(callable);
            connectionTask = connectionPool.putIfAbsent(key, newTask);
            if(connectionTask==null){
                connectionTask = newTask;
                connectionTask.run();
            }
            return connectionTask.get();
        }
    }
}
