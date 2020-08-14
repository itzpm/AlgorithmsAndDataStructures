package com.zhu.day05;

import java.util.Arrays;

/**
 * @author zpm
 * @version 1.0
 */
public class FibonacciSearch {
    public final static int MAXSIZE = 20;

    /**
     * 斐波那契数列
     *
     * @return
     */
    public static int[] fibonacci() {
        int[] f = new int[MAXSIZE];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < MAXSIZE; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    public static int fibonacciSearch2(int[] data, int key) {
        int mid;
        int k = 0;
        int low = 0;
        int high = data.length - 1;

        int[] f = fibonacci();
        while (high > f[k] - 1) {
            k++;
        }

        int[] temp = Arrays.copyOf(data, f[k]);
        for (int i = high; i < temp.length; i++) {
            temp[i] = data[high];
        }
        System.out.println(temp.length);

        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (temp[mid] < key) {
                //说明key在后半段 f(k-2)
                low = mid + 1;
                //将k定位到k-2区域
                k -= 2;
            } else if (temp[mid] > key) {
                //说明key在f(k-1)区域
                high = mid - 1;
                //将k定位到k-1区域
                k--;
            } else {
                //说明找到了但是要判断是否在填冲区域
                return Math.min(mid, high);
            }
        }
        return -1;
    }


    public static int fibonacciSearch(int[] data, int key) {
        int low = 0;
        int high = data.length - 1;
        int mid = 0;

        //斐波那契分割数值下标
        int k = 0;

        // 获取斐波那契数列
        int[] f = fibonacci();

        //从斐波拉契数列种找到第一个大于等于数组长度的k的下标
        while (data.length > f[k] - 1) {
            k++;
        }

        //创建临时数组
        int[] temp = new int[f[k]];
        System.arraycopy(data, 0, temp, 0, data.length);

        //序列补充至f[k]个元素,为什么要补充
        //因为为了迎合斐波拉契数列 方便把前半段分为f(k-1) 后半段分为 f(k-2)让他满足黄金分割比例,
        //如果没有看明白为什么非要分为f(k-1)和f(k-2) 举个简单的例子,假如要查找的key在前半段f(k-1)中我们就可以继续分为下一个 f(k-1)和f(k-2)如果不这样分那他就无法满足fblq
        //而且要补充高位,因为原数组是有序的并且我们总不能随便添加数列中没有的数字吧
        for (int i = data.length; i < f[k]; i++) {
            temp[i] = temp[high];
        }

        for (int j : temp) {
            System.out.print(j + " ");
        }
        System.out.println();
        System.out.println(temp.length);

        while (low <= high) {
            // low：起始位置
            // 前半部分有f[k-1]个元素，由于下标从0开始
            // 所以-1 获取数组中黄金分割位置元素的下标
            // 加上low是因为它每次找到的只是在给定范围中的黄金分割位置,需要加上low才能得到数组真实位置
            // exp: 5 = 3 + 2  mid = 0 + 3 -1 第三个为mid
            mid = low + f[k - 1] - 1;

            if (temp[mid] > key) {
                // 查找前半部分，高位指针移动
                high = mid - 1;
                // （全部元素） = （前半部分）+（后半部分）
                //   f[k]  =  f[k-1] + f[k-1]
                // 因为前半部分有f[k-1]个元素，所以 k = k-1
                k--;
            } else if (temp[mid] < key) {
                // 查找后半部分，高位指针移动
                low = mid + 1;
                // （全部元素） = （前半部分）+（后半部分）
                //   f[k]  =  f[k-1] + f[k-1]
                // 因为后半部分有f[k-2]个元素，所以 k = k-2
                k = k - 2;
            } else {
                //而补充的元素与high位置的元素一样,有可能会查找到补充的元素那里
                //如果查找到的是补充的元素那里我们只需要拿到最后原来数组的最后一个元素即可
                return Math.min(mid, high);
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        //
        //
        System.out.println();
        int[] f = fibonacci();
        for (int i : f) {
            System.out.print(i + " ");
        }
        System.out.println();

        int[] data = new int[100];
        for (int i = 0; i < 100; i++) {
            data[i] = i;
        }

        int search = 51;
        int position = fibonacciSearch2(data, search);
        System.out.println("值" + search + "的元素位置为：" + position);
    }
}
