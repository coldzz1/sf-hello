package ys.StringFormat;

public class StringFormat {
    public static void main(String[] args) {

        String userName="XXX";
        String userProvince="上海";
        int userAge=21;
        String userSex="男";
        String a ="n";
        String userInfo=String.format(a,userName,userProvince,userAge,userSex);
        System.out.println(userInfo);
    }
}
