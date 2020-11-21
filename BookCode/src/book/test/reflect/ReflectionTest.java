package book.test.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

/**
 * @PROJECT_NAME: DataStructures
 * @ClassName ReflectionTest
 * @Author YHL
 * @Email 1359541394@qq.com
 * @Date 2020/10/15 21:35
 * @Version 1.0
 * @Description TODO
 */
public class ReflectionTest {
    public static void main(String[] args) {
        // 读取命令参数args 或者用户输入的类名
        String name;
        if (args.length > 0) name = args[0];
        else {
            Scanner scan = new Scanner(System.in);
            System.out.println("请输入类名(eg : java.util.Date) : ");
            name = scan.next();
        }
        System.out.println(name + "类的所有信息如下 : ");

        try {
            //打印类名和超类 (if != Object )
            Class cl = Class.forName(name);
            Class supercl = cl.getSuperclass();
            String modifiers = Modifier.toString(cl.getModifiers());
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }
            System.out.print("class " + name);
            if (supercl != null && supercl != Object.class) {
                System.out.print(" extend " + supercl.getName());
            }
            System.out.print("\n{\n");
            printConstructors(cl);
            System.out.println();
            printMethods(cl);
            System.out.println();
            printFields(cl);
            System.out.print("}");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    //打印该类所有的构造器
    public static void printConstructors(Class cl) {
        Constructor[] constructors = cl.getConstructors();
        for (Constructor c : constructors) {
            String name = c.getName();
            // 打印修饰符和构造器名
            System.out.print("  ");
            String modifiers = Modifier.toString(c.getModifiers());
            if (modifiers.length() > 0) System.out.print(modifiers + " ");
            System.out.print(name + "(");
            // 打印参数类型
            Class[] paramTypes = c.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                if (i > 0) System.out.print(", ");
                System.out.print(paramTypes[i].getName());
            }
            System.out.println(");");
        }
    }

    //打印该类的所有方法
    public static void printMethods(Class cl) {
        Method[] methods = cl.getMethods();
        for (Method m : methods) {
            Class retType = m.getReturnType();
            String name = m.getName();
            // 打印修饰符，返回值类型和方法名
            System.out.print("  ");
            String modifiers = Modifier.toString(m.getModifiers());
            if (modifiers.length() > 0) System.out.print(modifiers + " ");
            System.out.print(retType.getName() + " " + name + "(");
            // 打印参数类型
            Class[] paramTypes = m.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                if (i > 0) System.out.print(", ");
                System.out.print(paramTypes[i].getName());
            }
            System.out.println(")");
        }
    }

    // 打印域
    public static void printFields(Class cl) {
        Field[] fields = cl.getFields();
        for (Field f : fields) {
            Class type = f.getType();
            String name = f.getName();
            System.out.print("  ");
            String modifiers = Modifier.toString(f.getModifiers());
            if (modifiers.length() > 0) System.out.print(modifiers + " ");
            System.out.println(type.getName() + " " + name + ";");
        }
    }
}
