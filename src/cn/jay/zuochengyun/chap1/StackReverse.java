package cn.jay.zuochengyun.chap1;

import java.util.Stack;

/**
 * 如何仅用递归函数和栈操作逆序一个栈
 */
public class StackReverse {

    static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }

        reverse();

        for (int i = 0; i < 10; i++) {
            System.out.print(stack.pop() + " ");
        }

    }


    static Integer getAndRemoveLastOne(){
        Integer num = stack.pop();
        if(stack.isEmpty()){
            return num;
        }
        else {
            Integer last = getAndRemoveLastOne();
            stack.push(num);
            return last;
        }
    }

    static void reverse(){
        if(stack.isEmpty()) return;
        Integer i = getAndRemoveLastOne();
        reverse();
        stack.push(i);
    }

}
