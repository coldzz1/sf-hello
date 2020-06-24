package DesignPattern.SingleTonDemo;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 破坏单例模式的几种方法
 */
public class DestorySingleTon {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, IOException {
        //dstorySingleTonByReflect();
        //destorySingleTonBySerialize();
    }

    //通过序列化破坏单例(底层是通过反射)
    private static void destorySingleTonBySerialize() throws IOException, ClassNotFoundException {
        SingleTonDemo singleTonDemo=SingleTonDemo.getInstance();
        //获取输出流(输出到硬盘)
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream("SingleTonFile"));
        //写入到这个文件
        objectOutputStream.writeObject(singleTonDemo);

        //获取输入流(读取硬盘到内存)
        ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream("SingleTonFile"));
        //读取该文件
        SingleTonDemo serialize = (SingleTonDemo) objectInputStream.readObject();
        System.out.println(singleTonDemo);
        System.out.println(serialize);
        System.out.println("singleTonDemo==serialize:"+(singleTonDemo==serialize));
    }

    //通过反射破坏单例
    //可以通过在构造函数中加判断的方式解决
    private static void dstorySingleTonByReflect() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        SingleTonDemo singleTonDemo=SingleTonDemo.getInstance();
        //获取SingleTonDemo的Class对象
        Class<SingleTonDemo> singleTonDemoClass = (Class<SingleTonDemo>) Class.forName("DesignPattern.SingleTonDemo.SingleTonDemo");
        //获取无参的构造函数(已声明的所有)
        Constructor<SingleTonDemo> constructor = singleTonDemoClass.getDeclaredConstructor(null);
        //设置为可访问
        constructor.setAccessible(true);
        //通过构造函数创建反射的对象
        SingleTonDemo reflect = constructor.newInstance();
        System.out.println(singleTonDemo);
        System.out.println(reflect);
        System.out.println("singleTonDemo==reflect:"+(singleTonDemo==reflect));
    }


}
