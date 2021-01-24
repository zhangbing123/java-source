package com.jdk.algorithm.leecode.medium;

/**
 * @Description: 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * @Author: zhangbing
 * @CreateDate: 2020/10/17 10:29 PM
 */
public class LeeCode3 {

    public static void main(String[] args) {

        System.out.println("最长的长度：" + test("pwwkew"));
    }

    public static int test(String s) {

        int length = s.length();

        int result = 0;
        String resultSubChar = null;

        StringBuilder subChar = new StringBuilder();//子串

        for (int i = 0; i < length;) {

            char c = s.charAt(i);

            Integer index = subChar.indexOf(String.valueOf(c));//获取重复字符的索引

            if (index >= 0) {
                int subLength = subChar.length();
                if (result < subLength) {
                    result = subLength;
                    resultSubChar = subChar.toString();
                }
                subChar = new StringBuilder(subChar.substring(index+1));//删除重复字符前面的所有字符

            }
            subChar.append(c);
            i++;
            if (i == length && result < subChar.length()) {
                result = subChar.length();
                resultSubChar = subChar.toString();
            }

        }
        System.out.println("最长不重复子串：" + resultSubChar);
        return result;
    }
}
