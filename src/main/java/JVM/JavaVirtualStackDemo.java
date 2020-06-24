package JVM;

/**
 * JVM中的方法区栈,是每个线程私有的,即生命周期和线程同步。
 * 方法区栈中包含栈帧,方法的调用其实就是栈帧的入栈出栈。
 * 栈帧中有局部变量表和操作数栈。 其中局部变量表的大小在方法调用前就已经确定大小了,单位为slot;
 * long 和  double 占两个slot，其余都只占一个slot;
 */

/**
 * 下面是 i++ 和 ++i 的区别
 * i++ 是先把局部变量表中的i读到操作数栈,然后在对局部局部变量表的数值自增；
 * 而++i则是先对局部变量便的数值进行自增,在把自增后的值读到操作数栈；
 */
public class JavaVirtualStackDemo {
    public static void main(String[] args) {
        int j = 0;
        for (int i = 0; i < 10; i++) {
            j=j++;
        }
        System.out.println(j);  //输出的是局部变量表中的数据
    }
    //public static void main(java.lang.String[]);
    //    Code:
    //       0: iconst_0    // 将常数0压入到操作数栈顶
    //       1: istore_1    // 将操作数栈顶元素弹出并压入到局部变量表中1号槽位，也就是j=0
    //       2: iconst_0    // 将常数0压入到操作数栈顶
    //       3: istore_2   // 将操作数栈顶元素弹出并压入到局部变量表中2号槽位，也就是i=0
    //       4: iload_2     // 将2号槽位的元素压入操作数栈顶
    //       5: bipush        10   // 将常数10压入到操作数栈顶，此时操作数栈中有两个数（常数10，以及i）
    //       7: if_icmpge     21  // 比较操作数栈中的两个数，如果i>=10,跳转到第21行
    //      10: iload_1    // 将局部变量表中的1号槽位的元素压入到操作数栈顶，就是将j=0压入操作数栈顶
    //      11: iinc          1, 1 // 将局部变量表中的1号元素自增1，此时局部变量表中的j=1
    //
    //
    //      14: istore_1    // 将操作数栈顶的元素（此时栈顶元素为0）弹出并赋值给局部变量表中的1号             槽位（一号槽位本来已经完成自增了，但是又被赋值成了0）
    //
    //      15: iinc          2, 1 // 将局部变量表中的2号槽位的元素自增1，此时局部变量表中的2号元素值为1，也就是i=1
    //
    //      18: goto          4  // 第一次循环结束，跳转到第四行继续循环
    //      21: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
    //      24: iload_1
    //      25: invokevirtual #3                  // Method java/io/PrintStream.println:(I)V
    //      28: return


  /*  public static void main(String[] args) {
        int i = 4;
        int b = i++;
        int a = ++i;
        System.out.println(b);
        System.out.println(a);
    }*/


   /* public static void main(java.lang.String[]);
    Code:
            0: iconst_4  //将常数4压入操作数栈顶
       1: istore_1       //将操作数栈顶元素弹出并压入局部变量表中1号槽位
       2: iload_1        //将局部变量表的1号槽位的元素压入操作数栈顶
       3: iinc          1, 1 //将局部变量表1号槽元素自增1,此时局部变量表的i=5
            6: istore_2      //将操作数栈顶的元素元素(此时为4)弹出并赋值给局部变量表槽位上2的元素,b=4;
       7: iinc          1, 1 //对局部变量表的1号槽的元素自增 此时i=6
            10: iload_1      //将局部变量表上的1号槽元素弹出压入操作栈顶部
      11: istore_3           //将操作数栈顶的元素赋值给3号槽的元素a a=6;
      12: return*/
}
