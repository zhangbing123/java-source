package com.jdk.collection.list.customerLinkedListImp;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Consumer;

/**

* @Description:    自定义LinkedList实现

* @Author:         zhangbing

* @CreateDate:     2020/7/12 4:56 PM

*/
public class LinkedListImp<T> implements Iterable<T> {

    private Node head;//头节点
    private Node tail;//尾节点
    private int size;//存储数据的量

    public LinkedListImp() {
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super T> action) {

    }


    /**
     * 链表节点对象
     */
    class Node<T> {
        private T data;//链表节点数据
        private Node pre;//前一个节点
        private Node next;//后一个节点

        public Node(T data, Node pre, Node next) {
            this.data = data;
            this.pre = pre;
            this.next = next;
        }
    }

    public boolean add(Object o) {

        if (head == null) {//链表为空
            Node node = new Node(o, null, null);
            head = node;
            tail = node;
        } else {//链表不为空 插入尾部
            Node last = tail;
            Node node = new Node(o, last, null);//新节点的前一个node设置为尾节点
            last.next = node;//尾部节点的next指向新节点
            tail = node;//尾部节点指针指向新节点
        }
        size++;
        return true;

    }

    public void add(int index, Object o) {

        if (!checkIndex(index)) {
            throw new RuntimeException("下标越界");
        }

        if (index == size) {//追加到尾部
            add(o);
        } else {
            Node nextNode = find(index);//找到要插入的下标位置的node
            Node node = new Node(o, null, nextNode);//新节点并指向后一个节点为oldNode

            Node preNode = nextNode.pre;//当前下标位置的老得节点  的前一个节点

            if (preNode != null) {
                preNode.next = node;//节点的next指针指向新插入节点
            }
            node.pre = preNode;//新节点的前一个节点指向nextNode的前一个节点

            nextNode.pre = node;//nextNode的前一个节点指向新节点

            if (index == 0) {//如果插入到头部  则head指针指向新节点
                head = node;
            }
            size++;
        }
    }

    /**
     * 校验索引下标的位置是否合法
     *
     * @param index
     * @return
     */
    private boolean checkIndex(int index) {

        return index >= 0 && index <= size;
    }

    public void remove(Object o) {
        Node node = head;
        Node preNode = null;
        Node nextNode = null;

        do {

            if (!node.data.equals(o)) {
                continue;
            }

            if (node.pre == null && node.next != null) {//删除的是头节点
                removeHead();
            } else if (node.pre != null && node.next != null) {//删除的是中间节点
                preNode = node.pre;//删除节点的前一个节点
                nextNode = node.next;//删除节点的后一个节点
                preNode.next = nextNode;//前一个节点的next指针指向后一个节点
                nextNode.pre = preNode;//后一个节点的pre指针指向前一个节点
                size--;//数量-1
            } else if (node.pre != null && node.next == null) {//删除尾部节点
                removeTail();
            }
            break;
        } while ((node = node.next) != null);
    }

    public void removeHead() {

        if (head == tail) {
            head = null;
            tail = null;
        } else {
            Node nextNode = head.next;
            nextNode.pre = null;//删除的头节点，把头节点的下一个节点的pre指向null
            head = nextNode;//head指针指向删除节点的下一个节点
        }
        size--;

    }

    public void removeTail() {
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            Node preNode = tail.pre;//尾部节点的前一个节点
            preNode.next = null;//前一个节点的next指针指向null
            tail = preNode;//尾部节点指针指向preNode节点
        }
        size--;
    }

    public Object set(int index, Object o) {

        if (!checkIndex(index)) {
            throw new RuntimeException("下标越界");
        }

        Node oldNode = find(index);
        Object old = oldNode.data;
        oldNode.data = o;
        return old;
    }

    public Object get(int index) {

        if (!checkIndex(index)) {
            throw new RuntimeException("下标越界");
        }

        return find(index).data;
    }

    private Node find(int index) {

        Node currentNode = null;
        if (index <= (size >> 1)) {//下标小于 总数的一半 则开始从头部开始遍历
            currentNode = head;//初始值为头节点
            for (int i = 0; i < index; i++) {//从头部节点开始遍历 直至指定下标的前一个节点
                currentNode = currentNode.next;// 前一个节点的next指针指向的就是自己要找的节点
            }
        } else {//索引下标位置在链表的后半段
            currentNode = tail;//初始值为尾节点
            for (int i = (size - 1); i > index; i--) {//从尾节点开始向前遍历。直至指定下标的后一个节点
                currentNode = currentNode.pre;//后一个节点的pre指针指向的就是自己要找到的节点
            }

        }
        return currentNode;
    }

    public int getSize(){
        return size;
    }


    /**
     * test
     * @param args
     */
    public static void main(String[] args) {
        LinkedListImp linkedListImp = new LinkedListImp();
        linkedListImp.add(1);
        linkedListImp.add(2);
        linkedListImp.add(3);
        linkedListImp.add(4);
        linkedListImp.add(0, 5);
        linkedListImp.add(3, 7);
        linkedListImp.add(linkedListImp.getSize(), 6);

        linkedListImp.remove(2);
        linkedListImp.set(linkedListImp.getSize(), 9);
        linkedListImp.set(linkedListImp.getSize() - 1, 10);



        Object o1 = linkedListImp.get(0);
        Object o2 = linkedListImp.get(2);
        Object o3 = linkedListImp.get(linkedListImp.getSize());
        Object o4 = linkedListImp.get(linkedListImp.getSize() - 1);
        System.out.println(o1);
        System.out.println(o2);
        System.out.println(o3);
        System.out.println(o4);

    }
}
