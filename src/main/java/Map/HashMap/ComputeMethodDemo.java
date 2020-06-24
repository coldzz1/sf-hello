package Map.HashMap;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Map接口中的Compute方法
 * 这是一个default的接口方法
 * JDK1.8新用法整理
 */
public class ComputeMethodDemo {

    //compute方法
    //对指定key对应的value进行BiFunction的操作
    //其中BiFunction前两个参数分别为key和key对应的oldValue


    public static void main(String[] args) {
        String str = "hello java, i am vary happy! nice to meet you";

        //JDK1.8以前
        //统计上述str字符每个字符出现的频率
        Map<Character,Integer>statisticsChar=new HashMap<>(16);
        for (int i = 0; i < str.length(); i++) {
            char curChar = str.charAt(i);
            Integer num = statisticsChar.get(curChar);
            if(num==null){
                statisticsChar.put(curChar,1);
            }else statisticsChar.put(curChar,num+1);
        }
        System.out.println(JSON.toJSONString(statisticsChar));

        //JDK1.8新用法
        statisticsChar=new HashMap<>(16);
        for (int i = 0; i < str.length(); i++) {
            char curChar = str.charAt(i);
            statisticsChar.compute(curChar,(k,v)->v==null?1:v+1);
        }
        System.out.println(JSON.toJSONString(statisticsChar));

    }

    /**
     * 函数式编程接口补充
     * Function<T,R>
     * BiFunction<T,U,R>
     */

    //Function<T,R> 是处理一个参数进行转化的函数接口
    //接受一个T类型的参数返回一个R类型的结果
    private static void functionDemo(){
        //实现Function接口的两种方式
        //lambda
        Function<Integer,Integer> function=K->K==null?1:K+1;
        //匿名类
        Function<Integer,Integer>integerFunction=new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer K) {
                return K==null?1:K+1;
            }
        };
    }

    //BiFunction<T, U, R> 是处理两个参数(T,U)的函数接口
    //接收T类型和U类型的两个参数 返回一个R类型的结果
    private static void BiFunctionDemo(){
        //实现BiFunctionDemo的两种方式
        BiFunction<Integer,Integer,Integer>biFunction=(T,U)->T+U;
        BiFunction<Integer,Integer,Integer>integerBiFunction=new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer T, Integer U) {
                return T+U;
            }
        };
    }


}
