package com.dfire.share.正确中断线程;

import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

/**
 * @author baiyundou
 * @date 17:27 2019/11/18
 * @description stop破坏一致性
 */
@Slf4j
public class StopTest {

    private static volatile int i = 100;

    private static volatile int j = 100;

    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        Thread thread = new Thread(() -> {
            synchronized (new Object()) {
                //内部原子性操作
                i--;
                try {
                    TimeUnit.SECONDS.sleep(10L);
                } catch (InterruptedException e) {
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
        thread.stop();
        log.info("i = {}, j = {}", i, j);
    }


}
