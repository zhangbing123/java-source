package main.jvm.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试堆内存
 */
public class HeapTest {

    public static void main(String[] args) {

        //测试heap内存 -Xms10M：堆内存10M -Xmx10M最大堆内存10M -Xmn2M：新生代内存配置为2M
        List<HeapTest> heapTestList = new ArrayList<>();

        while (true){
            heapTestList.add(new HeapTest());
//            heapTestList.size();
            try {
                Thread.currentThread().sleep(100);
                System.out.println("==="+heapTestList.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
