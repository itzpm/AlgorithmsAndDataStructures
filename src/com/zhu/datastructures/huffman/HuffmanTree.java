package com.zhu.datastructures.huffman;

import java.util.*;

/**
 * @author zpm
 * @version 1.0
 */
public class HuffmanTree {
    static Map<Character, String> map = new HashMap<>(64);
    static StringBuilder builder = new StringBuilder();
    static HuffmanNode root;
    static String endCode;

    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
        System.out.println(Arrays.toString(zip(str)));
    }

    private static String getBinaryStr(byte b) {
        //将b转为int
        int temp = b;
        temp |= 256;
        String str = Integer.toBinaryString(temp);
        return str.substring(str.length() - 8);
    }


    public static String decode(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        String binaryStr;
        for (int i = 0; i < bytes.length; i++) {
            boolean flag = (i == bytes.length - 1);
            if (flag) {
                binaryStr = endCode;
            } else {
                binaryStr = getBinaryStr(bytes[i]);
            }
            builder.append(binaryStr);
        }
        String st = builder.toString();
        builder.delete(0, builder.length());
        Map<String, Character> reverse = new HashMap<>(128);
        for (Map.Entry<Character, String> m : map.entrySet()) {
            reverse.put(m.getValue(), m.getKey());
        }
        for (int i = 0; i < st.length(); i++) {
            int count = 1;
            boolean flag = true;
            Character character;
            while (flag) {
                String str = st.substring(i, i + count);
                character = reverse.get(str);
                if (character == null) {
                    count++;
                } else {
                    flag = false;
                    i = i + count - 1;
                    builder.append(character);
                }
            }
        }
        return builder.toString();
    }


    public static byte[] zip(String str) {
        String encode = encode(str);
        int len;
        if (encode.length() % 8 == 0) {
            len = encode.length() / 8;
        } else {
            len = encode.length() / 8 + 1;
        }
        byte[] huffman = new byte[len];
        int index = 0;
        for (int i = 0; i < encode.length(); i += 8) {
            String s;
            if (i + 8 > encode.length()) {
                s = encode.substring(i);
            } else {
                s = encode.substring(i, i + 8);
            }
            huffman[index++] = (byte) Integer.parseInt(s, 2);
            if (index == huffman.length) {
                endCode = s;
            }
        }
        return huffman;
    }

    private static String encode(String str) {
        root = createHuffman(str);
        getHuffmanCode(root, "", builder);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            String s = map.get(str.charAt(i));
            builder.append(s);
        }
        return builder.toString();
    }

    private static void preOrder(HuffmanNode head) {
        if (head == null) {
            return;
        }
        System.out.print(head.weight + " ");
        preOrder(head.left);
        preOrder(head.right);
    }

    private static void getHuffmanCode(HuffmanNode node, String code, StringBuilder sbuilder) {
        StringBuilder builder1 = new StringBuilder(sbuilder);
        builder1.append(code);
        if (node != null) {
            if (node.data == null) {
                getHuffmanCode(node.left, "0", builder1);
                getHuffmanCode(node.right, "1", builder1);
            } else {
                map.put(node.data, builder1.toString());
            }
        }
    }

    private static Map<Character, Integer> getCharacter(String str) {
        Map<Character, Integer> map = new HashMap<>(128);
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            map.put(c, map.containsKey(c) ? map.get(c) + 1 : 1);
        }
        return map;
    }

    private static List<HuffmanNode> getList(Map<Character, Integer> map) {
        List<HuffmanNode> list = new ArrayList<>();
        map.forEach((data, weight) -> list.add(new HuffmanNode(data, weight)));
        return list;
    }

    private static HuffmanNode createHuffman(String str) {
        List<HuffmanNode> list = getList(getCharacter(str));
        while (list.size() > 1) {
            Collections.sort(list);
            HuffmanNode leftNode = list.remove(0);
            HuffmanNode rightNode = list.remove(0);
            HuffmanNode parentNode = new HuffmanNode(null, leftNode.weight + rightNode.weight);
            parentNode.left = leftNode;
            parentNode.right = rightNode;
            list.add(parentNode);
        }
        return list.remove(0);
    }

    private static Node createHuffman(int[] arr) {
        if (arr == null) {
            return null;
        }
        if (arr.length == 1) {
            return new Node(arr[0]);
        }
        List<Node> nodeList = new ArrayList<>();
        for (int num : arr) {
            nodeList.add(new Node(num));
        }
        while (nodeList.size() > 1) {
            Collections.sort(nodeList);
            Node leftNode = nodeList.remove(0);
            Node rightNode = nodeList.remove(0);
            Node parentNode = new Node(leftNode.data + rightNode.data);
            parentNode.left = leftNode;
            parentNode.right = rightNode;
            nodeList.add(parentNode);
        }
        return nodeList.remove(0);
    }

    private static void preOrder(Node head) {
        if (head == null) {
            return;
        }
        System.out.println(head.data);
        preOrder(head.left);
        preOrder(head.right);
    }

    private static int height(Node node) {
        int l;
        int r;
        if (node == null) {
            return 0;
        }
        l = height(node.left) + 1;
        r = height(node.right) + 1;
//        if (l < r) {
//            l = r;
//        }
//        return l;
        return Math.max(l, r);
    }

    private static int getNodeNum(Node node) {
        if (node == null) {
            return 0;
        }
        return getNodeNum(node.left) + getNodeNum(node.right) + 1;
    }

    static class Node implements Comparable<Node> {
        private int data;
        private Node left;
        private Node right;

        public Node() {
        }

        public Node(int data) {
            this.data = data;
        }

        @Override
        public int compareTo(Node node) {
            return this.data - node.data;
        }

        @Override
        public String toString() {
            return data + " ";
        }
    }

    static class HuffmanNode implements Comparable<HuffmanNode> {

        Character data;
        int weight;
        HuffmanNode left;
        HuffmanNode right;

        public HuffmanNode(Character data, int weight) {
            this.data = data;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "HuffmanNode{" +
                    "data='" + data +
                    "', weight=" + weight +
                    '}';
        }

        @Override
        public int compareTo(HuffmanNode o) {
            return weight - o.weight;
        }
    }
}
