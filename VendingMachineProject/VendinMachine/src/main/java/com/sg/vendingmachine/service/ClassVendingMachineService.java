
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dto.Product;
import java.math.BigDecimal;
import java.util.ArrayList;

public interface ClassVendingMachineService {
    
    ArrayList<Product> getListProducts() throws ClassVendingMachinePersistenceException, ClassNoItemInventoryException;
    
    Product sellProduct(String id, BigDecimal moneyUser) throws ClassNoItemInventoryException, ClassInsufficientFundsException, ClassNotFoundException, ClassVendingMachinePersistenceException;
    
}
