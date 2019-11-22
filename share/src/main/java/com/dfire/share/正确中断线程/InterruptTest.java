package com.dfire.share.正确中断线程;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InterruptTest {

    private static AtomicInteger ac = new AtomicInteger(1);

    private static int i = 10000;

    private static int j = 10000;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            //需求，循环,每轮 操作10次,可以中断操作,在发出中断操作后操作完当前的10次之后中断任务(sleep被中断的操作不算一次)
            while (!Thread.interrupted()) {
                System.out.println("\n新的一轮开始");
//                wrong();
                right();
                ac.set(1);
            }
            log.info("\n我被中断了, i = {}, j = {}", i, j);
        });
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        t1.interrupt();
    }

    //虚假的处理方式,无法中断,甚至造成程序BUG疯狂打印
    public static void wrong() {
        while (true) {
            try {
                if (!sleep()) {
                    //操作10次，跳出循环
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
                        //操作10次，跳出循环
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
        TimeUnit.MILLISECONDS.sleep(20L);
        i--;
        if (ac.getAndIncrement() == 10) {
            j += 10;
            return false;
        } else {
            return true;
        }
    }

}
