package com.jdk.algorithm.loadbalance;

/**
 * @description: 应用服务
 * @author: zhangbing
 * @create: 2020-09-30 09:46
 **/
public class AppServer {

    private String ip;
    private String serverName;

    private int initWeight;

    private int currentWeight;

    public int getInitWeight() {
        return initWeight;
    }

    public void setInitWeight(int initWeight) {
        this.initWeight = initWeight;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }

    public String getIp() {
        return ip;
    }

    public String getServerName() {
        return serverName;
    }

    public AppServer(String ip, String serverName) {
        this.ip = ip;
        this.serverName = serverName;
    }

    public AppServer(String ip, String serverName, int initWeight) {
        this.ip = ip;
        this.serverName = serverName;
        this.initWeight = initWeight;
        this.currentWeight = initWeight;
    }

    public void execute() {
        System.out.println("分发给服务器：" + serverName + ",ip:" + ip + ",处理请求");
    }
}
