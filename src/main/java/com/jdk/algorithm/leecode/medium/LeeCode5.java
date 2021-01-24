package com.jdk.algorithm.leecode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 最长回文子串
 * <p>
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 * <p>
 * 输入: "cbbd"
 * 输出: "bb"
 * @Author: zhangbing
 * @CreateDate: 2020/10/18 2:44 PM
 */
public class LeeCode5 {

    public static void main(String[] args) {
        String str = "abcda";


        System.out.println(longestPalindrome(str));
    }

    public static String longestPalindrome(String s) {

        int length = s.length();
        if (length == 1) return s;
        if (length == 2) {
            return s.charAt(0) == s.charAt(1) ? s : s.substring(0, 1);
        }


        HashMap<Character, List<Integer>> characterHashMap = new HashMap<>();

        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (characterHashMap.containsKey(c)) {
                List<Integer> list = characterHashMap.get(c);
                list.add(list.size() == 0 ? 0 : list.size() - 1, i);
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                characterHashMap.put(c, list);
            }
        }

        if (characterHashMap.size()==1)return s;


        int resultLen = 0;
        String result = null;

        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            List<Integer> list = characterHashMap.get(c);
            if (list.size() == 1) {
                continue;
            } else {
                list.remove(list.indexOf(i));
                for (Integer integer : list) {
                    int i1 = integer - i - 1;

                    if (i1 == 1) i1++;

                    boolean isTure = true;

                    for (int k = 1; k < i1; k++) {
                        if (s.charAt(i + k) != s.charAt(integer - k)) {
                            isTure = false;
                            break;
                        }
                    }
                    if (isTure && integer - i + 1 > resultLen) {
                        resultLen = integer - i + 1;
                        result = s.substring(i, integer + 1);
                    }
                }

            }
        }
        return result==null?s.substring(0, 1):result;
    }


    public static String longestPalindrome2(String s) {

        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        String ans = "";
        for (int l = 0; l < n; ++l) {
            for (int i = 0; i + l < n; ++i) {
                int j = i + l;
                if (l == 0) {
                    dp[i][j] = true;
                } else if (l == 1) {
                    dp[i][j] = (s.charAt(i) == s.charAt(j));
                } else {
                    dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]);
                }
                if (dp[i][j] && l + 1 > ans.length()) {
                    ans = s.substring(i, i + l + 1);
                }
            }
        }
        return ans;
    }
}
