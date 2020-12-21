package com.jdk.juc.aqs.reentrantlock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @description:
 * @author: zhangbing
 * @create: 2020-12-15 18:03
 **/
public class ReentrantReadWritLockTest {

    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public static void main(String[] args) {

        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

        new Thread(() -> {

            readLock.lock();

            System.out.println(Thread.currentThread().getName() + "添加读锁成功");

            try {
                Thread.currentThread().sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            readLock.unlock();

        }, "线程1").start();

        new Thread(() -> {

            readLock.lock();

            System.out.println(Thread.currentThread().getName() + "添加读锁成功");

            try {
                Thread.currentThread().sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            readLock.unlock();

        }, "线程2").start();

        new Thread(() -> {

            writeLock.lock();

            System.out.println(Thread.currentThread().getName() + "添加写锁成功");

            try {
                Thread.currentThread().sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            writeLock.unlock();

        }, "线程3").start();


    }
}
