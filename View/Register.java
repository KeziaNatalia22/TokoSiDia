package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;

import Modul.TokosiDiaFrame;

public class Register {
    TokosiDiaFrame frame;
    JPanel panel;
    File selectedFilePath;

    public Register() {
        Regist();
    }

    public void Regist() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        final int FRAME_WIDTH = 400;
        final int FRAME_HEIGHT = 720;

        int start_x = screenWidth / 2 - (FRAME_WIDTH / 2);
        int start_y = screenHeight / 2 - (FRAME_HEIGHT / 2);

        frame = new TokosiDiaFrame("Register Form");
        frame.setBounds(start_x, start_y, FRAME_WIDTH, FRAME_HEIGHT - 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 248, 255)); // Light blue background

        JLabel titleLabel = new JLabel("Tokosidia Registration", SwingConstants.CENTER);
        titleLabel.setBounds(50, 10, 300, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(new Color(0, 102, 204));
        panel.add(titleLabel);

        JLabel usnL = new JLabel("Username");
        usnL.setBounds(50, 60, 150, 20);
        panel.add(usnL);

        JTextField username = new JTextField();
        username.setBounds(50, 85, 300, 30);
        username.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(username);

        JLabel passwordL = new JLabel("Password");
        passwordL.setBounds(50, 125, 150, 20);
        panel.add(passwordL);

        JPasswordField password = new JPasswordField();
        password.setBounds(50, 150, 300, 30);
        password.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(password);

        JLabel emailL = new JLabel("Email");
        emailL.setBounds(50, 190, 150, 20);
        panel.add(emailL);

        JTextField email = new JTextField();
        email.setBounds(50, 215, 300, 30);
        email.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(email);

        JLabel addressL = new JLabel("Address");
        addressL.setBounds(50, 255, 150, 20);
        panel.add(addressL);

        JTextField address = new JTextField();
        address.setBounds(50, 280, 300, 30);
        address.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(address);

        JLabel phoneL = new JLabel("Phone Number");
        phoneL.setBounds(50, 320, 150, 20);
        panel.add(phoneL);

        JTextField phone = new JTextField();
        phone.setBounds(50, 345, 300, 30);
        phone.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(phone);

        JButton uploadPhotoButton = new JButton("Upload Photo");
        uploadPhotoButton.setBounds(50, 385, 150, 30);
        uploadPhotoButton.setBackground(new Color(0, 102, 204));
        uploadPhotoButton.setForeground(Color.WHITE);
        uploadPhotoButton.setFocusPainted(false);
        panel.add(uploadPhotoButton);

        uploadPhotoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    selectedFilePath = fileChooser.getSelectedFile();

                    File photoFolder = new File("Photos");
                    if (!photoFolder.exists()) {
                        photoFolder.mkdir(); 
                    }

                    File destinationFile = new File(photoFolder, selectedFilePath.getName());

                    try {
                        java.nio.file.Files.copy(
                                selectedFilePath.toPath(),
                                destinationFile.toPath(),
                                java.nio.file.StandardCopyOption.REPLACE_EXISTING
                        );
                        selectedFilePath = destinationFile;
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, "Failed to upload photo: " + ex.getMessage());
                        ex.printStackTrace();
                    }
                }
            }
        });

        JLabel typeL = new JLabel("Type");
        typeL.setBounds(50, 425, 100, 20);
        panel.add(typeL);

        JRadioButton buyerButton = new JRadioButton("Buyer");
        buyerButton.setBounds(50, 450, 100, 30);
        buyerButton.setBackground(new Color(240, 248, 255));

        JRadioButton sellerButton = new JRadioButton("Seller");
        sellerButton.setBounds(160, 450, 100, 30);
        sellerButton.setBackground(new Color(240, 248, 255));

        ButtonGroup typeGroup = new ButtonGroup();
        typeGroup.add(buyerButton);
        typeGroup.add(sellerButton);

        panel.add(buyerButton);
        panel.add(sellerButton);

        JLabel shopL = new JLabel("Shop Name");
        shopL.setBounds(50, 490, 150, 20);
        shopL.setVisible(false);
        panel.add(shopL);

        JTextField shopName = new JTextField();
        shopName.setBounds(50, 515, 300, 30);
        shopName.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        shopName.setVisible(false);
        panel.add(shopName);

        JLabel cityL = new JLabel("City Located");
        cityL.setBounds(50, 555, 150, 20);
        cityL.setVisible(false);
        panel.add(cityL);

        JTextField city = new JTextField();
        city.setBounds(50, 580, 300, 30);
        city.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        city.setVisible(false);
        panel.add(city);

        JButton submit = new JButton("Submit");
        submit.setBounds(220, 490, 100, 30);
        submit.setBackground(new Color(0, 102, 204));
        submit.setForeground(Color.WHITE);
        submit.setFocusPainted(false);
        panel.add(submit);

        JButton backButton = new JButton("Back");
        backButton.setBounds(70, 490, 100, 30);
        backButton.setBackground(new Color(220, 20, 60)); 
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        panel.add(backButton);

        buyerButton.addActionListener(e -> {
            shopL.setVisible(false);
            shopName.setVisible(false);
            cityL.setVisible(false);
            city.setVisible(false);
            submit.setBounds(220, 490, 100, 30);
            backButton.setBounds(70, 490, 100, 30);
            frame.setBounds(start_x, start_y, FRAME_WIDTH, FRAME_HEIGHT - 150);
        });

        sellerButton.addActionListener(e -> {
            shopL.setVisible(true);
            shopName.setVisible(true);
            cityL.setVisible(true);
            city.setVisible(true);
            frame.setBounds(start_x, start_y, FRAME_WIDTH, FRAME_HEIGHT);
            submit.setBounds(220, 630, 100, 30);
            backButton.setBounds(70, 630, 100, 30);
        });

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (username.getText().isEmpty() || new String(password.getPassword()).isEmpty() ||
                        email.getText().isEmpty() || address.getText().isEmpty() ||
                        phone.getText().isEmpty() || selectedFilePath == null ||
                        (!buyerButton.isSelected() && !sellerButton.isSelected()) ||
                        (sellerButton.isSelected() && (shopName.getText().isEmpty() || city.getText().isEmpty()))) {
                    JOptionPane.showMessageDialog(frame, "Please fill all required fields.");
                    return;
                }

                if (Controller.Register.checkUniqueUsername(username.getText()) == 0) {
                    JOptionPane.showMessageDialog(frame, "Username is already taken!");
                    return;
                }

                if (Controller.Register.checkUniqueEmail(email.getText()) == 0) {
                    JOptionPane.showMessageDialog(frame, "Email is already registered!");
                    return;
                }

                String type = sellerButton.isSelected() ? "seller" : "buyer";
                String photoPath = "Photos/" + selectedFilePath.getName();

                if (Controller.Register.inputDatatoDB(username.getText(), phone.getText(), email.getText(),
                        new String(password.getPassword()), address.getText(), type, shopName.getText(), city.getText(), new File(photoPath))) {
                    new Login();
                    frame.dispose();
                } else {
                    new Register();
                    frame.dispose();
                }
            }
        });

        backButton.addActionListener(e -> {
            new MenuLogin();
            frame.dispose();
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}
