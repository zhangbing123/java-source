package com.jdk.collection.map;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: zhangbing
 * @create: 2020-08-27 14:16
 **/
public class ConcurrentHashMapTest {

    public static void main(String[] args) {

        ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("12","123");

    }
}
