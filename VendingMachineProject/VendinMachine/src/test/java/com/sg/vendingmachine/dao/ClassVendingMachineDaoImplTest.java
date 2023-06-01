
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Product;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

public class ClassVendingMachineDaoImplTest {
    
    ClassVendingMachineDao testDao;

    public ClassVendingMachineDaoImplTest() throws Exception {
        String testFile = "testProducts.txt";
        // Use the FileWriter to quickly blank the file
        new FileWriter(testFile, true);
        testDao = new ClassVendingMachineDaoImpl(testFile);
    }    
    
    // set the testProducts file to be used for all tests
    @BeforeEach
    public void setUp() throws Exception {
        String testFile = "testProducts.txt";
        // Use the FileWriter to quickly blank the file
        new FileWriter(testFile, true);
        testDao = new ClassVendingMachineDaoImpl(testFile);
    }
    
    @Test // method by Michaela
    @DisplayName("Test get list products")
    public void testGetListProducts() throws Exception {
        // create sample data and load it into the file

        // -1::Dr Pepper::2.00::3
        // -2::Lemonade::1.23::4
        // -3::Fanta::0.85::12
        // -4::Gatorade::2.22::6

        Product p1 = testDao.addProduct(new Product("-1", "Dr Pepper", new BigDecimal("2.00"), 3));
        Product p2 = testDao.addProduct(new Product("-2", "Lemonade", new BigDecimal("1.23"), 4));
        Product p3 = testDao.addProduct(new Product("-3", "Fanta", new BigDecimal("0.85"), 12));
        Product p4 = testDao.addProduct(new Product("-4", "Gatorade", new BigDecimal("2.22"), 6));

        // put all the products into a list for comparison
        ArrayList<Product> expectedResult = new ArrayList<>();
        expectedResult.add(p1);
        expectedResult.add(p2);
        expectedResult.add(p3);
        expectedResult.add(p4);

        // get a list of the actual products
        ArrayList<Product> result = testDao.getListProducts();
        assertEquals(result, expectedResult, "Checking getListProducts");
        
        // Check that the inventory updates when an item is sold
        // Sell an item of Dr Pepper
        testDao.sellItem(p1);
        //Get updated list of products
        ArrayList<Product> updatedList = testDao.getListProducts();
        // Assert updated inventory
        assertEquals(2, updatedList.get(0).getNumberItemsInventory(), "Checking if inventory updated with one Dr. Pepper item sold");

        // Sell "Gatorade" 2 times
        testDao.sellItem(p4);
        testDao.sellItem(p4);

        ArrayList<Product> newUpdatedList = testDao.getListProducts();
        assertEquals(4, newUpdatedList.get(3).getNumberItemsInventory(), "Checking new updated inventory with 2 gatorade items sold");
        
        Product p10 = new Product("10", "corn", new BigDecimal(170), 10);
        testDao.addProduct(p10);
    }
    
    @Test // method by Michaela
    @DisplayName("Test add get product")
    public void testAddGetProduct() throws Exception {

        // add a new Product with values
        // 9::Gummies::0.99::4
        Product result = new Product("3456789", "Gummies", new BigDecimal("0.99"), 4);
        testDao.addProduct(result);

        // search the products for the new id
        Product expectedResult = testDao.getProductByID(result.getId());

        // String id, String name, BigDecimal price, int numberItemsInventory
        assertEquals(expectedResult.getId(), result.getId(), "Ids should be equal");
        assertEquals(expectedResult.getName(), result.getName(), "Name should be equal");
        assertEquals(expectedResult.getPrice(), result.getPrice(), "Price should be equal");
        assertEquals(expectedResult.getNumberItemsInventory(), result.getNumberItemsInventory(), "Inventory should be equal");
        
        
        
    }

    
}
