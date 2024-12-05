package View;

import java.util.Properties;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Register {
    JFrame frame;
    JPanel panel;

    public Register() {
        Regist();
    }

    public void Regist() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize(); // GET MY SCREEN SIZE

        int screenWidth = screenSize.width; // GET PIXELS FOR WIDTH
        int screenHeight = screenSize.height; // GET PIXELS FOR HEIGHT

        final int FRAME_WIDTH = 300; // SET WIDTH
        final int FRAME_HEIGHT = 600; // SET WEIGHT

        int start_x = screenWidth / 2 - (FRAME_WIDTH / 2); // SET START LOCATION FOR X
        int start_y = screenHeight / 2 - (FRAME_HEIGHT / 2); // SET START LOCATION FOR Y

        frame = new JFrame("Menu"); // CREATE FRAME AND SET TITLE
        
        frame.setBounds(start_x, start_y, FRAME_WIDTH, FRAME_HEIGHT); // SET FRAME BOUND
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

        JLabel label1 = new JLabel("Username");
        label1.setBounds(30, 50, 100, 30);
        panel.add(label1);

        JTextField textField1 = new JTextField();
        textField1.setBounds(30, 80, 150, 30);
        panel.add(textField1);

        JLabel label2 = new JLabel("Password");
        label2.setBounds(30, 120, 100, 30);
        panel.add(label2);

        JPasswordField passwordField1 = new JPasswordField();
        passwordField1.setBounds(30, 150, 150, 30);
        panel.add(passwordField1);

        JLabel label3 = new JLabel("Email");
        label3.setBounds(30, 180, 100, 30);
        panel.add(label3);

        JTextField textField3 = new JTextField();
        textField3.setBounds(30, 210, 150, 30);
        panel.add(textField3);

        JLabel label4 = new JLabel("Address");
        label4.setBounds(30, 250, 100, 30);
        panel.add(label4);

        JTextField textField4 = new JTextField();
        textField4.setBounds(30, 280, 150, 30);
        panel.add(textField4);

        JLabel label5 = new JLabel("Type");
        label5.setBounds(30, 310, 100, 30);
        panel.add(label5);

        JTextField textField5 = new JTextField();
        textField5.setBounds(30, 340, 150, 30);
        panel.add(textField5);

        JButton submit = new JButton("Submit");
        submit.setBounds(30, 380, 150, 30);
        panel.add(submit);

        frame.setVisible(true);
        frame.add(panel);
    }
}