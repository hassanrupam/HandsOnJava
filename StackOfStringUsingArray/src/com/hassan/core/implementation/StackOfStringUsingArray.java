package com.hassan.core.implementation;

import com.hassan.core.interfaces.IStackOfStringUsingArray;

/**
 * In computer science, a stack is an abstract data type that serves as a collection of elements,
 * with two main principal operations: Push, which adds an element to the collection, and. Pop,
 * which removes the most recently added element that was not yet removed.
 *
 * @Purpose: The Purpose of this class is to Create a Stack Like Functionality But Using Array Data Type.
 * To be precise, this Class will be servingb as a Stack Of String using a String Array.
 *
 * @Author: Hassan Sakib Afrin
 * @Created: 30-06-2021 11.40 PM
 * Reach Me out
 * @Email : hassanrupam@gmail.com
 * @Phone : +880-01746-034-727
 * @LinkedIn: https://www.linkedin.com/in/hassanrupam/
 */
public class StackOfStringUsingArray implements IStackOfStringUsingArray {

    //region PRIVATE FIELDS
    private static int IS_EMPTY_STACK = -1;
    private static String STACK_IS_EMPTY_MESSAGE = "UNDERFLOW!! The Stack Is Empty! Please Push Strings in the Stack!";
    private static String STACK_IS_FULL_MESSAGE = "OVERFLOW! The Stack Is Full!";
    private static String INVALID_INPUT = "The Input Is Not Valid! Please Insert a Valid input (Minimum 1 Character required)";

    private int size;
    private String arrayStack[];
    private int top;
    //endregion

    //region Constructor
    public StackOfStringUsingArray(int size) {
        this.size = size;
        this.arrayStack = new String[size];
        this.top = IS_EMPTY_STACK;
        System.out.println("Stack Initialized with size of :"+ size);
    }
    //endregion

    //region PUBLIC METHODS
    /**
     * This Method checks if the Stack Array Is Full!
     * The Method Will return TRUE if the Stack is Full.
     * If the Stack Is not Full it will return FALSE
     *
     * @return Boolean flag - TRUE OR FALSE
     */
    @Override
    public Boolean isFull() {
        return (size-1==top);
    }

    /**
     * The method checks if the Stack Is Empty.
     * If there are No element Left the method will return TRUE.
     * If the Stack Contains some values, it will return FALSE
     *
     * @return Boolean flag - TRUE OR FALSE
     */
    @Override
    public Boolean isEmpty() {
        return (top==IS_EMPTY_STACK);
    }

    /**
     * This Method resembles the PEEK() method for the Stack.
     * This method will Return the TOP Value of the Stack
     * If there are no values Inside the Stack , meaning- If the Stack is Empty
     * it will return the STACK_IS_EMPTY_MESSAGE
     *
     * @return Top value Of Stack or STACK_IS_EMPTY_MESSAGE
     */
    @Override
    public String peek() {
        return isEmpty() ? STACK_IS_EMPTY_MESSAGE: arrayStack[top];
    }

    /**
     * This Method resembles the PUSH() method for the Stack.
     * This Method takes a String value. Validates the String value, meaning,
     * It will not push the value is NULL or is Empty. The Input Must contain at least 1 character.
     * If the value is Valid and the Stack Is not FUll then the value will be pusher to the TOP of the Stack;
     * This Method will Show the proper message in the console.
     *
     * @param value The String type value you want to push into The Stack
     */
    @Override
    public void push(String value) {
        if(value!=null&&!value.trim().isEmpty()){
            if(!isFull()){
                arrayStack[++top] =  value;
                System.out.println("String Is Pushed to the Stack : " + value);
            }else{
                System.out.println(STACK_IS_FULL_MESSAGE);
            }
        }else{
            System.out.println(INVALID_INPUT);
        }
    }

    /**
     * This Method resembles the POP() method for the Stack.
     * If the array stack is not empty The It will remove the Top Element and println out the Value in Console.
     * If the Stack is Empty then it will provide the STACK_IS_EMPTY_MESSAGE
     */
    @Override
    public void pop() {
       if(!isEmpty()){
           String poppedValue = arrayStack[top];
           top--;
           System.out.println("Element \""+ poppedValue + "\" has been popped from the stack.");
       }else{
           System.out.println(STACK_IS_EMPTY_MESSAGE);
       }
    }
    //endregion
}
