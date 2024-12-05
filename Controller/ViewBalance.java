package Controller;

import Modul.Dummy;
import Modul.User;
import javax.swing.*;


public class ViewBalance {
    public double viewBalance (JTextField idUser){
        User user = Dummy.listUser.get(idUser.getText());

        return user.geteMoney();
    }
}
