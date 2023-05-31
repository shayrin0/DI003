
package com.sg.vendingmachine.service;

//One that is thrown when the user tries to purchase an item but doesn't deposit enough money
public class ClassInsufficientFundsException extends Exception{
    public ClassInsufficientFundsException(String message) {
        super(message);
    }
    
    public ClassInsufficientFundsException(String message, Throwable cause) {
        super(message, cause);
    }
}
