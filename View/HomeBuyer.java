package View;

import javax.swing.*;
import java.awt.*;
import Modul.Buyer;
import Controller.BalanceSection;

public class HomeBuyer {
    private JFrame frame;

    public HomeBuyer(Buyer user) {
        initialize(user);
    }

    private void initialize(Buyer user) {
        frame = new JFrame("Home");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 550);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        frame.add(mainPanel);

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(30, 15, 10, 15));

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        leftPanel.setBackground(Color.WHITE);

        JLabel mainLabel = new JLabel("TokosiDia");
        mainLabel.setFont(new Font("Montserrat", Font.BOLD, 20));
        mainLabel.setForeground(Color.GREEN);
        leftPanel.add(mainLabel);

        JTextField searchField = new JTextField(25);
        searchField.setFont(new Font("Arial", Font.PLAIN, 16));
        leftPanel.add(searchField);

        JButton searchButton = createButton("Search", new Color(100, 149, 237));
        leftPanel.add(searchButton);

        JButton cartButton = createIconButton("Photos/cart.jpeg", 40, 40, new Color(255, 165, 0));
        leftPanel.add(cartButton);

        headerPanel.add(leftPanel, BorderLayout.WEST);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 2, 0));
        rightPanel.setBackground(Color.WHITE);

        JPanel balancePanel = new JPanel(new BorderLayout());
        balancePanel.setBackground(Color.WHITE);
        balancePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));

        JLabel balanceLabel = new JLabel("Balance: Rp." + user.geteMoney());
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 14));
        balanceLabel.setForeground(Color.BLACK);
        balancePanel.add(balanceLabel, BorderLayout.CENTER);

        rightPanel.add(balancePanel);

        JButton profileButton = createIconButton(user.getPhotoPath(), 60, 60, Color.WHITE);
        rightPanel.add(profileButton);

        headerPanel.add(rightPanel, BorderLayout.EAST);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(Color.WHITE);
        imagePanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 30, 10)); // Jarak 30 di atas, 10 di kiri dan kanan
        JLabel imageLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon("Photos/green.jpeg"); 
        Image scaledImage = imageIcon.getImage().getScaledInstance(860, 300, Image.SCALE_SMOOTH); // Ubah ukuran gambar
        imageLabel.setIcon(new ImageIcon(scaledImage));
        imagePanel.add(imageLabel);

        mainPanel.add(imagePanel, BorderLayout.CENTER);

        JPanel categoryPanel = new JPanel();
        categoryPanel.setBackground(Color.WHITE);
        categoryPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 30)); 

        JButton bookButton = createButtonCategory("Book", new Color(72, 209, 204));
        JButton clothingButton = createButtonCategory("Clothing", new Color(244, 164, 96));
        JButton electronicButton = createButtonCategory("Electronic", new Color(255, 99, 71));
        JButton groceryButton = createButtonCategory("Grocery", new Color(50, 205, 50));

        categoryPanel.add(bookButton);
        categoryPanel.add(clothingButton);
        categoryPanel.add(electronicButton);
        categoryPanel.add(groceryButton);

        mainPanel.add(categoryPanel, BorderLayout.SOUTH);

        profileButton.addActionListener(e -> {
            frame.dispose();
            new ProfileBuyer(user);
        });

        cartButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Menu Keranjang belum diimplementasikan!");
        });

        searchButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Search functionality belum diimplementasikan!");
        });

        bookButton.addActionListener(e -> {

        });

        clothingButton.addActionListener(e -> {

        });

        electronicButton.addActionListener(e -> {

        });

        groceryButton.addActionListener(e -> {

        });

        frame.setVisible(true);
    }

    private JButton createButtonCategory(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(150, 40)); 
        return button;
    }
    private JButton createButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }

    private JButton createIconButton(String imagePath, int width, int height, Color backgroundColor) {
        JButton button = new JButton();
        button.setBackground(backgroundColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);

        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(img));
        button.setPreferredSize(new Dimension(width, height));
        return button;
    }
}
