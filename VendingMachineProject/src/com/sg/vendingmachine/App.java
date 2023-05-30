package com.sg.vendingmachine;

import com.sg.vendingmachine.controller.VendingMachineController;

public class App {
    public static void main(String[] args) {
        VendingMachineController controller = new VendingMachineController();
        controller.run();
    }
}
