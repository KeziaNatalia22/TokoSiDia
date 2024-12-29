package Modul;

public class Clothing extends Product implements Product_Interface{
    private ClothingSize_Enum size;
    private String color;

    public Clothing(String color, ClothingSize_Enum size, String idProduct, String sellerName, String name, int stock, double discount, double price, String photoProduct) {
        super(idProduct, sellerName, name, stock, discount, price, photoProduct);
        this.color = color;
        this.size = size;
    }    

    public ClothingSize_Enum getSize() {
        return size;
    }
    public void setSize(ClothingSize_Enum size) {
        this.size = size;
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