package com.jdk.executor.zb.policy;


import com.jdk.executor.zb.ZbThreadPoolExecutor;
import com.jdk.executor.zb.exception.RejectPolicyException;

/**
 * @description:
 * @author: zhangbing
 * @create: 2020-05-06 10:07
 **/
public class DefaultPolicyHandler implements RejectPolicy {

    @Override
    public void rejectedExecution(Runnable r, ZbThreadPoolExecutor executor) {
        System.out.println("任务已经满了");
        throw new RejectPolicyException("任务已经满了");
    }
}
