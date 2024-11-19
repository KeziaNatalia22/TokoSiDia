package Modul;

import java.util.HashMap;

public class Seller extends User implements User_Interface{
    private HashMap<String, Product> product= new HashMap<String, Product>();
    private String shopName, cityLocated;
    
    public Seller(HashMap<String, Product> product, String shopName, String cityLocated) {
        this.product = product;
        this.shopName = shopName;
        this.cityLocated = cityLocated;
    }

    public HashMap<String, Product> getProduct() {
        return product;
    }
    public void setProduct(HashMap<String, Product> product) {
        this.product = product;
    }
    public String getShopName() {
        return shopName;
    }
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
    public String getCityLocated() {
        return cityLocated;
    }
    public void setCityLocated(String cityLocated) {
        this.cityLocated = cityLocated;
    }
    
    public void addStock(int amount, String idProduct){
        Product searchProduct = product.get(idProduct);
        
        if(searchProduct != null)
            searchProduct.setStock(searchProduct.getStock()+amount);
    }

    
    public void reduceStock(int amount, String idProduct){
        Product searchProduct = product.get(idProduct);

        if(searchProduct != null)
            searchProduct.setStock(searchProduct.getStock()-amount);
    }
}