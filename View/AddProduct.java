package View;

import javax.swing.*;

import org.jdatepicker.impl.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Modul.*;
import Controller.*;
import java.io.File;
import java.lang.ModuleLayer.Controller;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle.Control;

public class AddProduct {
    JFrame frame;
    JPanel panel;
    JPanel type;
    File selectedPhotoPath; 
    JTextField nameField;
    JTextField stockField;
    JTextField discountField;
    JTextField priceField;
    JComboBox<String> typeCombo;
    String sellerName;


    public AddProduct(Seller user) {
        sellerName = user.getName();
        addProduct(user);
    }

    public void addProduct(Seller user) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize(); // Get screen size

        int screenWidth = screenSize.width; // Screen width
        int screenHeight = screenSize.height; // Screen height

        final int FRAME_WIDTH = 400; // Set frame width
        final int FRAME_HEIGHT = 400; // Set frame height

        int start_x = screenWidth / 2 - (FRAME_WIDTH / 2); // Center frame horizontally
        int start_y = screenHeight / 2 - (FRAME_HEIGHT / 2); // Center frame vertically

        frame = new TokosiDiaFrame("Add Product"); // Create frame and set title

        frame.setBounds(start_x, start_y, FRAME_WIDTH, FRAME_HEIGHT); // Set frame bounds
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0,0,FRAME_WIDTH, FRAME_HEIGHT);

        type = new JPanel();
        type.setLayout(null);
        type.setBounds(0,0,FRAME_WIDTH, FRAME_HEIGHT);

        JLabel photoLabel = new JLabel();
        photoLabel.setBounds(150, 30, 100, 100); // Adjust photo position
        photoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        photoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(photoLabel);

        JButton photoButton = new JButton("Upload Photo");
        photoButton.setBounds(145, 140, 110, 20);
        photoButton.setHorizontalAlignment(SwingConstants.CENTER);

        photoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    selectedPhotoPath = fileChooser.getSelectedFile();
                    photoLabel.setIcon(new ImageIcon(new ImageIcon(selectedPhotoPath.getAbsolutePath()).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
                }
            }
        });

        panel.add(photoButton);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(20, 170, 100, 20);
        panel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(120, 170, 200, 20);
        panel.add(nameField);

        JLabel stockLabel = new JLabel("Stock");
        stockLabel.setBounds(20, 200, 100, 20);
        panel.add(stockLabel);

        stockField = new JTextField();
        stockField.setBounds(120, 200, 200, 20);
        panel.add(stockField);
        
        JLabel discountLabel = new JLabel("Discount");
        discountLabel.setBounds(20, 230, 100, 20);
        panel.add(discountLabel);

        discountField = new JTextField();
        discountField.setBounds(120, 230, 200, 20);
        panel.add(discountField);

        JLabel priceLabel = new JLabel("Price");
        priceLabel.setBounds(20, 260, 100, 20);
        panel.add(priceLabel);

        priceField = new JTextField();
        priceField.setBounds(120, 260, 200, 20);
        panel.add(priceField);

        

        JLabel typeLabel = new JLabel("Product Type");
        typeLabel.setBounds(20, 290, 100, 20);
        panel.add(typeLabel);

        String productTypeList[] = {"Book", "Clothing", "Electronic", "Grocery"};
        JComboBox typeCombo = new JComboBox(productTypeList);
        typeCombo.setSelectedIndex(0);
        typeCombo.setBounds(120,290,200,20);
        panel.add(typeCombo);
        
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

        JButton typeData = new JButton("Next");
        typeData.setBounds(220, 320, 120, 20);
        panel.add(typeData);
        typeData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                type.removeAll();
                String s = (String) typeCombo.getSelectedItem();
        
                switch (s) {//check for a match
                    case "Book":
                        addBook();
                        break;
                    case "Clothing":
                        addClothing();
                        break;
                    case "Electronic":
                        addElectronic();
                        break;
                    case "Grocery":
                        addGrocery();
                        break;
                }
                
            }
        });

        frame.add(panel);
        frame.add(type);
        type.setVisible(false);
        frame.setVisible(true);
    }

    public void addBook(){
        panel.setVisible(false);

        JLabel title = new JLabel("Book", SwingConstants.CENTER);
        title.setBounds(50, 20, 300, 40);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        type.add(title);

        JLabel titleLabel = new JLabel("Title");
        titleLabel.setBounds(20, 70, 100, 20);
        type.add(titleLabel);

        JTextField titleField = new JTextField();
        titleField.setBounds(120, 70, 200, 20);
        type.add(titleField);

        JLabel authorLabel = new JLabel("Author");
        authorLabel.setBounds(20, 100, 100, 20);
        type.add(authorLabel);

        JTextField authorField = new JTextField();
        authorField.setBounds(120, 100, 200, 20);
        type.add(authorField);

        JLabel synopsisLabel = new JLabel("Synopsis");
        synopsisLabel.setBounds(20, 130, 100, 20);
        type.add(synopsisLabel);

        JTextArea synopsisArea = new JTextArea(10,30);
        synopsisArea.setBounds(120, 130, 200, 80);
        synopsisArea.setLineWrap(true);
        synopsisArea.setWrapStyleWord(true);
        type.add(synopsisArea);

        JLabel releaseDateLabel = new JLabel("Release Date");
        releaseDateLabel.setBounds(20, 220,100,20);
        type.add(releaseDateLabel);


        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl releaseDate = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        releaseDate.setBounds(120, 220,200,30);
        type.add(releaseDate);

        JLabel pageNumLabel = new JLabel("Page Number");
        pageNumLabel.setBounds(20, 260, 100, 20);
        type.add(pageNumLabel);

        JTextField pageNum = new JTextField();
        pageNum.setBounds(120, 260, 200, 20);
        type.add(pageNum);

        JButton backHome = new JButton("Back");
        backHome.setBounds(20, 300, 80, 20);
        type.add(backHome);
        backHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                type.removeAll();
                panel.setVisible(true);
            }
        });

        JButton typeData = new JButton("Add Product");
        typeData.setBounds(220, 300, 120, 20);
        type.add(typeData);
        typeData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(releaseDate.getJFormattedTextField().getText());
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Book book = new Book(authorField.getText(), Integer.parseInt(pageNum.getText()), formatter.parse(releaseDate.getJFormattedTextField().getText()), synopsisArea.getText(), titleField.getText(), null, sellerName, nameField.getText(), Integer.parseInt(stockField.getText()), Double.parseDouble(discountField.getText()), Double.parseDouble(priceField.getText()), selectedPhotoPath.getAbsolutePath());
                    ProductSection.addProduct(book);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        type.setVisible(true);
    }

    public void addClothing(){
        panel.setVisible(false);

        JLabel title = new JLabel("Clothing", SwingConstants.CENTER);
        title.setBounds(50, 20, 300, 40);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        type.add(title);

        JLabel ukuranLabel = new JLabel("Ukuran");
        ukuranLabel.setBounds(20, 70, 100, 20);
        type.add(ukuranLabel);

        JRadioButton s = new JRadioButton("S");
        s.setBounds(120, 70, 40, 20);
        s.setActionCommand("S");
        JRadioButton m = new JRadioButton("M");
        m.setBounds(165, 70, 40, 20);
        m.setActionCommand("M");
        JRadioButton l = new JRadioButton("L");
        l.setBounds(210, 70, 40, 20);
        l.setActionCommand("L");
        ButtonGroup grupUkuran = new ButtonGroup();
        grupUkuran.add(s);
        grupUkuran.add(m);
        grupUkuran.add(l);

        type.add(s);
        type.add(m);
        type.add(l);

        JLabel warnaLabel = new JLabel("Warna");
        warnaLabel.setBounds(20, 100, 100, 20);
        type.add(warnaLabel);

        JTextField warnaField = new JTextField();
        warnaField.setBounds(120, 100, 200, 20);
        type.add(warnaField);

        JButton backHome = new JButton("Back");
        backHome.setBounds(20, 300, 80, 20);
        type.add(backHome);
        backHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                type.removeAll();
                panel.setVisible(true);
            }
        });

        JButton typeData = new JButton("Add Product");
        typeData.setBounds(220, 300, 120, 20);
        type.add(typeData);
        typeData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClothingSize_Enum c = null;
                switch (grupUkuran.getSelection().getActionCommand()) {
                    case "S":
                        c = ClothingSize_Enum.S;
                        break;
                    case "M":
                        c = ClothingSize_Enum.M;
                        break;
                    case "L":
                        c = ClothingSize_Enum.L;   
                        break;              
                    default:
                        break;
                }
                ProductSection.addProduct(new Clothing(warnaField.getText(), c, null ,sellerName, nameField.getText(), Integer.parseInt(stockField.getText()), Double.parseDouble(discountField.getText()), Double.parseDouble(priceField.getText()), selectedPhotoPath.getAbsolutePath()));
            }
        });

        type.setVisible(true);
    }

    public void addElectronic(){
        panel.setVisible(false);

        JLabel title = new JLabel("Electronic", SwingConstants.CENTER);
        title.setBounds(50, 20, 300, 40);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        type.add(title);

        JLabel colorLabel = new JLabel("Warna");
        colorLabel.setBounds(20, 70, 100, 20);
        type.add(colorLabel);

        JTextField colorField = new JTextField();
        colorField.setBounds(120, 70, 200, 20);
        type.add(colorField);

        JLabel warrantyLabel = new JLabel("Garansi");
        warrantyLabel.setBounds(20, 100,100,20);
        type.add(warrantyLabel);


        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl warranty = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        warranty.setBounds(120, 100,200,30);
        type.add(warranty);

        
        JLabel manualLabel = new JLabel("Manual Book");
        manualLabel.setBounds(20, 140, 100, 20);
        type.add(manualLabel);

        JTextField manualField = new JTextField();
        manualField.setBounds(120, 140, 200, 20);
        type.add(manualField);

        JButton backHome = new JButton("Back");
        backHome.setBounds(20, 300, 80, 20);
        type.add(backHome);
        backHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                type.removeAll();
                panel.setVisible(true);
            }
        });

        JButton typeData = new JButton("Add Product");
        typeData.setBounds(220, 300, 120, 20);
        type.add(typeData);
        typeData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    ProductSection.addProduct(new Electronic(colorField.getText(), manualField.getText(), formatter.parse(warranty.getJFormattedTextField().getText()) ,null,sellerName, nameField.getText(), Integer.parseInt(stockField.getText()), Double.parseDouble(discountField.getText()), Double.parseDouble(priceField.getText()), selectedPhotoPath.getAbsolutePath()));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }   
            }
        });

        type.setVisible(true);
    }

    public void addGrocery(){
        panel.setVisible(false);

        JLabel title = new JLabel("Grocery", SwingConstants.CENTER);
        title.setBounds(50, 20, 300, 40);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        type.add(title);

        JLabel expLabel = new JLabel("Expiry Date");
        expLabel.setBounds(20, 70,100,20);
        type.add(expLabel);


        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl expDate = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        expDate.setBounds(120, 70,200,30);
        type.add(expDate);

        
        JLabel productionLabel = new JLabel("Production Date");
        productionLabel.setBounds(20, 110, 100, 20);
        type.add(productionLabel);

        UtilDateModel model2 = new UtilDateModel();
        Properties p2 = new Properties();
        JDatePanelImpl datePanel2 = new JDatePanelImpl(model2, p2);
        JDatePickerImpl productionDate = new JDatePickerImpl(datePanel2, new DateLabelFormatter());
        productionDate.setBounds(120, 110,200,30);
        type.add(productionDate);

        JButton backHome = new JButton("Back");
        backHome.setBounds(20, 300, 80, 20);
        type.add(backHome);
        backHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                type.removeAll();
                panel.setVisible(true);
            }
        });

        JButton typeData = new JButton("Add Product");
        typeData.setBounds(220, 300, 120, 20);
        type.add(typeData);
        typeData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    ProductSection.addProduct(new Grocery(formatter.parse(expDate.getJFormattedTextField().getText()), formatter.parse(productionDate.getJFormattedTextField().getText()),null,sellerName, nameField.getText(), Integer.parseInt(stockField.getText()), Double.parseDouble(discountField.getText()), Double.parseDouble(priceField.getText()), selectedPhotoPath.getAbsolutePath()));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }   
            }
        });
        type.setVisible(true);
    }
}
    

