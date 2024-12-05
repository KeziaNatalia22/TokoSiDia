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

    public static boolean updateProductBook(JTextField idUser, JTextField idProduct, boolean confirm, JTextField titleField, JTextField authorField, JTextField pageNumField, JTextField synopsisField, JTextField priceField, Date releaseDate, JTextField stockField) {
        boolean berhasil = false;
        User user = Dummy.listUser.get(idUser.getText());

        Product product = ((Seller) user).getProduct().get(idProduct.getText());
        if (product instanceof Book) {
            Book book = (Book) product;
            
            book.setName(titleField.getText());
            book.setAuthor(authorField.getText());
            book.setPageNum(Integer.parseInt(pageNumField.getText()));
            book.setSynopsis(synopsisField.getText());
            book.setPrice(Double.parseDouble(priceField.getText()));
            book.setReleaseDate(releaseDate);
            book.setStock(Integer.parseInt(stockField.getText()));
            
            if(DBController.updateBook(book)){
                berhasil = true;
            }
        }

        return berhasil;
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

