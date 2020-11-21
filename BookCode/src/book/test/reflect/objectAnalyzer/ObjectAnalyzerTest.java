package book.test.reflect.objectAnalyzer;

import java.util.ArrayList;

/**
 * @PROJECT_NAME: DataStructures
 * @ClassName ObjectAnalyzerTest
 * @Author YHL
 * @Email 1359541394@qq.com
 * @Date 2020/10/16 21:55
 * @Version 1.0
 * @Description TODO
 */
public class ObjectAnalyzerTest {
    public static void main(String[] args) {
        ArrayList<Integer> sequares = new ArrayList<>();
        for (int i = 1; i <= 5; i++) sequares.add(i * i);
        System.out.print(new ObjectAnalyzer().toString(sequares));
    }
}
