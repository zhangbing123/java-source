package main.java.juc.aqs;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: ReentrantLocks锁
 * @author: zhangbing
 * @create: 2020-03-26 16:21
 **/
public class ReentrantLockTest {

    private static int count;

    public static void main(String[] args) throws InterruptedException {
        //创建公平锁
        ReentrantLock reentrantLock = new ReentrantLock(true);
        //创建非公平锁
//        ReentrantLock reentrantLock = new ReentrantLock();

        Thread thread1 = new Thread(() -> {
            reentrantLock.lock();
            while (count < 50) {
                count++;
                System.out.println("线程1" + reentrantLock.getQueueLength());
            }
            reentrantLock.unlock();
        });
        thread1.setName("张兵测试线程1");
        thread1.start();
//
//        Thread.sleep(3000);
//
//        Thread thread2 = new Thread(() -> {
//            reentrantLock.lock();
//            System.out.println(reentrantLock.getQueueLength());
//            count++;
//            reentrantLock.unlock();
//        });
//        thread2.setName("张兵测试线程2");
//        thread2.start();


    }
}
