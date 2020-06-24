package java8;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Description: demo-learn
 * <p>
 * Created by ys on 2020/6/16 11:46
 */
public class LocalTimeDemo {

    public static void test(){
        Locale.setDefault(Locale.SIMPLIFIED_CHINESE);
        Calendar calendar=Calendar.getInstance();
        calendar.set(2019,Calendar.DECEMBER,29,0,0,0);
        SimpleDateFormat YYYY=new SimpleDateFormat("YYYY-MM-dd");
        System.out.println("格式化:"+YYYY.format(calendar.getTime()));
    }

    public static void main(String[] args) throws InterruptedException, ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(simpleDateFormat.parse("2020-01-01 11:12:13"));

        ExecutorService threadPool = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 20; i++) {
            //提交20个并发解析时间的任务到线程池，模拟并发环境
            threadPool.execute(() -> {
                for (int j = 0; j < 10; j++) {
                    try {
                        System.out.println(simpleDateFormat.parse("2020-01-01 11:12:13"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.HOURS);    }
}
