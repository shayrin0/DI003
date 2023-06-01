
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.service.ClassVendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Product;
import com.sg.vendingmachine.service.ClassNoItemInventoryException;
import java.util.ArrayList;
//import java.util.Map;


public interface ClassVendingMachineDao {
    
    Product sellItem(Product product) throws ClassVendingMachinePersistenceException;

    ArrayList<Product> getListProducts() throws ClassVendingMachinePersistenceException;
    
    Product getProductByID(String id) throws ClassNotFoundException;
    
    Product checkProductExistInventory(String id) throws ClassNoItemInventoryException;
    
    Product addProduct(Product product) throws Exception;
    
    //Map<String, Product> loadProductsFromFile() throws ClassVendingMachinePersistenceException;
}
