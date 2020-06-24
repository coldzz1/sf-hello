package multiThreading.ThreadLocal;

import java.util.concurrent.TimeUnit;

public class ThreadLocal1 {
    static volatile Person person=new Person("lisi");

    public static void main(String[] args) {
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(person.name);
        }).start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            person.name="zhangsan";
        }).start();

    }
}

class Person{
    public String name ;

    Person(String name){
        this.name=name;

    }


}
