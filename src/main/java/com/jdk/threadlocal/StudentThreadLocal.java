package com.jdk.threadlocal;

import com.jdk.entity.Student;

/**
 * @description:
 * @author: zhangbing
 * @create: 2020-08-07 10:33
 **/
public class StudentThreadLocal {

    private static ThreadLocal<Student> studentThreadLocal = new ThreadLocal<>();

    public static void set(Student student) {
        studentThreadLocal.set(student);
    }

    public static Student get() {
        return studentThreadLocal.get();
    }
}
