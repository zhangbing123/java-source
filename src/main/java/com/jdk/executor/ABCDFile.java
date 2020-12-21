package com.jdk.executor;

/**
 * @description:有四个线程1、2、3、4。线程1的功能就是输出1，线程2的功能就是输出2，
 * 以此类推.........现在有四个文件ABCD，初始都为空。现要让四个文件呈如下格式：
 * A：1 2 3 4 1 2....
 * B：2 3 4 1 2 3....
 * C：3 4 1 2 3 4....
 * D：4 1 2 3 4 1....
 * @author: zhangbing
 * @create: 2020-08-06 15:56
 **/
public class ABCDFile {

    public static void main(String[] args) {
        AFile aFile = new AFile();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10;i++){

                    aFile.test(1);
                }
            }
        }, "1");
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10;i++){

                    aFile.test(2);
                }
            }
        }, "2");
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10;i++){

                    aFile.test(3);
                }
            }
        }, "3");
        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10;i++){

                    aFile.test(0);
                }
            }
        }, "4");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

    }

    static class AFile {

        int i = 0;

        private synchronized void test(int order) {
            while (i != order) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName());
            i++;
            this.notifyAll();
            if (i == 4) {
                i = 0;//初始化为0  从头开始
                System.out.println("===================================");
            }
        }

    }
}
