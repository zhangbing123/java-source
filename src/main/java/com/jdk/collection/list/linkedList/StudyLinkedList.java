package com.jdk.collection.list.linkedList;

import java.util.LinkedList;

/**
 * @description: 学习LinkedList源码
 * @author: zhangbing
 * @create: 2020-12-21 11:41
 **/
public class StudyLinkedList {

    public static void main(String[] args) {
        /**
         * 底层数据结构：链表 双向链表
         * 构造方法：
         * 1.LinkedList()无参构造
         * 2.LinkedList(Collection<? extends E> c)
         *
         */
        LinkedList<Integer> list = new LinkedList<>();

        list.add(1);//追加到链表尾部
        list.add(1,2);
        //根据下标获取，index < (size >> 1)  下标 < 数据总量/2 ? 说明下标落在链表的前半部分，则从头开始遍历查找 : 说明下标落在链表后半部分，从尾部开始遍历 提高效率
        list.get(0);
        list.remove(0);//从链表中移除一个节点
    }
}
