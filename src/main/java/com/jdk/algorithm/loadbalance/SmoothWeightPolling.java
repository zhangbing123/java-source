package com.jdk.algorithm.loadbalance;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @description: 平滑加权轮询
 * @author: zhangbing
 * @create: 2020-09-30 10:26
 **/
public class SmoothWeightPolling {

    private static volatile List<AppServer> appServers = new ArrayList<>();

    private static int totalWeight = 0;

    //访问次数
    private static int num = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 8; i++) {
            new Thread(() -> invokeServer()).start();
        }
    }

    /**
     * 这就是平滑加权轮询，巧妙的利用了巧妙算法，既有轮询的效果，又避免了某台服务器压力突然升高，不可谓不妙。
     */
    private static void invokeServer() {

        synchronized (appServers) {

            if (appServers.size() <= 0) {//初始化服务列表
                initServerList();
            }
            num++;
            AppServer server = getServerByMaxWeight();
            server.execute();
            //处理请求的server的当前权重-总的权重
            server.setCurrentWeight(server.getCurrentWeight() - totalWeight);
        }


    }

    private static void initServerList() {
        appServers.add(new AppServer("10.10.10.01", "server1", 5));
        appServers.add(new AppServer("10.10.10.02", "server2", 1));
        appServers.add(new AppServer("10.10.10.03", "server3", 1));
        totalWeight = 7;
    }

    private static AppServer getServerByMaxWeight() {

        AppServer maxAppServer = appServers.get(0);
        int weight = maxAppServer.getCurrentWeight() + maxAppServer.getInitWeight();

        if (num == 1) {//第一次访问
            return appServers.stream().max(Comparator.comparingInt(AppServer::getCurrentWeight)).get();
        }

        for (AppServer appServer : appServers) {//不是第一次访问

            int i = appServer.getCurrentWeight() + appServer.getInitWeight();
            appServer.setCurrentWeight(i);
            System.out.println(i);

            if (i > weight) {
                weight = i;
                maxAppServer = appServer;
            }
        }

        return maxAppServer;


    }
}
