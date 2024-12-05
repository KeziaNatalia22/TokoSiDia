package Controller;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import java.util.Date;
import Modul.Book;
import Modul.Clothing;
import Modul.ClothingSize_Enum;
import Modul.Dummy;
import Modul.Electronic;
import Modul.Grocery;
import Modul.Product;
import Modul.User;
import Modul.Seller;

public class UpdateProduct {

    public static boolean updateProductBook(JTextField idUser, JTextField idProduct, boolean confirm,
            JTextField titleField, JTextField authorField, JTextField pageNumField, JTextField synopsisField,
            JTextField priceField, Date releaseDate, JTextField stockField) {
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

            if (DBController.updateBook(book)) {
                berhasil = true;
            }
        }

        return berhasil;
    }

    public boolean updateProductClothing(JTextField idUser, JTextField name, JTextField price, JTextField idProduct,
            boolean confirm, JTextField dc, JTextField stock, JRadioButton size,JTextField color) {
        boolean berhasil = false;
            
        ClothingSize_Enum sz;

        User user = Dummy.listUser.get(idUser.getText());

        Product product = ((Seller) user).getProduct().get(idProduct.getText());
        if (product instanceof Clothing) {
            Clothing clothing = (Clothing) product;

            clothing.setName(name.getText());
            clothing.setIdProduct(idProduct.getText());
            clothing.setPrice(Double.parseDouble(price.getText()));
            clothing.setDiscount(Double.parseDouble(dc.getText()));
            clothing.setStock(Integer.parseInt(stock.getText()));

            String sizeInput = size.getText().toUpperCase();

            if (sizeInput == "L") {
                sz = ClothingSize_Enum.L;
            } else if(sizeInput == "M") {
                sz = ClothingSize_Enum.M;
            } else {
                sz = ClothingSize_Enum.S;
            }

            clothing.setSize(sz); 
            clothing.setColor(color.getText());

            if (DBController.updateClothing(clothing)) {
                berhasil = true;
            } 
        }
    
            return berhasil;
    }

    public boolean updateElectronic(JTextField idUser, JTextField idProduct, JTextField name, JTextField price, JTextField discount, JTextField stock, Date waranty, JTextField manualBook, JTextField color ){
        // String name, String idProduct, double price, double discount, int stock, Date warranty,
        //String manualBook, String color
        boolean berhasil = false;

        User user = Dummy.listUser.get(idUser.getText());

        Product product = ((Seller) user).getProduct().get(idProduct.getText());
        if (product instanceof Electronic) {
            Electronic electronic = (Electronic) product;

            electronic.setName(name.getText());
            electronic.setPrice(Double.parseDouble(price.getText()));
            electronic.setDiscount(Double.parseDouble(discount.getText()));
            electronic.setStock(Integer.parseInt(stock.getText()));
            electronic.setWarranty(waranty);
            electronic.setManualBook(manualBook.getText());
            electronic.setColor(color.getText());
            
            if (DBController.updateElectronic(electronic)) {
                berhasil = true;
            }
        } 

        return berhasil;

    }

    public boolean updateGrocery(JTextField idUser, JTextField idProduct, JTextField name, JTextField price, JTextField discount, JTextField stock, Date expDate, Date productionDate){
        // String name, String idProduct, double price, double discount, int stock, Date warranty,
        //String manualBook, String color
        boolean berhasil = false;

        User user = Dummy.listUser.get(idUser.getText());

        Product product = ((Seller) user).getProduct().get(idProduct.getText());
        if (product instanceof Grocery) {
            Grocery grocery = (Grocery) product;

            grocery.setName(name.getText());
            grocery.setPrice(Double.parseDouble(price.getText()));
            grocery.setDiscount(Double.parseDouble(discount.getText()));
            grocery.setStock(Integer.parseInt(stock.getText()));
            grocery.setExpDate(expDate);
            grocery.setProductionDate(productionDate);
            
            if (DBController.updateGrocery(grocery)) {
                berhasil = true;
            }
        } 

        return berhasil;

    }

}