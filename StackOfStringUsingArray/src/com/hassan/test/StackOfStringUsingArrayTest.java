package com.hassan.test;

import com.hassan.core.implementation.StackOfStringUsingArray;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Purpose :  Testing Class for the StackOfStringUsingArray class with 100% Coverage
 *
 * @Author: Hassan Sakib Afrin
 * @Created: 30-06-2021 11.32 PM
 * Reach Me out
 * @Email : hassanrupam@gmail.com
 * @Phone : +880-01746-034-727
 * @LinkedIn: https://www.linkedin.com/in/hassanrupam/
 */
class StackOfStringUsingArrayTest {
    //region PRIVATE FIELDS
    private PrintStream original =  System.out;
    private ByteArrayOutputStream byteArrayOutputStream =  new ByteArrayOutputStream();
    private PrintStream pst = new PrintStream(byteArrayOutputStream);
    private StackOfStringUsingArray stack =  new StackOfStringUsingArray(1);
    //endregion

    //region TEST METHODS
    @Test
    void isFull() {
        //region CASE 1- Stack Is FUll
        stack =  new StackOfStringUsingArray(2);
        stack.push("Testing");
        stack.push("IsFull");
        assertEquals(true,stack.isFull());
        //endregion

        //region CASE 2- Stack Is not FULL
        stack =  new StackOfStringUsingArray(2);
        stack.push("It Works!! (y)");
        assertEquals(false,stack.isFull());
        //endregion
    }

    @Test
    void isEmpty() {
        //region CASE 1 -  Stack is Empty
        stack =  new StackOfStringUsingArray(3);
        assertEquals(true,stack.isEmpty());
        //endregion

        //region CASE-2 - Stack Is Not Empty
        stack.push("Testing");
        stack.push("Is Empty");
        assertEquals(false,stack.isEmpty());
        //endregion
    }

    @Test
    void peek() {
        //region CASE 1 -  Stack Is Empty and Tried to Peek()
        stack =  new StackOfStringUsingArray(1);
        assertEquals("UNDERFLOW!! The Stack Is Empty! Please Push Strings in the Stack!",stack.peek());
        //endregion

        //region CASE 2 - Stack is not Empty and Tried to Peek
        stack.push("WHOA, I can Now Peek!!");
        assertEquals("WHOA, I can Now Peek!!",stack.peek());
        //endregion
    }

    @Test
    void push() {
        //region CASE 1- Push a String In the Stack
        stack =  new StackOfStringUsingArray(1);
        System.setOut(pst);
        stack.push("Okay, It Seems to be Pushing in the Stack!");
        System.setOut(original);
        pst.flush();
        assertEquals("String Is Pushed to the Stack : Okay, It Seems to be Pushing in the Stack!",byteArrayOutputStream.toString().trim());
        //endregion


        //region CASE 2: Push an Invalid Input
        stack =  new StackOfStringUsingArray(1);
        original =  System.out;
        byteArrayOutputStream =  new ByteArrayOutputStream();
        pst = new PrintStream(byteArrayOutputStream);
        System.setOut(pst);
        stack.push("");
        System.setOut(original);
        assertEquals("The Input Is Not Valid! Please Insert a Valid input (Minimum 1 Character required)",byteArrayOutputStream.toString().trim());
        //endregion

        //region CASE 3 - Tried to Push While Stack Is Full
        stack =  new StackOfStringUsingArray(1);
        original =  System.out;
        byteArrayOutputStream =  new ByteArrayOutputStream();
        pst = new PrintStream(byteArrayOutputStream);
        stack.push("Pushing Data In Stack");
        System.setOut(pst);
        stack.push("Pushing Again");
        System.setOut(original);
        assertEquals("OVERFLOW! The Stack Is Full!",byteArrayOutputStream.toString().trim());
        //endregion
    }

    @Test
    void pop() {
        //region CASE 1- Pop a String From the Stack
        original =  System.out;
        byteArrayOutputStream =  new ByteArrayOutputStream();
        pst = new PrintStream(byteArrayOutputStream);
        stack =  new StackOfStringUsingArray(1);
        stack.push("Let's Get It!");
        System.setOut(pst);
        stack.pop();
        System.setOut(original);
        pst.flush();
        assertEquals("Element \"Let's Get It!\" has been popped from the stack.",byteArrayOutputStream.toString().trim());
        //endregion

        //region CASE 2- Pop a String Whiole the Stack Is Empty
        original =  System.out;
        byteArrayOutputStream =  new ByteArrayOutputStream();
        pst = new PrintStream(byteArrayOutputStream);
        stack =  new StackOfStringUsingArray(1);
        System.setOut(pst);
        stack.pop();
        System.setOut(original);
        pst.flush();
        assertEquals("UNDERFLOW!! The Stack Is Empty! Please Push Strings in the Stack!",byteArrayOutputStream.toString().trim());
        //endregion
    }
    //endregion
}