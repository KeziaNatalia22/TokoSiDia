package View;

import javax.swing.*;

import Modul.Product;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class SearchedP {
    static JPanel mainPanel;
    static JScrollPane scrollPane;
    static int offset = 0;
    static int i = 0;
    final static int SHOW_MORE = 8;

    public ProductSection(String search){
        searchedProduct(search);
    }

    public void searchedProduct(String search) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        JFrame frame = new JFrame("Searched Product");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(screenSize.width/2 - 800/2, screenSize.height/2 - 600/2, 800, 600); 
        frame.setResizable(false);

        // Main panel for product cards
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 4, 10, 10)); // Flexible rows, 4 columns
        mainPanel.setBackground(Color.WHITE);

        // Adding initial product cards
        addFirst8Searched(search);

        // Scroll pane for vertical scrolling
        scrollPane = new JScrollPane(mainPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Create a "Show More" button
        JButton showMoreButton = new JButton("Show More");
        showMoreButton.setFont(new Font("Arial", Font.PLAIN, 14));
        showMoreButton.setBackground(new Color(220, 220, 220));
        showMoreButton.setFocusPainted(false);
        showMoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMoreProducts(search); // Add more products when the button is clicked
            }
        });

        // Wrapper panel to include both scrollPane and "Show More" button
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.add(scrollPane, BorderLayout.CENTER);
        wrapperPanel.add(showMoreButton, BorderLayout.SOUTH);

        frame.add(wrapperPanel);
        frame.setVisible(true);
    }

    // Method to add the initial set of product cards
    private static void addFirst8Searched(String search) {
        ArrayList<Product> searchedProduct = Controller.BuyerSection.searchProduct(search, offset);
        for (Product product : searchedProduct) {
            mainPanel.add(createProductCard(product));
        }
    }

    // Method to add more products dynamically
    private static void addMoreProducts(String search) {
        offset += SHOW_MORE;

        ArrayList<Product> searchedProduct = Controller.BuyerSection.searchProduct(search, offset);
        for (Product product : searchedProduct) {
            mainPanel.add(createProductCard(product));
        }

        mainPanel.revalidate(); // Revalidate to refresh the panel
        mainPanel.repaint(); // Repaint for proper rendering
    }

    // Method to create a product cards
    private static JPanel createProductCard(Product product) {
        String productName = product.getName();
        String originalPrice = Controller.RupiahFormatter.formatRupiah((int)product.getPrice());
        String price = Controller.RupiahFormatter.formatRupiah((int)(product.getPrice() * (100 - product.getDiscount())/100));
        int discount = (int)(product.getDiscount());
        String sellerName = product.getSellerName();
        String photo = product.getPhotoProduct();

        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        card.setBackground(Color.WHITE);
        
        // Set a fixed size for the card
        card.setPreferredSize(new Dimension(180, 250)); // Width: 180px, Height: 250px

        // Discount badge
        if (discount > 0) {
            JLabel discountLabel = new JLabel(discount + "% OFF");
            discountLabel.setForeground(Color.WHITE);
            discountLabel.setOpaque(true);
            discountLabel.setBackground(Color.RED);
            discountLabel.setHorizontalAlignment(SwingConstants.CENTER);
            card.add(discountLabel, BorderLayout.NORTH);
        }

        // Product image (placeholder for now)
        JLabel imageLabel = new JLabel();
        ImageIcon icon = new ImageIcon("Photos/Seller/" + photo);
        Dimension dimImg = new Dimension(icon.getIconWidth(), icon.getIconHeight());
        Dimension dimBound = new Dimension(200, 200);
        Dimension scalledImg = Controller.Image.getScaledDimension(dimImg, dimBound);
        Image img = icon.getImage().getScaledInstance(scalledImg.width, scalledImg.height, Image.SCALE_REPLICATE);
        imageLabel.setIcon(new ImageIcon(img));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(imageLabel, BorderLayout.CENTER);

        // Product details
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(Color.WHITE);

        JLabel nameLabel = new JLabel(productName);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        detailsPanel.add(nameLabel);

        JLabel priceLabel = new JLabel("Price: " + price);
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        detailsPanel.add(priceLabel);

        if (!originalPrice.equals("Rp0")) {
            JLabel originalPriceLabel = new JLabel("Original: " + originalPrice);
            originalPriceLabel.setFont(new Font("Arial", Font.ITALIC, 12));
            originalPriceLabel.setForeground(Color.GRAY);
            detailsPanel.add(originalPriceLabel);
        }

        JLabel storeLabel = new JLabel("Store: " + sellerName);
        storeLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        storeLabel.setForeground(Color.BLUE);
        detailsPanel.add(storeLabel);

        card.add(detailsPanel, BorderLayout.SOUTH);

        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                DetailedProduct.detailProduct(product);
            }
        });

        return card;
    }

}
