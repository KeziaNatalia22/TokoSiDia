package Modul;

import java.util.HashMap;

public class SingletonManager {
    private static SingletonManager instance;
    private User user;
    private HashMap<String, HashMap<Product, Integer>> cart = new HashMap<>();

    SingletonManager(){
    }

    public static SingletonManager getInstance() {
        if (instance == null) {
            instance = new SingletonManager();
        }
        return instance;
    }

    public static void removeInstance (){
        instance = null;
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
}
