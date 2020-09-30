package com.jdk.timer;

import com.jdk.unsafe.UnsafeInstance;
import com.jdk.utils.DateUtil;
import sun.misc.Unsafe;

import java.util.Date;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: zhangbing
 * @create: 2020-08-25 14:46
 **/
public class ZBTimerTest {

    private static ZBTimer zbTimer = new ZBTimer();

    public static void main(String[] args) throws InterruptedException {

        String time = "2020-08-31 11:47:10";
        Date date = DateUtil.parseDate(time);
        zbTimer.start();
//        zbTimer.registerAtTime(date.getTime(), new RunTask("线程0"));
        zbTimer.registerAtFixedRate(1000,2000, new RunTask("线程1"),null);
//        zbTimer.registerAtFixedRate(1000,2000, new RunTask("线程2"));


//        Thread.currentThread().sleep(1000);
//
//        new Thread(() -> {
//            String time1 = "2020-08-31 11:03:04";
//            Date date1 = DateUtil.parseDate(time1);
////            zbTimer.registerAtTime(date1.getTime(), new RunTask("线程1"));
//            zbTimer.registerAtFixedRate(1000,1000, new RunTask("线程1"));
//        }).start();
//
//        new Thread(() -> {
//            String time2 = "2020-08-31 10:55:04";
//            Date date2 = DateUtil.parseDate(time2);
//            zbTimer.register(date2.getTime(), new RunTask("线程2"));
//        }).start();
//
//        new Thread(() -> {
//            String time3 = "2020-08-31 10:55:06";
//            Date date3 = DateUtil.parseDate(time3);
//            zbTimer.register(date3.getTime(), new RunTask("线程3"));
//        }).start();
//
//        new Thread(() -> {
//            String time4 = "2020-08-31 10:55:08";
//            Date date4 = DateUtil.parseDate(time4);
//            zbTimer.register(date4.getTime(), new RunTask("线程4"));
//        }).start();
//
//        String timeMain = "2020-08-27 20:40:16";
//        Date dateMain = DateUtil.parseDate(timeMain);
//        zbTimer.register(dateMain.getTime(), new RunTask("主线程"));
//        zbTimer.start();


    }


    static class RunTask implements Runnable {

        private String threadName;

        public RunTask(String threadName) {
            this.threadName = threadName;
        }

        @Override
        public void run() {
            System.out.println(threadName + "任务开始执行...");
            try {
                Thread.currentThread().sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(1 / 0);
            System.out.println(threadName + "任务执行结束....");
//            System.out.println(threadName + "到时间了，触发事件...");
        }
    }
}
