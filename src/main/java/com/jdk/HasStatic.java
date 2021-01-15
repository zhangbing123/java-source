package com.jdk;

/**
 * @description:
 * @author: zhangbing
 * @create: 2021-01-14 17:37
 **/
public class HasStatic {

    private static int x=100;

    public static void main(String[] args) {

        HasStatic h1 = new HasStatic();
        h1.x++;
        HasStatic h2 = new HasStatic();
        h2.x++;
        h1 = new HasStatic();
        h1.x++;
        HasStatic.x--;
        System.out.println("x="+x);

    }
}
