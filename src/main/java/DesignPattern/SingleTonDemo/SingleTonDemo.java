package DesignPattern.SingleTonDemo;

import java.io.Serializable;

/**
 * 懒加载(线程不安全)
 * 这里采用了DCL+volatile的方式保证线程安全
 * 用到了volatile的禁止重排的语义
 */
public class SingleTonDemo implements Serializable {
    private static final long serialVersionUID = 1L;

    private static volatile SingleTonDemo instance=null;

    private SingleTonDemo(){
        if(instance!=null){
            throw new RuntimeException();
        }
    }

    public static SingleTonDemo getInstance(){
        if(instance==null){
            synchronized (SingleTonDemo.class){
                if(instance==null){
                    instance=new SingleTonDemo();
                }
            }
        }
        return instance;
    }

}



