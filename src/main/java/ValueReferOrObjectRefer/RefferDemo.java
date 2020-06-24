package ValueReferOrObjectRefer;

/**
 * java中方法中传递时
 * 是值传递还是引用传递的问题
 * 一个引用传递到别的方法 实际上是将一份引用！！拷贝了一份！！传到一个方法中去
 * 但是指向的还是同一个对象
 * 而调用trans方法后对person变量引用了新的对象
 * 此时方法内外的两个指针就指向了不同的对象 所以更改一个指针所指向的对象的内容不影响另外一个指针所指想的对象。
 */
public class RefferDemo {
    public static void main(String[] args) {
        Person person=new Person();
        person.setName("可乐");
        System.out.println("传之前:"+person.getName());
        trans(person);
        System.out.println("回到原方法:"+person.getName());//重点看这里

    }


    public  static void  trans(Person person){
        System.out.println("传到trans方法中后1:"+person.getName());
        person=new Person();
        person.setName("雪碧");
        System.out.println("传到trans方法中后2:"+person.getName());
    }
}


class Person{
    private String name ;



    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return this.name;
    }
}