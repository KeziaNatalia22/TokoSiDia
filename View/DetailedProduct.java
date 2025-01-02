package View;

import Modul.Book;
import Modul.Clothing;
import Modul.Electronic;
import Modul.Grocery;
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
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;



public class DetailedProduct {
    public static void detailProduct(Product product){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        String photo = product.getPhotoProduct();
        int stock = product.getStock();
        int discount = (int)(product.getDiscount());
        int originalPrice = (int)product.getPrice();
        int price = (int)(originalPrice * (100 - discount)/100);
        String subTotal = Controller.RupiahFormatter.formatRupiah(price);

        TokosiDiaFrame frame = new TokosiDiaFrame("Detail Product");
        frame.setDefaultCloseOperation(TokosiDiaFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(screenSize.width/2 - 450/2, screenSize.height/2 - 600/2, 450, 600); 
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        JPanel abovePanel = new JPanel();
        abovePanel.setPreferredSize(new Dimension(450, 300));
        abovePanel.setLayout(new GridLayout(0, 2, 0, 0));
        abovePanel.setBackground(Color.decode("#D9EAFD"));

        JPanel picturePanel = new JPanel();
        picturePanel.setLayout(new BorderLayout());
        picturePanel.setBackground(Color.decode("#D9EAFD"));

        JLabel imageLabel = new JLabel();
        ImageIcon icon = new ImageIcon("Photos/Seller/" + photo);
        Dimension dimImg = new Dimension(icon.getIconWidth(), icon.getIconHeight());
        Dimension dimBound = new Dimension(220, 260);
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
        quantityField.setBounds(55, 40, 70, 30);
        quantityField.setHorizontalAlignment(JTextField.CENTER);
        buyPanel.add(quantityField);

        JButton plusQuantity = new JButton("+");
        plusQuantity.setBackground(Color.decode("#4DA1A9"));
        plusQuantity.setForeground(Color.WHITE);
        plusQuantity.setBounds(125, 40, 45, 30);
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

        if (product instanceof Book book) {
            JScrollPane scrollPane = new JScrollPane(makeDetailProductPanel(book), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setBorder(null);
            belowPanel.add(scrollPane, BorderLayout.CENTER);
        }
        else if (product instanceof Grocery grocery) {
            JScrollPane scrollPane = new JScrollPane(makeDetailProductPanel(grocery), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setBorder(null);
            belowPanel.add(scrollPane, BorderLayout.CENTER);
        }
        else if (product instanceof Electronic electronic) {
            JScrollPane scrollPane = new JScrollPane(makeDetailProductPanel(electronic), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setBorder(null);
            belowPanel.add(scrollPane, BorderLayout.CENTER);
        }
        else if (product instanceof Clothing clothing) {
            JScrollPane scrollPane = new JScrollPane(makeDetailProductPanel(clothing), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setBorder(null);
            belowPanel.add(scrollPane, BorderLayout.CENTER);
        }

        addToCartButton.addActionListener(e -> {
            Controller.BuyerSection.addToCart(product, Integer.parseInt(quantityField.getText()));
        });

        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        wrapperPanel.setBackground(Color.WHITE);
        wrapperPanel.add(belowPanel);

        frame.add(abovePanel, BorderLayout.NORTH);
        frame.add(wrapperPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private static JPanel makeDetailProductPanel(Book book){
        JPanel detailedPanel = new JPanel();
        detailedPanel.setBounds(0, 0, 450, 300);
        detailedPanel.setLayout(new BoxLayout(detailedPanel, BoxLayout.Y_AXIS));
        detailedPanel.setBackground(Color.WHITE);

        String name = book.getName();
        String seller = book.getSellerName();
        String title = book.getTitle();
        String author = book.getAuthor();
        String synopsis = book.getSynopsis();
        Date releaseDate = book.getReleaseDate();
        int pageNum = book.getPageNum();

        detailedPanel.add(Box.createRigidArea(new Dimension(0,10)));

        JLabel nameLabel = new JLabel("Product: " + name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        detailedPanel.add(nameLabel);

        JLabel sellerLabel = new JLabel("Seller: " + seller);
        sellerLabel.setFont(new Font("Arial", Font.BOLD, 14));
        detailedPanel.add(sellerLabel);

        detailedPanel.add(Box.createRigidArea(new Dimension(0,20)));

        JLabel titleLabel = new JLabel("Title: " + title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        detailedPanel.add(titleLabel);

        JLabel authorLabel = new JLabel("Author: " + author);
        authorLabel.setFont(new Font("Arial", Font.BOLD, 14));
        detailedPanel.add(authorLabel);

        JLabel synopsisLabel = new JLabel("Synopsis: " + synopsis);
        synopsisLabel.setFont(new Font("Arial", Font.BOLD, 14));
        detailedPanel.add(synopsisLabel);

        JLabel releaseDateLabel = new JLabel("Release Date: " + releaseDate);
        releaseDateLabel.setFont(new Font("Arial", Font.BOLD, 14));
        detailedPanel.add(releaseDateLabel);

        JLabel pageNumLabel = new JLabel("Page Num: " + pageNum);
        pageNumLabel.setFont(new Font("Arial", Font.BOLD, 14));
        detailedPanel.add(pageNumLabel);   

        return detailedPanel;
    }

    private static JPanel makeDetailProductPanel(Grocery grocery){
        JPanel detailedPanel = new JPanel();
        detailedPanel.setBounds(0, 0, 450, 300);
        detailedPanel.setLayout(new BoxLayout(detailedPanel, BoxLayout.Y_AXIS));
        detailedPanel.setBackground(Color.WHITE);

        String name = grocery.getName();
        String seller = grocery.getSellerName();
        Date prodDate = grocery.getProductionDate();
        Date expDate = grocery.getExpDate();

        detailedPanel.add(Box.createRigidArea(new Dimension(0,10)));

        JLabel nameLabel = new JLabel("Product: " + name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        detailedPanel.add(nameLabel);

        JLabel sellerLabel = new JLabel("Seller: " + seller);
        sellerLabel.setFont(new Font("Arial", Font.BOLD, 14));
        detailedPanel.add(sellerLabel);

        detailedPanel.add(Box.createRigidArea(new Dimension(0,20)));

        JLabel prodDateLabel = new JLabel("Production Date: " + prodDate);
        prodDateLabel.setFont(new Font("Arial", Font.BOLD, 14));
        detailedPanel.add(prodDateLabel);

        JLabel expDateLabel = new JLabel("Expired Date: " + expDate);
        expDateLabel.setFont(new Font("Arial", Font.BOLD, 14));
        detailedPanel.add(expDateLabel);

        return detailedPanel;
    }

    private static JPanel makeDetailProductPanel(Electronic electronic){
        JPanel detailedPanel = new JPanel();
        detailedPanel.setBounds(0, 0, 450, 300);
        detailedPanel.setLayout(new BoxLayout(detailedPanel, BoxLayout.Y_AXIS));
        detailedPanel.setBackground(Color.WHITE);

        String name = electronic.getName();
        String seller = electronic.getSellerName();
        String color = electronic.getColor();
        Date warranty = electronic.getWarranty();
        String manualBook = electronic.getManualBook();

        detailedPanel.add(Box.createRigidArea(new Dimension(0,10)));

        JLabel nameLabel = new JLabel("Product: " + name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        detailedPanel.add(nameLabel);

        JLabel sellerLabel = new JLabel("Seller: " + seller);
        sellerLabel.setFont(new Font("Arial", Font.BOLD, 14));
        detailedPanel.add(sellerLabel);

        detailedPanel.add(Box.createRigidArea(new Dimension(0,20)));

        JLabel colorLabel = new JLabel("Color: " + color);
        colorLabel.setFont(new Font("Arial", Font.BOLD, 14));
        detailedPanel.add(colorLabel);

        JLabel warrantyLabel = new JLabel("Warranty: " + warranty);
        warrantyLabel.setFont(new Font("Arial", Font.BOLD, 14));
        detailedPanel.add(warrantyLabel);

        JLabel manualBookLabel = new JLabel("Manual Book: " + manualBook);
        manualBookLabel.setFont(new Font("Arial", Font.BOLD, 14));
        detailedPanel.add(manualBookLabel);

        return detailedPanel;
    }

    private static JPanel makeDetailProductPanel(Clothing clothing){
        JPanel detailedPanel = new JPanel();
        detailedPanel.setBounds(0, 0, 450, 300);
        detailedPanel.setLayout(new BoxLayout(detailedPanel, BoxLayout.Y_AXIS));
        detailedPanel.setBackground(Color.WHITE);

        String name = clothing.getName();
        String seller = clothing.getSellerName();
        String size = clothing.getSize().name();
        String color = clothing.getColor();

        detailedPanel.add(Box.createRigidArea(new Dimension(0,10)));

        JLabel nameLabel = new JLabel("Product: " + name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        detailedPanel.add(nameLabel);

        JLabel sellerLabel = new JLabel("Seller: " + seller);
        sellerLabel.setFont(new Font("Arial", Font.BOLD, 14));
        detailedPanel.add(sellerLabel);

        detailedPanel.add(Box.createRigidArea(new Dimension(0,20)));

        JLabel sizeLabel = new JLabel("Size: " + size);
        sizeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        detailedPanel.add(sizeLabel);

        JLabel colorLabel = new JLabel("Color: " + color);
        colorLabel.setFont(new Font("Arial", Font.BOLD, 14));
        detailedPanel.add(colorLabel);

        return detailedPanel;
    }
}
