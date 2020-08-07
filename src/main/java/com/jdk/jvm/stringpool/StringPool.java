package com.jdk.jvm.stringpool;

/**
 * @description:
 * @author: zhangbing
 * @create: 2020-07-06 15:59
 **/
public class StringPool {

    public static void main(String[] args) {
        String str1 = new String("a") + new String("b");
        String str2 = str1.intern();
        String str3 = "ab";
        System.out.println(str1 == str2);
        System.out.println(str1 == str3);
    }
}
