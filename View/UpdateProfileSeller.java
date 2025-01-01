package View;

import javax.swing.*;
import Controller.Register;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Modul.Seller;
import java.io.File;

public class UpdateProfileSeller {
    JFrame frame;
    JPanel panel;
    File selectedPhotoProfile; 
    File selectedPhotoShop; 

    public UpdateProfileSeller(Seller user) {
        Seller(user);
    }

    public void Seller(Seller user) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize(); // Get screen size

        int screenWidth = screenSize.width; // Screen width
        int screenHeight = screenSize.height; // Screen height

        final int FRAME_WIDTH = 400; // Set frame width
        final int FRAME_HEIGHT = 400; // Set frame height

        int start_x = screenWidth / 2 - (FRAME_WIDTH / 2); // Center frame horizontally
        int start_y = screenHeight / 2 - (FRAME_HEIGHT / 2); // Center frame vertically

        frame = new JFrame("Profile"); // Create frame and set title

        frame.setBounds(start_x, start_y, FRAME_WIDTH, FRAME_HEIGHT); // Set frame bounds
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

        JButton photoButton = new JButton("Update Photo Profile");
        photoButton.setBounds(40, 30, 100, 100);
        photoButton.setHorizontalAlignment(SwingConstants.CENTER);

        // ImageIcon photoIcon = new ImageIcon(user.getPhotoPath().getAbsolutePath());
        // Image scaledPhoto = photoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        // photoButton.setIcon(new ImageIcon(scaledPhoto));
        // panel.add(photoButton);

        photoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    selectedPhotoProfile = fileChooser.getSelectedFile();
                }
            }
        });
        panel.add(photoButton);

        JButton shopPhotoButton = new JButton("Update Shop Profile");
        shopPhotoButton.setBounds(210, 30, 100, 100);
        shopPhotoButton.setHorizontalAlignment(SwingConstants.CENTER);

        // ImageIcon photoIcon = new ImageIcon(user.getPhotoPath().getAbsolutePath());
        // Image scaledPhoto = photoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        // shopPhotoButton.setIcon(new ImageIcon(scaledPhoto));
        // panel.add(shopPhotoButton);

        shopPhotoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    selectedPhotoShop = fileChooser.getSelectedFile();
                }
            }
        });
        panel.add(shopPhotoButton);

        JLabel nameLabel = new JLabel("Name  ");
        nameLabel.setBounds(20, 170, 100, 20);
        panel.add(nameLabel);

        JTextField nameField = new JTextField(user.getName());
        nameField.setBounds(150, 170, 200, 20);
        panel.add(nameField);

        JLabel phoneNum = new JLabel("Phone Number  ");
        phoneNum.setBounds(20, 200, 100, 20);
        panel.add(phoneNum);

        JTextField phoneField = new JTextField(user.getPhoneNum());
        phoneField.setBounds(150, 200, 200, 20);
        panel.add(phoneField);

        JLabel email = new JLabel("Email  ");
        email.setBounds(20, 230, 100, 20);
        panel.add(email);

        JTextField emailField = new JTextField(user.getEmail());
        emailField.setBounds(150, 230, 200, 20);
        panel.add(emailField);

        JLabel city = new JLabel("City Located  ");
        city.setBounds(20, 260, 100, 20);
        panel.add(city);

        JTextField cityLocated = new JTextField(user.getCityLocated());
        cityLocated.setBounds(150, 260, 200, 20);
        panel.add(cityLocated);

        JLabel shop = new JLabel("Shop Name  ");
        shop.setBounds(20, 290, 100, 20);
        panel.add(shop);

        JTextField shopName = new JTextField(user.getShopName());
        shopName.setBounds(150, 290, 200, 20);
        panel.add(shopName);

        JButton backHome = new JButton("Back");
        backHome.setBounds(20, 320, 80, 20);
        panel.add(backHome);
        backHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new HomeSeller(user);
            }
        });

        JButton updateData = new JButton("Change Data");
        updateData.setBounds(220, 320, 120, 20);
        panel.add(updateData);
        updateData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String phone = phoneField.getText();
                String emailText = emailField.getText();
                String city = cityLocated.getText();
                String shop = shopName.getText();

                user.setPhoneNum(phone);
                user.setEmail(emailText);
                user.setCityLocated(city);

                if (selectedPhotoProfile != null) {
                    user.setPhotoPath(selectedPhotoProfile.getAbsolutePath());
                    Register.updatePhotoPath(name, selectedPhotoProfile); 
                }
                
                if (selectedPhotoShop != null) {
                    user.setPhotoPath(selectedPhotoShop.getAbsolutePath());
                    Register.updatePhotoShop(name, selectedPhotoShop); 
                }

                if (Register.updateData(name, phone, emailText, city, shop)) {
                    JOptionPane.showMessageDialog(frame, "Data updated successfully!");
                }
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}
