package com.jdk.volatiletest;


import java.util.concurrent.CountDownLatch;

/**
 * @description:
 * @author: zhangbing
 * @create: 2020-08-07 16:42
 **/
public class VolatileTest {

    private volatile static int count = 0;

    private static Object lock = new Object();

    private volatile static boolean isflag = true;

    public static void main(String[] args) {

        //测试原子性
//        testAtomicity();

        //测试可见性
//        testVisibility();

        //测试有序性
        testOrderliness();

    }

    private static int x = 0, y = 0;
    private volatile static int a = 0, b = 0;//通过volatile关键字插入内存屏障  实现禁止重排序

    private static void testOrderliness() {
        int i = 0;
        for (; ; ) {
            i++;
            x = 0;
            y = 0;
            a = 0;
            b = 0;
            Thread t1 = new Thread(() -> {
                shortWait(10000);
                a = 1;
                x = b;
//                    UnsafeInstance.reflectGetUnsafe().fullFence();  //加内存屏障
            });

            Thread t2 = new Thread(() -> {
                b = 1;
//                    UnsafeInstance.reflectGetUnsafe().fullFence(); ////加内存屏障
                y = a;
            });

            t1.start();
            t2.start();
            try {
                t1.join();
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String result = "第" + i + "次 (" + x + "," + y + "）";
            if (x == 0 && y == 0) {
                System.out.println(result);
                break;
            } else {
                System.out.println(result);
            }
        }

    }

    /**
     * 等待一段时间，时间单位纳秒
     *
     * @param interval
     */
    public static void shortWait(long interval) {
        long start = System.nanoTime();
        long end;
        do {
            end = System.nanoTime();
        } while (start + interval >= end);
    }

    private static void testVisibility() {

        new Thread(() -> {
            while (isflag) {
                System.out.println(count++ + "次循环");
            }
            System.out.println("循环结束....");
        }).start();


        new Thread(() -> {
            int i = 0;
            while (i <= 10) {
                i++;
            }

            isflag = false;
        }).start();

    }

    private static void testAtomicity() {
        CountDownLatch countDownLatch = new CountDownLatch(10);


        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int k = 0; k < 1000; k++) {

                    count++;//非原子操作  所以最终结果一定是<=10000的

                    /**
                     * synchronized (lock){ count++; }
                     * 使用加锁方式，解决上面的问题
                     */
                }
                countDownLatch.countDown();
            }).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(count);
    }
}
