package com.zhu.algorithms.kmp;

import java.util.Arrays;

/**
 * @author zpm
 * @version 1.0
 */
public class KMP {
    public static void main(String[] args) {
        String a = "ABCDABD";
        String b = "AA";
        System.out.println(kmpMatch(a, b));
    }

    public static int violenceMatch(String source, String target) {
        char[] c1 = source.toCharArray();
        char[] c2 = target.toCharArray();
        int i = 0;
        int j = 0;
        while (i < c1.length && j < c2.length) {
            if (c1[i] == c2[j]) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        //说明c2已经遍历完了
        if (j == c2.length) {
            return i - j;
        }
        return -1;
    }

    /**
     * 得到kmp匹配表
     *
     * @param target
     * @return
     */
    private static int[] getKmpTab(String target) {
        int[] next = new int[target.length()];
        for (int i = 1, j = 0; i < next.length; i++) {
            while (j > 0 && target.charAt(j) != target.charAt(i)) {
                //看不懂这个
                //解释:从当前位置从后往前找到一个和charAt(i)相等的东西,这样可能不一定比直接让j=0快,但是一定程度上能减少j++的次数
                j = next[j - 1];
            }
            //如果他们相等那就j++
            if (target.charAt(j) == target.charAt(i)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    public static int kmpMatch(String source, String target) {
        int[] kmpTab = getKmpTab(target);
        for (int i = 0, j = 0; i < source.length(); i++) {
            while (j > 0 && source.charAt(i) != target.charAt(j)) {
                j = kmpTab[j - 1];
            }

            if (source.charAt(i) == target.charAt(j)) {
                j++;
            }
            if (j == target.length()) {
                return i - j + 1;
            }
        }

        return -1;
    }
}
