package com.jdk.unsafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

/**
 * @description:
 * @author: zhangbing
 * @create: 2020-12-16 14:21
 **/
public class ParkTest {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(1);

        Thread thread = new Thread(() -> {
            System.out.println("子线程开始阻塞，等在主线程逻辑执行完...");
            LockSupport.park();

            System.out.println("子线程开始执行....");

            for (int i = 0; i < 10; i++) {
                try {
                    Thread.currentThread().sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程执行:" + i);
            }


            System.out.println("子线程执行完毕....");

            countDownLatch.countDown();
        });

        thread.start();

        Thread.sleep(100);

        for (int i = 0; i < 10; i++) {
            Thread.sleep(500);
            System.out.println("主线程执行：" + i);
        }

        System.out.println("唤醒子线程....");
        //唤醒子线程
        LockSupport.unpark(thread);
        countDownLatch.await();

        System.out.println("子线程和主线程全部执行完毕");


    }
}
