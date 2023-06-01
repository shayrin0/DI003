package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.ClassVendingMachineDao;
import com.sg.vendingmachine.dto.Product;
import java.math.BigDecimal;
import java.util.ArrayList;

public class VendingMachineDaoStubImpl implements ClassVendingMachineDao{
    private Product onlyProduct;
    private int numberOfProducts;

    public VendingMachineDaoStubImpl() {
        BigDecimal productPrice = new BigDecimal("1.35");
        numberOfProducts = 10;
        onlyProduct = new Product("001", "KitKat", productPrice, numberOfProducts);
    }

    public VendingMachineDaoStubImpl(Product testDao){
        this.onlyProduct = testDao;
    }

    @Override
    public Product sellItem(Product product) throws ClassVendingMachinePersistenceException {
        if (product.getId().equals(onlyProduct.getId())) {
            return onlyProduct;
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<Product> getListProducts() throws ClassVendingMachinePersistenceException {
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(onlyProduct);
        return productList;
    }

    @Override
    public Product getProductByID(String id) throws ClassNotFoundException {
        if (id.equals(onlyProduct.getId())) {
            return onlyProduct;
        } else {
            return null;
        }
    }

    @Override
    public Product checkProductExistInventory(String id) {
        if (id.equals(onlyProduct.getId())) {
            return onlyProduct;
        } else {
            return null;
        }
    }

    @Override
    public Product addProduct(Product product) {
        if (product.getId().equals(onlyProduct.getId())) {
            return onlyProduct;
        } else {
            return null;
        }
    }

}
