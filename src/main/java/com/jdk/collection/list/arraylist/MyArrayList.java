package com.jdk.collection.list.arraylist;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * @Description: 数组集合的自定义实现
 * @Author: zhangbing
 * @CreateDate: 2020/6/21 5:09 PM
 */
public class MyArrayList<E> implements Iterable<E> {


    private Object[] elementData = null;//数组对象

    private int size;//已经存储的数据大小

    private Object[] EMPTY_ARRAY = {};//空数组

    private int DEFAULT_SIZE = 10;//数组默认大小

    public MyArrayList(int size) {
        elementData = new Object[size];
    }

    public MyArrayList() {
        elementData = EMPTY_ARRAY;//默认空数组
    }

    public E get(int index) {
        return (E) elementData[index];
    }

    public E update(int index, E o) {
        E oldValue = get(index);
        elementData[index] = o;
        return oldValue;
    }

    /**
     * 指定位置为插入
     *
     * @param local
     * @param o
     */
    public void add(int local, E o) {

        ifNeedGrow();
        //插入中间位置  则后面的都要向后移动
        for (int i = size - 1; i >= local; i--) {
            elementData[i + 1] = elementData[i];
        }
        elementData[local] = o;
        size++;
    }

    /**
     * 扩容操作
     */
    private void ifNeedGrow() {
        int count = size + 1;
        if (count > elementData.length) {//如果添加一个元素 超过数组容量 则
            //扩容
            int oldCap = elementData.length;
            int newCap = oldCap + (oldCap >> 1);//新容量=旧容量+（旧容量/2）

            newCap = newCap == 0 ? DEFAULT_SIZE : newCap;//一开始未指定数组长度，则使用默认长度10

            newCap = Math.min(newCap, Integer.MAX_VALUE);//数组最大只能Integer.MAX

            if (elementData == EMPTY_ARRAY) {
                elementData = new Object[newCap];//初始化数组
            } else {
                elementData = Arrays.copyOf(elementData, newCap);//数组的copy
            }

        }
    }

    /**
     * 添加元素
     *
     * @param o
     */
    public void add(E o) {
        ifNeedGrow();
        elementData[size] = o;
        size++;
    }

    /**
     * 获取元素个数
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 删除元素
     *
     * @param index
     * @return
     */
    public E delete(int index) {
        E elementDatum = get(index);

        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException("下标越界");
        }

        int copyNum = size - 1 - index;
        if (copyNum > 0)
            System.arraycopy(elementData, index + 1, elementData, index,
                    copyNum);
        elementData[--size] = null;

        return elementDatum;

    }


    public static void main(String[] args) {
        MyArrayList<Integer> myArrayList = new MyArrayList();

        for (int i = 0; i < 30; i++) {
            myArrayList.add(i);
        }
        myArrayList.add(3, 101001011);//插入指定位置
        myArrayList.delete(3);//删除下标为3的

        System.out.println(myArrayList.size());

//        for (Integer integer : myArrayList) {
//            System.out.println(integer);
//        }

        Iterator<Integer> iterator = myArrayList.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next == 24) {
                iterator.remove();
                continue;
            }
            System.out.println(next);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    /**
     * 迭代器的实现
     */
    class Itr implements Iterator<E> {

        int nextIndex = 0;
        int currentIndex = -1;

        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public E next() {
            if (nextIndex >= size) {
                throw new RuntimeException("下标越界");
            }

            currentIndex = nextIndex;
            nextIndex++;
            return (E) elementData[currentIndex];
        }

        @Override
        public void remove() {

            delete(currentIndex);
            nextIndex--;
            currentIndex = -1;
        }

        @Override
        public void forEachRemaining(Consumer action) {

        }
    }

    @Override
    public void forEach(Consumer action) {

    }
}
