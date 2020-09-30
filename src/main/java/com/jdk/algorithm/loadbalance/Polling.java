package com.jdk.algorithm.loadbalance;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 完全轮询算法
 * @author: zhangbing
 * @create: 2020-09-30 09:45
 **/
public class Polling {

    private static volatile List<AppServer> appServers = new ArrayList<>();

    private static int num = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {

            new Thread(() -> invokeServer()).start();
        }

    }

    /**
     * 这中方式 实现比较简单
     * 且对于集群所有节点都是公平的，每一个节点都能处理相同的请求
     *
     * 如果这些节点对于请求的处理能力不同，我想对于能力强的节点多分发点请求  能力不强的少分发点请求
     * 那么这种算法就无法实现了
     */
    private static void invokeServer() {

        synchronized (appServers) {

            if (appServers.size() <= 0) {//初始化服务列表
                initServerList();
            }

            int index = num < appServers.size() ? num : num % appServers.size();
            appServers.get(index).execute();
            num++;
        }


    }

    private static void initServerList() {
        appServers.add(new AppServer("10.10.10.01", "server1"));
        appServers.add(new AppServer("10.10.10.02", "server2"));
        appServers.add(new AppServer("10.10.10.03", "server3"));
        appServers.add(new AppServer("10.10.10.04", "server4"));
    }
}
