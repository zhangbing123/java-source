package com.jdk.executor;

import java.util.concurrent.*;

/**
 * @description:
 * @author: zhangbing
 * @create: 2020-04-27 10:21
 **/
public class ExecutorMain {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 5, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

        for (int i = 0; i < 15; i++) {
            int finalI = i;
            threadPoolExecutor.execute(() -> System.out.println(finalI + ":" + Thread.currentThread().getName()));
        }
        threadPoolExecutor.shutdown();
    }
}
