package main.java.collection.arrayList;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

/**
 * 源码解读
 */
public class MainArrayList {

    public static void main(String[] args) {
        add();
    }

    private static void add(){
        ArrayList<Integer> list = new ArrayList<>();
        /**
         * 默认数组为空数组
         * 1.添加第一个元素时，会默认数组容量为10
         * 2.数组不为空，判断数组长度是否足够 足够不需要扩容 不足 扩容为原来的一般 例如原来为10 扩容后为 10+(10 >> 1)
         */
        list.add(1);
        System.out.println(10+(10>>1));

        Vector<Integer> objects = new Vector<>();
        objects.add(1);
    }
}
