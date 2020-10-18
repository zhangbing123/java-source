package com.jdk.executor;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: zhangbing
 * @create: 2020-08-31 16:08
 **/
public class ScheduledThreadPoolExecutorTest {

    public static void main(String[] args) {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        scheduledThreadPoolExecutor.scheduleWithFixedDelay(new RunTask("线程2"),1,2, TimeUnit.SECONDS);
        scheduledThreadPoolExecutor.scheduleAtFixedRate(new RunTask("线程2"),1,2, TimeUnit.SECONDS);
    }

    static class RunTask implements Runnable {

        private String threadName;

        public RunTask(String threadName) {
            this.threadName = threadName;
        }

        @Override
        public void run() {
            System.out.println("开始执行");
            try {
                Thread.currentThread().sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName + "到时间了，触发事件...");
        }
    }
}
