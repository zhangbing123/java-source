package com.jdk.collection.list.copyonwritearraylist;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @description:
 * @author: zhangbing
 * @create: 2020-12-16 11:13
 **/
public class CopyOnWriteTest {

    /**
     * 写时复制技术  一般用于读多写少的场景
     * 优点：
     * 1.读数据性能提升
     * 2.不阻塞读操作，阻塞写操作
     * 缺点：
     * 1.时间换空间，比较占用内存
     * 2.数据一致性问题，有几率读取的是老的数据
     *
     * @param args
     */
    public static void main(String[] args) {

        /**
         * 底层数据结构数组
         * 初始化默认数组大小0
         */
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();

        /**
         * 添加元素:
         * 1.ReentrantLock.lock加锁
         * 2.获取原数组对象
         * 3.使用原数组复制一个新数组对象  长度=原数组len+1
         * 4.把元素加到数组末尾
         * 5.新数组覆盖原数组
         */
        copyOnWriteArrayList.add("test1");
        copyOnWriteArrayList.add(1, "test2");

        //查询元素，直接获取数组元素，没有进行加锁，所以当同时有写操作时，会出现读取到旧数据的问题
        copyOnWriteArrayList.get(1);
        /**
         * 加锁
         * 复制新数组 copy老数组数据到新数组中
         */
        copyOnWriteArrayList.remove(0);
    }
}
