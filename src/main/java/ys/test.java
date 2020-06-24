package ys;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class test {

    public static void main(String[] args) {
        testRandom();
    }
    public static void testRandom() {
        String eid = "aea2a39d8bba0a77487ffddbfe0c875c9";
        System.out.println(eid.hashCode());
        System.out.println(Math.abs(-eid.hashCode()));
        int hashCode = eid.hashCode() & Integer.MAX_VALUE;
        System.out.println(hashCode);
        double v = (18.57 / Integer.MAX_VALUE) * hashCode;
        System.out.println("ceshi:"+v);//获取最大值与hashCode的比值 因为hashCode一定小于MAX_VALUE所以会获取一个与eid相关的小于1的值
        v = new BigDecimal(Double.toString(v)).setScale(2, RoundingMode.UP).doubleValue();
        System.out.println(v);
        double random = Math.random()*25;
        System.out.println(random);
      /*  System.out.println(hashCode>0?true:false);
        System.out.println(new Random().nextInt());
        System.out.println(2.3/5);*/
        //System.out.println(18.57/Integer.MAX_VALUE);
        //double value = 4 + (22.57 - 4) / Integer.MAX_VALUE * hashCode;
//            System.out.printf("hashCode：%s\n", hashCode);
        //System.out.printf("value=%s\n", value);


       /* while(true){
           int i = new Random().nextInt(25);
           if(i>25||i<0){
               System.out.println("大于25");
               break;
           }
       }*/

        /*double a =0.5;
        int hashCoda = eid.hashCode() & Integer.MAX_VALUE;
        double v = 0.5 * hashCoda / Integer.MAX_VALUE;
        System.out.println(v);*/


    }
}
