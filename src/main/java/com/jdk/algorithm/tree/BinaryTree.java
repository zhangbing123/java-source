package com.jdk.algorithm.tree;

import java.util.*;

/**
 * @description: 二叉树
 * @author: zhangbing
 * @create: 2021-01-21 14:14
 **/
public class BinaryTree {

    public static void main(String[] args) {

        Node node = creact();

        System.out.println("=========前序=========");
        preOrder(node);

        System.out.println("=========中序=========");
        middleOrder(node);

        System.out.println("=========后序=========");
        postOrder(node);

        System.out.println("==========每层从左向右遍历==============");
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        while (queue.size() != 0) {

            Node n = queue.poll();
            System.out.println(n.val);

            if (n.left != null) {
                queue.add(n.left);
            }
            if (n.right != null) {
                queue.add(n.right);
            }
        }


    }

    private static void postOrder(Node node) {
        if (node==null) return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.val);
    }


    private static void middleOrder(Node node) {
        if (node==null){
            return;
        }

        middleOrder(node.left);
        System.out.println(node.val);
        middleOrder(node.right);
    }

    public static void preOrder(Node root) {
        if (root == null) {
            return;
        }

        System.out.println(root.val);
        preOrder(root.left);
        preOrder(root.right);


    }


    public static Node creact() {
        Node a = new Node(1);
        Node b = new Node(2);
        Node c = new Node(3);
        Node d = new Node(4);
        Node e = new Node(5);
        Node f = new Node(6);
        Node i = new Node(7);
        Node j = new Node(8);
        Node k = new Node(9);
        Node l = new Node(10);
        Node g = new Node(11);
        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        c.left = f;
        c.right = i;
        d.left = j;
        d.right = k;
        e.right = l;
        e.left = g;
        return a;
    }
}
