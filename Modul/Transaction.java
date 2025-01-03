package Modul;
import java.util.Date;
import java.util.ArrayList;

public class Transaction {
    private ArrayList<Product> listProduct = new ArrayList<Product>();
    private ArrayList<Integer> qty = new ArrayList<Integer>();
    private String idTransaksi, shopName, buyerName;
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
    
    public Transaction(String idTransaksi, ArrayList<Product> listProduct, ArrayList<Integer> listQty, String shopName, String buyerName,
            ShipmentStatus_Enum shipmentStatus, Date transactionDate) {
        this.idTransaksi = idTransaksi;
        this.listProduct = listProduct;
        this.qty = listQty;
        this.shopName = shopName;
        this.buyerName = buyerName;
        this.shipmentStatus = shipmentStatus;
        this.transactionDate = transactionDate;
    }
    
    public ArrayList<Integer> getListQty() {
        return qty;
    }
    public void setListQty(ArrayList<Integer> listQty) {
        this.qty = listQty;
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

    public String getIdTransaksi() {
        return idTransaksi;
    }
    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
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
