
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Product;
import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOImplementation;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.*;

public class ClassVendingMachineDaoImpl implements ClassVendingMachineDao{
    
    private final String VENDING_MACHINE_FILE;
    public static final String DELIMITER = "::";    
    private final Map<String, Product> listProducts;
    private final UserIO io;
    Scanner sc ;
    
    public ClassVendingMachineDaoImpl(){
        this.listProducts = new HashMap<>();
        this.sc= new Scanner(System.in);
        this.io = new UserIOImplementation();
        VENDING_MACHINE_FILE = "VendingMachineLibrary.txt";
   }

    public ClassVendingMachineDaoImpl(String productTextFile){
        this.listProducts = new HashMap<>();
        this.sc= new Scanner(System.in);
        this.io = new UserIOImplementation();
        VENDING_MACHINE_FILE = productTextFile;
    }
    
    @Override
    public Product sellItem(Product product) throws ClassVendingMachinePersistenceException{
        this.loadRoster();        
        Product p = listProducts.get(product.getId());
        p.setNumberItemsInventory((p.getNumberItemsInventory())-1);
        this.writeRoster();
        return p;
    }
    
    @Override
    public ArrayList<Product> getListProducts() throws ClassVendingMachinePersistenceException {
        loadRoster();
        ArrayList<Product> listProductsInventory = new ArrayList<Product>();
        Collection<Product> values = listProducts.values();
        for(Product p: values){
            if(p.getNumberItemsInventory()>0){
                listProductsInventory.add(p);
            }
        }
        return listProductsInventory;
        //return new ArrayList<Product>(listProducts.values());        
    }
    
    private String marshallProduct(Product product){
        String productAsText = product.getId() + DELIMITER;
        productAsText += product.getName() + DELIMITER;
        productAsText += product.getPrice()+ DELIMITER;
        productAsText += product.getNumberItemsInventory();
        return productAsText;
    }
    
    private Product unmarshallProduct(String productAsText){    
        //Get the properties separated by the delimiter
        String[] productTokens = productAsText.split(DELIMITER);
        //Getting each property of the object
        String id = productTokens[0];
        String name = productTokens[1];        
        BigDecimal price = new BigDecimal(productTokens[2]);
        int numberItemsInventory = Integer.parseInt(productTokens[3]);
        //Creating the Product object from the String
        Product productFromFile = new Product(id, name, price, numberItemsInventory);
        //returning the string as an object
        return productFromFile;
    }
    
    private void loadRoster() throws ClassVendingMachinePersistenceException {
        Scanner scanner;
        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(VENDING_MACHINE_FILE)));
        } catch (FileNotFoundException e) {
            throw new ClassVendingMachinePersistenceException(
                    "-_- Could not load roster data into memory.", e);
        }
        String currentLine;
        Product currentProduct;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentProduct = unmarshallProduct(currentLine);
            listProducts.put(currentProduct.getId(), currentProduct);
        }
        scanner.close();
    }
    
    private void writeRoster() throws ClassVendingMachinePersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(VENDING_MACHINE_FILE));
        } catch (IOException e) {
            throw new ClassVendingMachinePersistenceException(
                    "Could not save product data.", e);
        }
        String productAsText;
        List<Product> productList = this.getListProducts();
        for (Product currentProduct : productList) {
            // turn a Product into a String
            productAsText = marshallProduct(currentProduct);
            // write the Product object to the file
            out.println(productAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
        }

    @Override
    public Product getProductByID(String id) throws ClassNotFoundException {        
        Product product ;
        try {
            loadRoster();
            product = listProducts.get(id);
        } catch (ClassVendingMachinePersistenceException ex) {
            return null;
        }
        if(product == null){
            return null;
//            throw new ClassNotFoundException("The product with that ID doesn't exit.");
        }else{
            return product;
        }
    }

    @Override
    // changed the return type
    public Product checkProductExistInventory(String id) throws ClassVendingMachineInventoryException{
        Product myProduct = new Product();
        if(listProducts.get(id).getNumberItemsInventory()== 0){
            throw new ClassVendingMachineInventoryException("");
        }
        return myProduct;
    }
    
}