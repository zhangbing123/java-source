package com.jdk.executor;

/**
 * @description: 启动3个线程打印递增的数字, 线程1先打印1,2,3,4,5,
 * 然后是线程2打印6,7,8,9,10, 然后是线程3打印11,12,13,14,15. 接着再由线程1打印16,17,18,19,20....以此类推, 直到打印到75.
 * 程序的输出结果应该为:
 * 线程1: 1
 * 线程1: 2
 * 线程1: 3
 * 线程1: 4
 * 线程1: 5
 * 线程2: 6
 * 线程2: 7
 * 线程2: 8
 * 线程2: 9
 * 线程2: 10
 * ...
 * 线程3: 71
 * 线程3: 72
 * 线程3: 73
 * 线程3: 74
 * 线程3: 75
 * @author: zhangbing
 * @create: 2020-08-06 16:05
 **/
public class PrintNumMain {

    private static volatile int count = 0;

    public static void main(String[] args) {

        PrintNum printNum = new PrintNum();

        Thread thread1 = new Thread(() -> {
            while (count < 75) {
                printNum.print(0);
            }

        }, "线程1");

        Thread thread2 = new Thread(() -> {

            while (count < 75) {
                printNum.print(1);
            }
        }, "线程2");

        Thread thread3 = new Thread(() -> {
            while (count < 75) {
                printNum.print(2);
            }

        }, "线程3");

        thread1.start();
        thread2.start();
        thread3.start();
    }

    static class PrintNum {

        int i = 0;

        private synchronized void print(int order) {
            while (i != order) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (int k = 0; k < 5; k++) {
                if (count < 75) {
                    count++;
                    System.out.println(Thread.currentThread().getName() + ":" + count);
                }
            }
            i++;
            if (i == 3) {
                i = 0;//一轮结束 置为初始值
            }
            this.notifyAll();

        }
    }
}
