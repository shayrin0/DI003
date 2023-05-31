
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.ClassVendingMachineAuditDao;
import com.sg.vendingmachine.dao.ClassVendingMachineChange;
import com.sg.vendingmachine.dao.ClassVendingMachineChangeImpl;
import com.sg.vendingmachine.dao.ClassVendingMachineDao;
import com.sg.vendingmachine.dao.ClassVendingMachineInventoryException;
import com.sg.vendingmachine.dao.ClassVendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

public class ClassVendingMachineServiceImpl implements ClassVendingMachineService{
    private final ClassVendingMachineDao dao;
    private final ClassVendingMachineAuditDao audit;
    private final ClassVendingMachineChange change;

    public ClassVendingMachineServiceImpl(ClassVendingMachineDao dao, ClassVendingMachineAuditDao audit) {
        this.dao = dao;
        this.audit = audit;
        this.change = new ClassVendingMachineChangeImpl();
    }

    public ArrayList<Product> getListProducts() throws ClassVendingMachinePersistenceException {
        return dao.getListProducts();
    }
    
    public void isMoneyUserValid(double money) throws ClassInsufficientFundsException{
        if(money == 0){
            throw new ClassInsufficientFundsException("Insufficient Funds.");
        }
    }

    @Override
    // changed the return type and added an if-else statement
    public Product sellProduct(String id, BigDecimal moneyUser) throws ClassNoItemInventoryException, ClassInsufficientFundsException, ClassNotFoundException, ClassVendingMachineInventoryException {
        Product prod;
        Product soldProduct;
        Map<String, Integer> changeUser;
        try{
            prod = dao.getProductByID(id);
            if (prod != null) {
                isEnoughtMoneyUser(moneyUser, prod.getPrice());
                //Check if the product exist in the inventory
                dao.checkProductExistInventory(id);
                //sell product
                soldProduct = dao.sellItem(prod);
                //get the change in pennies
                int pennies = this.getChangeUserPennies(moneyUser, prod.getPrice());
                change.getChangeUser(pennies);
            }
            else {
                return null;
            }
        }
        catch(ClassNotFoundException e){
            throw new ClassNotFoundException("The product with that ID doesn't exit.");
        }
        catch(ClassInsufficientFundsException e){
            throw new ClassInsufficientFundsException("Insufficient Funds.");
        }
        catch(ClassVendingMachinePersistenceException e){
            throw new ClassInsufficientFundsException("-_- Could not load the product data into memory.", e);
        }
        catch(ClassVendingMachineInventoryException e){
            throw new ClassInsufficientFundsException("The product doesn't have any item in the inventory.");
        }
        return soldProduct;
    }
    
    private int getChangeUserPennies(BigDecimal moneyUser, BigDecimal price){
            //moneyUser - price
            moneyUser = moneyUser.subtract(price);            
            //converts BigDecimal to an integer value
            double cents = moneyUser.doubleValue(); 
            return (int) (cents * 100);
    }

    private void isEnoughtMoneyUser(BigDecimal money, BigDecimal price) throws ClassInsufficientFundsException {
        /*0 : if value of this BigDecimal is equal to that of BigDecimal object passed as parameter.
          1 : if value of this BigDecimal is greater than that of BigDecimal object passed as parameter.
         -1 : if value of this BigDecimal is less than that of BigDecimal object passed as parameter.*/
        if(money.compareTo(price)== -1){
            throw new ClassInsufficientFundsException("");
        }
    }

    
   
    
}
