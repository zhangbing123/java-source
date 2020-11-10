package com.jdk.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @description:
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 例如
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 *
 * @author: zhangbing
 * @create: 2020-06-12 09:53
 **/
public class TestAlgorithm {

    public static void main(String[] args) {
        int [] nums = {3, 3, 11, 15};
        int target = 9;
        int[] ints = twoSum(nums, target);
        for (int anInt : ints) {
            System.out.printf(""+anInt);
        }


    }

    /**
     * 效率较高的方式
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap();

        for(int i=0;i<nums.length;i++){
            map.put(nums[i],i);
        }

        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        for (Map.Entry<Integer, Integer> entry : entries) {
            Integer key = entry.getKey();
            if (map.containsKey(target-key)){
                return new int[]{entry.getValue(),map.get(target-key)};
            }
        }
        return null;
    }

    /**
     * 两层循环  效率较低
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        for(int i=0;i<nums.length-1;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]+nums[j]==target){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }
}
