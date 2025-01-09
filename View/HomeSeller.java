package View;

import javax.swing.*;
import java.awt.*;

import Modul.Buyer;
import Modul.Seller;
import Modul.SingletonManager;
import Modul.TokosiDiaFrame;

public class HomeSeller {
    private TokosiDiaFrame frame;
    public HomeSeller() {
        initialize();
    }

    private void initialize() {
        SingletonManager login = SingletonManager.getInstance();
        Seller user = (Seller) login.getUser();
        frame = new TokosiDiaFrame("Home Seller - Tokosidia");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        frame.add(mainPanel);

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(240, 248, 255)); 
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        leftPanel.setBackground(new Color(240, 248, 255));

        JLabel mainLabel = new JLabel("TokosiDia");
        mainLabel.setFont(new Font("Montserrat", Font.BOLD, 25));
        mainLabel.setForeground(Color.GREEN);
        leftPanel.add(mainLabel);

        headerPanel.add(leftPanel, BorderLayout.WEST);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        rightPanel.setBackground(new Color(240, 248, 255));

        JPanel balancePanel = new JPanel(new BorderLayout());
        balancePanel.setBackground(Color.WHITE);
        balancePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));

        JLabel balanceLabel = new JLabel("Income: Rp." ); // manggil function di controller buat income nya ntar
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        balanceLabel.setForeground(Color.BLACK);
        balancePanel.add(balanceLabel, BorderLayout.CENTER);

        rightPanel.add(balancePanel);

        JButton profileButton = createIconButton(user.getPhotoPath(), 50, 50, new Color(240, 248, 255));

        rightPanel.add(profileButton);

        headerPanel.add(rightPanel, BorderLayout.EAST);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel panelMain = new JPanel();
        panelMain.setBackground(Color.WHITE);
        panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS)); 
        panelMain.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.add(panelMain, BorderLayout.CENTER);

        JLabel welcomeLabel = new JLabel("Welcome, seller " + user.getName() + "!");
        welcomeLabel.setFont(new Font("Montserrat", Font.BOLD, 17));
        welcomeLabel.setForeground(Color.BLACK);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelMain.add(welcomeLabel);

        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color(250, 250, 250));
        contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 30)); 
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton historyPenjualan = createButton("History Penjualan", new Color(72, 209, 204));
        JButton addProduct = createButton("Add Product", new Color(244, 164, 96));
        JButton deleteProduct = createButton("Delete Product", new Color(255, 99, 71));
        JButton updateProduct = createButton("Update Product", new Color(72, 209, 204));

        contentPanel.add(historyPenjualan);
        contentPanel.add(addProduct);
        contentPanel.add(deleteProduct);
        contentPanel.add(updateProduct);

        panelMain.add(contentPanel);

        profileButton.addActionListener(e -> {
            frame.dispose();
            new ProfileSeller();
        });

        addProduct.addActionListener(e -> {
            frame.dispose();
            new AddProduct(user);
        });

        deleteProduct.addActionListener(e -> {
            frame.dispose();
            new RemoveProduct();
        });

        historyPenjualan.addActionListener(e -> {
            frame.dispose();
            new TransactionViewer(user);
        });

        frame.setVisible(true);
    }

    private JButton createButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(backgroundColor.darker(), 2),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        return button;
    }

    private JButton createIconButton(String imagePath, int width, int height, Color backgroundColor) {
        JButton button = new JButton();
        button.setBackground(backgroundColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder());

        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(img));
        button.setPreferredSize(new Dimension(width, height));
        return button;
    }
}
