package View;

import Modul.Product;
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
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class Checkout {
    private static TokosiDiaFrame frame;

    public Checkout(HashMap<Product, Integer> productList, int idShop){
        checkout(productList, idShop);
    }

    public void checkout(HashMap<Product, Integer> productList, int idShop){
        if(frame != null){
            frame.dispose();
        }

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        frame = new TokosiDiaFrame("Checkout");
        frame.setDefaultCloseOperation(TokosiDiaFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(screenSize.width / 2 - 450 / 2, screenSize.height / 2 - 600 / 2, 450, 600);
        frame.setResizable(false);

        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
        containerPanel.setBackground(Color.decode("#D9EAFD"));

        JPanel checkoutPanel = new JPanel();
        checkoutPanel.setPreferredSize(new Dimension(450, 40));
        checkoutPanel.setBackground(Color.WHITE);
        checkoutPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        AtomicInteger totalPrice = new AtomicInteger(0);

        for (Map.Entry<Product, Integer> productEntry : productList.entrySet()) {
            Product product = productEntry.getKey();
            int amount = productEntry.getValue();
            totalPrice.addAndGet(((int)product.getPrice() * (100 - (int)product.getDiscount())/100) * amount);
            containerPanel.add(makeProductPanel(product, amount));
        }

        int totalPrice2 = totalPrice.get();

        JLabel subtotalLabel = new JLabel("Subtotal: " + Controller.RupiahFormatter.formatRupiah(totalPrice2));
        subtotalLabel.setBounds(10, 70, 100, 30);
        checkoutPanel.add(subtotalLabel);

        JButton buy = new JButton("BUY");
        buy.setBounds(10, 120, 50, 30);
        checkoutPanel.add(buy);
        
        containerPanel.add(checkoutPanel, BorderLayout.WEST);

        buy.addActionListener(e -> {
            Controller.BuyerSection.checkout(productList, totalPrice, idShop);
            frame.dispose();
        });

        JScrollPane scrollPane = new JScrollPane(containerPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public static JPanel makeProductPanel(Product product, int amount) {
        String photo = product.getPhotoProduct();
        int discount = (int)product.getDiscount();
        int originalPrice = (int)product.getPrice() * amount;
        int price = ((int)product.getPrice() * (100 - (int)product.getDiscount())/100) * amount;
        String subTotal = Controller.RupiahFormatter.formatRupiah(price);
    
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
    
        JLabel stockLabel = new JLabel("Amount: " + amount);
        stockLabel.setForeground(Color.BLUE);
        stockLabel.setBounds(10, 70, 100, 30);
        buyPanel.add(stockLabel);
        
        JLabel subtotalLabel = new JLabel("Subtotal:");
        subtotalLabel.setBounds(10, 90, 100, 30);
        buyPanel.add(subtotalLabel);
        
        JLabel priceLabel = new JLabel(subTotal);
        priceLabel.setBounds(10, 110, 200, 30);
        buyPanel.add(priceLabel);
    
        JLabel originalPriceLabel = new JLabel("Original: " + Controller.RupiahFormatter.formatRupiah(originalPrice));
        originalPriceLabel.setBounds(10, 130, 200, 30);
        originalPriceLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        originalPriceLabel.setForeground(Color.GRAY);
        if (originalPrice != price) {
            buyPanel.add(originalPriceLabel);
        }
        
        productPanel.add(buyPanel, BorderLayout.CENTER);
        return productPanel;
    }
}
