
package com.sg.vendingmachine.dao;

public class VendingMachineDaoException extends Exception{
    public VendingMachineDaoException(String msg){
        super(msg);
    }
    public VendingMachineDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
