package Modul;

public interface BuyerMethod_Interface {
    void addToCart(String idProduct);
    void viewCart();
    void removeFromCart(String idProduct);
    void checkout();
    void viewTransactions();
    void topUp(double eMoney);
    void viewBalance();
    void searchShop(String shopName);
    void searchItem(String itemName);
    
}