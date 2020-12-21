package com.jdk.collection.list.arraylist;

import java.util.Vector;

/**
 * @description:
 * @author: zhangbing
 * @create: 2020-12-21 11:35
 **/
public class StudyVector {

    public static void main(String[] args) {
        /**
         * Vector集合的实现与ArrayList几乎一模一样  底层数据结构都是数组
         * 区别：Vector通过在方法上加synchronized关键字保证线程安全，效率性能低下  ArrayList非线程安全 但是没有了锁申请和竞争的开销 效率很高
         */
        Vector<Integer> vector = new Vector<>(12);
        vector.add(1);//synchronized 加在方法上保证线程安全
        vector.get(0);//synchronized 加在方法上保证线程安全
        vector.add(2, 2);//synchronized 加在方法上保证线程安全
        vector.remove(1);//synchronized 加在方法上保证线程安全
    }
}
