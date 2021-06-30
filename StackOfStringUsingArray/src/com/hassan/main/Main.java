package com.hassan.main;

import com.hassan.core.implementation.StackOfStringUsingArray;

import java.util.Scanner;

/**
 * @Purpose: Created the Class for Utilizing the StackOfStringUsingArray class
 * and show the Functionalities in the Console Output
 *
 * @Author: Hassan Sakib Afrin
 * @Created: 30-06-2021 11.32 PM
 * Reach Me out
 * @Email : hassanrupam@gmail.com
 * @Phone : +880-01746-034-727
 * @LinkedIn: https://www.linkedin.com/in/hassanrupam/
 */
public class Main {

    public static void main(String[] args) {
	    StackOfStringUsingArray stack =  new StackOfStringUsingArray(10);

        System.out.println("Is the Stack Empty ? : " + (stack.isEmpty()? "Yes" : "No"));

        stack.pop();
        System.out.println(stack.peek());

        stack.push("Value 1");
        stack.push("Value 2");

        System.out.println("Is the Stack Empty ? : " + (stack.isEmpty()? "Yes" : "No"));

        stack.push("Value 3");
        System.out.println(stack.peek());

        stack.push("Value 4");
        stack.pop();
        stack.push("Value 4");
        stack.push("Value 5");
        System.out.println(stack.peek());

        stack.push("");
        stack.push("Value 6");
        stack.push("Value 7");
        stack.pop();

        stack.push("Value 7");
        stack.push("Value 8");
        System.out.println(stack.peek());

        stack.push("Value 9");
        stack.push("Value 10");
        stack.push("Value 11");

    }
}
