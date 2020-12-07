package book.test.thread.unsynch;

import java.util.Arrays;

/**
 * @PROJECT_NAME: DataStructures
 * @ClassName Bank
 * @Author YHL
 * @Email 1359541394@qq.com
 * @Date 2020/12/5 14:44
 * @Version 1.0
 * @Description TODO
 */
public class Bank {
    private final double[] accounts;

    public Bank(int n, double initalBalance) {
        accounts = new double[n];
        Arrays.fill(accounts, initalBalance);
    }

    public void transfer(int from, int to, double amount) {
        if (accounts[from] < amount) return;
        System.out.print(Thread.currentThread());
        accounts[from] -= amount;
        System.out.printf(" %10.2f from %d to %d", amount, from, to);
        accounts[to] += amount;
        System.out.printf("Total Balance: %10.2f%n", getTotalBalance());
    }

    public double getTotalBalance() {
        double sum = 0;
        for (double a : accounts) {
            sum += a;
        }
        return sum;
    }

    public int size() {
        return accounts.length;
    }

}
