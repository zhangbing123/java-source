package com.jdk.algorithm;

/**
 * @description: 找到两个数组的中位数
 * @author: zhangbing
 * @create: 2020-07-07 11:38
 **/
public class ArrayMedian {

    public static void main(String[] args) {

        int[] nums1 = new int[]{1,3};
        int[] nums2 = new int[]{2};

        double v = calMedian(nums1, nums2);
        System.out.println(v);
    }

    private static double calMedian(int[] nums1, int[] nums2) {


        int len1 = nums1.length;
        int len2 = nums2.length;
        int nums[] = new int[len1 + len2];
        if (len1 == 0) {
            nums = nums2;
        } else if (len2 == 0) {
            nums = nums1;
        } else {

            int point1 = 0;//指向数组1的下标
            int point2 = 0;//指向数组2的下标

            int count = 0;
            while (count < (len1 + len2)) {

                if (point1 == len1) {
                    while (point2 < len2){
                        nums[count++] = nums2[point2++];
                    }
                    break;
                }
                if (point2 == len2) {
                    while (point1 < len1){
                        nums[count++] = nums1[point1++];
                    }
                    break;
                }


                if (nums1[point1] <= nums2[point2]) {

                    nums[count++] = nums1[point1++];

                } else {
                    nums[count++] = nums2[point2++];
                }
            }


        }

        int length = nums.length;
        if (length > 0) {
            if (length % 2 == 0) {
                //偶数 取两数之和
                int a = nums[(length / 2) - 1];
                int b = nums[length / 2];
                return (double) (a + b) / 2;
            } else {
                //奇数 取中间的数
                return nums[((length + 1) / 2) - 1];
            }
        }
        return 0;

    }
}
