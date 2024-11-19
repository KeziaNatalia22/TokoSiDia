package Modul;

public class Clothing extends Product implements Product_Interface{
    private ClothingSize_Enum size;
    private String color;

    public Clothing(String name, String idProduct, double price, double discount, int stock, ClothingSize_Enum size,
            String color) {
        super(name, idProduct, price, discount, stock);
        this.size = size;
        this.color = color;
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
}