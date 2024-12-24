package View;

import javax.swing.*;

import Controller.Register;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Modul.User;

public class Profile {
    JFrame frame;
    JPanel panel;

    public Profile(User user) {
        Profile(user);
    }

    public void Profile(User user) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize(); // Get screen size

        int screenWidth = screenSize.width; // Screen width
        int screenHeight = screenSize.height; // Screen height

        final int FRAME_WIDTH = 400; // Set frame width
        final int FRAME_HEIGHT = 500; // Set frame height

        int start_x = screenWidth / 2 - (FRAME_WIDTH / 2); // Center frame horizontally
        int start_y = screenHeight / 2 - (FRAME_HEIGHT / 2); // Center frame vertically

        frame = new JFrame("Profile"); // Create frame and set title

        frame.setBounds(start_x, start_y, FRAME_WIDTH, FRAME_HEIGHT); // Set frame bounds
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

        JLabel nameLabel = new JLabel("Name : ");
        nameLabel.setBounds(20, 20, 100, 20);
        panel.add(nameLabel);

        JTextField nameField = new JTextField(user.getName());
        nameField.setBounds(150, 20, 200, 20);
        panel.add(nameField);

        JLabel phoneNum = new JLabel("Phone Number : ");
        phoneNum.setBounds(20, 50, 100, 20);
        panel.add(phoneNum);

        JTextField phoneField = new JTextField(user.getPhoneNum());
        phoneField.setBounds(150, 50, 200, 20);
        panel.add(phoneField);

        JLabel email = new JLabel("Email : ");
        email.setBounds(20, 80, 100, 20);
        panel.add(email);

        JTextField emailField = new JTextField(user.getEmail());
        emailField.setBounds(150, 80, 200, 20);
        panel.add(emailField);

        JLabel address = new JLabel("Address : ");
        address.setBounds(20, 110, 100, 20);
        panel.add(address);

        JTextField addressField = new JTextField(user.getAddress());
        addressField.setBounds(150, 110, 200, 20);
        panel.add(addressField);

        JButton backHome = new JButton("Back");
        backHome.setBounds(20, 160, 80, 20);
        panel.add(backHome);
        backHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MainPage(user);
            }
        });

        JButton updateData = new JButton("Change Data");
        updateData.setBounds(120, 160, 120, 20);
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

                if (Register.updateData(name, phone, emailText, addressText)) {
                    JOptionPane.showMessageDialog(frame, "Data updated successfully!");
                }
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}
