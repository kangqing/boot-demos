package com.yunqing.demoatest.multithread.cas;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author kangqing
 * @since 2023/7/20 10:09
 */
public class CASABAVersion {
    public static void main(String[] args) {
        final AtomicStampedReference<String> reference = new AtomicStampedReference<>("AAA", 1);
        String oldValue = reference.getReference();
        int oldVersion = reference.getStamp();

        final boolean b = reference.compareAndSet(oldValue, "B", oldVersion, oldVersion + 1);
        System.out.println("第一次修改结果 = " + b);

        final boolean c = reference.compareAndSet("B", "ABA", 1, 1 + 1);
        System.out.println("第二次修改结果 = " + c);
    }
}
