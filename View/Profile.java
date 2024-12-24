package View;

import javax.swing.*;
import Controller.Register;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Modul.Buyer;

public class Profile {
    JFrame frame;
    JPanel panel;

    public Profile(Buyer user) {
        ProfileBuyer(user);
    }

    public void ProfileBuyer(Buyer user) {
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
        panel.add(nameLabel);

        JLabel phoneNum = new JLabel("Phone : " + user.getPhoneNum());
        phoneNum.setBounds(20, 180, 350, 20);
        phoneNum.setFont(font); 
        panel.add(phoneNum);

        JLabel email = new JLabel("Email : " + user.getEmail());
        email.setBounds(20, 210, 350, 20);
        email.setFont(font); 
        panel.add(email);

        JLabel address = new JLabel("Address : " + user.getAlamat());
        address.setBounds(20, 240, 350, 20);
        address.setFont(font); 
        panel.add(address);

        JButton backHome = new JButton("Back");
        backHome.setBounds(20, 310, 80, 30);
        backHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new HomeBuyer(user);
            }
        });

        JButton updateData = new JButton("Change Data");
        updateData.setBounds(220, 310, 120, 30);
        updateData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateProfile(user);
                frame.dispose();
            }
        });

        panel.add(backHome);
        panel.add(updateData);

        frame.add(panel);
        frame.setVisible(true);
    }
}
