package com.jdk.algorithm.loadbalance;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @description: 加权轮询
 * @author: zhangbing
 * @create: 2020-09-30 10:26
 **/
public class WeightPolling {

    private static volatile List<AppServer> appServers = new ArrayList<>();

    private static int totalWeight = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 7; i++) {
            new Thread(() -> invokeServer()).start();
        }
    }

    /**
     * 加权轮询算法实现
     * 针对处理请求能力不同的服务器，可以灵活的分发请求数量。
     * 缺点：其中一台服务器的压力可能会突然上升，
     * 而另外的服务器却很“悠闲，喝着咖啡，看着新闻”。我们希望虽然是按照轮询，但是中间最好可以有交叉，所以出现了第三种轮询算法：平滑加权轮询。
     */
    private static void invokeServer() {

        synchronized (appServers) {

            if (appServers.isEmpty()) {//初始化服务列表
                initServerList();
            }
            AppServer server = getServerByMaxWeight();
            server.execute();
            //处理请求的server的当前权重-总的权重
            server.setCurrentWeight(server.getCurrentWeight() - totalWeight);
            reSetWeight();

        }


    }

    private static void reSetWeight() {

        for (AppServer appServer : appServers) {
            //当前权重=当前权重+初始权重
            appServer.setCurrentWeight(appServer.getCurrentWeight() + appServer.getInitWeight());
            System.out.println(appServer.getServerName() + "的当前权重：" + appServer.getCurrentWeight());
        }

    }

    private static void initServerList() {
        appServers.add(new AppServer("10.10.10.01", "server1", 5));
        appServers.add(new AppServer("10.10.10.02", "server2", 1));
        appServers.add(new AppServer("10.10.10.03", "server3", 1));
//        appServers.add(new AppServer("10.10.10.04", "server4", 4, 4));
        totalWeight = 7;
    }

    private static AppServer getServerByMaxWeight() {
        return appServers.stream().max(Comparator.comparingInt(AppServer::getCurrentWeight)).get();
    }
}
