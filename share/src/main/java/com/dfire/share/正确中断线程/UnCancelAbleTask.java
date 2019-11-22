package com.dfire.share.正确中断线程;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author baiyundou
 * @date 11:09 2019/11/22
 * @description
 */
public class UnCancelAbleTask {


    public static void main(String[] args) {
        final BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
        Thread producer = new Thread(() -> {
            int i = 0;
            while (true) {
                queue.add(i++);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    //ignored
                }
            }
        });
        producer.start();
        Thread consumer = new Thread(() -> {
            while (!Thread.interrupted()) {
                Integer current = getNextTask(queue);
                System.out.println(current);
            }
            System.out.println("线程中断");
        });
        consumer.start();
        try {
            TimeUnit.SECONDS.sleep(10L);
        } catch (InterruptedException e) {
            //ignored
        }
        consumer.interrupt();
    }


    public static Integer getNextTask(BlockingQueue<Integer> queue) {
        boolean interrupted = false;
        try {
            while (true) {
                try {
                    return queue.take();
                } catch (InterruptedException e) {
                    interrupted = true;
                    System.out.println("捕获异常，但是不能取消,否则返回空指针");
                }
            }
        } finally {
            if (interrupted)
                Thread.currentThread().interrupt();
        }
    }

}
