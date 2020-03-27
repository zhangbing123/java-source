package main.java.map.hashMap;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: HashMap源码解读
 * @author: zhangbing
 * @create: 2019-07-26 11:36
 **/
public class MainHashMap {
    public static void main(String[] args) {

        HashMap<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(1, 100);
        hashMap.remove(1);
        LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("1223", "312312");
        linkedHashMap.get("1223");

        ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("1", "423");
    }
}
