package com.jdk.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @description:
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
}
