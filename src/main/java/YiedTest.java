public class YiedTest  extends Thread{

    /**
     * ThreadLocal.yield();
     * 执行该方法的线程 running  ->   runnable
     * 把当前执行方法线程从执行状态(运行状态变为可执行态(就绪状态)
     * 这个时候cpu会从当前就绪状态下的众多线程中选择一个,
     * 特别注意！！的是也有可能再一次执行被yield的线程
     *
     */

    public YiedTest(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 1; i <= 50; i++) {
            System.out.println(this.getName()+"------"+i);
            if (i == 30) {
                Thread.yield();
            }

        }
    }

    public static void main(String[] args) {
        YiedTest yt1 = new YiedTest("张三");
        YiedTest yt2 = new YiedTest("李四");
        yt1.start();
        yt2.start();
    }

}
