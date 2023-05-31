
package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dao.VendingMachineDaoException;

public interface UserIO {

    void print(String message);

    String readString(String prompt);

    int readInt(String prompt);

    int readInt(String prompt, int min, int max);

    double readDouble(String prompt) throws VendingMachineDaoException;

    double readDouble(String prompt, double min, double max);

    float readFloat(String prompt);

    float readFloat(String prompt, float min, float max);

    long readLong(String prompt) throws VendingMachineDaoException;

    long readLong(String prompt, long min, long max);
    
    //LocalDate readDate(String date) throws DVDDaoException;

}
