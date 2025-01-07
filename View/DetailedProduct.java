package View;

import Modul.Book;
import Modul.Clothing;
import Modul.Electronic;
import Modul.Grocery;
import Modul.Product;
import Modul.TokosiDiaFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;



public class DetailedProduct {

    private static TokosiDiaFrame frame;

    public static void detailProduct(Product product){
        if (frame != null) {
            frame.dispose();
        }

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        String photo = product.getPhotoProduct();
        int stock = product.getStock();
        int discount = (int)(product.getDiscount());
        int originalPrice = (int)product.getPrice();
        int price = (int)(originalPrice * (100 - discount)/100);
        String subTotal = Controller.RupiahFormatter.formatRupiah(price);

        frame = new TokosiDiaFrame("Detail Product");
        frame.setDefaultCloseOperation(TokosiDiaFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(screenSize.width/2 - 450/2, screenSize.height/2 - 600/2, 450, 600); 
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

        if (discount > 0) {
            JLabel discountLabel = new JLabel(discount + "% OFF");
            discountLabel.setForeground(Color.WHITE);
            discountLabel.setOpaque(true);
            discountLabel.setBackground(Color.RED);
            discountLabel.setHorizontalAlignment(SwingConstants.CENTER);
            picturePanel.add(discountLabel, BorderLayout.NORTH);
        }

        abovePanel.add(picturePanel);

        JPanel buyPanel = new JPanel();
        buyPanel.setBackground(Color.decode("#D9EAFD"));
        buyPanel.setLayout(null);

        JLabel titleLabel = new JLabel("Atur Jumlah");
        titleLabel.setBounds(10, 5, 200, 30);
        buyPanel.add(titleLabel);

        JButton minusQuantity = new JButton("-");
        minusQuantity.setBackground(Color.decode("#4DA1A9"));
        minusQuantity.setForeground(Color.WHITE);
        minusQuantity.setBounds(10, 40, 45, 30);
        buyPanel.add(minusQuantity);

        JTextField quantityField = new JTextField("1");
        quantityField.setBounds(55, 40, 100, 30);
        quantityField.setHorizontalAlignment(JTextField.CENTER);
        buyPanel.add(quantityField);

        JButton plusQuantity = new JButton("+");
        plusQuantity.setBackground(Color.decode("#4DA1A9"));
        plusQuantity.setForeground(Color.WHITE);
        plusQuantity.setBounds(155, 40, 45, 30);
        buyPanel.add(plusQuantity);

        JLabel stockLabel = new JLabel("Stok: " + stock);
        stockLabel.setForeground(Color.BLUE);
        stockLabel.setBounds(10, 70, 100, 30);
        buyPanel.add(stockLabel);

        JLabel subtotalLabel = new JLabel("Subtotal:");
        subtotalLabel.setBounds(10, 90, 100, 30);
        buyPanel.add(subtotalLabel);
        
        JLabel priceLabel = new JLabel(subTotal);
        priceLabel.setBounds(10, 110, 200, 30);
        buyPanel.add(priceLabel);

        JButton addToCartButton = new JButton("+ Keranjang");
        addToCartButton.setBackground(Color.decode("#4DA1A9"));
        addToCartButton.setForeground(Color.WHITE);
        addToCartButton.setBounds(10, 160, 190, 40);
        buyPanel.add(addToCartButton);

        JButton buyButton = new JButton("Beli");
        buyButton.setBackground(Color.WHITE);
        buyButton.setForeground(Color.decode("#4DA1A9"));
        buyButton.setBounds(10, 210, 190, 40);
        buyPanel.add(buyButton);
 
        JLabel originalPriceLabel = new JLabel("Original: " + Controller.RupiahFormatter.formatRupiah(originalPrice));
        originalPriceLabel.setBounds(10, 130, 200, 30);
        originalPriceLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        originalPriceLabel.setForeground(Color.GRAY);
        if (originalPrice != price) {
            buyPanel.add(originalPriceLabel);
        }

        quantityField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateSubtotal();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                updateSubtotal();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                updateSubtotal();
            }
            private void updateSubtotal() {
                try {
                    int quantity = Integer.parseInt(quantityField.getText());
                    String newSubTotal = Controller.RupiahFormatter.formatRupiah(quantity * price);
                    priceLabel.setText(newSubTotal);
                    String newSubTotalOri = Controller.RupiahFormatter.formatRupiah(quantity * originalPrice);
                    originalPriceLabel.setText("Original: " + newSubTotalOri);
                    buyPanel.revalidate();
                    buyPanel.repaint();
                    if (quantity <= 0 || quantity > stock) {
                        priceLabel.setText("Invalid Quantity");
                        originalPriceLabel.setText("Invalid Quantity");
                        addToCartButton.setEnabled(false);
                        buyButton.setEnabled(false);
                    }else{
                        addToCartButton.setEnabled(true);
                        buyButton.setEnabled(true);
                    }
                } catch (NumberFormatException ex) {
                    priceLabel.setText("Invalid Quantity");
                    originalPriceLabel.setText("Invalid Quantity");
                    addToCartButton.setEnabled(false);
                    buyButton.setEnabled(false);
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

        addToCartButton.addActionListener(e -> {
            Controller.BuyerSection.addToCart(product, Integer.parseInt(quantityField.getText()));
            Controller.CartSection.insertCartDB(product, Integer.parseInt(quantityField.getText()));
            new ShowCart();
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
