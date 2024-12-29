package View;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Modul.Product;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

public class DetailedProduct {
    public static void detailProduct(Product product){
        JFrame frame = new JFrame("Detail Barang");
        
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        final int FRAME_WIDTH = screenSize.width/2;
        final int FRAME_HEIGHT = screenSize.height/2;

        frame.setBounds(screenSize.width/2 - FRAME_WIDTH/2, screenSize.height/2 - FRAME_HEIGHT/2, FRAME_WIDTH, FRAME_HEIGHT); 
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        panel.setBackground(new Color(230, 238, 255)); 
        
        JLabel photoProduct = new JLabel();
        photoProduct.setBounds(0, 0, FRAME_WIDTH/2, FRAME_HEIGHT - FRAME_HEIGHT/10);
        ImageIcon icon = new ImageIcon("Photos/Seller/" + product.getPhotoProduct());
        Image img = icon.getImage().getScaledInstance(photoProduct.getWidth(), photoProduct.getHeight(), Image.SCALE_SMOOTH);
        photoProduct.setIcon(new ImageIcon(img));
        panel.add(photoProduct);
        
        JLabel name = new JLabel("Name: " + product.getName());
        name.setBounds(FRAME_WIDTH/2, FRAME_HEIGHT/10 * 2, FRAME_WIDTH/2, 30);
        panel.add(name);
        JLabel stock = new JLabel("Stock: " + Integer.toString(product.getStock()));
        stock.setBounds(FRAME_WIDTH/2, FRAME_HEIGHT/10 * 3, FRAME_WIDTH/2, 30);
        panel.add(stock);
        JLabel price = new JLabel("Harga: " + Double.toString(product.getPrice()));
        price.setBounds(FRAME_WIDTH/2, FRAME_HEIGHT/10 * 4, FRAME_WIDTH/2, 30);
        panel.add(price);
        JLabel discount = new JLabel("Diskon: " + Double.toString(product.getDiscount()));
        discount.setBounds(FRAME_WIDTH/2, FRAME_HEIGHT/10 * 5, FRAME_WIDTH/2, 30);
        panel.add(discount);
        JLabel seller = new JLabel("Seller: " + product.getSellerName());
        seller.setBounds(FRAME_WIDTH/2, FRAME_HEIGHT/10 * 6, FRAME_WIDTH/2, 30);
        panel.add(seller);

        frame.add(panel);
        frame.setVisible(true);
    }
}
