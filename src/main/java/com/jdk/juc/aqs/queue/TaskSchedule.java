package com.jdk.juc.aqs.queue;

import com.jdk.unsafe.UnsafeInstance;
import sun.misc.Unsafe;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

/**
 * @description:
 * @author: zhangbing
 * @create: 2020-08-26 10:47
 **/
public class TaskSchedule {

    private DelayQueue<Item> queue = new DelayQueue<>();

    private volatile int state;
    private static Unsafe unsafe;
    private static long valueOffset;
    private ThreadPoolExecutor executor;

    static {

        //通过反射获取Unsafe类 此类中的cas原子操作实现方式主要基于cmpxchg指令实现的
        unsafe = UnsafeInstance.reflectGetUnsafe();

        //获取对象属性的偏移量（在内存中的位置）
        try {
            valueOffset = unsafe.objectFieldOffset(TaskSchedule.class.getDeclaredField("state"));
        } catch (NoSuchFieldException e) {

        }
    }

    public TaskSchedule() {
        executor = new ThreadPoolExecutor(5, 10, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
    }

    static class Item implements Delayed {

        private long time;

        private Runnable runnable;

        public Item(long time, Runnable runnable) {
            this.time = time;
            this.runnable = runnable;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return time - System.currentTimeMillis();
        }

        @Override
        public int compareTo(Delayed o) {
            Item item = (Item) o;
            long diff = this.time - item.time;
            if (diff <= 0) {// 改成>=会造成问题
                return -1;
            } else {
                return 1;
            }
        }

        private Runnable getRunnable() {
            return runnable;
        }
    }

    public void registerAndRun(long time, Runnable runnable) {
        queue.put(new Item(time, runnable));
        if (startRunning(0, 1)) {
            start();
        }
    }

    private void start() {
        System.out.println("begin time:" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        while (true) {
            try {
                Item take = queue.take();
                executor.execute(take.getRunnable());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean startRunning(int oldState, int newState) {

        return unsafe.compareAndSwapInt(this, valueOffset, oldState, newState);
    }
}
