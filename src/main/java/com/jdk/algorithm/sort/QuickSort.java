package com.jdk.algorithm.sort;

/**
 * @description: 快速排序
 * @author: zhangbing
 * @create: 2021-01-21 18:04
 **/
public class QuickSort {

    public static void main(String[] args) {
        int[] array = {6, 3, 2, 1, 5, 2, 6, 1, 7, 4, 23, 14, 56, 12, 6, 8, 10};

        quickSort(array, 0, array.length - 1);
        for (int i : array) {
            System.out.println(i);
        }
    }

    public static void quickSort(int[] array, int low, int hight) {
        if (low > hight) {
            return;
        }

        int tmp = array[low];//基准数
        int i = low;
        int j = hight;

        while (i < j) {

            while (array[j] >= tmp && i < j) {//向左遍历  直到找到 小于tmp的元素
                j--;
            }

            while (array[i] <= tmp && i < j) {//向右遍历  直到找到 大于tmp的元素
                i++;
            }

            //交换元素
            int x = array[i];
            array[i] = array[j];
            array[j] = x;
        }

        //跳出循环  说明i==j了 与基准元素交换位置
        array[low] = array[j];
        array[j] = tmp;

        quickSort(array, low, j - 1);
        quickSort(array, j + 1, hight);
    }
}
