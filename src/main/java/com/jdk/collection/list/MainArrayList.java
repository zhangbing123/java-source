package com.jdk.collection.list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Vector;

/**
 * 源码解读
 */
public class MainArrayList {

    public static void main(String[] args) {
        System.out.println(6 >> 1);//除以2
        System.out.println(6 << 2);//乘以2
        add();

    }

    private static void add() {
        ArrayList<Integer> list = new ArrayList<>(10);
        /**
         * 默认数组为空数组
         * 1.添加第一个元素时，会默认数组容量为10
         * 2.数组不为空，判断数组长度是否足够 足够不需要扩容 不足 扩容为原来的一般 例如原来为10 扩容后为 10+(10 >> 1)
         */
        list.add(1);
        list.add(2);
        list.add(2, 3);
        list.get(0);
        System.out.println("数组中保存的数量：" + list.size());

        Vector<Integer> objects = new Vector<>();
        objects.add(1);

        LinkedList<String> strings = new LinkedList<>();
        strings.add("123");
        strings.add("234");
        strings.add("456");
        strings.add("345");
        strings.remove("123");

        ListIterator<String> stringListIterator = strings.listIterator(1);
        String next = stringListIterator.next();
        System.out.println(next);

    }
}
