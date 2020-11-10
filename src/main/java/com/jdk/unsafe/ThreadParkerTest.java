package com.jdk.unsafe;

import sun.misc.Unsafe;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @description: 线程调度测试
 * @author: zhangbing
 * @create: 2020-04-07 16:25
 **/
public class ThreadParkerTest {

    private static Unsafe unsafe;

    static {
        //通过反射获取Unsafe实例
        unsafe = UnsafeInstance.reflectGetUnsafe();
    }

    /**
     * //取消阻塞线程
     * public native void unpark(Object thread);
     * <p>
     * //阻塞线程
     * public native void park(boolean isAbsolute, long time);
     */
    public static void main(String[] args) throws InterruptedException {


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("线程循环第" + i + "遍");
                    if (i == 5) {
                        System.out.println("线程中断。。。");
                        LockSupport.park(this);
                        System.out.println(Thread.interrupted());
                        System.out.println("线程被唤醒，重新执行。。。");
                    }
                }
            }
        });
        thread.start();

//        thread.interrupt();
        Thread.currentThread().sleep(2000);

//        System.out.println(thread.interrupt());
        LockSupport.unpark(thread);


//        //等待线程
//        TestThread testThread = new TestThread(unsafe);
//        testThread.start();
//
//        //工作线程
//        WorkThread workThread = new WorkThread(unsafe, testThread);
//        workThread.start();
//
//        try {
//            //等待工作线程执行完毕
//            workThread.join();
//        } catch (InterruptedException e) {
//
//        }
//
//        System.out.println("the end.");

    }


    /**
     * 工作线程
     */
    static class WorkThread extends Thread {
        private Thread testThread;
        private Unsafe unsafe;

        public WorkThread(Unsafe unsafe, Thread testThread) {
            this.testThread = testThread;
            this.unsafe = unsafe;
        }

        public void run() {
            int i = 0;
            while (true) {
                if (i == 5) {
                    System.out.println("WorkThread 现在开始唤醒：TestThread");
                    unsafe.unpark(testThread);

                    break;
                }

                System.out.println("WorkThread is now working for " + (++i) + " s");

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class TestThread extends Thread {
        private Unsafe unsafe;

        public TestThread(Unsafe unsafe) {
            this.unsafe = unsafe;
        }

        public void run() {
            System.out.println("TestThread 现在开始阻塞!");
            unsafe.park(false, 0);
            System.out.println("TestThread 已被唤醒");
        }
    }
}
