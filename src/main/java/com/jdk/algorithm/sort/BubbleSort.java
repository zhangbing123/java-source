package com.jdk.algorithm.sort;

/**
 * @description: 冒泡排序
 * @author: zhangbing
 * @create: 2021-01-14 15:20
 **/
public class BubbleSort {

    /**
     * 时间复杂度O(n^2)
     * 使用数据量较少的排序
     * <p>
     * 从第一个元素开始  与后一个元素比较  大的移到后面
     * 依次类推
     * 当index-1 与 index位置的元素比较完后  这一轮 最大的元素就被移动到最后面了
     * <p>
     * 然后找出第二大的元素
     * 通过不断遍历上面的操作
     * 遍历length遍就ok了
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] array = {2, 3, 6, 1, 5, 9, 13, 7, 4, 11, 14, 12, 8, 10};

        /**
         * 正序
         */
        int i = array.length - 1;
        while (i >= 0) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = tmp;
                }
            }
            i--;
        }

        /**
         * 倒序
         */
//        int i = 0;
//        while (i < array.length) {
//            for (int j = array.length - 1; j > i; j--) {
//                if (array[j] > array[j - 1]) {
//                    int tmp = array[j - 1];
//                    array[j - 1] = array[j];
//                    array[j] = tmp;
//                }
//            }
//            i++;
//        }

        for (int k : array) {
            System.out.println(k);
        }


    }
}
