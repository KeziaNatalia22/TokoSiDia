package Modul;

import java.util.*;

public class Electronic extends Product {
    private Date warranty;
    private String manualBook, color;
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
    
}