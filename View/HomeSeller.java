package View;

import javax.swing.*;
import java.awt.*;
import Modul.Seller;

public class HomeSeller {
    private JFrame frame;

    public HomeSeller(Seller user) {
        initialize(user);
    }

    private void initialize(Seller user) {
        // Setup frame
        frame = new JFrame("Home Seller - Tokosidia");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 400);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        frame.add(mainPanel);

        // Header panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 15, 10, 15));

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        leftPanel.setBackground(Color.WHITE);

        JLabel mainLabel = new JLabel("Tokosidia");
        mainLabel.setFont(new Font("Montserrat", Font.BOLD, 24));
        mainLabel.setForeground(new Color(0, 155, 119));
        leftPanel.add(mainLabel);

        JTextField searchField = new JTextField(25);
        searchField.setFont(new Font("Arial", Font.PLAIN, 16));
        leftPanel.add(searchField);

        JButton searchButton = createButton("Search", new Color(0, 155, 119));
        leftPanel.add(searchButton);

        headerPanel.add(leftPanel, BorderLayout.WEST);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        rightPanel.setBackground(Color.WHITE);

        JLabel balanceLabel = new JLabel("Balance: Rp." + user.geteMoney());
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 14));
        balanceLabel.setForeground(Color.BLACK);
        rightPanel.add(balanceLabel);

        JButton profileButton = createIconButton(user.getPhotoPath(), 60, 60, Color.WHITE);
        rightPanel.add(profileButton);

        headerPanel.add(rightPanel, BorderLayout.EAST);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new GridLayout(1, 2, 15, 15));
        contentPanel.setBackground(Color.LIGHT_GRAY);

        String[] dashboardItems = {"Total Penjualan", "Tambah Produk"};
        for (String item : dashboardItems) {
            JButton dashboardButton = createButton(item, new Color(0, 155, 119));
            contentPanel.add(dashboardButton);

            dashboardButton.addActionListener(e -> 
                JOptionPane.showMessageDialog(frame, item + " belum diimplementasikan!")
            );
        }

        mainPanel.add(contentPanel, BorderLayout.SOUTH);

        profileButton.addActionListener(e -> {
            frame.dispose();
            new ProfileSeller(user);
        });

        frame.setVisible(true);
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
