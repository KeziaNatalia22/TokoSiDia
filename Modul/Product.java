package Modul;

public abstract class Product {
    private String idProduct, sellerName, name;
    private int stock;
    private double discount, price;
    private String photoProduct;
    
    public Product(String idProduct, String sellerName, String name, int stock, double discount, double price,
            String photoProduct) {
        this.idProduct = idProduct;
        this.sellerName = sellerName;
        this.name = name;
        this.stock = stock;
        this.discount = discount;
        this.price = price;
        this.photoProduct = photoProduct;
    }

    public String getIdProduct() {
        return idProduct;
    }
    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }
    public String getSellerName() {
        return sellerName;
    }
    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public double getDiscount() {
        return discount;
    }
    public void setDiscount(double discount) {
        this.discount = discount;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getPhotoProduct() {
        return photoProduct;
    }
    public void setPhotoProduct(String photoProduct) {
        this.photoProduct = photoProduct;
    }
}   

