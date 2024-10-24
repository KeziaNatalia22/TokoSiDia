package Modul;

import java.util.HashMap;

public class Seller extends User implements SellerMethod, ProductMethod_Interface {
    private HashMap<String, Product> product= new HashMap<String, Product>();
    private String shopName, cityLocated;
    
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

}