package multiThreading.WeakRerference;


import java.lang.ref.WeakReference;

public class WeakReferenceDemo {
    public static void main(String[] args) {
        Person p=new Person();
        WeakReference<Person> weakReference=new WeakReference<Person>(p);
        System.gc();

    }
}

class Person{
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("person 被回收了");
    }
}
