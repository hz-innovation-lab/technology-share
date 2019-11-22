package com.dfire.share.正确中断线程;

import java.util.concurrent.TimeUnit;

/**
 * @author baiyundou
 * @date 14:35 2019/11/22
 * @description
 */
public class NormalInterrupt {

    private static int i = 0;

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (!Thread.interrupted()){
                task();
            }
        });
        thread.start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }

    public static void task() {
        System.out.println(i++);
    }

}
