package com.jdk.productionconsum;

import java.util.LinkedList;

/**
 * @description: 自定义队列  一个简单的生产消费系统
 * @author: zhangbing
 * @create: 2020-10-13 17:08
 **/
public class CustomerMQ {

    private int size = 10;

    private LinkedList<String> linkedList = new LinkedList<String>();

    public synchronized void add(String s) throws InterruptedException {
        if (linkedList.size() == size) {
            this.wait();
        }
        linkedList.add(s);
        this.notifyAll();

    }

    public synchronized String get() throws InterruptedException {

        if (linkedList.isEmpty()) {
            this.wait();
        }
        String last = linkedList.getLast();
        linkedList.remove(last);
        this.notifyAll();
        return last;

    }

}
