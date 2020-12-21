package com.jdk.collection.set.treeset;

import java.util.TreeSet;

/**
 * @description:
 * @author: zhangbing
 * @create: 2020-12-21 14:54
 **/
public class StudyTreeSet {

    public static void main(String[] args) {

        /**
         *
         * 1.TreeSet是一个有序的，且元素不允许重复的集合
         * 2.底层通过TreeMap实现数据的有序性，和数据的唯一性 {@link java.util.TreeSet}.
         * 3.可以传入一个比较器Comparator 自定义排序规则
         */
        TreeSet<Integer> treeSet = new TreeSet<>();

        //底层调用TreeMap.put(1,PRESENT)方法保存数据  重复的数据返回false
        treeSet.add(1);

        //底层调用TreeMap.remove(o)方法实现
        treeSet.remove(1);
    }
}
