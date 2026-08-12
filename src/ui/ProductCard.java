package ui;
import javax.swing.*;
import java.awt.*;

public class ProductCard extends JPanel {
    private String name;
    private String price;
    public ProductCard(String name, String price) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Theme.CARD_BG);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.name = name;
        this.price = price;
        JLabel namelabe1 = new JLabel(this.name);
        namelabe1.setForeground(Theme.TEXT_MAIN);
        namelabe1.setFont(new Font("Arial", Font.PLAIN, 15));
        add(namelabe1);
        add(Box.createVerticalStrut(5));
        JLabel pricelabe1 = new JLabel(this.price);
        pricelabe1.setForeground(Theme.ACCENT);
        pricelabe1.setFont(new Font("Arial", Font.PLAIN, 13));
        add(pricelabe1);
    }
}
