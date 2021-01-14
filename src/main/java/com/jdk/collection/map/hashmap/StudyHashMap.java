package com.jdk.collection.map.hashmap;

import java.util.HashMap;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @description: HashMap源码解读
 * @author: zhangbing
 * @create: 2019-07-26 11:36
 **/
public class StudyHashMap {

    /**
     * 底层数据结构：
     * 1.7：数组+链表
     * 1.8: 数组+链表+红黑树
     * 测试目的：理解HashMap发生resize扩容的时候对于链表的优化处理：
     * * 初始化一个长度为8的HashMap，因此threshold为6，所以当添加第7个数据的时候会发生扩容；
     * <p>
     * 1.8 HashMap扩容针对元素迁移使用高低位策略 移动元素的优化证明
     * <p>
     * 判断高低位
     * 16 00000000 00000000 00000000 00010000
     * &
     * 8  00000000 00000000 00000000 00001000
     * =  00000000 00000000 00000000 00000000  结果：0  所以8就是低位
     * <p>
     * 原数组下标：8 & (16-1) = 8
     * 8  00000000 00000000 00000000 00001000
     * &
     * 15 00000000 00000000 00000000 00001111
     * =  00000000 00000000 00000000 00001000  结果：8  所以下标：8
     * <p>
     * 新数组下标: 8 & (32-1) = 8
     * 8  00000000 00000000 00000000 00001000
     * &
     * 31 00000000 00000000 00000000 00011111
     * =  00000000 00000000 00000000 00001000 结果：8  所以下标位：8
     * 总结：HashMap的扩容后(扩容为原来的两倍)，元素迁移 针对数组同一个位置上的元素  如果是低位，就移动新数组的原下标位置
     * <p>
     *
     * <p>
     * 判断高低位
     * 16 00000000 00000000 00000000 00010000
     * &
     * 24 00000000 00000000 00000000 00011000
     * =  00000000 00000000 00000000 00010000 结果：1 所以24就是高位
     * <p>
     * 原数组下标：24 & (16-1) =
     * 24 00000000 00000000 00000000 00011000
     * &
     * 15 00000000 00000000 00000000 00001111
     * =  00000000 00000000 00000000 00001000 结果：8 所以下标：8
     * <p>
     * 新数组下标: 8 & (32-1) = 24
     * 24 00000000 00000000 00000000 00011000
     * &
     * 31 00000000 00000000 00000000 00011111
     * =  00000000 00000000 00000000 00011000 结果：24 所以下标：24 = 原数组的下标位置 + 原数组的长度 = 8+16
     * 总结：HashMap的扩容后(扩容为原来的两倍)，元素迁移 针对数组同一个位置上的元素  如果是高位，移动到新数组下标= (原数组的下标+原数组长度)
     *
     * @param args
     */
    public static void main(String[] args) {

        Random random = new Random();
        HashMap<Object, String> hashMap = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            int number = random.nextInt(62);
            hashMap.put(i, String.valueOf(number));
            hashMap.get(i);
            hashMap.size();
            System.out.println(hash(i));
        }
        //会产生hash冲突的key
        hashMap.put("Aa", "Aa");
        hashMap.put("BB", "BB");

        System.out.println(hash("Aa"));
        System.out.println(hash("BB"));


        /**
         * 如果当前key 不存在  则新增key
         * 如果当前key 已存在  则不做操作
         */
        hashMap.putIfAbsent("Aa", "12");

        /**
         * 当key已经存在 且对应的value！=null 则不做操作直接返回oldValue
         * 当key已经存在 且对应的value == null 则用新值覆盖老值 返回newValue
         * 当key不存在 新增
         */
        hashMap.computeIfAbsent("Aa", new Function<Object, String>() {
            @Override
            public String apply(Object o) {
                return "123";
            }
        });

        /**
         *
         * 当前key不存在的话 或者当前key对应的value为null 直接跳过 不做任何操作
         * 当前key存在 并且value不为null：
         * 1.新值不为null，覆盖老值
         * 2.新值为null,移除老值
         *
         */
        hashMap.computeIfPresent("Aa", new BiFunction<Object, String, String>() {
            @Override
            public String apply(Object o, String s) {
                return null;
            }
        });


        /**
         * 下面所有的key都是定位到数组的同一个位置上 全部hash冲突
         */
//        HashMap<Integer, Integer> map = new HashMap<>();
//        map.put(24,24);
//        map.put(56,56);
//        map.put(120,120);
//        map.put(248,248);
//        map.put(232,232);
//        map.put(200,200);
//        map.put(136,136);
//        map.put(168,168);
//        map.put(184,184);//到第九个 链表长度>8 转化位红黑树
//        map.put(264,264);
//        map.put(392,392);
//        map.put(456,456);
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

}
