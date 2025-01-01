package Modul;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class TokosiDiaFrame extends JFrame{
    public TokosiDiaFrame(String title){
        innitComponent(title);
    }
    
    private void innitComponent(String title){
        ImageIcon logo = new ImageIcon("Photos/TokosiDia/TokosiDia Logo.jpeg");
        this.setIconImage(logo.getImage());
        this.setTitle(title);
    }
}
