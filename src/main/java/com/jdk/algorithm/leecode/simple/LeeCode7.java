package com.jdk.algorithm.leecode.simple;

/**
 * @description: 整数翻转  简单  力扣 7题
 * @author: zhangbing
 * @create: 2021-01-22 15:47
 **/
public class LeeCode7 {

    public static void main(String[] args) {

        System.out.println(reverse(1534236469));
        System.out.println(reverse2(121));
        System.out.println(isPalindrome(121));
        System.out.println(isPalindrome2(121));

    }

    public static int reverse(int x) {
        boolean isFlage = x < 0;
        String s = String.valueOf(x);
        s = s.replace("-", "");
        char[] chars = s.toCharArray();
        int maxIndex = chars.length - 1;
        for (int i = maxIndex; i >= 0; i--) {
            int replaceIndex = maxIndex - i;
            if (i > replaceIndex) {
                char aChar = chars[i];
                chars[i] = chars[replaceIndex];
                chars[replaceIndex] = aChar;
            } else {
                break;
            }
        }
        Long result = Long.valueOf(new String(chars));
        double pow = Math.pow(2, 31);
        if (-pow <= result && result <= pow - 1) {
            int num = result.intValue();
            return isFlage ? -num : num;
        }
        return 0;
    }

    public static int reverse2(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }

    /**
     * 判断数字是否是回文数字  12321    翻过来 还是  12321
     *
     * @param x
     * @return
     */
    public static boolean isPalindrome(int x) {
        int y = x;
        Long rev = 0L;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            rev = rev * 10 + pop;
        }
        return rev.equals(new Long(y));
    }

    public static boolean isPalindrome2(int x) {

        if (x < 0 || (x > 0 && x % 10 == 0)) {//当x<0 肯定不是回文数字  x>0 且 个位数为0 也不是回文 因为 高位不可能是0  120  1210 230等等
            return false;
        }

        int result = 0;

        while (x > result) {

            result = result * 10 + x % 10;
            x /= 10;

        }

        return x == result || x == result / 10;
    }
}
