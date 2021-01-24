package com.jdk.algorithm.leecode.simple;

/**
 * @description: 最长公共前缀
 * @author: zhangbing
 * @create: 2021-01-22 17:44
 **/
public class LeeCode14 {

    public static void main(String[] args) {
        String[] strs = {"flower", "flow", "flight"};
//        System.out.println(longestCommonPrefix(strs));
        System.out.println(longestCommonPrefix2(strs));
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        String str1 = strs[0];

        for (int i = 1; i < strs.length; i++) {
            StringBuilder result = new StringBuilder();
            String str2 = strs[i];
            int length1 = str1.length();
            int length2 = str2.length();
            int k = length1 < length2 ? length1 : length2;
            int j = 0;
            while (j <= k - 1) {
                char c1 = str1.charAt(j);
                char c2 = str2.charAt(j);
                if (c1 == c2) {
                    result.append(c1);
                    j++;
                } else {
                    break;
                }
            }
            str1 = result.toString();

        }
        return str1;
    }

    /**
     * 最优方案
     *
     * @param strs
     * @return
     */
    public static String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }

        String str1 = strs[0];
        int index = str1.length();

        for (int i = 1; i < strs.length; i++) {
            String str = strs[i];
            int length = str.length();
            boolean isTrue = false;
            while (index > 0) {
                String substring1 = str1.substring(0, index);
                String substring2 = str.substring(0, index > length ? length : index);
                if (substring1.equals(substring2)) {
                    str1 = substring1;
                    index = substring1.length();
                    isTrue = true;
                    break;
                }
                index--;
            }

            if (!isTrue) {
                return "";
            }

        }
        return str1;
    }
}
