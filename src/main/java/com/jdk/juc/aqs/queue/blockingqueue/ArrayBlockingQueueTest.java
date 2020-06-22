package com.jdk.juc.aqs.queue.blockingqueue;

import java.util.Scanner;

/**
 * @description: 阻塞队列
 * @author: zhangbing
 * @create: 2020-04-08 13:53
 **/
public class ArrayBlockingQueueTest {

    public static void main(String[] args) {

        //基于数组实现的（数组不可扩容）
        ZBArrayBlockingQueue arrayBlockingQueue = new ZBArrayBlockingQueue(1, false);

        new Thread(() -> {

            Scanner sc = new Scanner(System.in);
            while (sc.hasNext()) {
                String next = sc.next();
                try {
                    arrayBlockingQueue.put(next);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (next.equals("quit")) {
                    break;
                }
            }

        }, "线程1").start();

        new Thread(() -> {
            while (true) {
                try {
                    String take = (String) arrayBlockingQueue.take();

                    System.out.println(Thread.currentThread().getName() + "获取信息:" + take);

                    if (take.equals("quit")) {
                        break;
                    }

                } catch (InterruptedException e) {

                }


            }
        }, "线程2").start();


    }
}
