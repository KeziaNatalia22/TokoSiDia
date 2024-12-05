package Controller;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import java.util.Date;

import Modul.Book;
import Modul.Clothing;
import Modul.Dummy;
import Modul.Electronic;
import Modul.Grocery;
import Modul.Product;
import Modul.User;
import Modul.Seller;

public class UpdateProduct { // di page update product lalu user input iduser dan updatenya apa
        static Scanner scan = new Scanner(System.in);
        static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Format tanggal

        public boolean updateProductBook (JTextField idUser, JTextField idProduct, boolean confirm, JTextField tittle, JTextField author, JTextField pageNum, JTextField synopsis, JTextField price, Date releaseDate, JTextField stock){
        String title, authors, sinopsis;
        Double prices;
        int stocks, pageNums;
        Date releasDate;

        User user = Dummy.listUser.get(idUser.getText());
        
        if (user instanceof Seller) {
            Product product = ((Seller) user).getProduct().get(idProduct.getText());
            if (product instanceof Book) {
                Book book = (Book) product;
                System.out.print("Masukan tittle :");
                title = tittle.getText();
                book.setName(title);

                System.out.print("Masukan author :");
                authors = author.getText();
                book.setAuthor(authors);
                
                System.out.print("Masukan page num :");
                pageNums = Integer.parseInt(pageNum.getText());
                book.setPageNum(pageNums);
                
                System.out.print("Masukan synopsis :");
                sinopsis = synopsis.getText();
                book.setSynopsis(sinopsis);
                
                System.out.print("Masukan price :");
                prices = Double.parseDouble(price.getText());
                book.setPrice(prices);
                
                System.out.print("Masukan release date :");
                book.setReleaseDate(releaseDate);
                
                System.out.print("Masukan stock :");
                stocks = Integer.parseInt(pageNum.getText());
                book.setStock(stocks);

                
            }
            Book books = new Book(sinopsis, title, stocks, stocks, stocks, authors, sinopsis, releaseDate, pageNums);
            
            return (DBController.updateBook(books));
            
        }
    }

        public boolean updateProductClothing (JTextField idUser, JTextField name, JTextField price, JTextField idProduct, boolean confirm, JTextField dc, JTextField stock, JRadioButton size, Date releaseDate, JTextField color){
            String names, authors, sinopsis, idProducts;
            Double discount;
            int stocks, pageNums, prices;
            Date releasDate;
    
            User user = Dummy.listUser.get(idUser.getText());
            
            // String name, String idProduct, double price, double discount, int stock, ClothingSize_Enum size,
            //String color
            
            if (user instanceof Seller) {
                Product product = ((Seller) user).getProduct().get(idProduct.getText());
                if (product instanceof Clothing) {
                    Clothing clothing = (Clothing) product;
                    System.out.print("Masukan name :");
                    names = name.getText();
                    clothing.setName(names);
    
                    System.out.print("Masukan id product :");
                    idProducts = idProduct.getText();
                    clothing.setIdProduct(idProducts);
                    
                    System.out.print("Masukan price :");
                    prices = Integer.parseInt(price.getText());
                    clothing.setPrice(prices);
                    
                    System.out.print("Masukan discount :");
                    discount = Double.parseDouble(dc.getText());
                    clothing.setDiscount(pageNums);
                    
                    System.out.print("Masukan stock :");
                    stocks = Integer.parseInt(stock.getText());
                    clothing.setStock(stocks);
                    
                    System.out.print("Masukan size :");
                    if (confirm) {
                        
                    }
                    clothing.setSize(null);
                    
    
                    
                }
                
                return (DBController.updateBook(books));
                
            }
    
    }
}
