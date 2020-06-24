package Map.HashMap;

import java.util.*;
import java.util.function.BiFunction;

/**
 * Map接口中的
 * default V putIfAbsent(K key, V value) 如果key对应的oldValue不存在则put value 返回的是执行方法后key对应的value
 *
 * default V computeIfAbsent(K key,
 *             Function<? super K, ? extends V> mappingFunction)
 *             如果key对应的oldValue不存在 则put 对key进行Function后的value 返回的是执行方法后key对应的value
 *
 * default V computeIfPresent(K key,
 *             BiFunction<? super K, ? super V, ? extends V>
 *             如果key对应的oldValue存在时才进行put 对key和oldValue作为 K,V进行BiFunction得到的newValue作为参数进行put 如果newValue为null则会移除原来的key-value对
 *             如果key对应的oldValue不存在 不进行任何操作 返回null
 * JDK1.8新用法整理
 */
public class PutIfAbsentMethodDemo {
    public static void main(String[] args) {
        Map<String,Object>map=new HashMap<>();
        map.put("老婆","骆猪猪");
        //如果没有key对应的value才添加成功  返回的是key对应的新值
        Object o = map.putIfAbsent("老婆", "猪骆骆");
        System.out.println("putIfAbsent返回:"+o);//添加成功返回新值 添加失败返回旧值
        System.out.println(map.get("老婆"));

        //当oldValue==null时,put根据key的Function获取的新值
        map.computeIfAbsent("女儿",k->k+":毛豆");
        System.out.println(map.get("女儿"));



        //只有当key对应的value存在时才能操作
        //会将key ,对应的oldValue作为参数 返回一个oldValue类型的子类 进行put
        Map<String, List<String>>hashMap=new HashMap<>();
        hashMap.put("shareId", new ArrayList<>(Arrays.asList("董事长","直接股东")));
        hashMap.computeIfPresent("shareId3", new BiFunction<String, List<String>, List<String>>() {
            @Override
            public List<String> apply(String s, List<String> strings) {
                strings.add("监视");
                return strings;
            }
        });
        System.out.println(hashMap.get("shareId3"));

        hashMap.compute("shareId2",(k,v)->v==null?new ArrayList<>():v).add("监视");
    }


}
