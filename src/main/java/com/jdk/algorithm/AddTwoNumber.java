package com.jdk.algorithm;

/**
 * @Description: 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * @Author: zhangbing
 * @CreateDate: 2020/10/17 9:43 PM
 */
public class AddTwoNumber {


    public static void main(String[] args) {

        ListNode node1 = new ListNode(2, new ListNode(4, new ListNode(3, null)));
        ListNode node2 = new ListNode(5, new ListNode(6, new ListNode(4, null)));
        ListNode node = addTwoNum(node1, node2);
        System.out.println("结果：" + node);

    }


    public static ListNode addTwoNum(ListNode l1, ListNode l2) {

        ListNode resultNode = null;
        ListNode preNode = null;

        ListNode currNode1 = l1;
        ListNode currNode2 = l2;

        boolean isIn = false;

        while (true) {

            int v1 = 0;

            if (currNode1 != null) {
                v1 = currNode1.val;
                currNode1 = currNode1.next;
            }

            int v2 = 0;
            if (currNode2 != null) {
                v2 = currNode2.val;
                currNode2 = currNode2.next;
            }

            int sumNum = v1 + v2;

            if (isIn) sumNum++;//上此需要进位 则结果+1

            if (sumNum >= 10) {
                isIn = true;
                sumNum = sumNum % 10;//取余
            }else {
                isIn = false;//没有超过10 则不进位
            }


            if (resultNode == null) {
                resultNode = new ListNode(sumNum);
                preNode =resultNode;
            } else {
                preNode.next = new ListNode(sumNum);
                preNode = preNode.next;
            }

            if (currNode1 == null && currNode2 == null & !isIn) break;
        }

        return resultNode;
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(val).append("->");
            ListNode current = next;

            while (current != null) {
                stringBuilder.append(current.val).append("->");
                current = current.next;
            }

            return stringBuilder.toString();
        }
    }


}
