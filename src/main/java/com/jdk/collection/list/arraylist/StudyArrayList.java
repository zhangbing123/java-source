package com.jdk.collection.list.arraylist;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 源码解读
 */
public class StudyArrayList {

    public static void main(String[] args) {
        System.out.println(6 >> 1);//除以2的1次方
        System.out.println(6 << 2);//乘以2的2次方
        System.out.println(6 << 3);//乘以2的3次方
        add();

    }

    private static void add() {
        /**
         * 底层数据结构为数组，ArrayList封装了对数组对象的增删改查操作，是的我们不用关心数组下标越界问题
         * 数组的操作：查询修改块  删除新增慢
         * 特点：
         * 1.允许元素重复
         * 2.数组存储数据
         * 3.无序的
         */
        ArrayList<Integer> list = new ArrayList<>(10);
        /**
         * 默认数组为空数组
         * 1.添加第一个元素时，会默认数组容量为10
         * 2.数组不为空，判断数组长度是否足够 足够不需要扩容 不足 扩容为原来的一般 例如原来为10 扩容后为 10+(10 >> 1)
         * 3.每次新增数据时，都会被追加到数组的尾部
         */
        list.add(1);
        list.add(2);
        list.add(2, 3);
        list.get(0);
        list.remove(2);
        System.out.println("数组中保存的数量：" + list.size());

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            System.out.println(next);
        }
    }
}
