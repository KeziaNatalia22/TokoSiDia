package Controller;

import Modul.User;

import java.util.Map;

import Modul.Dummy;
import Modul.Transaction;
import Modul.Seller;

public class ViewIncome {
    public double viewIncome (String idUser){
        double income = 0;
        for (Map.Entry<String, Transaction> entry : Dummy.listTransaction.entrySet()) {
            User user = Dummy.listUser.get(idUser);
            if (user instanceof Seller) {
                ((Seller) user).;
            }
        }
    }
}
