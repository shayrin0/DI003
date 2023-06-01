package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.ClassVendingMachineAuditDao;
import com.sg.vendingmachine.dao.ClassVendingMachineDao;
import com.sg.vendingmachine.dto.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

public class ClassVendingMachineServiceImplTest {
    private ClassVendingMachineService service;
    ClassVendingMachineDao testDao;

    @BeforeEach
    public void setUp() {
        testDao = new VendingMachineDaoStubImpl();
    }

    public ClassVendingMachineServiceImplTest() {
        testDao = new VendingMachineDaoStubImpl();
        ClassVendingMachineAuditDao auditDao = (ClassVendingMachineAuditDao) new VendingMachineAuditDaoStubImpl();

        service = new ClassVendingMachineServiceImpl(testDao, auditDao);
    }

    @Test
    public void testGetAllProducts() throws Exception {
        // ARRANGE
        Product testProduct;
        BigDecimal productPrice = new BigDecimal("1.35");
        testProduct = new Product("001", "KitKat", productPrice, 10);

        // ACT & ASSERT
        assertEquals( 1, service.getListProducts().size(),
                "Should only have one product.");
        assertTrue( service.getListProducts().contains(testProduct),
                "The one product should be KitKat.");
    }

    // sell product - 1 product will be reduced from the list
    @Test
    public void testAddSellProducts() throws Exception {
        // ARRANGE
        BigDecimal productPrice = new BigDecimal("1.35");
        Product testProduct = new Product("001", "KitKat", productPrice, 10);

        BigDecimal userMoney = new BigDecimal("5");

        // ACT & ASSERT
        Product soldProduct = service.sellProduct(testProduct.getId(), userMoney);
        assertNotNull( soldProduct, "Removing KitKat should be not null.");
        assertEquals( testProduct, soldProduct, "KitKat removed from the list.");

        Product shouldBeNull = service.sellProduct("0042", userMoney);
        assertNull( shouldBeNull, "Removing 0042 should be null.");
    }
    
    @Test
    public void testSellInvalidIdProducts() throws Exception {
        Product testProduct = new Product("");

        BigDecimal userMoney = new BigDecimal("5");

        // ACT & ASSERT
        Product shouldBeNull = service.sellProduct(testProduct.getId(), userMoney);
        assertNull(shouldBeNull, "Cannot sell a product with null id.");
    }
   @Test
    public void testAddProduct() throws Exception {
        // ARRANGE
        Product testProduct;
        BigDecimal productPrice = new BigDecimal("1.35");
        testProduct = new Product("001", "KitKat", productPrice, 10);

        Product addedProduct = testDao.addProduct(testProduct);

        // ACT & ASSERT
        assertEquals( 1, service.getListProducts().size(),
                "Should only have one product.");
        assertTrue( service.getListProducts().contains(testProduct),
                "The one product should be KitKat.");
    }
}
