package BigDecimal;

import java.math.BigDecimal;

public class BigdecialMethodDemo {
    public static void main(String[] args) {
        System.out.println(new BigDecimal("1225.120000").setScale(2));//出错原因精度丢失问题，要指定舍入模式即可

    }
}
