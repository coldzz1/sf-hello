package DesignPattern.Adapter;

/**
 *  需要使用适配器的客户端
 */
public class Client {

    public static void main(String[] args) {
        //创建需要被适配的对象
        Adaptee adaptee=new Adaptee();
        //创建客户端需要调用的接口对象
        Target target=new Adapter(new Adaptee());
        //请求处理
        target.request();
    }
}
