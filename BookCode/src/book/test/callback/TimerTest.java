package book.test.callback;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.*;

/**
 * @PROJECT_NAME: DataStructures
 * @ClassName TimerTest
 * @Author YHL
 * @Email 1359541394@qq.com
 * @Date 2020/10/26 21:26
 * @Version 1.0
 * @Description TODO
 */
public class TimerTest {
    public static void main(String[] args) {
        ActionListener listener = new TimePrinter();

        Timer t = new Timer(1000, listener);
        t.start();
        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }
}

class TimePrinter implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("At the tone, the time is " + new Date());
        Toolkit.getDefaultToolkit().beep();
    }
}