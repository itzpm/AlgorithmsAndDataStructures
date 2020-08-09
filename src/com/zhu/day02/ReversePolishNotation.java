package com.zhu.day02;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author ZPM
 * @version 1.0
 */
public class ReversePolishNotation {

    private final static String[] OPERATION = {"+", "-", "*", "/", "(", ")", "x"};

    public boolean isOpera(String ch) {
        for (String opera : OPERATION) {
            if (opera.equals(ch)) {
                return true;
            }
        }
        return false;
    }

    public int priority(String operation) {
        if ("-".equals(operation) || "+".equals(operation)) {
            return 0;
        } else if ("*".equals(operation)|| "/".equals(operation)||"x".equals(operation)){
            return 1;
        } else{
            return -1;
        }
    }

    public String getReversePolishExpression(String midExp) {
        midExp = midExp.replace(" ", "");
        Stack<Character> stack = new Stack<>();
        StringBuilder builder = new StringBuilder();
        int index = 0;
        for (; ; ) {
            char ch = midExp.charAt(index);
            if (isOpera(String.valueOf(ch))) {
                if (!stack.isEmpty() && ch != ')') {
                    while ((!stack.isEmpty()) && (priority(String.valueOf(ch)) <= priority(String.valueOf(stack.peek())))) {
                        if (ch == '(') {
                            break;
                        }
                        builder.append(stack.pop()).append(" ");
                    }
                }
                if (ch != ')') {
                    stack.push(ch);
                } else {
                    char c;
                    while (!stack.isEmpty()) {
                        if ((c = stack.pop()) != '(') {
                            builder.append(c).append(" ");
                        } else {
                            break;
                        }
                    }
                }
            } else {
                char c;
                String str = "";
                while (index < midExp.length() && midExp.charAt(index) <= 57 && (c = midExp.charAt(index)) >= 48) {
                    //builder
                    str += c;
                    index++;
                }
                //1+((200+3)*4)-5
                index = index - 1;
                if (str.length() > 1) {
                    builder.append(str).append(" ");
                } else {
                    builder.append(ch).append(" ");
                }
            }
            index++;
            if (index >= midExp.length()) {
                break;
            }
        }
        while (!stack.isEmpty()) {
            builder.append(stack.pop()).append(" ");
        }
        return builder.toString().trim();
    }


    public int calculator(int num1, int num2, String opera) {
        switch (opera) {
            case "+":
                return num1 + num2;
            case "-":
                return num2 - num1;
            case "*":
            case "x":
                return num1 * num2;
            case "/":
                return num2 / num1;
            default:
                throw new RuntimeException("操作符" + opera + "无效");
        }
    }

    public int calculatorResult(String suffixExpress) {
        List<String> list = Arrays.asList(suffixExpress.split(" "));
        Stack<String> stack = new Stack<>();
        Stack<Integer> numStack = new Stack<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            stack.push(list.get(i));
        }
        while (!stack.isEmpty()) {
            String ch = stack.pop();
            if (isOpera(ch)) {
                numStack.push(calculator(numStack.pop(), numStack.pop(), ch));
            } else {
                numStack.push(Integer.parseInt(ch));
            }
        }

        return numStack.pop();
    }

    public static void main(String[] args) {
        ReversePolishNotation reversePolishNotation = new ReversePolishNotation();
        //3+17-10+2  / 5-10+5+6  12/6*5-1+6
        String reversePolish = reversePolishNotation.getReversePolishExpression("((3/1+17-10+(2*10/5))/(2+3-5*2+5+(2*(2+1))))*10/2-1+2*3");
        System.out.println(reversePolish);
        System.out.println(reversePolishNotation.calculatorResult(reversePolish));
        System.out.println("=========================================");
        String re = reversePolishNotation.getReversePolishExpression("15*25+(3x2/3-50)/2x30");
        System.out.println(re);
        System.out.println(reversePolishNotation.calculatorResult(re));

        System.out.println(15*25+(3*2/3-50)/2*30);
        System.out.printf("%.2f",(double) 2/3);

        String reg = "\\+|-|\\*|/|\\(|\\)";
    }
}
