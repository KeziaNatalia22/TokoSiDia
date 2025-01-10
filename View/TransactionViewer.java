package View;

import javax.swing.*;
import Controller.HistoryTransaction;

import java.awt.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import Modul.*;
import Controller.UpdateShipment;

public class TransactionViewer {
    TokosiDiaFrame frame;
    JPanel panel;

    public TransactionViewer(Seller user) {
        displayTransactionSeller(user);
    }

    public TransactionViewer(Buyer user) {
        displayTransactionBuyer(user);
    }

    public void displayTransactionBuyer(Buyer user) {
        List<Transaction> transactions = HistoryTransaction.transactionHistory(user.getName());
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        final int FRAME_WIDTH = 900;
        final int FRAME_HEIGHT = 700;

        int startX = screenWidth / 2 - (FRAME_WIDTH / 2);
        int startY = screenHeight / 2 - (FRAME_HEIGHT / 2);

        frame = new TokosiDiaFrame("Transaction Viewer");
        frame.setBounds(startX, startY, FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.decode("#f4f4f9")); // Soft background color

        Font titleFont = new Font("Segoe UI", Font.BOLD, 20);
        Font contentFont = new Font("Segoe UI", Font.PLAIN, 14);

        JLabel titleLabel = new JLabel("Transaction History");
        titleLabel.setFont(titleFont);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        panel.add(titleLabel);

        JPanel transactionGrid = new JPanel();
        transactionGrid.setLayout(new GridLayout(0, 3, 10, 10));
        transactionGrid.setBackground(Color.decode("#f4f4f9"));

        for (Transaction transaction : transactions) {
            JPanel transactionPanel = new JPanel();
            transactionPanel.setLayout(new BoxLayout(transactionPanel, BoxLayout.Y_AXIS));
            transactionPanel.setBackground(Color.WHITE);
            transactionPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.decode("#dddddd"), 1),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
            String formattedDate = dateFormat.format(transaction.getTransactionDate());

            JLabel date = new JLabel("Transaction Date: " + formattedDate);
            date.setFont(new Font("Segoe UI", Font.BOLD, 14));
            date.setAlignmentX(Component.CENTER_ALIGNMENT);
            transactionPanel.add(date);
            transactionPanel.add(Box.createRigidArea(new Dimension(0, 10)));

            List<Product> productList = transaction.getListProduct();
            int index = 0;
            Double total = 0.0;
            DecimalFormat priceFormat = new DecimalFormat("#,###.00");

            for (Product product : productList) {
                int qty = transaction.getListQty().get(index);
                JPanel productPanel = new JPanel();
                productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));
                productPanel.setBackground(Color.decode("#f9f9f9"));
                productPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                JLabel photoLabel = new JLabel();
                photoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                ImageIcon photoIcon = new ImageIcon("Photos/Seller/" + product.getPhotoProduct());
                Image scaledPhoto = photoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                photoLabel.setIcon(new ImageIcon(scaledPhoto));
                productPanel.add(photoLabel);

                JLabel nameLabel = new JLabel("Name: " + product.getName());
                nameLabel.setFont(contentFont);
                nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                productPanel.add(nameLabel);

                if (product instanceof Book) {
                    Book book = (Book) product;
                    JLabel bookLabel = new JLabel("Author: " + book.getAuthor());
                    bookLabel.setFont(contentFont);
                    bookLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    productPanel.add(bookLabel);

                    JLabel pageNum = new JLabel("Page Num: " + book.getPageNum());
                    pageNum.setFont(contentFont);
                    pageNum.setAlignmentX(Component.CENTER_ALIGNMENT);
                    productPanel.add(pageNum);
                } else if (product instanceof Clothing) {
                    Clothing clothing = (Clothing) product;
                    JLabel color = new JLabel("Color: " + clothing.getColor());
                    color.setFont(contentFont);
                    color.setAlignmentX(Component.CENTER_ALIGNMENT);
                    productPanel.add(color);

                    JLabel size = new JLabel("Size: " + clothing.getSize());
                    size.setFont(contentFont);
                    size.setAlignmentX(Component.CENTER_ALIGNMENT);
                    productPanel.add(size);
                } else if (product instanceof Electronic) {
                    Electronic electronic = (Electronic) product;
                    JLabel electronicLabel = new JLabel("Warranty: " + electronic.getWarranty());
                    electronicLabel.setFont(contentFont);
                    electronicLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    productPanel.add(electronicLabel);

                    JLabel color = new JLabel("Color: " + electronic.getColor());
                    color.setFont(contentFont);
                    color.setAlignmentX(Component.CENTER_ALIGNMENT);
                    productPanel.add(color);
                } else if (product instanceof Grocery) {
                    Grocery grocery = (Grocery) product;
                    JLabel groceryLabel = new JLabel("Expiry Date: " + grocery.getExpDate());
                    groceryLabel.setFont(contentFont);
                    groceryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    productPanel.add(groceryLabel);

                    JLabel productionDate = new JLabel("Production Date: " + grocery.getProductionDate());
                    productionDate.setFont(contentFont);
                    productionDate.setAlignmentX(Component.CENTER_ALIGNMENT);
                    productPanel.add(productionDate);
                }

