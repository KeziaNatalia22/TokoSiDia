package Modul;

import java.util.ArrayList;
import java.util.HashMap;

public class SingletonManager {
    private static SingletonManager instance;
    private User user;
    private HashMap<String, ArrayList<Product>> cart;
    private ArrayList<Transaction> transactions;

    SingletonManager(){
    }

    public static SingletonManager getInstance() {
        if (instance == null) {
            instance = new SingletonManager();
        }
        return instance;
    }
    public static void setInstance(SingletonManager instance) {
        SingletonManager.instance = instance;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public HashMap<String, ArrayList<Product>> getCart() {
        return cart;
    }
    public void setCart(HashMap<String, ArrayList<Product>> cart) {
        this.cart = cart;
    }
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }
}
