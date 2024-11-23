package Controller;

import Modul.Dummy;
import Modul.User;

public class ViewBalance {
    public double viewBalance (String idUser){
        User user = Dummy.listUser.get(idUser);

        return user.geteMoney();
    }
}
