package Modul;

import java.util.ArrayList;
import java.util.HashMap;

public class Buyer extends User implements User_Interface{
    private ArrayList<Product> cart= new ArrayList<Product>();
    private String alamat;

    public Buyer(String name, String phoneNum, String email, String password, String photoPath,
            double eMoney, AccountStatus_Enum accStat, String alamat) {
        super(name, phoneNum, email, password, photoPath, eMoney, accStat);
        this.alamat = alamat;
    }

    public ArrayList<Product> getCart() {
        return cart;
    }
    public void setCart(ArrayList<Product> cart) {
        this.cart = cart;
    }
    public String getAlamat() {
        return alamat;
    }
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
    