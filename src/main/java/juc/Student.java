package main.java.juc;

/**
 * @description:
 * @author: zhangbing
 * @create: 2020-04-07 16:33
 **/
public class Student {

    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
