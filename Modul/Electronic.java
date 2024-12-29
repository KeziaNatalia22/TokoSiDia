package Modul;

import java.util.*;

public class Electronic extends Product implements Product_Interface{
    private Date warranty;
    private String manualBook, color;

    public Electronic(String color, String manualBook, Date warranty, String idProduct, String sellerName, String name, int stock, double discount, double price, String photoProduct) {
        super(idProduct, sellerName, name, stock, discount, price, photoProduct);
        this.color = color;
        this.manualBook = manualBook;
        this.warranty = warranty;
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