package com.jdk.timer;

import com.jdk.utils.DateUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @description:
 * @author: zhangbing
 * @create: 2020-08-25 14:15
 **/
public class TimerTest {
    public static void main(String[] args) {



        new Thread(() -> {
            Timer timer = new Timer("定时线程",false);
            String time1 = "2020-08-26 10:16:15";
            Date date1 = DateUtil.parseDate(time1);
            System.out.println("触发事件为：" +time1);
            timer.schedule(new RemindTask(timer), date1);
        }).start();

        new Thread(() -> {
            Timer timer = new Timer("定时线程",false);
            String time2 = "2020-08-26 10:16:14";
            Date date2 = DateUtil.parseDate(time2);
            System.out.println("触发事件为：" + time2);
            timer.schedule(new RemindTask(timer), date2);
        }).start();

        new Thread(() -> {
            Timer timer = new Timer("定时线程",false);
            String time3 = "2020-08-26 10:16:13";
            Date date3 = DateUtil.parseDate(time3);
            System.out.println("触发事件为：" + time3);
            timer.schedule(new RemindTask(timer), date3);
        }).start();

        new Thread(() -> {
            Timer timer = new Timer("定时线程",false);
            String time4 = "2020-12-16 13:39:12";
            Date date4 = DateUtil.parseDate(time4);
            System.out.println("触发事件为：" + time4);
            timer.schedule(new RemindTask(timer), date4);
        }).start();
    }

    static class RemindTask extends TimerTask {

        private Timer timer;

        public RemindTask(Timer timer) {
            this.timer = timer;
        }

        @Override
        public void run() {
            System.out.println("你指定2013-11-27号15:34:01分执行已经触发！");
            timer.cancel();

        }
    }
}
