package kr;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

/* github에서 주석추가해보기 */
public class MybatisUtil {

    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Object obj) {
        if (obj instanceof String)
            return obj == null || "".equals(obj.toString().trim());
        else if (obj instanceof List)
            return obj == null || ((List) obj).isEmpty();
        else if (obj instanceof Map)
            return obj == null || ((Map) obj).isEmpty();
        else if (obj instanceof Object[])
            return obj == null || Array.getLength(obj) == 0;
        else
            return obj == null;
    }

    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }

    public static boolean contains(String str, String s) {
        if (s.contains(str)) {
            return true;
        }
        return false;
    }

    public static boolean contains(String str, String[] s) {
        if (s[0].contains(str)) {
            return true;
        }
        return false;
    }

}
