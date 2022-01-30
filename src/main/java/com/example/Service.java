package com.example;

import java.util.*;

@org.springframework.stereotype.Service
public class Service {

    private static Map<String,Integer> inOperatorOrder = new HashMap<>(){{
        put("+", 3);
        put("-", 3);
        put("*", 5);
        put("/", 5);
        put("(", 0);
        put(")", 1);
    }};
    private static Map<String,Integer> outOperatorOrder = new HashMap<>(){{
        put("+", 3);
        put("-", 3);
        put("*", 5);
        put("/", 5);
        put("(", 8);
        put(")", 1);
    }};

    //infix to postfix expression

    public static String infixToPostfix(String infix){
        Stack<String> operators = new Stack<>();
        Queue<String> postfix = new LinkedList<>();
        for (String op: splitToArray(infix)) {
            if (isOperator(op)){
                while (!operators.isEmpty() && inOperatorOrder.get(operators.peek()) >= outOperatorOrder.get(op))
                    postfix.add(operators.pop());
                if (!op.equals(")"))
                    operators.push(op);
                else
                    operators.pop();
            }
            else {
                postfix.add(op);
            }
        }
        while (!operators.empty())
            postfix.add(operators.pop());
        System.out.println(postfix.toString());
        return calculate(postfix);

    }

    private static List<String> splitToArray(String infix) {
        List<String> infixSplit = new ArrayList<>(Arrays.asList(
                infix.replaceAll(" ", "")
                        .split("(?<=[-+*/()])|(?=[-+*/()])")));

        return infixSplit;
    }

    private static String calculate(Queue<String> postfix) {
        Stack<String> operand = new Stack<>();
        double num1 = 0, num2 = 0;
        String operator = "";
        while (!postfix.isEmpty()){
            if (!isOperator(postfix.peek())){
                operand.push(postfix.poll());
            }
            else {
                operator = postfix.poll();
                num2 = Double.parseDouble(operand.pop());
                num1 = Double.parseDouble(operand.pop());

                if (operator.equals("+"))
                    num1 += num2;
                else if (operator.equals("-"))
                    num1 -= num2;
                else if (operator.equals("*"))
                    num1 *= num2;
                else
                    num1 /= num2;

                operand.push(String.valueOf(num1));
            }
        }
        return "" + num1;
    }


    private static boolean isOperator(String op) {
        return inOperatorOrder.containsKey(op);
    }


}
