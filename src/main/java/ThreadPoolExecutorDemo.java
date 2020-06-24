import java.util.concurrent.*;

public class ThreadPoolExecutorDemo {

    

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(10,30,100L,TimeUnit.SECONDS,new LinkedBlockingDeque<>());
        FutureTask<String> futureTask=new FutureTask(()->util.getResultA());
        threadPoolExecutor.submit(futureTask);
        FutureTask<String> futureTask1=new FutureTask<>(()->util.getReultB());
        //threadPoolExecutor.submit(futureTask1);

      /*  FutureTask futureTask = new FutureTask<Object>(
                new Callable<Object>() {
                    private String args;
                    private String args2;
                    @Override
                    public Object call() throws Exception {
                        for (int i = 0; i < 100; i++) {
                            System.out.println(args+args2+"_"+i);
                        }
                        return null;
                    }
                }
        );
        ThreadLocal thread=new ThreadLocal(futureTask);
        thread.start();

        try {
            Boolean aBoolean = (Boolean) futureTask.get();
            System.out.println(aBoolean);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
*/

        try {
            futureTask.cancel(true);
            System.out.println(futureTask.get());
            System.out.println(futureTask1.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}

class util{
    String a="a";
    String b="b";


    public static String getResultA(){
        return "a";
    }

    public static String getReultB(){
        return "b";
    }
}
