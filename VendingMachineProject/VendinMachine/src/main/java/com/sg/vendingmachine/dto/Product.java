
package com.sg.vendingmachine.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    
    private String id;
    private String name;
    private BigDecimal price;
    private int numberItemsInventory;

    // added the default constructor
    public Product() {

    }

    public Product(String id) {
        this.id = id;
    }

    public Product(String id, String name, BigDecimal price, int numberItemsInventory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.numberItemsInventory = numberItemsInventory;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getNumberItemsInventory() {
        return numberItemsInventory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setNumberItemsInventory(int numberItemsInventory) {
        this.numberItemsInventory = numberItemsInventory;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.name);
        hash = 41 * hash + Objects.hashCode(this.price);
        hash = 41 * hash + this.numberItemsInventory;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (this.numberItemsInventory != other.numberItemsInventory) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.price, other.price);
    }

    @Override
    public String toString() {
        return "Product{" + "name=" + name + ", cost=" + price + ", numberItemsInventory=" + numberItemsInventory + '}';
    }  
}
