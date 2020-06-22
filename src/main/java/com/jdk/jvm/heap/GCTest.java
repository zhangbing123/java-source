package com.jdk.jvm.heap;

/**

* @Description:    GC测试

* @Author:         zhangbing

* @CreateDate:     2020/6/20 9:25 PM

*/
public class GCTest {

    //配置 -XX:+PrintGCDetails  打印Gc的详细信息
    public static void main(String[] args) {
        //创建一个bute的数组  大概占用60MB
        byte [] allocate1 = new byte[70000*1024];
        /**
         * 结果：
         * Heap
         *  PSYoungGen      total 153088K, used 70527K 年轻代 用了大概70M
         *   eden space 131584K, 53% used 创建的对象都在eden区
         *   from space 21504K, 0% used 没占用
         *   to   space 21504K, 0% used 没占用
         *  ParOldGen       total 349696K, used 0K 老年代用了0  没有占用内存空间
         *   object space 349696K, 0% used
         *  Metaspace       used 3095K, capacity 4496K, committed 4864K, reserved 1056768K
         *   class space    used 339K, capacity 388K, committed 512K, reserved 1048576K
         */

        /**
         * 配置 -Xmn100 年轻代大小为100MB 则eden占80MB  from区占10MB to区占10MB
         */
//        byte[] allocate2 = new byte[10000 * 1024]; //占用大约20MB  此时eden区的内存大小不够了 需要发生一次GC 然后移动到 from区  from区不够用  移动到老年代
        /**
         * 结果：
         * 发生了一次GC
         * [GC (Allocation Failure) [PSYoungGen: 74608K->672K(89600K)] 74608K->70680K(511488K), 0.0457953 secs] [Times: user=0.55 sys=0.02, real=0.05 secs]
         * Heap
         *  PSYoungGen      total 89600K, used 11440K 年轻代占用的10MB是分配给allocate2引用的对象  在此之前  发生了一次GC 数据移动到了老年代
         *   eden space 76800K, 14% used
         *   from space 12800K, 5% used
         *   to   space 12800K, 0% used
         *  ParOldGen       total 421888K, used 70008K //allocate1引用的占用70MB被移动到了old区 allocate2引用的10MB被分配到了年轻代
         *   object space 421888K, 16% used [0x00000005c0000000,0x00000005c445e010,0x00000005d9c00000)
         *  Metaspace       used 3096K, capacity 4496K, committed 4864K, reserved 1056768K
         *   class space    used 339K, capacity 388K, committed 512K, reserved 1048576K
         */


        /**
         * 后面的对象还是会分配到eden的区
         */
//        byte[] allocate3 = new byte[1000 * 1024];
//        byte[] allocate4 = new byte[1000 * 1024];
//        byte[] allocate5 = new byte[1000 * 1024];
//        byte[] allocate6 = new byte[1000 * 1024];
    }
}
