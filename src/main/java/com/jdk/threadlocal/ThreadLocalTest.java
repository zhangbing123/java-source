package com.jdk.threadlocal;

import com.jdk.entity.Student;

/**
 * @description: threadLocal 测试
 * @author: zhangbing
 * @create: 2020-08-07 10:35
 **/
public class ThreadLocalTest {

    public static void main(String[] args) {
        Student student = new Student();
        student.setAge(1);
        student.setName("zhangsan");
        StudentThreadLocal.set(student);
        Student student1 = StudentThreadLocal.get();

    }
}
