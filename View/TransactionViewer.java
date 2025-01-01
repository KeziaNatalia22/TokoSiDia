package View;

import javax.swing.*;
import Controller.HistoryTransaction;
import java.awt.*;
import java.util.List;
import Modul.*; 

public class TransactionViewer {
    JFrame frame;
    JPanel panel;

    public TransactionViewer(Buyer user) {
        displayTransaction(user);
    }

    public void displayTransaction(Buyer user) {
        Transaction transaction = HistoryTransaction.transactionBuyer(user.getName());
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        final int FRAME_WIDTH = 600;
        final int FRAME_HEIGHT = 800;

        int startX = screenWidth / 2 - (FRAME_WIDTH / 2);
        int startY = screenHeight / 2 - (FRAME_HEIGHT / 2);

        frame = new JFrame("Transaction Viewer");
        frame.setBounds(startX, startY, FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);

        Font titleFont = new Font("Montserrat", Font.BOLD, 18);
        Font contentFont = new Font("Montserrat", Font.PLAIN, 14);

        JLabel titleLabel = new JLabel("Transaction Details");
        titleLabel.setFont(titleFont);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        panel.add(titleLabel);

        JLabel shopLabel = new JLabel("Shop Name: " + transaction.getShopName());
        shopLabel.setFont(contentFont);
        shopLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(shopLabel);

        panel.add(Box.createRigidArea(new Dimension(0, 20))); 

        List<Product> productList = transaction.getListProduct();
        for (Product product : productList) {
            JPanel productPanel = new JPanel();
            productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));
            productPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            productPanel.setBackground(Color.LIGHT_GRAY);

            JLabel nameLabel = new JLabel("Name: " + product.getName());
            nameLabel.setFont(contentFont);

            JLabel priceLabel = new JLabel("Price: Rp." + product.getPrice());
            priceLabel.setFont(contentFont);

            JLabel quantityLabel = new JLabel("Quantity: " + product.getStock());
            quantityLabel.setFont(contentFont);

            productPanel.add(nameLabel);
            productPanel.add(priceLabel);
            productPanel.add(quantityLabel);

            if (product instanceof Book) {
                Book book = (Book) product;
                JLabel bookLabel = new JLabel("Author: " + book.getAuthor());
                bookLabel.setFont(contentFont);
                productPanel.add(bookLabel);
            } else if (product instanceof Clothing) {
                Clothing clothing = (Clothing) product;
                JLabel clothingLabel = new JLabel("Color: " + clothing.getColor());
                clothingLabel.setFont(contentFont);
                productPanel.add(clothingLabel);
            } else if (product instanceof Electronic) {
                Electronic electronic = (Electronic) product;
                JLabel electronicLabel = new JLabel("Warranty: " + electronic.getWarranty());
                electronicLabel.setFont(contentFont);
                productPanel.add(electronicLabel);
            } else if (product instanceof Grocery) {
                Grocery grocery = (Grocery) product;
                JLabel groceryLabel = new JLabel("Expiry Date: " + grocery.getExpDate());
                groceryLabel.setFont(contentFont);
                productPanel.add(groceryLabel);
            }

            panel.add(productPanel);
            panel.add(Box.createRigidArea(new Dimension(0, 10))); 
        }

        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setBackground(Color.GRAY);
        backButton.addActionListener(e -> {
            frame.dispose();
            new HomeBuyer(user);
        });
        panel.add(backButton);

        JScrollPane scrollPane = new JScrollPane(panel);
        frame.add(scrollPane);
        frame.setVisible(true);
    }
}