                double priceAfterDisc = product.getPrice();
                if (product.getDiscount() > 0) {
                    priceAfterDisc = product.getPrice() - (product.getPrice() * (product.getDiscount() / 100));
                }

                if (product.getDiscount() > 0) {
                    JLabel discLabel = new JLabel("Discount: " + product.getDiscount() + "%");
                    discLabel.setFont(contentFont);
                    discLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    discLabel.setBackground(Color.RED);
                    productPanel.add(discLabel);
                }

                total += priceAfterDisc * qty;

                JLabel qtyLabel = new JLabel("Quantity: " + transaction.getListQty().get(index));
                qtyLabel.setFont(contentFont);
                qtyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                productPanel.add(qtyLabel);

                JLabel priceLabel = new JLabel("Price: Rp " + priceFormat.format(priceAfterDisc));
                priceLabel.setFont(contentFont);
                priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                productPanel.add(priceLabel);

                transactionPanel.add(productPanel);
                transactionPanel.add(Box.createRigidArea(new Dimension(0, 10)));

                index++;
            }

            JLabel totalLabel = new JLabel("Total: Rp " + priceFormat.format(total));
            totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
            totalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            transactionPanel.add(totalLabel);

            String[] shipmentStatusList = { "DELIVERED", "COMPLETED" };

            String currentStatus = transaction.getShipmentStatus().toString();

            if ("COMPLETED".equals(currentStatus)) {
                JLabel shipmentStatusLabel = new JLabel("Shipment Status: " + currentStatus);
                shipmentStatusLabel.setMaximumSize(new Dimension(190, 20));
                shipmentStatusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                transactionPanel.add(shipmentStatusLabel);
            } else {
                JComboBox<String> shipmentStatus = new JComboBox<>(shipmentStatusList);
                shipmentStatus.setMaximumSize(new Dimension(190, 20));
                shipmentStatus.setSelectedItem(currentStatus);
                shipmentStatus.setAlignmentX(Component.CENTER_ALIGNMENT);
                transactionPanel.add(shipmentStatus);

                JButton changeButton = new JButton("Change Shipment Status");
                changeButton.setBackground(Color.BLUE);
                changeButton.setForeground(Color.WHITE);
                changeButton.setMaximumSize(new Dimension(190, 40));
                changeButton.setAlignmentX(Component.CENTER_ALIGNMENT);

                changeButton.addActionListener(e -> {
                    String selectedStatus = shipmentStatus.getSelectedItem().toString();
                    if ("COMPLETED".equals(selectedStatus)) {
                        transactionPanel.remove(shipmentStatus);
                        transactionPanel.remove(changeButton);

                        JLabel completedLabel = new JLabel("Shipment Status: " + selectedStatus);
                        completedLabel.setMaximumSize(new Dimension(190, 20));
                        completedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                        transactionPanel.add(completedLabel);
                    }

                    transaction.setShipmentStatus(ShipmentStatus_Enum.valueOf(selectedStatus));
                    UpdateShipment.updateShipment(transaction);

                    JOptionPane.showMessageDialog(null, "Shipment Status Changed to " + selectedStatus);

                    transactionPanel.revalidate();
                    transactionPanel.repaint();

                    frame.dispose();
                    new TransactionViewer(user);
                });

                transactionPanel.add(changeButton);
            }

            transactionGrid.add(transactionPanel);
        }

        panel.add(transactionGrid);

        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setBackground(Color.decode("#007BFF"));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        backButton.addActionListener(e -> {
            frame.dispose();
            new HomeBuyer();
        });
        panel.add(backButton);

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        frame.add(scrollPane);
        frame.setVisible(true);
    }

    public void displayTransactionSeller(Seller user) {
        List<Transaction> transactions = HistoryTransaction.transactionSeller(user.getName());
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        final int FRAME_WIDTH = 900;
        final int FRAME_HEIGHT = 700;

        int startX = screenWidth / 2 - (FRAME_WIDTH / 2);
        int startY = screenHeight / 2 - (FRAME_HEIGHT / 2);

        frame = new TokosiDiaFrame("Transaction Viewer");
        frame.setBounds(startX, startY, FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.decode("#f4f4f9")); // Soft background color

        Font titleFont = new Font("Segoe UI", Font.BOLD, 20);
        Font contentFont = new Font("Segoe UI", Font.PLAIN, 14);

        JLabel titleLabel = new JLabel("Transaction History");
        titleLabel.setFont(titleFont);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        panel.add(titleLabel);

        JPanel transactionGrid = new JPanel();
        transactionGrid.setLayout(new GridLayout(0, 3, 10, 10));
        transactionGrid.setBackground(Color.decode("#f4f4f9"));

        for (Transaction transaction : transactions) {
            JPanel transactionPanel = new JPanel();
            transactionPanel.setLayout(new BoxLayout(transactionPanel, BoxLayout.Y_AXIS));
            transactionPanel.setBackground(Color.WHITE);
            // transactionPanel.setPreferredSize(new Dimension(280, 150));
            transactionPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.decode("#dddddd"), 1),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
            String formattedDate = dateFormat.format(transaction.getTransactionDate());

            Double total = 0.0;
            DecimalFormat priceFormat = new DecimalFormat("#,###.00");

            JLabel date = new JLabel("Transaction Date: " + formattedDate);
            date.setFont(new Font("Segoe UI", Font.BOLD, 14));
            date.setAlignmentX(Component.CENTER_ALIGNMENT);
            transactionPanel.add(date);
            transactionPanel.add(Box.createRigidArea(new Dimension(0, 10)));

            List<Product> productList = transaction.getListProduct();
            int index = 0;
            for (Product product : productList) {
                int qty = transaction.getListQty().get(index);

                JPanel productPanel = new JPanel();
                productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));
                productPanel.setBackground(Color.decode("#f9f9f9"));
                productPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                JLabel photoLabel = new JLabel();
                photoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                ImageIcon photoIcon = new ImageIcon("Photos/Seller/" + product.getPhotoProduct());
                Image scaledPhoto = photoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                photoLabel.setIcon(new ImageIcon(scaledPhoto));
                productPanel.add(photoLabel);

                JLabel nameLabel = new JLabel("Name: " + product.getName());
                nameLabel.setFont(contentFont);
                nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                productPanel.add(nameLabel);

                if (product instanceof Book) {
                    Book book = (Book) product;
                    JLabel bookLabel = new JLabel("Author: " + book.getAuthor());
                    bookLabel.setFont(contentFont);
                    bookLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    productPanel.add(bookLabel);

                    JLabel pageNum = new JLabel("Page Num: " + book.getPageNum());
                    pageNum.setFont(contentFont);
                    pageNum.setAlignmentX(Component.CENTER_ALIGNMENT);
                    productPanel.add(pageNum);
                } else if (product instanceof Clothing) {
                    Clothing clothing = (Clothing) product;
                    JLabel color = new JLabel("Color: " + clothing.getColor());
                    color.setFont(contentFont);
                    color.setAlignmentX(Component.CENTER_ALIGNMENT);
                    productPanel.add(color);

                    JLabel size = new JLabel("Size: " + clothing.getSize());
                    size.setFont(contentFont);
                    size.setAlignmentX(Component.CENTER_ALIGNMENT);
                    productPanel.add(size);
                } else if (product instanceof Electronic) {
                    Electronic electronic = (Electronic) product;
                    JLabel electronicLabel = new JLabel("Warranty: " + electronic.getWarranty());
                    electronicLabel.setFont(contentFont);
                    electronicLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    productPanel.add(electronicLabel);

                    JLabel color = new JLabel("Color: " + electronic.getColor());
                    color.setFont(contentFont);
                    color.setAlignmentX(Component.CENTER_ALIGNMENT);
                    productPanel.add(color);
                } else if (product instanceof Grocery) {
                    Grocery grocery = (Grocery) product;
                    JLabel groceryLabel = new JLabel("Expiry Date: " + grocery.getExpDate());
                    groceryLabel.setFont(contentFont);
                    groceryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    productPanel.add(groceryLabel);

                    JLabel productionDate = new JLabel("Production Date: " + grocery.getProductionDate());
                    productionDate.setFont(contentFont);
                    productionDate.setAlignmentX(Component.CENTER_ALIGNMENT);
                    productPanel.add(productionDate);
                }

                double priceAfterDisc = product.getPrice();
                if (product.getDiscount() > 0) {
                    priceAfterDisc = product.getPrice() - (product.getPrice() * (product.getDiscount() / 100));
                }

                if (product.getDiscount() > 0) {
                    JLabel discLabel = new JLabel("Discount: " + product.getDiscount() + "%");
                    discLabel.setFont(contentFont);
                    discLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    discLabel.setBackground(Color.RED);
                    productPanel.add(discLabel);
                }

                total += priceAfterDisc * qty;

                JLabel qtyLabel = new JLabel("Quantity: " + qty);
                qtyLabel.setFont(contentFont);
                qtyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                productPanel.add(qtyLabel);

                JLabel priceLabel = new JLabel("Price: Rp " + priceFormat.format(priceAfterDisc));
                priceLabel.setFont(contentFont);
                priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                productPanel.add(priceLabel);

                transactionPanel.add(productPanel);
                transactionPanel.add(Box.createRigidArea(new Dimension(0, 10)));

                index++;
            }

            JLabel totalLabel = new JLabel("Total: Rp " + priceFormat.format(total));
            totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
            totalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            transactionPanel.add(totalLabel);

            String[] shipmentStatusList = { "PACKED", "SHIPPED" };
            String currentStatus = transaction.getShipmentStatus().toString();

            if ("SHIPPED".equals(currentStatus)) {
                JLabel shipmentStatusLabel = new JLabel("Shipment Status: " + currentStatus);
                shipmentStatusLabel.setMaximumSize(new Dimension(190, 20));
                shipmentStatusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                transactionPanel.add(shipmentStatusLabel);
            } else {
                JComboBox<String> shipmentStatus = new JComboBox<>(shipmentStatusList);
                shipmentStatus.setMaximumSize(new Dimension(190, 20));
                shipmentStatus.setSelectedItem(currentStatus);
                shipmentStatus.setAlignmentX(Component.CENTER_ALIGNMENT);
                transactionPanel.add(shipmentStatus);

                JButton changeButton = new JButton("Change Shipment Status");
                changeButton.setBackground(Color.BLUE);
                changeButton.setForeground(Color.WHITE);
                changeButton.setMaximumSize(new Dimension(190, 40));
                changeButton.setAlignmentX(Component.CENTER_ALIGNMENT);

                changeButton.addActionListener(e -> {
                    String selectedStatus = shipmentStatus.getSelectedItem().toString();
                    if ("SHIPPED".equals(selectedStatus)) {
                        transactionPanel.remove(shipmentStatus);
                        transactionPanel.remove(changeButton);

                        JLabel shippedLabel = new JLabel("Shipment Status: " + selectedStatus);
                        shippedLabel.setMaximumSize(new Dimension(190, 20));
                        shippedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                        transactionPanel.add(shippedLabel);
                    }

                    transaction.setShipmentStatus(ShipmentStatus_Enum.valueOf(selectedStatus));
                    UpdateShipment.updateShipment(transaction);

                    JOptionPane.showMessageDialog(null, "Shipment Status Changed to " + selectedStatus);

                    transactionPanel.revalidate();
                    transactionPanel.repaint();

                    frame.dispose();
                    new TransactionViewer(user);
                });

                transactionPanel.add(changeButton);
            }

            transactionGrid.add(transactionPanel);
        }

        panel.add(transactionGrid);

        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setBackground(Color.decode("#007BFF"));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        backButton.addActionListener(e -> {
            frame.dispose();
            new HomeSeller();
        });
        panel.add(backButton);

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        frame.add(scrollPane);
        frame.setVisible(true);
    }

}
