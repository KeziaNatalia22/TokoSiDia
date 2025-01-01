package View;
import javax.swing.*;
import Controller.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Modul.Buyer;

public class ProfileBuyer {
    JFrame frame;
    JPanel panel;

    public ProfileBuyer(Buyer user) {
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

        JLabel address = new JLabel("Address : " + user.getAlamat());
        address.setBounds(20, 240, 350, 20);
        address.setFont(font); 
        address.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(address);

        JButton backHome = new JButton("Back");
        backHome.setBounds(60, 310, 120, 30);
        backHome.setBackground(Color.gray);
        backHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new HomeBuyer(user);
            }
        });

        JButton updateData = new JButton("Change Data");
        updateData.setBounds(220, 310, 120, 30);
        updateData.setBackground(Color.CYAN);
        updateData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateProfile(user);
                frame.dispose();
            }
        });

        JButton addBalance = new JButton("Add Balance");
        addBalance.setBounds(60, 350, 120, 30);
        addBalance.setBackground(Color.pink);
        addBalance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(frame, "Enter the amount to add to your balance:", "Add Balance", JOptionPane.PLAIN_MESSAGE);
                if (input != null && !input.isEmpty()) {
                    try {
                        double amount = Double.parseDouble(input);
                        if (amount > 0) {
                            user.seteMoney(user.geteMoney() + amount);
                            if (BalanceSection.addBalance(user.getName(), user.geteMoney())) {
                                JOptionPane.showMessageDialog(frame, "Balance added successfully! New Balance: " + user.geteMoney(), "Success", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(frame, "Please enter a positive amount.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Invalid amount entered. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        JButton logout = new JButton("Log Out");
        logout.setBounds(220, 350, 120, 30);
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
                    new MenuLogin();
                }
            }
        });
        panel.add(logout);

        panel.add(backHome);
        panel.add(updateData);
        panel.add(addBalance);

        frame.add(panel);
        frame.setVisible(true);
    }
}
