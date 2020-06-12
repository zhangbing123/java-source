package main.executor.zb.policy;

import main.executor.zb.ZbThreadPoolExecutor;

/**
 * @description: 拒绝策略
 * @author: zhangbing
 * @create: 2020-05-06 10:05
 **/
public interface RejectPolicy {

    void rejectedExecution(Runnable r, ZbThreadPoolExecutor executor);
}
