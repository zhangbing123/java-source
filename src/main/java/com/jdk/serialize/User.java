package com.jdk.serialize;

import com.jdk.entity.Student;

import java.io.Serializable;

/**
 * @description:
 * @author: zhangbing
 * @create: 2020-08-28 17:00
 **/
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    private String name;

    private int age;

    private static int sex=1;

    private transient String code;

    private String department;

    private Student student;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static int getSex() {
        return sex;
    }

    public static void setSex(int sex) {
        User.sex = sex;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", department=" + department +
                ", code='" + code + '\'' +
                '}';
    }
}
