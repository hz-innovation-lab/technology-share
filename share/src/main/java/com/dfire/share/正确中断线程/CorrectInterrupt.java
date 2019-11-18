package com.dfire.share.正确中断线程;

import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

/**
 * @author baiyundou
 * @date 19:26 2019/11/18
 * @description
 */
@Slf4j
public class CorrectInterrupt {

    private static volatile int i = 100;

    private static volatile int j = 100;

    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        Thread thread = new Thread(() -> {
            while (!Thread.interrupted()) {
                i--;
                try {
                    //do sth
                    TimeUnit.SECONDS.sleep(2L);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                j++;
            }
        });
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
        try {
            //等待线程执行完毕
            thread.join();
        } catch (InterruptedException e) {
            //ignore
        }
        log.info("i = {}, j = {}", i, j);
    }

}
