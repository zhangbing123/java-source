package main.executor.zb;

import java.util.concurrent.Future;

/**
 * @description:
 * @author: zhangbing
 * @create: 2020-05-06 09:49
 **/
public interface ZbExecutorService {

    void shutdown();

    boolean isShutdown();

    Future<?> submit(Runnable task);

    void execute(Runnable task);
}
