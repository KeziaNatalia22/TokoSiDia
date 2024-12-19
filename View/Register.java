package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Register {
    JFrame frame;
    JPanel panel;

    public Register() {
        Regist();
    }

    public void Regist() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        final int FRAME_WIDTH = 400;
        final int FRAME_HEIGHT = 700;

        int start_x = screenWidth / 2 - (FRAME_WIDTH / 2);
        int start_y = screenHeight / 2 - (FRAME_HEIGHT / 2);

        frame = new JFrame("Register Form");
        frame.setBounds(start_x, start_y, FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 248, 255)); // Light blue background

        JLabel titleLabel = new JLabel("Tokosidia Registration", SwingConstants.CENTER);
        titleLabel.setBounds(50, 10, 300, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(new Color(0, 102, 204));
        panel.add(titleLabel);

        // Username Field
        JLabel usnL = new JLabel("Username");
        usnL.setBounds(50, 60, 150, 20);
        panel.add(usnL);

        JTextField username = new JTextField();
        username.setBounds(50, 85, 300, 30);
        username.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(username);

        // Password Field
        JLabel passwordL = new JLabel("Password");
        passwordL.setBounds(50, 125, 150, 20);
        panel.add(passwordL);

        JPasswordField password = new JPasswordField();
        password.setBounds(50, 150, 300, 30);
        password.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(password);

        // Email Field
        JLabel emailL = new JLabel("Email");
        emailL.setBounds(50, 190, 150, 20);
        panel.add(emailL);

        JTextField email = new JTextField();
        email.setBounds(50, 215, 300, 30);
        email.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(email);

        // Address Field
        JLabel addressL = new JLabel("Address");
        addressL.setBounds(50, 255, 150, 20);
        panel.add(addressL);

        JTextField address = new JTextField();
        address.setBounds(50, 280, 300, 30);
        address.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(address);

        // Phone Number Field
        JLabel phoneL = new JLabel("Phone Number");
        phoneL.setBounds(50, 320, 150, 20);
        panel.add(phoneL);

        JTextField phone = new JTextField();
        phone.setBounds(50, 345, 300, 30);
        phone.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(phone);

        // Type Radio Buttons
        JLabel typeL = new JLabel("Type");
        typeL.setBounds(50, 385, 100, 20);
        panel.add(typeL);

        JRadioButton buyerButton = new JRadioButton("Buyer");
        buyerButton.setBounds(50, 410, 100, 30);
        buyerButton.setBackground(new Color(240, 248, 255));

        JRadioButton sellerButton = new JRadioButton("Seller");
        sellerButton.setBounds(160, 410, 100, 30);
        sellerButton.setBackground(new Color(240, 248, 255));

        ButtonGroup typeGroup = new ButtonGroup();
        typeGroup.add(buyerButton);
        typeGroup.add(sellerButton);

        panel.add(buyerButton);
        panel.add(sellerButton);

        // Shop Name and City Fields
        JLabel shopL = new JLabel("Shop Name");
        shopL.setBounds(50, 450, 150, 20);
        shopL.setVisible(false);
        panel.add(shopL);

        JTextField shopName = new JTextField();
        shopName.setBounds(50, 475, 300, 30);
        shopName.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        shopName.setVisible(false);
        panel.add(shopName);

        JLabel cityL = new JLabel("City Located");
        cityL.setBounds(50, 515, 150, 20);
        cityL.setVisible(false);
        panel.add(cityL);

        JTextField city = new JTextField();
        city.setBounds(50, 540, 300, 30);
        city.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        city.setVisible(false);
        panel.add(city);

        buyerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shopL.setVisible(false);
                shopName.setVisible(false);
                cityL.setVisible(false);
                city.setVisible(false);
            }
        });

        sellerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shopL.setVisible(true);
                shopName.setVisible(true);
                cityL.setVisible(true);
                city.setVisible(true);
            }
        });

        JButton submit = new JButton("Submit");
        submit.setBounds(220, 600, 100, 30);
        submit.setBackground(new Color(0, 102, 204));
        submit.setForeground(Color.WHITE);
        submit.setFocusPainted(false);
        panel.add(submit);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Controller.Register.checkUniqueUsername(username.getText()) == 0) {
                    JOptionPane.showMessageDialog(frame, "Username is used!");
                } 
                
                if(Controller.Register.checkUniqueEmail(email.getText()) == 0){
                    JOptionPane.showMessageDialog(frame, "Email is used!");
                }

                String type = sellerButton.isSelected() ? "seller" : "buyer";

                Controller.Register.inputDatatoDB(username.getText(), phone.getText(), email.getText(), new String(password.getPassword()), address.getText(), type, shopName.getText(), city.getText());

                frame.dispose();
                new Login();
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setBounds(70, 600, 100, 30);
        backButton.setBackground(new Color(220, 20, 60)); // Crimson background
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        panel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuLogin(); 
                frame.dispose();
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}
