package Modul;

import java.util.ArrayList;
import java.util.HashMap;

public class SingletonManager {
    private static SingletonManager instance;
    private User user;
    private HashMap<String, HashMap<Product, Integer>> cart = new HashMap<>();
    private ArrayList<Transaction> transactions = new ArrayList<>();

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
    public HashMap<String, HashMap<Product, Integer>> getCart() {
        return cart;
    }
    public void setCart(HashMap<String, HashMap<Product, Integer>> cart) {
        this.cart = cart;
    }
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }
}
