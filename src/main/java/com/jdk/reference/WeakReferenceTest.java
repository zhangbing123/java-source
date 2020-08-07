package com.jdk.reference;

import com.jdk.entity.Student;

import java.lang.ref.WeakReference;

/**
 * @description: 弱引用测试
 * @author: zhangbing
 * @create: 2020-08-07 10:56
 **/
public class WeakReferenceTest {

    public static void main(String[] args) {
        WeakReference<Student> reference = new WeakReference<Student>(new Student("zhangsan",10));
        System.out.println(reference.get());
        System.gc();//通知GVM回收资源
        System.out.println(reference.get());

        /**
         * 结果：
         * com.jdk.entity.Student@14ae5a5
         * null 第二次输出null 说明Gc回收后  弱引用直接被回收了，不管内存足不足够
         *
         */



        //仿照ThreadLocal中的ThreadLocalMap类中的Entity类 集成弱引用
        WeakRefTest weakRefTest = new WeakRefTest(new Student("zhangsan", 10), "test");

        System.out.println(weakRefTest);
        System.gc();

        System.out.println(weakRefTest);

        /**
         * 结果：
         * 第一次输出：weakRefTest对象的  value不为null，WeakReference对象中的referent属性引用student对象
         * gc过后
         *
         * 第二次输出：weakRefTest对象的  value不为null WeakReference对象中的referent属性为null  说明被回收了
         *
         * 从结果可以看出：ThreadLocal中的key其实是一个弱引用，不会因为这个发生内存溢出的，但是value不是弱引用，
         * 所以可能发生oom，因此在使用完ThreadLcoal时或者线程结束时，要手动移除value的值，避免发生oom
         */

    }

    static class WeakRefTest extends WeakReference<Student>{

        String value = null;

        public WeakRefTest(Student referent,String string) {
            super(referent);
            this.value = string;
        }
    }
}
