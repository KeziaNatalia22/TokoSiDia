package View;

import Controller.Register.*;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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
        Dimension screenSize = toolkit.getScreenSize();

        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        final int FRAME_WIDTH = 300;
        final int FRAME_HEIGHT = 600;

        int start_x = screenWidth / 2 - (FRAME_WIDTH / 2);
        int start_y = screenHeight / 2 - (FRAME_HEIGHT / 2);

        frame = new JFrame("Menu");
        frame.setBounds(start_x, start_y, FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);

        JLabel label1 = new JLabel("Username");
        label1.setBounds(30, 50, 150, 30);
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

        JLabel label6 = new JLabel("Shop Name");
        label6.setBounds(30, 370, 150, 30);
        label6.setVisible(false); // Hidden by default
        panel.add(label6);

        JTextField textField6 = new JTextField();
        textField6.setBounds(30, 400, 150, 30);
        textField6.setVisible(false); // Hidden by default
        panel.add(textField6);

        JLabel label7 = new JLabel("City Located");
        label7.setBounds(30, 430, 150, 30);
        label7.setVisible(false); // Hidden by default
        panel.add(label7);

        JTextField textField7 = new JTextField();
        textField7.setBounds(30, 460, 150, 30);
        textField7.setVisible(false); // Hidden by default
        panel.add(textField7);

        textField5.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                toggleFields();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                toggleFields();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                toggleFields();
            }

            private void toggleFields() {
                String type = textField5.getText().trim().toLowerCase();
                boolean isSeller = type.equals("seller");
                label6.setVisible(isSeller);
                textField6.setVisible(isSeller);
                label7.setVisible(isSeller);
                textField7.setVisible(isSeller);
            }
        });

        JButton submit = new JButton("Submit");
        submit.setBounds(30, 500, 150, 30);
        panel.add(submit);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Controller.Register.checkUniqueUsername(textField1.getText()) == 0){
                    JOptionPane.showMessageDialog(frame, "Username is used!");
                } 
                
                if(Controller.Register.checkUniqueEmail(textField3.getText()) == 0){
                    JOptionPane.showMessageDialog(frame, "Email is used!");
                }
            }
        });

        frame.setVisible(true);
        frame.add(panel);
    }
}
