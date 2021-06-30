package com.hassan.core.interfaces;

/**
 * Interface for the StackOfStringUsingArray
 * the purpose is to Serve the Stack Functionality Using An Array of String
 *
 * @Author : Hassan Sakib Afrin
 * @Created :  30-06-2021 on 11:35 PM
 *
 * Reach Me out
 * @Email : hassanrupam@gmail.com
 * @Phone : +880-01746-034-727
 * @LinkedIn: https://www.linkedin.com/in/hassanrupam/
 */
public interface IStackOfStringUsingArray {
    public Boolean isFull();
    public Boolean isEmpty();
    public String peek();
    public void push(String value);
    public void pop();
}
