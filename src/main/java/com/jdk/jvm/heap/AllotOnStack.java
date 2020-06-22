package com.jdk.jvm.heap;

import com.jdk.entity.Student;

/**
 * @Description: 测试对象栈内分配内存空间  标量替换
 * @Author: zhangbing
 * @CreateDate: 2020/6/20 8:50 PM
 */
public class AllotOnStack {

    /**
     * 代码调用了1亿此的alloc方法  假设在对上分配内存
     * 调用1亿次  产生1亿个Student对象  假设一个对象8B大小 总大小为：（8*1亿）/1000/1024 = 781MB 大概1GB 如果堆内存小于此值 则必然触发GC
     *
     * jvm堆内存配置 -Xms15m -Xmx15m
     *
     * 如下配置不会发生大量的GC 几乎不会发生GC 开启逃逸分析  开启标量替换
     * -XX:+DoEscapeAnalysis(开启逃逸分析) -XX:+PrintGC  -XX:+EliminateAllocations(开启标量替换)
     *
     * 如下配置会发生大量GC 不开启逃逸分析  开启标量替换（开启也没用 因为没有开启逃逸分析 标量替换是在开启逃逸分析情况下生效）
     * -XX:-DoEscapeAnalysis(不开启逃逸分析) -XX:+PrintGC  -XX:+EliminateAllocations(开启标量替换)
     *
     * 如下配置会发声大量GC 开启逃逸分析  不开启标量替换
     * -XX:+DoEscapeAnalysis(开启逃逸分析) -XX:+PrintGC  -XX:-EliminateAllocations(开启标量替换)
     *
     * @param args
     */
    public static void main(String[] args) {

        long l = System.currentTimeMillis();

        for (int i = 0; i < 100000000; i++) {
            alloc();
        }

        long l1 = System.currentTimeMillis();

        System.out.println(l1-l);

        /**
         * 结论：栈上分配内存 依赖逃逸分析和标量替换
         */

    }

    public static void alloc() {
        Student student = new Student();
        student.setName("zhangsan");
        student.setAge(20);
    }
}
