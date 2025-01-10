package View;

import Modul.TokosiDiaFrame;
import Modul.User;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.*;

public class SearchedSeller {
    static TokosiDiaFrame frame;
    static JPanel mainPanel;
    static JScrollPane scrollPane;
    static JButton showMoreButton;
    static int offset = 0;
    final static int SHOW_MORE = 8;

    public SearchedSeller(String search){
        searchedSeller(search);
    }

    public void searchedSeller(String search) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        frame = new TokosiDiaFrame("Searched Seller");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(screenSize.width/2 - 900/2, screenSize.height/2 - 600/2, 900, 600); 
        frame.setResizable(false);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 4, 10, 10));
        mainPanel.setBackground(Color.decode("#D9DFC6"));

        addFirst8Searched(search);

        scrollPane = new JScrollPane(mainPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        showMoreButton = new JButton("Show More");
        showMoreButton.setFont(new Font("Arial", Font.PLAIN, 14));
        showMoreButton.setBackground(new Color(220, 220, 220));
        showMoreButton.setFocusPainted(false);
        showMoreButton.addActionListener(e -> {
            addMoreProducts(search);
        });

        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.add(scrollPane, BorderLayout.CENTER);
        wrapperPanel.add(showMoreButton, BorderLayout.SOUTH);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                offset = 0;
            }
        });

        frame.add(wrapperPanel);
        frame.setVisible(true);
    }

    private void addFirst8Searched(String search) {
        ArrayList<User> searchedSeller = Controller.BuyerSection.searchSeller(search, offset);
        for (User user : searchedSeller) {
            mainPanel.add(createSellerCard(user));
        }
    }

    private void addMoreProducts(String search) {
        offset += SHOW_MORE;

        ArrayList<User> searchedSeller = Controller.BuyerSection.searchSeller(search, offset);

        if (searchedSeller.isEmpty()) {
            showMoreButton.setVisible(false);
        }
        else{
            for (User seller : searchedSeller) {
                mainPanel.add(createSellerCard(seller));
            }
            mainPanel.revalidate(); 
            mainPanel.repaint(); 
        }
    }

    private JPanel createSellerCard(User seller) {
        String sellerName = seller.getName();
        String photo = seller.getPhotoPath();
    
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        card.setBackground(Color.WHITE);
        card.setPreferredSize(new Dimension(180, 250));
    
        JLabel imageLabel = new JLabel();
        ImageIcon icon = new ImageIcon(photo);
        Dimension dimImg = new Dimension(icon.getIconWidth(), icon.getIconHeight());
        Dimension dimBound = new Dimension(200, 200);
        Dimension scalledImg = Controller.ImageScaling.getScaledDimension(dimImg, dimBound);
        Image img = icon.getImage().getScaledInstance(scalledImg.width, scalledImg.height, Image.SCALE_REPLICATE);
        imageLabel.setIcon(new ImageIcon(img));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(imageLabel, BorderLayout.CENTER);
    
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(Color.WHITE);
    
        JLabel nameLabel = new JLabel(sellerName);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailsPanel.add(nameLabel);
    
        card.add(detailsPanel, BorderLayout.SOUTH);
    
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                card.setBackground(Color.decode("#D9EAFD"));
                detailsPanel.setBackground(Color.decode("#D9EAFD"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                card.setBackground(Color.WHITE);
                detailsPanel.setBackground(Color.WHITE);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                new SearchedProduct(sellerName, 5);
            }
        });
    
        return card;
    }    
}
