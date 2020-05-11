package main.java.collection.hashMap;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: HashMap源码解读
 * @author: zhangbing
 * @create: 2019-07-26 11:36
 **/
public class MainHashMap {

    /**
     * 底层数据结构：
     * 1.7：数组+链表
     * 1.8: 数组+链表+红黑树
     *
     * @param args
     */
    public static void main(String[] args) {

        Random random = new Random();
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        HashMap<Object, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < 13; i++) {
//            int number = random.nextInt(62);
            hashMap.put(i, 100);
        }

        Hashtable<Object, Object> objectObjectHashtable = new Hashtable<>();
        objectObjectHashtable.put("123",1233);

        LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("1223", "312312");
        linkedHashMap.get("1223");

        ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("1", "423");

        System.out.println(1 << 30);
        System.out.println(Integer.MAX_VALUE);
    }
}
