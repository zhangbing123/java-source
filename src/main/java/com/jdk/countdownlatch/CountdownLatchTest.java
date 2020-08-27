package com.jdk.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: zhangbing
 * @create: 2020-05-11 15:46
 **/
public class CountdownLatchTest {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(3);//set state的值为3

        countDownLatch.countDown();//每个线程调用此方法  state-1 知道state被减为0  唤醒阻塞队列中的线程
        countDownLatch.await();//state>0 调用此方法的线程阻塞，直到被调用countDown方法的线程唤醒
        countDownLatch.await(1, TimeUnit.SECONDS);

        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

        Thread thread1 = new Thread(new MyTask("线程1",null), "线程1");

        Thread thread2 = new Thread(new MyTask("线程2",thread1), "线程2");

        Thread thread3 = new Thread(new MyTask("线程3",thread2), "线程3");

        thread1.start();
        thread2.start();
        thread3.start();

        thread3.join();
        System.out.println("all threads is finished");

    }

    static class MyTask implements Runnable{

        String threadName = "";
        Thread thread;

        public MyTask(String threadName,Thread thread) {
            this.threadName = threadName;
            this.thread = thread;
        }

        @Override
        public void run() {

            if (thread!=null){
                try {
                    thread.join();
                } catch (InterruptedException e) {

                }
            }
            System.out.println(threadName+":is running...");
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
