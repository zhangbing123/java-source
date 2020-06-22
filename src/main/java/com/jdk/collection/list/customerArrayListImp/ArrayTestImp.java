package com.jdk.collection.list.customerArrayListImp;

import java.util.Arrays;

/**
 * @Description: 数组集合的自定义实现
 * @Author: zhangbing
 * @CreateDate: 2020/6/21 5:09 PM
 */
public class ArrayTestImp {

    private int size; //数组的长度

    private Object[] elementData = null;//数组对象

    private int index;//已经存储的数据大小

    private Object[] EMPTY_ARRAY = {};//空数组

    private int DEFAULT_SIZE = 10;//数组默认大小

    public ArrayTestImp(int size) {
        this.size = size;
        elementData = new Object[size];
    }

    public ArrayTestImp() {
        elementData = EMPTY_ARRAY;//默认空数组
    }

    public Object get(int index) {
        return elementData[index];
    }

    public Object update(int index, Object o) {
        Object oldValue = elementData[index];
        elementData[index] = o;
        return oldValue;
    }

    /**
     * 指定位置为插入
     *
     * @param local
     * @param o
     */
    public void add(int local, Object o) {

        ifNeedGrow();
        //插入中间位置  则后面的都要向后移动
        for (int i = index - 1; i >= local;i--){
            elementData[i+1] = elementData[i];
        }
        elementData[local] = o;
        index++;
    }

    private void ifNeedGrow() {
        int count = index+1;
        if (count > size) {//如果添加一个元素 超过数组容量 则
            //扩容
            int oldCap = elementData.length;
            int newCap = oldCap + (oldCap >> 1);//新容量=旧容量+（旧容量/2）

            newCap = newCap == 0 ? DEFAULT_SIZE : newCap;//一开始未指定数组长度，则使用默认长度10

            newCap = Math.min(newCap, Integer.MAX_VALUE);//数组最大只能Integer.MAX

            size = newCap;

            if (elementData == EMPTY_ARRAY) {
                elementData = new Object[newCap];//初始化数组
            } else {
                elementData = Arrays.copyOf(elementData, newCap);//数组的copy
            }

        }
    }

    public void add(Object o) {
        ifNeedGrow();
        elementData[index] = o;
        index++;
    }

    public int size(){
        return index;
    }


    public static void main(String[] args) {
        ArrayTestImp arrayTestImp = new ArrayTestImp();

        for (int i = 0; i < 30; i++) {
            arrayTestImp.add(i);
        }

        arrayTestImp.add(3, 101001011);
        System.out.println(arrayTestImp);
        System.out.println(arrayTestImp.size());
    }
}
