
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.service.ClassVendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Product;
import com.sg.vendingmachine.service.ClassNoItemInventoryException;
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
import java.util.stream.Collectors;

public class ClassVendingMachineDaoImpl implements ClassVendingMachineDao{
    
    public static String VENDING_MACHINE_FILE;
    public static final String DELIMITER = "::";    
    private final Map<String, Product> listProducts = new HashMap<>();
    private final UserIO io = new UserIOImplementation();    
    
    public ClassVendingMachineDaoImpl(){
        this.VENDING_MACHINE_FILE = "VendingMachineLibrary.txt";
    }
    
    public ClassVendingMachineDaoImpl(String library){
        this.VENDING_MACHINE_FILE = library;
    }
    
    @Override
    public Product sellItem(Product product) throws ClassVendingMachinePersistenceException{
        this.loadLibrary();
        Product p = listProducts.get(product.getId());
        p.setNumberItemsInventory(p.getNumberItemsInventory()-1);
        this.writeLibrary();
        return p;
    }
    
    @Override
    public ArrayList<Product> getListProducts() throws ClassVendingMachinePersistenceException {
        loadLibrary();
        //-listProducts.values() retrieves a Collection of all the Product objects stored in the listProducts map.
        return listProducts.values().stream()
                //-filters the stream, keeping only the Product objects where the numberItemsInventory is greater than 0.
                .filter(p -> p.getNumberItemsInventory() >= 0)
                //-collects the filtered Product objects into an ArrayList using the Collectors.toCollection method.
                .collect(Collectors.toCollection(ArrayList::new));
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
    
    public void loadLibrary() throws ClassVendingMachinePersistenceException {
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
    
    private void writeLibrary() throws ClassVendingMachinePersistenceException {
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
            loadLibrary();
            product = listProducts.get(id);
        } catch (ClassVendingMachinePersistenceException ex) {
            return null;
        }
        if(product == null){
            throw new ClassNotFoundException("The product with that ID doesn't exit.");
        }else{
            return product;
        }
    }

    @Override
    public Product checkProductExistInventory(String id) throws ClassNoItemInventoryException{
        Product myProduct = new Product();
        if(listProducts.get(id).getNumberItemsInventory()== 0){
            throw new ClassNoItemInventoryException("");
        }
        return myProduct;
        
    }
    
    @Override
    public Product addProduct(Product product) throws Exception {
        listProducts.put(product.getId(), product);
        writeLibrary();
        return product;
    }
   
}