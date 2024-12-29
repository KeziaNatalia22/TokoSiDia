import Modul.*;
import View.*;

public class Main {
    public static void main(String[] args) {
        Seller user = new Seller("hehe", "0213912", "jeje", "231", "ad", 0, AccountStatus_Enum.ACTIVE, "", "sda");
        // MenuLogin login = new MenuLogin();
        // login.Menu();

        new MenuLogin();

        // new SearchedProduct("a");
        // new ProfileSeller(user);
        
        // new HomeBuyer(user);
    }
}
