package com.jdk.algorithm;

/**
 * @description: 选择排序
 * @author: zhangbing
 * @create: 2021-01-14 15:48
 **/
public class SelectSort {

    /**
     * 时间复杂度O(n^2)
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] array = {2, 3, 6, 1, 5, 9, 13, 7, 4, 11, 14, 12, 8, 10};

        for (int i = 0; i < array.length - 1; i++) {

            int min = array[i];
            int minIndex = i;

            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < min) {
                    min = array[j];
                    minIndex = j;
                }
            }

            if (i != minIndex) {//说明最小的值发生了变化
                array[minIndex] = array[i];
                array[i] = min;
            }

        }

        for (int i : array) {
            System.out.println(i);
        }
    }
}
