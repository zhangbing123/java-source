package com.jdk.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @description: 罗马数字翻转  力扣13题
 * @author: zhangbing
 * @create: 2021-01-22 16:52
 **/
public class LeeCode13 {

    public static void main(String[] args) {
//        System.out.println(romanToInt("LVIII"));
        System.out.println(romanToInt2("IV"));

    }

    public static int romanToInt(String s) {
        HashMap<String, Integer> rule = new HashMap<>();
        rule.put("I", 1);
        rule.put("V", 5);
        rule.put("X", 10);
        rule.put("L", 50);
        rule.put("C", 100);
        rule.put("D", 500);
        rule.put("M", 1000);
        rule.put("IV", 4);
        rule.put("IX", 9);
        rule.put("XL", 40);
        rule.put("XC", 90);
        rule.put("CD", 400);
        rule.put("CM", 900);
        List<String> specs = new ArrayList<>();
        specs.add("IV");
        specs.add("IX");
        specs.add("XL");
        specs.add("XC");
        specs.add("CD");
        specs.add("CM");

        Integer integer = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i <= chars.length - 1; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(chars[i]);
            if (i < chars.length - 1) {
                stringBuilder.append(chars[i + 1]);
            }
            String string = stringBuilder.toString();
            if (specs.contains(string)) {
                integer = integer + rule.get(string);
                i++;
            } else {
                String s1 = String.valueOf(chars[i]);
                integer = integer + rule.get(s1);
            }
        }

        return integer;
    }

    public static int romanToInt2(String s) {

        char[] chars = s.toCharArray();

        int length = chars.length;

        int result = 0;
        for (int i = 0; i < length; i++) {
            int value1 = getValue(chars[i]);
            if (i + 1 < length) {
                int value2 = getValue(chars[i + 1]);
                if (value1 < value2) {
                    result += (value2 - value1);
                    i++;
                    continue;
                }
            }
            result += value1;

        }

        return result;

    }

    private static int getValue(char ch) {
        switch (ch) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }
}
