package com.dfire.share.正确中断线程;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class InterruptTest {

    private static AtomicInteger ac = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            //需求，无限睡眠,每轮 睡眠10次,可以中断睡眠,在发出中断操作后睡满当前的10次之后中断任务(被中断的睡眠不算一次)
            while (!Thread.interrupted()) {
                System.out.println("\n新的一轮睡眠开始");
//                wrong();
                right();
                ac.set(0);
            }
            System.out.println("\n我被中断了");
        });
        t1.start();
        Thread t2 = new Thread(() -> {
            t1.interrupt();
        });
        t2.start();
    }

    //虚假的处理方式,无法中断,甚至造成程序BUG疯狂打印
    public static void wrong() {
        while (true) {
            try {
                if (!sleep()) {
                    //睡够一千次，跳出循环
                    break;
                }
            } catch (InterruptedException e) {
                System.out.println("\n catch InterruptedException");
                Thread.currentThread().interrupt();
            }
        }
    }

    //真正的处理方式,正确中断，只打印一次
    public static void right() {
        boolean interrupted = false;
        try {
            while (true) {
                try {
                    if (!sleep()) {
                        //睡够10次，跳出循环
                        break;
                    }
                } catch (InterruptedException e) {
                    System.out.println("\ncatch InterruptedException");
                    interrupted = true;
                }
            }
        } finally {
            if (interrupted)
                Thread.currentThread().interrupt();
        }
    }

    public static boolean sleep() throws InterruptedException {
        System.out.print(" " + ac.get());
        TimeUnit.MILLISECONDS.sleep(1L);
        if (ac.getAndIncrement() == 10) {
            return false;
        } else {
            return true;
        }
    }

}
