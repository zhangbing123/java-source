package com.jdk.juc.aqs.reentrantlock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @description:
 * @author: zhangbing
 * @create: 2020-08-17 11:18
 **/
public class ReentrantReadWriteLockTest {

    private static int i = 0;
    private static ZBReentrantReadWriteLock reentrantReadWriteLock = new ZBReentrantReadWriteLock(true);

    public static void main(String[] args) {

        ZBReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        ZBReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

        new Thread(() -> {
            readLock.lock();
            System.out.println("子线程获取到锁");
            try {
                Thread.currentThread().sleep(2000);
            } catch (InterruptedException e) {


            }
        }).start();
        readLock.lock();
        i++;
        System.out.println(i);
        /**
         * 不能在获取到读锁后  在加写锁  必须释放读锁后  才能加上写锁    但是可以在加上写锁后  再次加上一次读锁
         * 换句话说
         * 锁可以降级 但不能升级  写锁可以降级为读锁  但不能读锁升级为写锁
         */
//        writeLock.lock();
        readLock.unlock();

    }
}
