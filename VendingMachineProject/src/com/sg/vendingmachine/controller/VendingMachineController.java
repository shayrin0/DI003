package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOConsoleImpl;

public class VendingMachineController {
    private UserIO io = new UserIOConsoleImpl();

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        while (keepGoing) {
            io.print("Main Menu");
            io.print("The items in the vending machine");
            io.print("1. Vend an item");
            io.print("2. Exit");

            menuSelection = io.readInt("Please select from the"
                    + " above choices.", 1, 2);

            switch (menuSelection) {
                case 1:
                    io.print("ASK WHICH ITEM");
                    break;
                case 2:
                    keepGoing = false;
                    break;
                default:
                    io.print("UNKNOWN COMMAND");
            }

        }
        io.print("GOOD BYE");
    }
}
