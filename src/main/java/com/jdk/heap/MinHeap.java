package com.jdk.heap;

/**
 * @description: 最小堆实现
 * @author: zhangbing
 * @create: 2020-09-01 11:46
 **/
public class MinHeap {

    public int[] nums;
    private int pointer;

    public MinHeap(int capacity) {
        nums = new int[capacity + 1];
        pointer = 1;
    }

    public void siftUp(int newVal) {
        if (pointer < 0 || nums == null || pointer >= nums.length) return;

        nums[pointer] = newVal;
        int i = pointer, p;
        pointer ++;

        while (true) {
            if (i == 1) break;
            p = i / 2;
            if (nums[p] <= nums[i]) break;
            swap(p, i);
            i = p;
        }
    }

    /*
        1. 左子节点如果不存在，break
        2. 如果存在右子节点，判断两个子节点的大小，取小的一个
        3. 比较nums[i]和最小字节点的大小，小则break，大则替换
     */
    public int siftDown() {
        if (pointer <= 0 || nums == null || pointer > nums.length) return -1;
        int res = nums[1];
        nums[1] = nums[pointer - 1];
        int i = 1, child;
        pointer --;
        while (true) {
            child = 2 * i;
            if  (child >= pointer) break;
            if (child + 1 < pointer) {
                if (nums[child] > nums[child + 1]) child ++;
            }
            if (nums[i] <= nums[child]) break;
            swap(i, child);
            i = child;
        }
        return res;
    }

    private void swap(int i, int p) {
        if (i < 0 || p < 0 || nums == null || i >= nums.length || p >= nums.length) return;
        int tmp = nums[i];
        nums[i] = nums[p];
        nums[p] = tmp;
    }
}
