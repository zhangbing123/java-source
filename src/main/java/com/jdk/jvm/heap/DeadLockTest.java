package com.jdk.jvm.heap;

/**

* @Description:    死锁测试

* @Author:         zhangbing

* @CreateDate:     2020/7/5 1:16 PM

*/
public class DeadLockTest {

    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public static void main(String[] args) {
        new Thread(()->{
            synchronized (lock1){
                System.out.println("the thread1 acquire lock1");
                try {
                    Thread.currentThread().sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2){
                    System.out.println("the thread1 acquire lock2");
                }
            }
        },"thread1").start();

        new Thread(() -> {
            synchronized (lock2){
                System.out.println("the thread1 acquire lock2");
                try {
                    Thread.currentThread().sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1){
                    System.out.println("the thread1 acquire lock1");
                }
            }
        },"thread2").start();

        System.out.println("the main thread end");
    }
}
