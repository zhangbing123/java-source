package com.jdk.juc.aqs.queue;

import com.jdk.utils.DateUtil;

import java.util.Date;

/**
 * @description:
 * @author: zhangbing
 * @create: 2020-08-26 10:21
 **/
public class DelayedQueneTest {

    public static void main(String[] args) {
        TaskSchedule taskSchedule = new TaskSchedule();
        new Thread(() -> {
            String time1 = "2020-08-26 11:48:30";
            Date date1 = DateUtil.parseDate(time1);
            System.out.println("触发事件时间：" + time1);
            taskSchedule.registerAndRun(date1.getTime(), new RunTask("线程1"));
        }).start();

        try {
            Thread.currentThread().sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String time1 = "2020-08-26 11:51:10";
        Date date1 = DateUtil.parseDate(time1);
        System.out.println("触发事件时间：" + time1);
        taskSchedule.registerAndRun(date1.getTime(), new RunTask("线程1"));
    }


    static class RunTask implements Runnable {

        private String threadName;

        public RunTask(String threadName) {
            this.threadName = threadName;
        }

        @Override
        public void run() {
            System.out.println(threadName + "到时间了，触发事件...");
        }
    }
}
