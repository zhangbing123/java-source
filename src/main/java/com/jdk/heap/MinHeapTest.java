package com.jdk.heap;

/**
 * @description: 、
 * @author: zhangbing
 * @create: 2020-09-01 11:47
 **/
public class MinHeapTest {

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap(10);
        System.out.println("数量："+minHeap.nums.length);
        minHeap.siftUp(2);
        minHeap.siftUp(5);
        minHeap.siftUp(3);
        minHeap.siftUp(7);
        minHeap.siftUp(9);
        minHeap.siftUp(1);
        minHeap.siftUp(4);
        minHeap.siftUp(6);
        minHeap.siftUp(10);
        minHeap.siftUp(23);
        minHeap.siftUp(22);
        minHeap.siftUp(18);

//        int i = minHeap.siftDown();
//        while (true){
        System.out.println("数量："+minHeap.nums.length);
            System.out.println(minHeap.siftDown());
        System.out.println("数量："+minHeap.nums.length);
            System.out.println(minHeap.siftDown());
        System.out.println("数量："+minHeap.nums.length);
            System.out.println(minHeap.siftDown());
        System.out.println("数量："+minHeap.nums.length);
            System.out.println(minHeap.siftDown());
        System.out.println("数量："+minHeap.nums.length);
            System.out.println(minHeap.siftDown());
        System.out.println("数量："+minHeap.nums.length);
            System.out.println(minHeap.siftDown());
        minHeap.siftUp(18);
        System.out.println("数量："+minHeap.nums.length);

            System.out.println(minHeap.siftDown());
            System.out.println(minHeap.siftDown());
            System.out.println(minHeap.siftDown());
            System.out.println(minHeap.siftDown());
            System.out.println(minHeap.siftDown());
        System.out.println("数量："+minHeap.nums.length);
            System.out.println(minHeap.siftDown());
            System.out.println(minHeap.siftDown());
//        }
    }
}
