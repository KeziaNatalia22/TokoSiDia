package Modul;

import java.util.ArrayList;

public class Buyer extends User implements BuyerMethod_Interface {
    private ArrayList<Product> cart= new ArrayList<Product>();
    private Alamat alamat;

    public ArrayList<Product> getCart() {
        return cart;
    }
    public void setCart(ArrayList<Product> cart) {
        this.cart = cart;
    }
    public Alamat getAlamat() {
        return alamat;
    }
    public void setAlamat(Alamat alamat) {
        this.alamat = alamat;
    }
}
    