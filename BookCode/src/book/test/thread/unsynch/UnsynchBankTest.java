package book.test.thread.unsynch;

import com.sun.org.apache.xpath.internal.functions.FuncBoolean;

import java.awt.image.BandCombineOp;
import java.util.Map;

/**
 * @PROJECT_NAME: DataStructures
 * @ClassName UnsynchBankTest
 * @Author YHL
 * @Email 1359541394@qq.com
 * @Date 2020/12/5 14:30
 * @Version 1.0
 * @Description TODO
 */
public class UnsynchBankTest {
    public static final int NACCOUNT = 100;
    public static final double INITAL_BALANCE = 1000;
    public static final double MAX_AMOUNT = 1000;
    public static final int DELAY = 10;

    public static void main(String[] args) {
        Bank bank = new Bank(NACCOUNT, INITAL_BALANCE);
        for (int i = 0; i < NACCOUNT; i++) {
            int fromAccount = i;
            Runnable r = () -> {
                try {
                    while (true) {
                        int toAccount = (int) (bank.size() * Math.random());
                        double amount = MAX_AMOUNT * Math.random();
                        bank.transfer(fromAccount, toAccount, amount);
                        Thread.sleep((int) (DELAY * Math.random()));
                    }
                } catch (InterruptedException e) {

                }
            };
            Thread t = new Thread(r);
            t.start();
        }
    }
}
