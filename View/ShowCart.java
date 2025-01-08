package View;

import Controller.BuyerSection;
import Controller.CartSection;
import Modul.Product;
import Modul.SingletonManager;
import Modul.TokosiDiaFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ShowCart {
    private static TokosiDiaFrame frame;
    private static HashMap<String, HashMap<Product, Integer>> checkoutList = new HashMap<>();

    public ShowCart() {
        showCart();
    }

    public void showCart() {
        if(frame != null){
            frame.dispose();
        }

        SingletonManager login = SingletonManager.getInstance();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        frame = new TokosiDiaFrame("Cart");
        frame.setDefaultCloseOperation(TokosiDiaFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(screenSize.width / 2 - 450 / 2, screenSize.height / 2 - 600 / 2, 450, 600);
        frame.setResizable(false);

        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
        containerPanel.setBackground(Color.decode("#D9EAFD"));

        HashMap<String, HashMap<Product, Integer>> cart = login.getCart();
        for (Map.Entry<String, HashMap<Product, Integer>> entry : cart.entrySet()) {
            String seller = entry.getKey();
            int idShop = Controller.DBController.getIDShop(seller);
            HashMap<Product, Integer> productInCart = entry.getValue();

            HashMap<Product, Integer> prodToCheckout = new HashMap<>();
            checkoutList.put(seller, prodToCheckout);

            JPanel sellerPanel = new JPanel();
            sellerPanel.setLayout(new BorderLayout());
            sellerPanel.setBackground(Color.decode("#4DA1A9"));
            sellerPanel.setBorder(javax.swing.BorderFactory.createLineBorder(Color.GRAY));

            JLabel sellerLabel = new JLabel("Seller: " + seller);
            sellerLabel.setFont(new Font("Arial", Font.BOLD, 16));
            sellerPanel.add(sellerLabel, BorderLayout.NORTH);

            JButton checkout = new JButton("Checkout");
            checkout.setBackground(Color.WHITE);
            checkout.setForeground(Color.decode("#4DA1A9"));
            checkout.setBounds(300, 0, 100, 20);
            sellerPanel.add(checkout);

            checkout.addActionListener(e -> {
                if (!checkoutList.get(seller).isEmpty()) {
                    new Checkout(checkoutList.get(seller), idShop);
                }
            });

            JPanel productListPanel = new JPanel();
            productListPanel.setLayout(new BoxLayout(productListPanel, BoxLayout.Y_AXIS));
            productListPanel.setBackground(Color.WHITE);

            for (Map.Entry<Product, Integer> productEntry : productInCart.entrySet()) {
                Product product = productEntry.getKey();
                int amount = productEntry.getValue();
                productListPanel.add(makeProductPanel(product, amount));
            }

            sellerPanel.add(productListPanel, BorderLayout.CENTER);
            containerPanel.add(sellerPanel);
        }

        JScrollPane scrollPane = new JScrollPane(containerPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        frame.add(scrollPane, BorderLayout.CENTER);
        
        frame.setVisible(true);
    }

    public static JPanel makeProductPanel(Product product, int amount) {
        String photo = product.getPhotoProduct();
        int stock = product.getStock();
        int discount = (int) (product.getDiscount());
        int originalPrice = (int) product.getPrice();
        int totalOriginalPrice = originalPrice * amount;
        int price = (int) (originalPrice * (100 - discount) / 100);
        int totalPrice = price * amount;
        String subTotal = Controller.RupiahFormatter.formatRupiah(totalPrice);

        JPanel productPanel = new JPanel();
        productPanel.setPreferredSize(new Dimension(450, 260));
        productPanel.setLayout(new GridLayout(0, 2, 0, 0));
        productPanel.setBackground(Color.decode("#D9EAFD"));
        productPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

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

        productPanel.add(picturePanel);

        JPanel buyPanel = new JPanel();
        buyPanel.setBackground(Color.decode("#D9EAFD"));
        buyPanel.setLayout(null);

        JButton minusQuantity = new JButton("-");
        minusQuantity.setBackground(Color.decode("#4DA1A9"));
        minusQuantity.setForeground(Color.WHITE);
        minusQuantity.setBounds(10, 40, 45, 30);
        buyPanel.add(minusQuantity);

        JTextField quantityField = new JTextField("" + amount);
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

        JCheckBox addCheckOut = new JCheckBox("checkout");
        addCheckOut.setBackground(Color.decode("#D9EAFD"));
        addCheckOut.setBounds(90, 90, 100, 20);
        buyPanel.add(addCheckOut);
        
        JLabel priceLabel = new JLabel(subTotal);
        priceLabel.setBounds(10, 110, 200, 30);
        buyPanel.add(priceLabel);

        JButton remove = new JButton("Remove");
        remove.setBackground(Color.decode("#4DA1A9"));
        remove.setForeground(Color.WHITE);
        remove.setBounds(10, 160, 190, 80);
        buyPanel.add(remove);
 
        JLabel originalPriceLabel = new JLabel("Original: " + Controller.RupiahFormatter.formatRupiah(totalOriginalPrice));
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
                    }
                    else{
                        BuyerSection.updateCart(product, quantity);
                        CartSection.updateCartDB(product, quantity);
                        if (addCheckOut.isSelected()) {
                            checkoutList.get(product.getSellerName()).put(product, quantity);
                        }
                    }
                } catch (NumberFormatException ex) {
                    priceLabel.setText("Invalid Quantity");
                    originalPriceLabel.setText("Invalid Quantity");
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

        remove.addActionListener(e -> {
            Controller.CartSection.removeCartDB(product);
            Controller.BuyerSection.removeProductFromCart(product);

            JPanel productListPanel = (JPanel) productPanel.getParent();
            JPanel sellerPanel = (JPanel) productListPanel.getParent();
            JPanel containerPanel = (JPanel) sellerPanel.getParent();
        
            productListPanel.remove(productPanel);
        
            if (productListPanel.getComponentCount() == 0) {
                containerPanel.remove(sellerPanel);
            }
        
            containerPanel.revalidate();
            containerPanel.repaint();
        });

        addCheckOut.addActionListener(e -> {
            String seller = product.getSellerName();
            if (addCheckOut.isSelected()) {
                int amountProduct = Integer.parseInt(quantityField.getText());
                checkoutList.get(seller).put(product, amountProduct);
            } else {
                checkoutList.get(seller).remove(product);
            }
        });
        

        productPanel.add(buyPanel, BorderLayout.CENTER);
        return productPanel;
    }
}
