package View;

import javax.swing.*;
import Controller.Register;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Modul.User;
import java.io.File;

public class UpdateProfile {
    JFrame frame;
    JPanel panel;
    File selectedPhotoPath; 

    public UpdateProfile(User user) {
        UpdateProfile(user);
    }

    public void UpdateProfile(User user) {
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

        JButton photoButton = new JButton("Upload Photo");
        photoButton.setBounds(150, 30, 100, 100);
        photoButton.setHorizontalAlignment(SwingConstants.CENTER);
        photoButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // ImageIcon photoIcon = new ImageIcon(user.getPhotoPath().getAbsolutePath());
        // Image scaledPhoto = photoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        // photoButton.setIcon(new ImageIcon(scaledPhoto));
        // panel.add(photoButton);

        photoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    selectedPhotoPath = fileChooser.getSelectedFile();
                }
            }
        });
        panel.add(photoButton);

        JLabel nameLabel = new JLabel("Name  ");
        nameLabel.setBounds(20, 170, 100, 20);
        panel.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(150, 170, 200, 20);
        panel.add(nameField);

        JLabel phoneNum = new JLabel("Phone Number  ");
        phoneNum.setBounds(20, 200, 100, 20);
        panel.add(phoneNum);

        JTextField phoneField = new JTextField();
        phoneField.setBounds(150, 200, 200, 20);
        panel.add(phoneField);

        JLabel email = new JLabel("Email  ");
        email.setBounds(20, 230, 100, 20);
        panel.add(email);

        JTextField emailField = new JTextField();
        emailField.setBounds(150, 230, 200, 20);
        panel.add(emailField);

        JLabel address = new JLabel("Address  ");
        address.setBounds(20, 260, 100, 20);
        panel.add(address);

        JTextField addressField = new JTextField();
        addressField.setBounds(150, 260, 200, 20);
        panel.add(addressField);

        JButton backHome = new JButton("Back");
        backHome.setBounds(20, 310, 80, 20);
        panel.add(backHome);
        backHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new HomeBuyer(user);
            }
        });

        JButton updateData = new JButton("Change Data");
        updateData.setBounds(220, 310, 120, 20);
        panel.add(updateData);
        updateData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String phone = phoneField.getText();
                String emailText = emailField.getText();
                String addressText = addressField.getText();

                user.setPhoneNum(phone);
                user.setEmail(emailText);
                user.setAddress(addressText);

                if (selectedPhotoPath != null) {
                    user.setPhotoPath(selectedPhotoPath.getAbsolutePath());
                    Register.updatePhotoPath(name, selectedPhotoPath); 
                }

                if (Register.updateData(name, phone, emailText, addressText)) {
                    JOptionPane.showMessageDialog(frame, "Data updated successfully!");
                }
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}
