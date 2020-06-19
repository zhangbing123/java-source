package main.algorithm;

import java.util.*;

/**
 * @description:
 * @author: zhangbing
 * @create: 2020-06-12 09:53
 **/
public class TestAlgorithm {

    public static void main(String[] args) {

//        Node nodeA1 = new Node(2);
//        Node nodeA2 = new Node(5);
//        nodeA1.next = nodeA2;
//        Node nodeA3 = new Node(3);
//        nodeA2.next = nodeA3;
//
//        Node nodeB1 = new Node(1);
//        Node nodeB2 = new Node(7);
//        nodeB1.next = nodeB2;
//        Node nodeB3 = new Node(6);
//        nodeB2.next = nodeB3;
////        Node nodeA1 = new Node(5);
////        Node nodeB1 = new Node(5);
//
//        Node node = twoNums(nodeA1, nodeB1);
//        System.out.println(node);

        String str = "bpfbhmipx";
        int str1 = str(str);
        System.out.println(str1);
    }

    /**
     * 两数之和
     *
     * @param nodeA1
     * @param nodeB1
     * @return
     */
    public static Node twoNums(Node nodeA1, Node nodeB1) {
        Node nodeA = nodeA1, nodeB = nodeB1, newNode = null, currentNode = null;
        int k = 0;//记录上位的十位数
        while (nodeA != null || nodeB != null || k != 0) {
            int valueA = 0;
            if (nodeA != null) {
                valueA = nodeA.value;
                nodeA = nodeA.next;
            }

            int valueB = 0;
            if (nodeB != null) {
                valueB = nodeB.value;
                nodeB = nodeB.next;
            }

            int value = valueA + valueB + k;
            if (value >= 10) {
                //取余
                value = value % 10;
                k = 1;
            } else {
                k = 0;
            }

            if (newNode == null) {
                newNode = new Node(value);
                currentNode = newNode;
            } else {
                currentNode.next = new Node(value);
                currentNode = currentNode.next;
            }
        }
        return newNode;
    }

    static class Node {
        private int value;

        private Node next;

        private Node pre;

        public Node(int value) {
            this.value = value;
        }

    }


    public static int str(String s) {

        if ("".equals(s)) return 0;
        if (" ".equals(s)) return 1;
        //abcd befgjgjf
        int result = 0;
        int len = 0;//记录每次遍历后的长度
        HashMap<Character, Integer> map = new HashMap<>(s.length());
        int n = s.length();//优化1
        int point = 0;
        int i = 0;
        while (i < n) {
            char str = s.charAt(i);
            if (map.containsKey(str))  {
                //取出重复字符的下标
                Integer integer = map.get(str);
                //移除重复字符前(包括重复字符)的所有数据
                while (point <= integer) {
                    char key = s.charAt(point);
                    map.remove(key);
                    point++;
                }
                len = map.size();
            }
            map.put(str, i);
            len++;
            result = Math.max(len, result);//遇到重复的了，比较结果 取大的值
            i++;

        }
        return result;

    }

    public static int lengthOfLongestSubstring(String s) {

        //abcabcbb
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

}
