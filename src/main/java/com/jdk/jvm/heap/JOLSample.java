package com.jdk.jvm.heap;

import org.openjdk.jol.info.ClassLayout;

/**

* @Description:    计算对象的大小

* @Author:         zhangbing

* @CreateDate:     2020/6/20 7:52 PM

*/
public class JOLSample {

    public static void main(String[] args) {

        //打印Object对象的大小
        System.out.println("=========打印Object对象的大小========");
        ClassLayout classLayout = ClassLayout.parseInstance(new Object());
        System.out.println(classLayout.toPrintable());
        /**
         * 结果:
         *  OFFSET  SIZE   TYPE DESCRIPTION
         *       0     4        (object header) 对象头 mark word 4个字节
         *       4     4        (object header) 对象头 mark word 4个字节 加上 上面的总共占8个字节  说明是64位操作系统
         *       8     4        (object header) 对象头 klass point 类元数据指针 占4个字节  这个指针大小进行了指针压缩  不压缩时 在64位操作系统中占8B
         *      12     4        (loss due to the next object alignment) 对其填充位  使对象大小保证位8的倍数（32位操作系统中保证位4字节的倍数）
         * Instance size: 16 bytes  总共16字节
         * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
         *
         */

        //打印数组对象的大小
        System.out.println("========打印数组对象的大小=========");
        ClassLayout classLayout1 = ClassLayout.parseInstance(new int[]{});
        System.out.println(classLayout1.toPrintable());
        /**
         * 结果：
         *     OFFSET  SIZE   TYPE DESCRIPTION
         *       0     4        (object header) 对象头 mark word 4个字节
         *       4     4        (object header) 对象头 mark word 4个字节 加上 上面的总共占8个字节  说明是64位操作系统
         *       8     4        (object header) 对象头 klass point 类元数据指针 占4个字节  这个指针大小进行了指针压缩  不压缩时 在64位操作系统中占8B
         *      12     4        (object header) 对象头 记录的是数组长度（长度一般使用数字记录也就是int类型 所以占用4B）
         *      16     0    int [I.<elements>   上面的占用内存大小已经位8B的倍数了  所以不再有对其填充位
         * Instance size: 16 bytes
         * Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
         *
         */

        //打印有成员变量的对象大小
        System.out.println("========打印有成员变量的对象大小=========");
        ClassLayout classLayout2 = ClassLayout.parseInstance(new A());
        System.out.println(classLayout2.toPrintable());
        /**
         * 结果：
         *     OFFSET  SIZE               TYPE DESCRIPTION
         *       0     4                    (object header) 对象头 mark word 4个字节
         *       4     4                    (object header) 对象头 mark word 4个字节 加上 上面的总共占8个字节  说明是64位操作系统
         *       8     4                    (object header) 对象头 klass point 类元数据指针 占4个字节  这个指针大小进行了指针压缩  不压缩时(默认压缩) 在64位操作系统中占8B
         *      12     4                int A.id 成员变量int类型 占4B
         *      16     1               byte A.aByte 成员变量byte类型  占1B
         *      17     3                    (alignment/padding gap) 针对byte类型的成员变量做一个对齐填充
         *      20     4   java.lang.String A.name //String对象类型的成员变量 占4B  进行了指针压缩
         *      24     4   java.lang.Object A.o    //Object对象类型的成员变量 占4B  进行了指针压缩
         *      28     4                    (loss due to the next object alignment) 为了保证对象大小为8B的倍数  对齐填充4个字节
         * Instance size: 32 bytes // 总共32B
         * Space losses: 3 bytes internal + 4 bytes external = 7 bytes total
         */

        //关闭指针压缩之后打印A对象的大小
        System.out.println("========关闭指针压缩之后打印A对象的大小=========");
        ClassLayout classLayout3 = ClassLayout.parseInstance(new A());
        System.out.println(classLayout3.toPrintable());
        /**
         * 关闭指针压缩结果:
         * OFFSET  SIZE               TYPE DESCRIPTION
         *       0     4                    (object header)   mark word
         *       4     4                    (object header)   mark word  总共占8B
         *       8     4                    (object header)   klass point 类元数据指针
         *      12     4                    (object header)   klass point 类元数据指针  总共占8B  未压缩的占用大小
         *      16     4                int A.id
         *      20     1               byte A.aByte
         *      21     3                    (alignment/padding gap)
         *      24     8   java.lang.String A.name    //对象类型的成员变量  未压缩 占8B
         *      32     8   java.lang.Object A.o       //对象类型的成员变量  未压缩 占8B
         * Instance size: 40 bytes
         * Space losses: 3 bytes internal + 0 bytes external = 3 bytes total
         */


        /**
         * 指针压缩可以节省对象的内存空间
         */

    }

    // ‐XX:+UseCompressedOops默认开启的压缩所有指针
    // ‐XX:+UseCompressedClassPointers默认开启的压缩对象头里的类型指针KlassPointer
    // Oops:OrdinaryObjectPointer
    static class A{
        int id;//4B
        String name;
        byte aByte;
        Object o;
    }
}
