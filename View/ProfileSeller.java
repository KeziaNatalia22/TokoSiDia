package View;
import javax.swing.*;
import Controller.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Modul.Buyer;
import Modul.Seller;
import Modul.SingletonManager;

public class ProfileSeller {
    JFrame frame;
    JPanel panel;

    public ProfileSeller() {
        ProfileSeller();
    }

    public void ProfileSeller() {
        SingletonManager login = SingletonManager.getInstance();
        Seller user = (Seller) login.getUser();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize(); // Get screen size

        int screenWidth = screenSize.width; // Screen width
        int screenHeight = screenSize.height; // Screen height

        final int FRAME_WIDTH = 400; // Set frame width
        final int FRAME_HEIGHT = 450; // Set frame height (adjusted to accommodate content)

        int start_x = screenWidth / 2 - (FRAME_WIDTH / 2); // Center frame horizontally
        int start_y = screenHeight / 2 - (FRAME_HEIGHT / 2); // Center frame vertically

        frame = new JFrame("Profile"); 
        frame.setBounds(start_x, start_y, FRAME_WIDTH, FRAME_HEIGHT); // Set frame bounds
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null); 

        Font font = new Font("Montserrat", Font.PLAIN, 18); 

        JLabel photoLabel = new JLabel();
        photoLabel.setBounds(150, 30, 100, 100); // Adjust photo position
        photoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        photoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        ImageIcon photoIcon = new ImageIcon(user.getPhotoPath());
        Image scaledPhoto = photoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        photoLabel.setIcon(new ImageIcon(scaledPhoto));
        panel.add(photoLabel);

        JLabel nameLabel = new JLabel("Name : " + user.getName());
        nameLabel.setBounds(20, 150, 350, 20);
        nameLabel.setFont(font); 
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(nameLabel);

        JLabel phoneNum = new JLabel("Phone : " + user.getPhoneNum());
        phoneNum.setBounds(20, 180, 350, 20);
        phoneNum.setFont(font); 
        phoneNum.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(phoneNum);

        JLabel email = new JLabel("Email : " + user.getEmail());
        email.setBounds(20, 210, 350, 20);
        email.setFont(font); 
        email.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(email);

        JLabel address = new JLabel("City : " + user.getCityLocated());
        address.setBounds(20, 240, 350, 20);
        address.setFont(font); 
        address.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(address);

        JLabel shopName = new JLabel("Shop Name : " + user.getShopName());
        shopName.setBounds(20, 270, 350, 20);
        shopName.setFont(font); 
        shopName.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(shopName);

        JButton backHome = new JButton("Back");
        backHome.setBounds(60, 310, 100, 30);
        backHome.setBackground(new Color(244, 164, 96));
        backHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new HomeSeller();
            }
        });

        JButton updateData = new JButton("Change Data");
        updateData.setBounds(220, 310, 120, 30);
        updateData.setBackground(new Color(72, 209, 204));
        updateData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateProfileSeller();
                frame.dispose();
            }
        });

        JButton logout = new JButton("Log Out");
        logout.setBounds(60, 350, 100, 30);
        logout.setBackground(Color.RED);
        logout.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, 
                        "Are you sure you want to log out?", 
                        "Log Out", 
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    frame.dispose();
                    SingletonManager.removeInstance();
                    new MenuLogin();
                }
            }
        });

        JButton changePass = new JButton("Change Password");
        changePass.setBackground(Color.ORANGE);
        changePass.setBounds(220, 350, 120, 30);
        changePass.setHorizontalAlignment(SwingConstants.CENTER);
        changePass.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChangePassword(SingletonManager.getInstance().getUser().getName());
            }
        });
        
        panel.add(backHome);
        panel.add(updateData);
        panel.add(logout);
        panel.add(changePass);

        frame.add(panel);
        frame.setVisible(true);
    }
}
