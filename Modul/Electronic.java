package Modul;

import java.util.*;

public class Electronic extends Product implements Product_Interface{
    private Date warranty;
    private String manualBook, color;
    
    public Electronic(String name, String idProduct, double price, double discount, int stock, Date warranty,
            String manualBook, String color) {
        super(name, idProduct, price, discount, stock);
        this.warranty = warranty;
        this.manualBook = manualBook;
        this.color = color;
    }

    public Date getWarranty() {
        return warranty;
    }
    public void setWarranty(Date warranty) {
        this.warranty = warranty;
    }
    public String getManualBook() {
        return manualBook;
    }
    public void setManualBook(String manualBook) {
        this.manualBook = manualBook;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    
    @Override
    public Product addProduct (Product product) {
        Dummy.listProducts.add(product);
        return product;
    }
}