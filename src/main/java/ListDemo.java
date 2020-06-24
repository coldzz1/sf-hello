import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 对List调用sort进行排序
 */
public class ListDemo {
    public static void main(String[] args) {
        List<Menus> menus = Arrays.asList(new Menus("360view", 1), new Menus("risk-waring", 3), new Menus("quick-tools", 2));
        for (int i = 0; i < menus.size(); i++) {
            System.out.println(menus.get(i));
        }

        //对符合某个条件的List的对象在进行foreach操作
        menus.stream().filter(item->item.getSortNum()>1).forEach(item->item.setCode("aaaa"));
        for (Menus menu : menus) {
            if (menu.getSortNum() > 1) {
                menu.setCode("aaaa");
            }
        }
        //对符合sortNum大于1的对象按SortNum排序
        //collect 生成新的List集合
        List<Menus> collect = menus.stream().filter(item -> item.getSortNum() > 1).sorted(Comparator.comparing(Menus::getSortNum)).collect(Collectors.toList());
        collect.stream().forEach(item->item.setCode("test"));
        //menus.sort(Comparator.comparing(Menus::getSortNum));
        for (int i = 0; i < collect.size(); i++) {
            System.out.println("更改了："+collect.get(i));
        }
    }
}

class Menus{

    Menus(String code,int sortNum){
        this.code=code;
        this.sortNum=sortNum;
    }
    private int sortNum;//排序编码
    private int id;//主键id
    private String code;//菜单编码
    private String hasData;//数据权限
    private String isBtn;//操作权限

    public int getSortNum() {
        return sortNum;
    }

    public void setSortNum(int sortNum) {
        this.sortNum = sortNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getHasData() {
        return hasData;
    }

    public void setHasData(String hasData) {
        this.hasData = hasData;
    }

    public String getIsBtn() {
        return isBtn;
    }

    public void setIsBtn(String isBtn) {
        this.isBtn = isBtn;
    }

    @Override
    public String toString() {
        return  "code:"+code+",sort:"+sortNum;
    }
}
