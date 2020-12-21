package com.jdk.collection.set.linkedhashset;

import java.util.LinkedHashSet;

/**
 * @description:
 * @author: zhangbing
 * @create: 2020-12-21 15:03
 **/
public class StudyLinkedHashSet {
    public static void main(String[] args) {
        /**
         * 1.底层通过LinkedHashMap实现  继承了HashSet
         * {@link java.util.LinkedHashMap}.
         */
        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();

        /**
         * 基于LinkedHashMap实现新增数据  作为map的key保存
         */
        linkedHashSet.add(1);
    }
}
