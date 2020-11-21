package book.test.reflect.objectAnalyzer;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/**
 * @PROJECT_NAME: DataStructures
 * @ClassName ObjectAnalyzer
 * @Author YHL
 * @Email 1359541394@qq.com
 * @Date 2020/10/16 22:01
 * @Version 1.0
 * @Description TODO
 */
public class ObjectAnalyzer {
    private ArrayList<Object> visited = new ArrayList<>();

    // 可以使用该方法查看任意对象内部信息
    public String toString(Object object) {
        if (object == null) return "null";
        if (visited.contains(object)) return "...";
        visited.add(object);
        Class cl = object.getClass();
        if (cl == String.class) return (String) object;
        if (cl.isArray()) {
            String r = cl.getComponentType() + "[]{";
            for (int i = 0; i < Array.getLength(object); i++) {
                if (i > 0) r += ",";
                Object val = Array.get(object, i);
                if (cl.getComponentType().isPrimitive()) r += val;
                else r += toString(val);
            }
            return r + "}";
        }

        String r = cl.getName();
        do {
            r += "[";
            Field[] fields = cl.getDeclaredFields();
            AccessibleObject.setAccessible(fields, true);
            for (Field f : fields) {
                if (!Modifier.isStatic(f.getModifiers())) {
                    if (!r.endsWith("[")) r += ",";
                    r += f.getName() + "=";
                    try {
                        Class t = f.getType();
                        Object val = f.get(object);
                        if (t.isPrimitive()) r += val;
                        else r += toString(val);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            r += "]";
            cl = cl.getSuperclass();
        } while (cl != null);
        return r;
    }
}
