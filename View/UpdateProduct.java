package View;

import javax.swing.*;


import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Modul.*;
import java.io.File;
import java.util.ArrayList;

public class UpdateProduct {

    static JPanel mainPanel;
    static JScrollPane scrollPane;
    JButton backButton;
    TokosiDiaFrame frame;
    Seller globalSeller;
    

    public UpdateProduct(Seller seller){
        updateProduct(seller);
    }

    public void updateProduct(Seller seller){
        globalSeller = seller;
        String username = seller.getName();
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
            new HomeSeller(seller);
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
        String price = Controller.RupiahFormatter.formatRupiah((int)product.getPrice());
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

        JLabel discountLabel = new JLabel("Discount: " + discount + "%");
        discountLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        discountLabel.setForeground(Color.GRAY);
        discountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailsPanel.add(discountLabel);

        card.add(detailsPanel, BorderLayout.SOUTH);

        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                card.setBackground(Color.decode("#D9EAFD"));
                detailsPanel.setBackground(Color.decode("#D9EAFD"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                card.setBackground(Color.WHITE);
                detailsPanel.setBackground(Color.WHITE);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                DetailedUpdateProduct.detailProduct(product, globalSeller);
            }
        });
    
        return card;
    }    

    
}
