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

    public static void test1(){
        Thread thread = new Thread(() -> {
            i--;
            try {
                //do sth
                TimeUnit.SECONDS.sleep(10L);
            } catch (InterruptedException e) {
                //ignored
            }
            j++;
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
