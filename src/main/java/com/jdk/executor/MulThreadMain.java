package com.jdk.executor;

/**
 * @description:
 * 编写一个程序，开启3个线程，这3个线程的ID分别为A、B、C，
 * 每个线程将自己的ID在屏幕上打印10遍，要求输出结果必须按ABC的顺序显示；如：ABCABC….依次递推。
 * @author: zhangbing
 * @create: 2020-05-14 10:46
 **/
public class MulThreadMain {


    public static void main(String[] args) {
        MainTask mainTask = new MainTask();
        Thread threadC = new Thread(() -> {
            for (int k = 0; k < 10; k++) {
                mainTask.task("C", 2, 0);
            }

        }, "C");

        Thread threadB = new Thread(() -> {
            for (int k = 0; k < 10; k++) {
                mainTask.task("B", 1, 2);
            }

        }, "B");

        Thread threadA = new Thread(() -> {
            for (int k = 0; k < 10; k++) {
                mainTask.task("A", 0, 1);
            }

        }, "A");

        threadA.start();
        threadB.start();
        threadC.start();

    }

    static class MainTask {

        private int i = 0;

        public synchronized void task(String id, int k, int update) {

            while (i != k) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(id);

            i = update;

            this.notifyAll();

        }
    }
}
