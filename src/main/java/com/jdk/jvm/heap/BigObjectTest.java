package com.jdk.jvm.heap;


/**

* @Description:    大对象会直接进入老年代

* @Author:         zhangbing

* @CreateDate:     2020/6/20 9:47 PM

*/
public class BigObjectTest {

    /**
     * -XX:PretenureSizeThreshold=1000000(单位字节) 配置指定大对象的大小  1000kb 约为1MB大小
     * 次参数 只有在使用Serial和ParNew两个垃圾收集器时才有用
     * 所以还需要配置
     * -XX:+UseSerialGC
     *
     * 配置 -XX:+PrintDCDetails 查看GC详细信息
     * @param args
     */
    public static void main(String[] args) {

        //一个5MB大小的数组对象 > 1MB 应该直接进入老年代
        byte[] bytes = new byte[5000 * 1024];
        /**
         * 结果：
         * Heap
         *  def new generation   total 157248K, used 8386K
         *   eden space 139776K,   6% used
         *   from space 17472K,   0% used
         *   to   space 17472K,   0% used
         *  tenured generation   total 349568K, used 5000K  直接分配到老年代了
         *    the space 349568K,   1% used
         *  Metaspace       used 3014K, capacity 4496K, committed 4864K, reserved 1056768K
         *   class space    used 328K, capacity 388K, committed 512K, reserved 1048576K
         *
         */

        /**
         * 结论：
         * 当使用SerialGC或者ParNew两种垃圾收集器时
         * 指定了大对象大小的话，超过这个数值的对象是会直接进入老年代的
         *
         * 为什么？
         * 为了避免大对象分配内存时的复制操作降低效率
         */

    }
}
