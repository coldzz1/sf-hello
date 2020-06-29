import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description: github
 * <p>
 * Created by ys on 2020/6/28 19:48
 */
public class DoWhileDemo {
    public static void main(String[] args) {
        aliasMap.put("aa","ab");
        String aa = canonicalName("aa");
        System.out.println(aa);

    }
    /** Map from alias to canonical name */
    private  static final Map<String, String> aliasMap = new ConcurrentHashMap<>(16);
    /**
     * Determine the raw name, resolving aliases to canonical names.
     * @param name the user-specified name
     * @return the transformed name
     */
    public static String canonicalName(String name) {
        String canonicalName = name;
        // Handle aliasing...
        String resolvedName;
        do {
            resolvedName = aliasMap.get(canonicalName);
            if (resolvedName != null) {
                canonicalName = resolvedName;
            }
        }
        while (resolvedName != null);
        return canonicalName;
    }
}
