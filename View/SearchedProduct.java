package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Modul.Product;
import java.awt.event.MouseAdapter;
import javax.swing.JButton;

public class SearchedProduct {
    JFrame frame;
    JPanel panelGrid;
    JPanel panelCol1;
    JPanel panelCol2;
    JPanel panelCol3;
    JScrollPane scrollPane;

    public SearchedProduct(String search) {
        searchedProduct(search);
    }

    public void searchedProduct(String search){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        final int FRAME_WIDTH = screenSize.width; // Screen width
        final int FRAME_HEIGHT = screenSize.height; // Screen height

        final int PRODUCT_PANEL_WIDTH = screenSize.width/3;
        final int PRODUCT_PANEL_HEIGHT = screenSize.height/2;


        frame = new JFrame("Hasil Pencarian Barang"); // Create frame and set title

        frame.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT); // Set frame bounds
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panelGrid = new JPanel();
        panelGrid.setLayout(new GridLayout(1, 3));
        panelGrid.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        panelGrid.setBackground(new Color(240, 248, 255)); // Light blue background

        panelCol1 = new JPanel();
        panelCol1.setLayout(new BoxLayout(panelCol1, BoxLayout.Y_AXIS));
        panelGrid.add(panelCol1);
        
        panelCol2 = new JPanel();
        panelCol2.setLayout(new BoxLayout(panelCol2, BoxLayout.Y_AXIS));
        panelGrid.add(panelCol2);
        
        panelCol3 = new JPanel();
        panelCol3.setLayout(new BoxLayout(panelCol3, BoxLayout.Y_AXIS));
        panelGrid.add(panelCol3);
        
        int offset = 0;
        // final int SHOW_MORE = 12;
        ArrayList<Product> searchedProduct = Controller.BuyerSection.searchProduct(search, offset);
        int i = 0;
        for (Product product : searchedProduct) {
            JPanel panelProduct = new JPanel();
            panelProduct.setLayout(null);
            panelProduct.setPreferredSize(new Dimension(PRODUCT_PANEL_WIDTH, PRODUCT_PANEL_HEIGHT));
            panelProduct.setBackground(new Color(230, 238, 255));
            
            JLabel photoProduct = new JLabel();
            photoProduct.setBounds(0, PRODUCT_PANEL_HEIGHT/10, PRODUCT_PANEL_WIDTH/2, PRODUCT_PANEL_HEIGHT - PRODUCT_PANEL_HEIGHT/10);
            ImageIcon icon = new ImageIcon("Photos/Seller/" + product.getPhotoProduct());
            Image img = icon.getImage().getScaledInstance(photoProduct.getWidth(), photoProduct.getHeight(), Image.SCALE_SMOOTH);
            photoProduct.setIcon(new ImageIcon(img));
            panelProduct.add(photoProduct);
            
            JLabel name = new JLabel("Nama: " + product.getName());
            name.setBounds(PRODUCT_PANEL_WIDTH/2, PRODUCT_PANEL_HEIGHT/10 * 3, PRODUCT_PANEL_WIDTH/2, 30);
            panelProduct.add(name);
            JLabel stock = new JLabel("Stock: " + Integer.toString(product.getStock()));
            stock.setBounds(PRODUCT_PANEL_WIDTH/2, PRODUCT_PANEL_HEIGHT/10 * 4, PRODUCT_PANEL_WIDTH/2, 30);
            panelProduct.add(stock);
            JLabel price = new JLabel("Harga: " + Double.toString(product.getPrice()));
            price.setBounds(PRODUCT_PANEL_WIDTH/2, PRODUCT_PANEL_HEIGHT/10 * 5, PRODUCT_PANEL_WIDTH/2, 30);
            panelProduct.add(price);
            JLabel discount = new JLabel("Diskon: " + Double.toString(product.getDiscount()));
            discount.setBounds(PRODUCT_PANEL_WIDTH/2, PRODUCT_PANEL_HEIGHT/10 * 6, PRODUCT_PANEL_WIDTH/2, 30);
            panelProduct.add(discount);
            JLabel seller = new JLabel("Seller: " + product.getSellerName());
            seller.setBounds(PRODUCT_PANEL_WIDTH/2, PRODUCT_PANEL_HEIGHT/10 * 7, PRODUCT_PANEL_WIDTH/2, 30);
            panelProduct.add(seller);

            if (i % 3 == 0) {
                panelCol1.add(panelProduct);
                i++;
            }
            else if (i % 3 == 1) {
                panelCol2.add(panelProduct);
                i++;
            }
            else{
                panelCol3.add(panelProduct);
                i++;
            }
            panelProduct.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    DetailedProduct.detailProduct(product);
                }
            }); 
        }

        JButton showMore = new JButton("SHOW MORE");
        showMore.setPreferredSize(new Dimension(panelCol2.getWidth(), 30));
        panelCol2.add(showMore);

        panelCol2.revalidate(); 
        panelCol2.repaint();

        scrollPane = new JScrollPane(panelGrid);

        frame.add(scrollPane);
        frame.setVisible(true);
    }
}
