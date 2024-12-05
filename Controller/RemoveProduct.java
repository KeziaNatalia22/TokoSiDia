package Controller;

import Modul.Dummy;
import Modul.Seller;
import Modul.User;


public class RemoveProduct {
    public boolean removeProduct (String idUser, String idProduct, boolean confirm){
        // Product product = Dummy.listProducts.get(idProduct);
        User user = Dummy.listUser.get(idUser);
        if (confirm) {
            if (user instanceof Seller) { 
                ((Seller) user).getProduct().remove(idProduct);
                return true;
            }
        }
        return false;
    }
}
