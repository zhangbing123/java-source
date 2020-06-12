package main.executor.zb;

import main.executor.zb.policy.DefaultPolicyHandler;
import main.executor.zb.policy.RejectPolicy;

import java.util.HashSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: zhangbing
 * @create: 2020-05-06 09:55
 **/
public class ZbThreadPoolExecutor implements ZbExecutorService {

    /**
     * 核心线程池大小
     */

    private volatile int corePoolSize;

    /**
     * 最大线程池大小
     */
    private volatile int maxPoolSize;

    /**
     * 队列大小
     */
    private volatile int queueSize;

    /**
     * 存活时间 默认 60s
     */
    private volatile long keepAliveTime;

    /**
     * 是否允许超时
     */
    private volatile boolean allowThreadTimeOut;

    /**
     * 拒绝策略
     */
    private RejectPolicy defaultHandler;

    /**
     * 默认队列
     */
    private final BlockingQueue<Runnable> workQueue;

    /**
     * worker集合
     */
    private final HashSet<ZbWorker> workers = new HashSet<ZbWorker>();

    /**
     * active当前激活线程数
     */
    private AtomicInteger ctl = new AtomicInteger();

    /**
     * Lock
     */
    private final ReentrantLock mainLock = new ReentrantLock();

    /**
     * 是否已经中断
     */
    private volatile boolean isShutDown = false;

    /**
     * 任务容量
     */
    private long completedTaskCount;


    public ZbThreadPoolExecutor(int corePoolSize,
                                int maxPoolSize,
                                int queueSize,
                                long keepAliveTime) {
        this(corePoolSize, maxPoolSize, queueSize, keepAliveTime, keepAliveTime > 0, new DefaultPolicyHandler(), new ArrayBlockingQueue<Runnable>(queueSize));
    }

    public ZbThreadPoolExecutor(int corePoolSize,
                                int maxPoolSize,
                                int queueSize,
                                long keepAliveTime,
                                boolean allowThreadTimeOut,
                                RejectPolicy defaultHandler,
                                BlockingQueue<Runnable> workQueue) {
        this.corePoolSize = corePoolSize;
        this.maxPoolSize = maxPoolSize;
        this.queueSize = queueSize;
        this.keepAliveTime = keepAliveTime;
        this.allowThreadTimeOut = allowThreadTimeOut;
        this.defaultHandler = defaultHandler;
        this.workQueue = workQueue;
    }

    @Override
    public void shutdown() {
        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        try {
            isShutDown = true;
            for (ZbWorker w : workers) {
                Thread t = w.thread;
                if (!t.isInterrupted() && w.tryLock()) {
                    try {
                        t.interrupt();
                    } catch (Exception e) {
                        //e.printStackTrace();
                    } finally {
                        w.unlock();
                    }
                }
            }
        } finally {
            mainLock.unlock();
        }
    }

    @Override
    public boolean isShutdown() {
        return isShutDown;
    }

    @Override
    public Future<?> submit(Runnable task) {
        return null;
    }

    @Override
    public void execute(Runnable task) {

        if (task == null)
            throw new NullPointerException("the task is null");
        if (isShutdown())
            throw new IllegalStateException("the thread pool is shutdown");

        int i = ctl.get();

        if (i < corePoolSize) {//工作线程数未达到核心线程数
            if (addWorker(task, true))//创建核心线程并且执行任务
                return;
        } else if (!isShutdown() && workQueue.offer(task)) {//工作线程数已经达到核心线程数，线程池未关闭，任务添加到队列，由空余的核心线程取出并执行

            return;

        } else if (ctl.get() >= maxPoolSize || !addWorker(task, false)) {//队列满了，未达到最大线程数，则新增非核心线程执行任务，
            defaultHandler.rejectedExecution(task, this);//工作线程数达到了最大线程数  则执行拒绝策略
        }

    }

    private Runnable getTask() {
        try {
            return allowThreadTimeOut ? workQueue.poll(keepAliveTime, TimeUnit.SECONDS) : workQueue.take();
        } catch (InterruptedException e) {
            System.out.println("this queue is empty！");
        }
        return null;
    }

    private boolean addWorker(Runnable task, boolean core) {
        if (core)
            ctl.incrementAndGet();

        boolean workerAdded = false;
        boolean workerStarted = false;
        ZbWorker worker = null;
        try {
            worker = new ZbWorker(task);
            Thread thread = worker.thread;
            if (thread != null) {
                ReentrantLock mainLock = this.mainLock;
                mainLock.lock();//加锁

                try {
                    if (!isShutdown()) {//当前线程池未关闭
                        if (thread.isAlive())//当前线程正在运行  无法重新启动
                            throw new IllegalStateException("this thread is running");
                        workers.add(worker);
                        workerAdded = true;
                    }
                } finally {
                    mainLock.unlock();
                }

                if (workerAdded) {
                    thread.start();
                    workerStarted = true;
                }
            }
        } finally {
            if (!core) {
                Thread thread = worker.thread;
                if (!thread.isInterrupted() && worker.tryLock()) {
                    try {
                        thread.interrupt();
                    } finally {
                        worker.unlock();
                    }
                }
            }
        }
        return workerStarted;
    }

    private void processWorkerExit(ZbWorker worker, boolean completedAbruptly) {
        if (completedAbruptly)
            ctl.decrementAndGet();

        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        try {
            completedTaskCount += worker.completedTask;
            workers.remove(worker);
        } finally {
            mainLock.unlock();
        }
        if (completedAbruptly && !workQueue.isEmpty()) {
            addWorker(null, false);
        }
    }

    private void runWorker(ZbWorker worker) {

        Thread thread = Thread.currentThread();
        Runnable task = worker.task;
        worker.task = null;
        boolean completedAbruptly = true;
        try {
            while (task != null || (task = getTask()) != null) {
                worker.lock();
                if (isShutdown() && !thread.isInterrupted()) {
                    //线程池已关闭，线程未中断 需要中断线程池
                    thread.interrupt();
                }
                try {
                    task.run();
                } finally {
                    task = null;
                    worker.completedTask++;
                    worker.unlock();
                }
            }
            completedAbruptly = false;
        } finally {
            processWorkerExit(worker, completedAbruptly);
        }

    }

    class ZbWorker extends ReentrantLock implements Runnable {

        final Thread thread;

        private Runnable task;

        volatile long completedTask;

        public ZbWorker(Runnable task) {
            this.task = task;
            this.thread = new Thread(this);
        }

        @Override
        public void run() {
            runWorker(this);
        }
    }
}
