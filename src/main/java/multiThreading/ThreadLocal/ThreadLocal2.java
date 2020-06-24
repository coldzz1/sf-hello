package multiThreading.ThreadLocal;

import java.util.concurrent.TimeUnit;

public class ThreadLocal2 {
   volatile static ThreadLocal<Person>t=new ThreadLocal<>();
   volatile static ThreadLocal<Person>t2=new ThreadLocal<>();
   static ThreadLocal<Person> t3=new ThreadLocal<Person>(){
       public Person initialValue(){
           return new Person("张三");
       }
   };
    public static void main(String[] args) {
       /* Thread thread1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread1"+t.get().name);
            System.out.println("thread2"+t2.get().name);
        });
        thread1.setName("线程1");
        thread1.start();*/
       /* Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            t.set(new Person("lisi"));
            t2.set(new Person("zhangsan"));
        }
        );*/

       Thread thread=new Thread(new RunnableTets());
       thread.start();

       Thread thread3=new Thread(()->{
           System.out.println(t3.get());
           t3.remove();
       });
       thread3.start();


    }
    static class RunnableTets implements Runnable{


        @Override
        public void run() {
            t.set(new Person("lisi"));
            t2.set(new Person("zhangsan"));
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Person person = t.get();
            Person person1 = t2.get();
            System.out.println(person.name);
            System.out.println(person1.name);
        }
    }
}

