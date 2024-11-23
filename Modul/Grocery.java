package Modul;

import java.util.Date;

public class Grocery extends Product implements Product_Interface{
    private Date expDate, productionDate;

    public Grocery(String name, String idProduct, double price, double discount, int stock, Date expDate,
            Date productionDate) {
        super(name, idProduct, price, discount, stock);
        this.expDate = expDate;
        this.productionDate = productionDate;
    }

    public Date getExpDate() {
        return expDate;
    }
    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }
    public Date getProductionDate() {
        return productionDate;
    }
    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    @Override
    public Product addProduct (Product product) {
        Dummy.listProducts.add(product);
        return product;
    }
}