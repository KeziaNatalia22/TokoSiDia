package Modul;

import java.util.HashMap;

public class Seller extends User implements SellerMethod_Interface, ProductMethod_Interface {
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

    @Override
    public void addStock(int amount, String idProduct){
        Product searchProduct = product.get(idProduct);
        
        if(searchProduct != null)
            searchProduct.setStock(searchProduct.getStock()+amount);
    }

    @Override
    public void reduceStock(int amount, String idProduct){
        Product searchProduct = product.get(idProduct);

        if(searchProduct != null)
            searchProduct.setStock(searchProduct.getStock()-amount);
    }
}