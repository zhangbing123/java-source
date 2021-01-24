package com.jdk.algorithm.leecode.difficulty;

/**
 * @description: 找到两个数组的中位数
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。
 *
 * 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
 *
 * 示例 1：
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 *
 * 示例 2：
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 *
 * 示例 3：
 * 输入：nums1 = [0,0], nums2 = [0,0]
 * 输出：0.00000
 *
 * 示例 4：
 * 输入：nums1 = [], nums2 = [1]
 * 输出：1.00000
 *
 * 示例 5：
 * 输入：nums1 = [2], nums2 = []
 * 输出：2.00000
 *
 * @author: zhangbing
 * @create: 2020-07-07 11:38
 **/
public class LeeCode4 {

    public static void main(String[] args) {

        int[] nums1 = new int[]{1,3};
        int[] nums2 = new int[]{2};

        double v = calMedian(nums1, nums2);
        System.out.println(v);
    }

    /**
     * 第一种算法 时间复杂度O(n)
     * @param nums1
     * @param nums2
     * @return
     */
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
            while (count < (len1 + len2)) {//时间复杂度为 O(n)

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


    /**
     * 第二种算法  时间复杂度O(logn)
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length, length2 = nums2.length;
        int totalLength = length1 + length2;
        if (totalLength % 2 == 1) {//奇数个总元素
            int midIndex = totalLength / 2;
            double median = getKthElement(nums1, nums2, midIndex + 1);
            return median;
        } else {//偶数个总元素
            int midIndex1 = totalLength / 2 - 1, midIndex2 = totalLength / 2;
            double median = (getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(nums1, nums2, midIndex2 + 1)) / 2.0;
            return median;
        }
    }


    /**
     * 寻找第k小数算法
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public int getKthElement(int[] nums1, int[] nums2, int k) {
        /* 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
         * 这里的 "/" 表示整除
         * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
         * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
         * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
         * 这样 pivot 本身最大也只能是第 k-1 小的元素
         * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
         * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
         * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
         */

        int length1 = nums1.length, length2 = nums2.length;
        int index1 = 0, index2 = 0;
        int kthElement = 0;

        while (true) {
            // 边界情况
            if (index1 == length1) {
                return nums2[index2 + k - 1];
            }
            if (index2 == length2) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }

            // 正常情况
            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, length1) - 1;
            int newIndex2 = Math.min(index2 + half, length2) - 1;
            int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
            if (pivot1 <= pivot2) {
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }
}
