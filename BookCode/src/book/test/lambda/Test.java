package book.test.lambda;

import java.awt.*;
import java.util.Arrays;
import java.util.Date;
import javax.swing.*;

/**
 * @PROJECT_NAME: DataStructures
 * @ClassName Test
 * @Author YHL
 * @Email 1359541394@qq.com
 * @Date 2020/10/31 21:01
 * @Version 1.0
 * @Description TODO
 */
public class Test {
    public static void main(String[] args) {
        String[] plants = new String[] {"Mercury","Venum","Earth","Mars","Jupiter","Saturn","Uranus","Neptune"};
        System.out.println(Arrays.toString(plants));
        System.out.println("Sorted om doctopmary order:");
        Arrays.sort(plants);
        System.out.println(Arrays.toString(plants));
        System.out.println("Sorted by length:");
        Arrays.sort(plants,(String first,String second)->first.length() - second.length());
        System.out.println(Arrays.toString(plants));

        Timer t = new Timer(1000,event->
            System.out.println("The time is " + new Date()));
        t.start();
        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }
}
