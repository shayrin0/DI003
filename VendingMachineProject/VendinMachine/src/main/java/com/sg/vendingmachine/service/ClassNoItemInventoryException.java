
package com.sg.vendingmachine.service;

//One that is thrown when the user tries to purchase an item that has zero inventory
public class ClassNoItemInventoryException extends Exception{
    public ClassNoItemInventoryException(String message) {
        super(message);
    }
    
    public ClassNoItemInventoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
