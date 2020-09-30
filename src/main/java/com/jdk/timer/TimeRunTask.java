package com.jdk.timer;

/**
 * @description:
 * @author: zhangbing
 * @create: 2020-08-31 16:12
 **/
public class TimeRunTask implements Runnable {

    private ZBTimer zbTimer;

    private Runnable runnable;

    private long period;

    private boolean isDelay;

    private TaskExceptionHandler exceptionHandler;

    public TimeRunTask(ZBTimer zbTimer, Runnable runnable, long period, TaskExceptionHandler exceptionHandler) {
        this(zbTimer, runnable, period, true, exceptionHandler);
    }

    public TimeRunTask(ZBTimer zbTimer, Runnable runnable, long period, boolean isDelay, TaskExceptionHandler exceptionHandler) {
        this.zbTimer = zbTimer;
        this.runnable = runnable;
        this.period = period;
        this.isDelay = isDelay;
        this.exceptionHandler = exceptionHandler;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        try {
            runnable.run();
        } catch (Exception e) {
            //进入异常处理逻辑
            exceptionHandler.handle(e, this);
        }
        if (isDelay) {
            zbTimer.registerAtTime(System.currentTimeMillis() + period, this);
        } else {
            zbTimer.registerAtTime(startTime + period, this);
        }
    }


}
