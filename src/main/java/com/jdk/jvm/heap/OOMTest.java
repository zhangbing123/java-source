package com.jdk.jvm.heap;

import com.jdk.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**

* @Description:    内存益处测试

* @Author:         zhangbing

* @CreateDate:     2020/7/5 1:01 PM

*/
public class OOMTest {

    private static List<Object> objectList = new ArrayList<>();

    /**
     * jvm配置
     * -Xms5m -Xmx5m -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/Users/zhangbing/worker/opt2.dump
     * @param args
     */
    public static void main(String[] args) {

        int i=0;
        while (true){
            objectList.add(new Student(UUID.randomUUID().toString(),i++));
            new Student(UUID.randomUUID().toString(),i--);
        }
    }
}
