package ys.util;

import java.util.Arrays;

public class StringUtil {
    public static boolean isAnyEmpty(String... s) {
        return s == null || s.length == 0 || Arrays.stream(s).anyMatch(StringUtil::isEmpty);
    }

    public static boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty() || s.equalsIgnoreCase("null");
    }
}
