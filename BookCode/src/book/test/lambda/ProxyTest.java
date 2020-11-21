package book.test.lambda;

import com.sun.deploy.trace.Trace;

import javax.swing.*;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @PROJECT_NAME: DataStructures
 * @ClassName ProxyTest
 * @Author YHL
 * @Email 1359541394@qq.com
 * @Date 2020/11/1 20:42
 * @Version 1.0
 * @Description TODO
 */
public class ProxyTest {
    public static void main(String[] args) {
        Object[] elements = new Object[1000];

        for (int i = 0; i < elements.length; i++) {
            Integer value = i + 1;
            InvocationHandler handler = new TraceHandler(value);
            Object proxy = Proxy.newProxyInstance(null, new Class[]{Comparable.class}, handler);
            elements[i] = proxy;
        }


    }
}

class TraceHandler implements InvocationHandler {
    private Object target;

    public TraceHandler(Object t) {
        target = t;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.print(target);
        System.out.print("." + method.getName() + "(");
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.print(args[i]);
                if (i < args.length - 1) System.out.print(",");
            }
        }
        System.out.println(")");
        return method.invoke(target, args);
    }
}

