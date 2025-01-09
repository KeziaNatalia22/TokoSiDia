package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import org.w3c.dom.events.MouseEvent;

import Controller.ProductSection;
import Modul.Buyer;
import Modul.Grocery;
import Modul.Product;
import Modul.Seller;
import Modul.SingletonManager;
import Modul.TokosiDiaFrame;

public class RemoveProduct {
    static JPanel mainPanel;
    static JScrollPane scrollPane;
    JButton backButton;
    TokosiDiaFrame frame;
    

    public RemoveProduct(){
        removeProduct();
    }

    public void removeProduct(){
        SingletonManager login = SingletonManager.getInstance();
        Seller user = (Seller) login.getUser();
        String username = user.getName();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        frame = new TokosiDiaFrame("Searched Product");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(screenSize.width/2 - 900/2, screenSize.height/2 - 600/2, 900, 600); 
        frame.setResizable(false);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 4, 10, 10));
        mainPanel.setBackground(Color.decode("#D9DFC6"));

        addProducts(username);

        scrollPane = new JScrollPane(mainPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        backButton.setBackground(new Color(220, 220, 220));
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> {
            frame.dispose();
            new HomeSeller();
        });

        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.add(scrollPane, BorderLayout.CENTER);
        wrapperPanel.add(backButton, BorderLayout.SOUTH);

        frame.add(wrapperPanel);
        frame.setVisible(true);
    }

    private void addProducts(String sellerName) {
        ArrayList<Product> searchedProduct = Controller.RemoveProduct.productList(sellerName);
        for (Product product : searchedProduct) {
            mainPanel.add(createProductCard(product));
        }
        mainPanel.revalidate(); 
        mainPanel.repaint(); 
    }
    

    private JPanel createProductCard(Product product) {
        String productName = product.getName();
        int discount = (int)(product.getDiscount());
        String originalPrice = Controller.RupiahFormatter.formatRupiah((int)product.getPrice());
        String price = Controller.RupiahFormatter.formatRupiah((int)(product.getPrice() * (100 - discount)/100));
        String photo = product.getPhotoProduct();
    
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        card.setBackground(Color.WHITE);
        card.setPreferredSize(new Dimension(180, 250));

        JLabel imageLabel = new JLabel();
        ImageIcon icon = new ImageIcon("Photos/Seller/" + photo);
        Dimension dimImg = new Dimension(icon.getIconWidth(), icon.getIconHeight());
        Dimension dimBound = new Dimension(200, 180);
        Dimension scalledImg = Controller.ImageScaling.getScaledDimension(dimImg, dimBound);
        Image img = icon.getImage().getScaledInstance(scalledImg.width, scalledImg.height, Image.SCALE_REPLICATE);
        imageLabel.setIcon(new ImageIcon(img));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(imageLabel, BorderLayout.CENTER);
    
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(Color.WHITE);
    
        JLabel nameLabel = new JLabel(productName);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailsPanel.add(nameLabel);
    
        JLabel priceLabel = new JLabel("Price: " + price);
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailsPanel.add(priceLabel);
    
        if (!originalPrice.equals(price)) {
            JLabel originalPriceLabel = new JLabel("Original: " + originalPrice);
            originalPriceLabel.setFont(new Font("Arial", Font.ITALIC, 12));
            originalPriceLabel.setForeground(Color.GRAY);
            originalPriceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            detailsPanel.add(originalPriceLabel);
        }

        JButton removeButton = new JButton("Remove");
        removeButton.setBackground(Color.RED);
        removeButton.setForeground(Color.white);
        removeButton.setSize(190, 40);
        removeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogResult = JOptionPane.showConfirmDialog (null, "Are You Sure You Want To Delete " + product.getName() + "?","Warning",JOptionPane.YES_NO_OPTION);
                if(dialogResult == JOptionPane.YES_OPTION){
                    frame.dispose();
                    Controller.RemoveProduct.removeProduct(product.getIdProduct());
                    new RemoveProduct();
                }
            }
        });

        detailsPanel.add(removeButton);
    
        card.add(detailsPanel, BorderLayout.SOUTH);
    
        return card;
    }    
}
