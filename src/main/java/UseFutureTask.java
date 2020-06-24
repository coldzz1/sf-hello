import java.util.concurrent.*;

//可以把FutureTask交给Executor执行；也可以通ExecutorService.submit（…）方法返回一个FutureTask，
// 然后执行FutureTask.get()方法或FutureTask.cancel（…）方法。除此以外，还可以单独使用FutureTask。
//当一个线程需要等待另一个线程把某个任务执行完后它才能继续执行，此时可以使用FutureTask。
// 假设有多个线程执行若干任务，每个任务最多只能被执行一次。
// 当多个线程试图同时执行同一个任务时，只允许一个线程执行任务，其他线程需要等待这个任务执行完后才能继续执行。下面是对应的示例代码。
public class UseFutureTask {
    private final ConcurrentMap<Object, Future<String>> taskCache=new ConcurrentHashMap<>();

    private String executionTask(String taskName) throws ExecutionException, InterruptedException {
        while(true){
            Future<String> future=taskCache.get(taskName);//根据传的任务名获取map中的Future
            if(future==null){
                Callable<String>task=()->{ return taskName;};//定义一个线程任务返回参数名称
                FutureTask<String> futureTask  = new FutureTask<>(task);//定义一个task
                future=taskCache.putIfAbsent(taskName,futureTask);
                if(future==null){
                    future=futureTask;
                    futureTask.run();//执行任务
                }

            }
            try {
                taskCache.remove(taskName, future);
                return future.get(); // 1.5,

            } catch (CancellationException e) {
            }

        }
    }
    public static void main(String[] args) {
        ConcurrentHashMap<Object, Object> objectObjectConcurrentHashMap = new ConcurrentHashMap<>();
        objectObjectConcurrentHashMap.put("a","A");
        System.out.println(objectObjectConcurrentHashMap.putIfAbsent("a","b"));//更新value的值
        System.out.println(objectObjectConcurrentHashMap.get("a"));
        System.out.println(objectObjectConcurrentHashMap.put("b","b"));
    }


}
