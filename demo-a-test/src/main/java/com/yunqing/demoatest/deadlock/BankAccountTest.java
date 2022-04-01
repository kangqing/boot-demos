package com.yunqing.demoatest.deadlock;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * 银行转账死锁案例
 *
 * @author kangqing
 * @since 2021/9/22 20:06
 */
public class BankAccountTest {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Account a1 = new Account();
        a1.setAccountId("1");
        a1.setMoney(new BigDecimal("1000"));

        Account a2 = new Account();
        a2.setAccountId("2");
        a2.setMoney(new BigDecimal("1000"));

        new Thread(() -> {
            bank.transferMoneyRight(a1, a2, BigDecimal.valueOf(50));
        }).start();

        new Thread(() -> {
            bank.transferMoneyRight(a2, a1, BigDecimal.valueOf(100));
        }).start();
    }
}

/**
 * 新建银行账户类
 */
@Getter
@Setter
class Account {

    private String accountId;

    private BigDecimal money;

    public void addMoney(BigDecimal money) {
        this.money = this.money.add(money);
    }

    public void subMoney(BigDecimal money) {
        this.money = this.money.subtract(money);
    }
}

/**
 * 新建银行类，包括一个转账方法
 */
@Slf4j
class Bank {
    // 错误，没有确定锁的顺序，造成死锁
    public void transferMoney(Account from, Account to, BigDecimal money) {
        try {
            // 锁定转出账户
            synchronized (from) {
                TimeUnit.SECONDS.sleep(1);
                if (from.getMoney().subtract(money).compareTo(BigDecimal.ZERO) < 0) {
                    System.out.println("余额不足，转账失败");
                }
                from.subMoney(money);
                // 锁定转入账户
                synchronized (to) {
                    to.addMoney(money);
                }
                System.out.println("转账成功， 账户" + from.getAccountId() + "转出" + money + "元到账户" + to.getAccountId());
            }
        } catch (Exception e) {
            //e.printStackTrace();
            log.error("转账出错，捕获异常 {[]}", e);
        }
    }

    // 正确，确定锁的顺序
    public void transferMoneyRight(Account from, Account to, BigDecimal money) {
        try {
            // 确定锁的执行顺序
            int result = from.getAccountId().compareTo(to.getAccountId());
            Account left = from;// id小的先加锁
            Account right = to;// id大的后加锁
            if (result > 0) {
                left = to;
                right = from;
            }
            // 锁定转出账户
            synchronized (left) {
                synchronized (right) {
                    TimeUnit.SECONDS.sleep(1);
                    if (from.getMoney().subtract(money).compareTo(BigDecimal.ZERO) < 0) {
                        System.out.println("余额不足，转账失败");
                    }
                    from.subMoney(money);
                    // 转入账户
                    to.addMoney(money);
                    System.out.println("转账成功， 账户" + from.getAccountId() + "转出" + money + "元到账户" + to.getAccountId());

                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
            log.error("转账出错，捕获异常 {[]}", e);
        }
    }

    /**
     * 等待、通知机制
     */
    public void transferNotifyAll(Account from, Account to, BigDecimal money) throws InterruptedException {
        // 获取需要的锁
        Allocator.getInstance().apply(from, to);
        TimeUnit.SECONDS.sleep(1);
        if (from.getMoney().subtract(money).compareTo(BigDecimal.ZERO) < 0) {
            System.out.println("余额不足，转账失败");
        }
        from.subMoney(money);
        // 转入账户
        to.addMoney(money);
        System.out.println("转账成功， 账户" + from.getAccountId() + "转出" + money + "元到账户" + to.getAccountId());
        // 释放锁并通知其他线程抢锁
        Allocator.getInstance().release(from, to);
    }
}
