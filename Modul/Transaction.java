package Modul;
import java.util.Date;
import java.util.ArrayList;

public class Transaction {
    private ArrayList<Product> listProduct = new ArrayList<Product>();
    private String shopName, buyerName;
    private ShipmentStatus_Enum shipmentStatus;
    private Date transactionDate;

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Transaction(){
        
    }
    
    public Transaction(ArrayList<Product> listProduct, String shopName, String buyerName,
            ShipmentStatus_Enum shipmentStatus, Date transactionDate) {
        this.listProduct = listProduct;
        this.shopName = shopName;
        this.buyerName = buyerName;
        this.shipmentStatus = shipmentStatus;
        this.transactionDate = transactionDate;
    }
    
    public ArrayList<Product> getListProduct() {
        return listProduct;
    }
    public void setListProduct(ArrayList<Product> listProduct) {
        this.listProduct = listProduct;
    }
    public String getShopName() {
        return shopName;
    }
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
    public String getBuyerName() {
        return buyerName;
    }
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }
    public ShipmentStatus_Enum getShipmentStatus() {
        return shipmentStatus;
    }
    public void setShipmentStatus(ShipmentStatus_Enum shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }
}
