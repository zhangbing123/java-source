package com.jdk.reference;

import com.jdk.entity.Student;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @description: 软引用测试
 * @author: zhangbing
 * @create: 2020-08-07 10:56
 **/
public class SoftReferenceTest {

    public static void main(String[] args) {
        SoftReference<Student> reference = new SoftReference<Student>(new Student("zhangsan",10));
        System.out.println(reference.get());
        System.gc();//通知GVM回收资源
        System.out.println(reference.get());

        /**
         * 结果：
         * com.jdk.entity.Student@14ae5a5
         * com.jdk.entity.Student@14ae5a5  说明Gc回收后  软引用不会被回收，只有在内存不够时 在抛出oom之前回收软引用
         *
         */
    }
}
