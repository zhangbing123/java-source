package com.jdk.algorithm;

/**
 * @description: 插入排序算法
 * @author: zhangbing
 * @create: 2021-01-14 14:53
 **/
public class InsertionSort {

    /**
     * 时间复杂度 O(n^2)
     * 适合数据量比较小的场景
     * @param args
     */
    public static void main(String[] args) {

        int[] array = {2, 3, 6, 1, 5, 2, 6, 1, 7, 4, 23, 14, 56, 12, 6, 8, 10};

        int tmp;

        for (int i = 1; i < array.length; i++) {
            tmp = array[i];
            int j = i;
            for (; j > 0 && array[j - 1] > tmp; j--) {
                array[j] = array[j - 1];
                array[j - 1] = tmp;
            }
        }

        for (int i : array) {
            System.out.println(i);
        }


    }
}
