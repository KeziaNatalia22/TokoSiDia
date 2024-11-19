package Modul;

public abstract class Product {
    private String name, idProduct;
    private double price, discount;
    private int stock;

    public Product(String name, String idProduct, double price, double discount, int stock) {
        this.name = name;
        this.idProduct = idProduct;
        this.price = price;
        this.discount = discount;
        this.stock = stock;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIdProduct() {
        return idProduct;
    }
    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public double getDiscount() {
        return discount;
    }
    public void setDiscount(double discount) {
        this.discount = discount;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }

    
}   

