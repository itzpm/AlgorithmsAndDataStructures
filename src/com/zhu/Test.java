package com.zhu;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * @author ZPM
 * @version 1.0
 */
public class Test {
    public static void main(String[] args) throws FileNotFoundException {
//        PrintStream p = new PrintStream("a.txt");
//        System.setOut(p);
//        p.println("a b c");
//        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
//        System.out.println("hello world");

        Object[] a = new Object[10];
        System.out.println(a.length);
    }
}

class Calculator {
    public static void main(String[] args) {
        String expression = "5-10*5+2-20";
        //创建两个栈，一个数栈，一个符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义相关变量
        int index = 0;//用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';//将每次扫描得到的char保存ch
        String keepNum = "";//用于拼接多位数
        while (true) {
            ch = expression.substring(index, index + 1).charAt(0);
            if (operStack.isOper(ch)) {//是运算符
                //判断当前符号栈是否为空
                if (!operStack.isEmpty()) {
                    //不为空要判断优先级
                    //如果当前的操作符的优先级小于或者等于栈中的操作符，就需要从数栈中pop出两个数，再从符号栈中pop出一个符号，进行运算，将得到的结果，入数栈，然后将当前的操作符入符号栈
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, (char) oper);
                        numStack.push(res);
                        operStack.push(ch);
                    } else {
                        operStack.push(ch);
                    }
                } else {
                    //为空，直接入栈
                    operStack.push(ch);
                }
            } else {//数字,直接入数栈
                //1.当处理多位数时，不能发现是一个数就直接入栈，因为有可能是多位数
                //2.在处理数时，需要向expression的表达式的index后再看以为，如果是数就进行扫毛，如果是符号才入栈
                //3.因此我们需要定义一个变量字符串，用于拼接

                //处理多位数
                keepNum += ch;

                //如果是最后一位直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    //判断下一个字符是不是数字，是就继续扫描，不是就入栈
                    //注意最后一位，不是index++
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        //如果最后一位是运算符，则入栈keppNum=“1”或者“123”
                        numStack.push(Integer.parseInt(keepNum));
                        //!!！！!清空
                        keepNum = "";
                    }

                }
            }
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

//        4.当表达式扫描完成，就顺序的从数栈和符号栈中pop出相应的数和符号，并运行
        while (true) {
            //如果符号栈为空，则计算到最后的结果，数栈中只有一个数字
            if (operStack.isEmpty()) {
                break;
            } else {
                num1 = numStack.pop();
                num2 = numStack.pop();
                oper = operStack.pop();
                res = numStack.cal(num1, num2, (char) oper);
                numStack.push(res);
            }
        }
        System.out.printf("表达式%s = %d", expression, numStack.pop());
    }
}

//先创建一个栈，需要扩展功能
class ArrayStack2 {
    private int maxSize;//栈的大小
    private int[] stack;//数组，数组模拟栈，数据放在该数组
    private int top = -1;//top表示栈顶，初始化-1

    //构造器
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //查看当前栈顶值
    public int peek() {
        return stack[top];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        //先判断栈是否满
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历栈,遍历时，需要从栈顶显示数据
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据~");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d \n", i, stack[i]);
        }
    }

    //返回运算符的优先级，优先级是程序员来定，优先级使用数字表示，数字越大，优先级越高
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;//假定目前表达式只有+，-，*，/
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1, int num2, char oper) {
        int res = 0;//res用于存放计算的结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num2 * num1;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }

}
