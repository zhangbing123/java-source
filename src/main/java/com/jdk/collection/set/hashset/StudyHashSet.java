package com.jdk.collection.set.hashset;

import java.util.HashSet;

/**
 * @description:
 * @author: zhangbing
 * @create: 2020-12-21 14:38
 **/
public class StudyHashSet {

    public static void main(String[] args) {
        /**
         * HashSet底层使用HashMap实现
         * {@link java.util.HashMap}.
         * 不允许新增重复的数据
         */
        HashSet<Integer> hashSet = new HashSet<>();

        /**
         * 利用map中key的唯一性实现HashSet集合不重复的功能
         * 把数据作为key，一个object对象作为value，put到map中，根据map.put方法返回值判断数据保存是否成功
         * 1.map.put()方法返回null，说明这个值不在map存在，add到set集合成功
         * 2.map.put()方法返回object,说明这个值存在map中，add到set结合失败
         */
        hashSet.add(1);//第一次保存成功
        hashSet.add(1);//第二次保存失败

        hashSet.add(2);

        //hashSet 没有get(int index)方法，因为无法根据index查找map的key

        //调用map的remove()方法实现
        hashSet.remove(2);
    }
}
