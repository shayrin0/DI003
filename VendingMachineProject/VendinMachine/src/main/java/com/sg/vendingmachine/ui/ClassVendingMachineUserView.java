
package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dao.VendingMachineDaoException;
import com.sg.vendingmachine.dto.Product;
import java.util.ArrayList;
import java.util.Map;

public class ClassVendingMachineUserView {
    private UserIO io;

    public ClassVendingMachineUserView(UserIO io) {
        this.io = io;
    }
    
    public void bannerApp() throws VendingMachineDaoException{
        io.print("*******Wellcome to the Vending Machine*******");
    }
    
    public void showListProducts(ArrayList<Product> listItem) throws VendingMachineDaoException{
        this.getListItems(listItem);
    }
    public String getMenuOption() throws VendingMachineDaoException{
        Integer msg = io.readInt("\nIf you wanna exit, please just press 0. \nType the ID of the product you wish purchase: ");
        return msg.toString();
//        return io.readString("\nIf you wanna exit, please just press cero. \nType the ID of the product you wish purchase: ");
    }
    
    private void getListItems(ArrayList<Product> listItem) throws VendingMachineDaoException{
        io.print("\n*** Displaying Information of Products***");
        String head = String.format("%10s | %20s | %20s" , 
                "ID Product",
                "Product",
                "Price");
        io.print(head);
        listItem.stream().filter((p) -> p.getNumberItemsInventory() > 0)
                .forEach((p) -> System.out.printf("%10s | %20s | %20s%n", p.getId(), p.getName(), p.getPrice()));
    }
        /*io.print("\n*** Displaying Information of Products***");
        String head = String.format("%10s | %20s | %20s" , 
                "ID Product",
                "Product",
                "Price");
        io.print(head);
        for(Product p: listItem){
            String dataProducts = String.format("%10s | %20s | %20s" , 
                p.getId(),
                p.getName(),
                p.getPrice());
            io.print(dataProducts);
        } */       
    
    public double getMoneyUser() throws VendingMachineDaoException{
       String output = "Please enter your amount of money: ";
       return io.readDouble(output);
    }
    public void insufficientFunds() throws VendingMachineDaoException{
        io.print("Insufficient Funds");
    }
    
    public void programEnding() throws VendingMachineDaoException{
        io.print("\nThank you for all, come back soon!!!");
    }
    
    public void productSoldSuccessfully() throws VendingMachineDaoException{
        io.print("\nProduct sold successfully. Take your product.");
    }

    public void displayErrorMessage(String message) throws VendingMachineDaoException {
        io.print("\n=== ERROR ===");
        io.print(message);
    }
    
    public void displayMessage(String message) throws VendingMachineDaoException {
        io.print(message);
    }

    public void outputChangeUser(Map<String, Integer> changeUser) throws VendingMachineDaoException {
        String output = ("\nYour change is: "  
                + "\n" + "QUARTERS: " + changeUser.get("QUARTERS")
                + "\n" + "DIMES: " + changeUser.get("DIMES")
                + "\n" + "NICKELS: " + changeUser.get("NICKELS")
                + "\n" + "PENNIES: " + changeUser.get("PENNIES")
                );        
        io.print(output);
    }
}
