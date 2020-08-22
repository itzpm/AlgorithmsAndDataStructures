package com.zhu;

import com.zhu.huffman.HuffmanTree;

import java.util.Arrays;

/**
 * @author zpm
 * @version 1.0
 */
public class Test {
    public static void main(String[] args) {
        String str = "i like like like java do you like a java ?";
        byte[] bs = HuffmanTree.zip(str);
        System.out.println(Arrays.toString(bs));
        System.out.println(HuffmanTree.decode(bs));
        //110
        //101
        //1011
        //00000010
        //01111111 11111111 11111111 11111110
        //00000000 00000000 00000000 11111111
        //                           00000010
        //                         1 00000000
        //                         1 00000010

        String s = Integer.toBinaryString((78 & 0xff));
        System.out.println(s);
    }
}
