package com.jdk.collection.map;

import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: zhangbing
 * @create: 2020-08-27 14:16
 **/
public class ConcurrentHashMapTest {

    public static void main(String[] args) {

        ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("12","123");

        Hashtable<Object, Object> hashtable = new Hashtable<>();
        hashtable.put(1,2);
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.incrementAndGet();


    }
}
