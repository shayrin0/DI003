
package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.ClassVendingMachineInventoryException;
import com.sg.vendingmachine.dao.ClassVendingMachinePersistenceException;
import com.sg.vendingmachine.dao.VendingMachineDaoException;
import com.sg.vendingmachine.dto.Product;
import com.sg.vendingmachine.service.ClassInsufficientFundsException;
import com.sg.vendingmachine.service.ClassNoItemInventoryException;
import com.sg.vendingmachine.service.ClassVendingMachineServiceImpl;
import com.sg.vendingmachine.ui.ClassVendingMachineUserView;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

public class VendingMachineController {
    private final ClassVendingMachineUserView io;
    private final ClassVendingMachineServiceImpl service;

    public VendingMachineController(ClassVendingMachineUserView io, ClassVendingMachineServiceImpl service) {
        this.io = io;
        this.service = service;
    }
    
    public void run() throws VendingMachineDaoException, ClassVendingMachinePersistenceException, ClassInsufficientFundsException, ClassNoItemInventoryException, ClassNotFoundException, ClassVendingMachineInventoryException{
        io.bannerApp();
        double moneyUser = 0;
        while(true){
            ArrayList<Product> listItem = service.getListProducts();            
            io.showListProducts(listItem);
            
            if(moneyUser == 0){
                moneyUser = io.getMoneyUser(); 
                try{
                    service.isMoneyUserValid(moneyUser);
                }catch(ClassInsufficientFundsException e){
                    io.insufficientFunds();
                    continue;
                }
            }
            String option = io.getMenuOption();
            if (Integer.parseInt(option)==0){
                io.programEnding();
                System.exit(0);                
            }else{
//                try{
//                    Map<String, Integer> changeUser = service.sellProduct(option, new BigDecimal(moneyUser));
//                    moneyUser=0;
//                    io.productSoldsuccefully();
//                    io.outputChangeUser(changeUser);
//                }catch(ClassNoItemInventoryException | ClassNotFoundException  e){
//                    io.displayErrorMessage(e.getMessage());
//                }catch(ClassInsufficientFundsException e){
//                    io.displayErrorMessage(e.getMessage() + ", you only count with $" + moneyUser + ". ");
//                }
                
            }
        }
    }
    
}
