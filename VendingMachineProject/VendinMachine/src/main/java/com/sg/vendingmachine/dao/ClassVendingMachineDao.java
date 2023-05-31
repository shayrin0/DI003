
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Product;
import java.math.BigDecimal;
import java.util.ArrayList;


public interface ClassVendingMachineDao {
    
    Product sellItem(Product product) throws ClassVendingMachinePersistenceException;

    ArrayList<Product> getListProducts() throws ClassVendingMachinePersistenceException;
    
    Product getProductByID(String id) throws ClassNotFoundException;

    Product checkProductExistInventory(String id) throws ClassVendingMachineInventoryException;
    
}
