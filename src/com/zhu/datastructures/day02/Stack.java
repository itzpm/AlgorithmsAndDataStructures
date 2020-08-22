package com.zhu.datastructures.day02;

import java.util.Arrays;

/**
 * @author ZPM
 * @version 1.0
 */
public class Stack<T> {
    private final T[] arr;
    private int top = -1;

    public Stack(int maxSize) {
        arr = (T[]) new Object[maxSize];
    }

    public Stack() {
        this(5);
    }

    public void push(T data) {
        if (isFull()) {
            throw new RuntimeException("栈已经满了不能再加入数据: " + data);
        }
        arr[++top] = data;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == arr.length - 1;
    }

    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("栈已经空了");
        }
        return arr[top];
    }

    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈已经空了");
        }
        return arr[top--];
    }

    public int priority(char operation) {
        if (operation == '+' || operation == '-') {
            return 0;
        } else if (operation == '*' || operation == '/') {
            return 1;
        } else {
            return -1;
        }
    }

    public boolean isOpera(char var) {
        return var == '+' || var == '-' || var == '*' || var == '/';
    }

    public int calculator(int num1, int num2, char opera) {
        int result = 0;
        switch (opera) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num2 - num1;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num2 / num1;
                break;
            default:
                break;
        }
        return result;
    }

    @Override
    public String toString() {
        return "Stack{" +
                "arr=" + Arrays.toString(arr) +
                '}';
    }

    public static void main(String[] args) {
        //-63
        String express = "2*2";
        Stack<Integer> numStack = new Stack<>(10);
        Stack<Character> operaStack = new Stack<>(10);
        int index = 0;
        int num1;
        int num2;
        char ch;
        int result = 0;
        while (true) {
            ch = express.charAt(index);
            if (operaStack.isOpera(ch)) {
                if (!operaStack.isEmpty()) {
                    while ((!operaStack.isEmpty()) && (operaStack.priority(ch) <= operaStack.priority(operaStack.peek()))) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        Character poll = operaStack.pop();
                        result = numStack.calculator(num1, num2, poll);
                        numStack.push(result);
                    }
                }
                operaStack.push(ch);
            } else {
                int temp = index;
                StringBuilder str = new StringBuilder();
                for (int i = temp; i < express.length(); i++) {
                    char c = express.charAt(i);
                    if (!numStack.isOpera(c)) {
                        str.append(c);
                    } else {
                        break;
                    }
                }
                String s = str.toString();
                if (str.length() > 1) {
                    index = index + s.length() - 1;
                }
                int num = Integer.parseInt(s);
                numStack.push(num);
            }
            index++;
            if (index >= express.length()) {
                break;
            }
        }
        while (!operaStack.isEmpty()) {
            num1 = numStack.pop();
            num2 = numStack.pop();
            Character poll = operaStack.pop();
            result = numStack.calculator(num1, num2, poll);
            numStack.push(result);
        }
        System.out.println("表达式:" + express + "得到计算结果:" + numStack.pop());

    }

}
