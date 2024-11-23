package Controller;

import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;

import Modul.Book;
import Modul.Clothing;
import Modul.Dummy;
import Modul.Electronic;
import Modul.Grocery;
import Modul.Product;
import Modul.User;
import Modul.Seller;

public class UpdateProduct {
        static Scanner scan = new Scanner(System.in);
        static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); // Format tanggal

        public void updateProduct (String idUser, String idProduct, boolean confirm){
        User user = Dummy.listUser.get(idUser);
        
        if (user instanceof Seller) {
            Product product = ((Seller) user).getProduct().get(idProduct);
            if (product instanceof Book) {
                Book book = (Book) product;
                System.out.print("Masukan tittle :");
                book.setName(scan.nextLine());
                System.out.print("Masukan author :");
                book.setAuthor(scan.nextLine());
                System.out.print("Masukan page num :");
                book.setPageNum(scan.nextInt());
                System.out.print("Masukan synopsis :");
                book.setSynopsis(scan.nextLine());
                System.out.print("Masukan price :");
                book.setPrice(scan.nextDouble());
                System.out.print("Masukan release date :");
                Date releaseDate = dateFormat.parse(scan.nextLine());
                book.setReleaseDate(releaseDate);
                System.out.print("Masukan stock :");
                book.setStock(scan.nextInt());
            } else if (product instanceof Clothing){
    
            } else if (product instanceof Electronic){
    
            } else {
                
            }
        }
    }
}
