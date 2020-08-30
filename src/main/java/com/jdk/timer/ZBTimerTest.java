package com.jdk.timer;

import com.jdk.unsafe.UnsafeInstance;
import com.jdk.utils.DateUtil;
import sun.misc.Unsafe;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: zhangbing
 * @create: 2020-08-25 14:46
 **/
public class ZBTimerTest {

    public static void main(String[] args) throws InterruptedException {
        ZBTimer zbTimer = new ZBTimer();
        String time = "2020-08-26 10:05:10";
        Date date = DateUtil.parseDate(time);
        zbTimer.register(date.getTime(), new RunTask("线程0"));

        new Thread(() -> {
            String time1 = "2020-08-27 20:21:04";
            Date date1 = DateUtil.parseDate(time1);
            zbTimer.register(date1.getTime(), new RunTask("线程1"));
        }).start();

        new Thread(() -> {
            String time2 = "2020-08-27 20:24:04";
            Date date2 = DateUtil.parseDate(time2);
            zbTimer.register(date2.getTime(), new RunTask("线程2"));
        }).start();

        new Thread(() -> {
            String time3 = "2020-08-27 20:23:30";
            Date date3 = DateUtil.parseDate(time3);
            zbTimer.register(date3.getTime(), new RunTask("线程3"));
        }).start();

        new Thread(() -> {
            String time4 = "2020-08-27 20:23:16";
            Date date4 = DateUtil.parseDate(time4);
            zbTimer.register(date4.getTime(), new RunTask("线程4"));
        }).start();

        String timeMain = "2020-08-27 20:40:16";
        Date dateMain = DateUtil.parseDate(timeMain);
        zbTimer.register(dateMain.getTime(), new RunTask("主线程"));

//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                int i = 0;
//                while (i < 100002000) {
//
//                    if (i > 10000000) {
//                        try {
//                            System.out.println("开始睡眠");
//                            Thread.currentThread().sleep(2000);
//                        } catch (InterruptedException e) {
//                            System.out.println("睡眠结束");
//                        }
//                    }
//                    i++;
//
//                }
//            }
//        });
//        thread.start();
//        System.out.println("456");
//        System.out.println("中断子线程..............");
//        thread.interrupt();
//        System.out.println("先中断线程...");


    }

    static class ZBTimer extends ReentrantLock {

        private volatile List<Long> dates = null;
        private volatile ConcurrentHashMap<Long, List<Runnable>> taskMap = new ConcurrentHashMap<>();
        private ThreadPoolExecutor executor;
        private volatile int state;
        private static Unsafe unsafe;
        private static long stateOffset;
        private static long minDateOffset;
        private Object object = new Object();
        private volatile long minDate;

        private Thread thread = new Thread(() -> doRunning());

        static {

            //通过反射获取Unsafe类 此类中的cas原子操作实现方式主要基于cmpxchg指令实现的
            unsafe = UnsafeInstance.reflectGetUnsafe();

            //获取对象属性的偏移量（在内存中的位置）
            try {
                stateOffset = unsafe.objectFieldOffset(ZBTimer.class.getDeclaredField("state"));
                minDateOffset = unsafe.objectFieldOffset(ZBTimer.class.getDeclaredField("minDate"));
            } catch (NoSuchFieldException e) {

            }
        }

        private void addQueue(Long date, Runnable runnable) {

            lock();
            try {

                List<Runnable> runnables = taskMap.get(date);
                if (runnables == null) {
                    taskMap.put(date, new ArrayList<>(Arrays.asList(runnable)));
                } else {
                    runnables.add(runnable);
                }

                if (dates == null) {
                    dates = new ArrayList<>();
                }
                dates.add(date);
                Collections.sort(dates);

                setMinDate(date);

            } finally {
                unlock();
            }
        }

        private void setMinDate(Long date) {
            if (minDate == 0) {
                minDate = date;
                return;
            }

            if (date < minDate) {
                minDate = date;
                thread.interrupt();
            }

        }

        public void register(Long date, Runnable runnable) {

            if (date == null) {
                date = System.currentTimeMillis();
            }

            if (runnable == null) {
                throw new RuntimeException("the task is null,please check ");
            }

            addQueue(date, runnable);

            executor = new ThreadPoolExecutor(5, 10, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

            if (!isRunning()) {
                start();
            }
        }

        public void start() {

            if (!startRunning(0, 1)) return;

//            if (!thread.isAlive()) {
                thread.start();
//            }
        }

        private void runTask(long time) {
            lock();
            try {
                List<Runnable> runnables = taskMap.get(time);

                System.out.println("获取到的任务："+runnables);

                if (runnables == null || runnables.isEmpty()) return;

                for (Runnable runnable : runnables) {
                    executor.execute(runnable);
                }
                runnables.removeAll(runnables);

                dates.remove(0);

                if (!isEmpty(dates)) {
                    minDate = dates.get(0);
                }

            } finally {
                System.out.println("解锁");
                unlock();
            }

        }

        private boolean startRunning(int oldState, int newState) {

            return unsafe.compareAndSwapInt(this, stateOffset, oldState, newState);
        }

        private boolean isEmpty(List<Long> dates) {
            return dates == null || dates.size() == 0;
        }

        private boolean isRunning() {
            return state == 1;
        }

        private void doRunning() {
            do {
                System.out.println("任务总数"+dates.size());
                long time = minDate;
                System.out.println("当前时间:" + minDate);
                long currentTimeMillis = System.currentTimeMillis();

                if (time > 0 & currentTimeMillis >= time) {

                    runTask(time);

                    System.out.println("执行完了之后的任务总数："+dates.size());

                    continue;

                } else {
                    try {
                        //当前线程睡眠
                        System.out.println("当前线程睡眠:" + (time - currentTimeMillis) + "时间");

                        Thread.currentThread().sleep(time - currentTimeMillis);

                    } catch (InterruptedException e) {

                    }
                }
            } while (true);

//            state = 0;
        }
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
