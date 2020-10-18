package com.jdk.collection.map;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class TreeMapTest {

    private static Object object = new Object();

    public static void main(String[] args) {
//        TreeMap<Long, Long> longLongTreeMap = new TreeMap<>();
//        longLongTreeMap.put(3l, 3l);
//        longLongTreeMap.put(17l, 17l);
//        longLongTreeMap.put(6l, 6l);
//        longLongTreeMap.put(4l, 4l);
//        longLongTreeMap.put(2l, 2l);
//        longLongTreeMap.put(1l, 1l);
//
//        longLongTreeMap.remove(6l);
//
//        Set<Long> longs = longLongTreeMap.keySet();
//        for (Long aLong : longs) {
//            System.out.println(aLong);
//        }
//
//
//        for (Map.Entry<Long, Long> longLongEntry : longLongTreeMap.entrySet()) {
//            System.out.println(longLongEntry.getKey());
//        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object){
                    try {
                        System.out.println("线程1获取到锁，并等待");
                        object.wait(5000);//等待  会释放锁
                        System.out.println("线程1等待结束");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object){

                    System.out.println("线程2获取到锁");

                    while (true){

                    }
                }
            }
        }).start();


    }
}
