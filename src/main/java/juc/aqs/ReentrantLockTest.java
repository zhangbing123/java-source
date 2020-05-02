package main.java.juc.aqs;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: ReentrantLocks锁
 * @author: zhangbing
 * @create: 2020-03-26 16:21
 **/
public class ReentrantLockTest {

    private static int count;

    public static void main(String[] args) {
        //创建公平锁
        ReentrantLock reentrantLock = new ReentrantLock(true);
        //创建非公平锁
//        ReentrantLock reentrantLock = new ReentrantLock();

        Thread thread1 = new Thread(() -> run(reentrantLock));
        thread1.setName("张兵测试线程1");
        thread1.start();


        Thread thread2 = new Thread(() -> run(reentrantLock));
        thread2.setName("张兵测试线程2");
        thread2.start();

        Thread thread3 = new Thread(() -> run(reentrantLock));
        thread3.setName("张兵测试线程3");
        thread3.start();


    }

    private static void run(ReentrantLock reentrantLock) {
        reentrantLock.lock();
        System.out.println(Thread.currentThread().getName() + "获取到锁");
        while (count < 5) {
            count++;
            System.out.println(Thread.currentThread() + ":" + count);
        }
        reentrantLock.unlock();
        count = 0;
    }
}
