package multiThreading;

import java.util.concurrent.TimeUnit;

public class TestNotifyNotifyAll {
    public static Object object=new Object();
    public static void main(String[] args) {
        //说明notifyAll()会唤醒所有等待池中的所有线程
        /*ThreadLocal thread1=new ThreadLocal(new RunableImplA(object));
        thread1.setName("线程一");
        ThreadLocal thread2=new ThreadLocal(new RunableImplA(object));
        thread2.setName("线程二");
        ThreadLocal thread3=new ThreadLocal(new RunnableImplC(object));
        thread3.start();
        thread1.start();
        thread2.start();*/

        //说明notify()只会随机唤醒等待池中的某一线程
       /* ThreadLocal thread1=new ThreadLocal(new RunableImplA(object));
        thread1.setName("线程1");
        ThreadLocal thread2=new ThreadLocal(new RunableImplA(object));
        thread2.setName("线程2");
        ThreadLocal thread3=new ThreadLocal(new RunnableImplB(object));
        thread1.start();
        thread2.start();
        thread3.start();
*/
    }
}




class RunableImplA implements Runnable{

    private Object obj ;

    public RunableImplA(Object obj){
        this.obj=obj;
    }


    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+":run on RunnableImpA ");

        synchronized (obj){
            System.out.println(Thread.currentThread().getName()+":RunnableImplA 获得了锁obj");
            System.out.println(Thread.currentThread().getName()+":将要释放锁 进如等待池");
            try {
                obj.wait();//释放obj对象锁,线程进入等待区(不会去获得锁)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+":重新被唤醒");
            //obj.notify();
        }

    }
}

class RunnableImplB implements Runnable{

    private Object obj;//锁变量

    public RunnableImplB(Object obj){
        this.obj=obj;
    }

    @Override
    public void run() {
        System.out.println("run on RunnableImplB ");
        System.out.println("先睡眠3s");
        try {
            Thread.sleep(3000);//避免先被cpu调度
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (obj){
            System.out.println("RunnableImplB 获得了obj锁");
            System.out.println("RunnableImplB 即将唤醒等待池中的线程");//等待池中的线程是不会竞争锁资源的
            obj.notify();
        }
    }
}


class RunnableImplC implements Runnable{

    private Object obj;//锁变量

    public RunnableImplC(Object obj){
        this.obj=obj;
    }


    @Override
    public void run() {
        System.out.println("run on RunnableImplC ");
        System.out.println("C先睡眠3s");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (obj){
            System.out.println("RunnableImpC 获得了锁");
            System.out.println("唤醒等待池中的 所有线程 进入锁区竞争锁资源");
            obj.notifyAll();
        }

    }
}
