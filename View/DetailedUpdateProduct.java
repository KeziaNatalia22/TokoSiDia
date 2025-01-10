package View;

import Modul.Book;
import Modul.Clothing;
import Modul.Electronic;
import Modul.Grocery;
import Modul.Product;
import Modul.Seller;
import Modul.TokosiDiaFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class DetailedUpdateProduct {

    private static TokosiDiaFrame frame;

    public static void detailProduct(Product product, Seller seller) {
        if (frame != null) {
            frame.dispose();
        }

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        String photo = product.getPhotoProduct();
        int stock = product.getStock();
        int discount = (int) (product.getDiscount());
        int price = (int) product.getPrice();

        frame = new TokosiDiaFrame("Detail Product");
        frame.setDefaultCloseOperation(TokosiDiaFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(screenSize.width / 2 - 450 / 2, screenSize.height / 2 - 600 / 2, 450, 600);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        JPanel abovePanel = new JPanel();
        abovePanel.setPreferredSize(new Dimension(450, 260));
        abovePanel.setLayout(new GridLayout(0, 2, 0, 0));
        abovePanel.setBackground(Color.decode("#D9EAFD"));

        JPanel picturePanel = new JPanel();
        picturePanel.setLayout(new BorderLayout());
        picturePanel.setBackground(Color.decode("#D9EAFD"));

        JLabel imageLabel = new JLabel();
        ImageIcon icon = new ImageIcon("Photos/Seller/" + photo);
        Dimension dimImg = new Dimension(icon.getIconWidth(), icon.getIconHeight());
        Dimension dimBound = new Dimension(190, 260);
        Dimension scalledImg = Controller.ImageScaling.getScaledDimension(dimImg, dimBound);
        Image img = icon.getImage().getScaledInstance(scalledImg.width, scalledImg.height, Image.SCALE_REPLICATE);
        imageLabel.setIcon(new ImageIcon(img));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        picturePanel.add(imageLabel, BorderLayout.CENTER);

        abovePanel.add(picturePanel);

        JPanel buyPanel = new JPanel();
        buyPanel.setBackground(Color.decode("#D9EAFD"));
        buyPanel.setLayout(null);

        JLabel titleLabel = new JLabel("Atur Stock");
        titleLabel.setBounds(10, 5, 200, 30);
        buyPanel.add(titleLabel);

        JButton minusQuantity = new JButton("-");
        minusQuantity.setBackground(Color.decode("#4DA1A9"));
        minusQuantity.setForeground(Color.WHITE);
        minusQuantity.setBounds(10, 40, 45, 30);
        buyPanel.add(minusQuantity);

        JTextField quantityField = new JTextField(stock + "");
        quantityField.setBounds(55, 40, 100, 30);
        quantityField.setHorizontalAlignment(JTextField.CENTER);
        buyPanel.add(quantityField);

        JButton plusQuantity = new JButton("+");
        plusQuantity.setBackground(Color.decode("#4DA1A9"));
        plusQuantity.setForeground(Color.WHITE);
        plusQuantity.setBounds(155, 40, 45, 30);
        buyPanel.add(plusQuantity);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(10, 65, 100, 30);
        buyPanel.add(priceLabel);

        JTextField priceTextField = new JTextField(price + "");
        priceTextField.setBounds(10, 90, 200, 30);
        buyPanel.add(priceTextField);

        JLabel discountLabel = new JLabel("Discount:");
        discountLabel.setBounds(10, 115, 100, 30);
        buyPanel.add(discountLabel);

        JTextField discounTextField = new JTextField(discount + "");
        discounTextField.setBounds(10, 140, 200, 30);
        buyPanel.add(discounTextField);

        JButton updateButton = new JButton("Update");
        updateButton.setBackground(Color.decode("#4DA1A9"));
        updateButton.setForeground(Color.WHITE);
        updateButton.setBounds(10, 175, 190, 40);
        buyPanel.add(updateButton);

        JButton removeButton = new JButton("Remove");
        removeButton.setBackground(Color.RED);
        removeButton.setForeground(Color.white);
        removeButton.setBounds(10, 220, 190, 40);
        buyPanel.add(removeButton);

        quantityField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateStock();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateStock();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateStock();
            }

            private void updateStock() {
                try {
                    int quantity = Integer.parseInt(quantityField.getText());
                    if (quantity <= 0) {
                        JOptionPane.showMessageDialog(null, "Stock Tidak Bisa Dibawah 0", "Stock Error",
                                JOptionPane.ERROR_MESSAGE);
                        updateButton.setEnabled(false);
                    } else {
                        updateButton.setEnabled(true);
                    }
                } catch (NumberFormatException ex) {
                    updateButton.setEnabled(false);
                }
            }
        });

        minusQuantity.addActionListener(e -> {
            int quantity = Integer.parseInt(quantityField.getText());
            quantity--;
            quantityField.setText("" + quantity);
        });

        plusQuantity.addActionListener(e -> {
            int quantity = Integer.parseInt(quantityField.getText());
            quantity++;
            quantityField.setText("" + quantity);
        });

        abovePanel.add(buyPanel);

        JPanel belowPanel = new JPanel();
        belowPanel.setLayout(new BorderLayout());

        JPanel detailPanel = createDetailProduct(product);
        belowPanel.add(detailPanel);

        updateButton.addActionListener(e -> {
            product.setPrice(Double.parseDouble(discounTextField.getText()));
            product.setStock(Integer.parseInt(quantityField.getText()));
            product.setDiscount(Double.parseDouble(discounTextField.getText()));
            Controller.UpdateProduct.updateProduct(product);
            frame.dispose();
            JOptionPane.showMessageDialog(null, "Update Berhasil");
            new UpdateProduct(seller);
        });

        removeButton.addActionListener(e -> {
            int dialogResult = JOptionPane.showConfirmDialog(null,
                    "Are You Sure You Want To Delete " + product.getName() + "?", "Warning", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                frame.dispose();
                Controller.RemoveProduct.removeProduct(product.getIdProduct());
                new UpdateProduct(seller);
            }
        });

        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        wrapperPanel.setBackground(Color.WHITE);
        wrapperPanel.add(belowPanel);

        frame.add(abovePanel, BorderLayout.NORTH);
        frame.add(wrapperPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private static JPanel createDetailProduct(Product product) {
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(Color.WHITE);

        StringBuilder detailsText = new StringBuilder();
        detailsText.append("Product: ").append(product.getName()).append("\n")
                .append("Seller: ").append(product.getSellerName()).append("\n\n");

        if (product instanceof Book book) {
            detailsText.append(book.getDetail());
        } else if (product instanceof Clothing clothing) {
            detailsText.append(clothing.getDetail());
        } else if (product instanceof Electronic electronic) {
            detailsText.append(electronic.getDetail());
        } else if (product instanceof Grocery grocery) {
            detailsText.append(grocery.getDetail());
        }

        JTextArea detailsTextArea = new JTextArea(detailsText.toString());
        detailsTextArea.setLineWrap(true);
        detailsTextArea.setWrapStyleWord(true);
        detailsTextArea.setEditable(false);
        detailsTextArea.setBackground(Color.WHITE);
        detailsTextArea.setForeground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(detailsTextArea);
        scrollPane.setPreferredSize(new Dimension(400, 150));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        detailsPanel.add(scrollPane);
        return detailsPanel;
    }

}
