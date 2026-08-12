package ui;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame("PCBuilder");
        window.setSize(900, 650);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Theme.BACKGROUND);



        CenterPanel centerPanel = new CenterPanel(); // сначала создай центр
        LeftPanel leftPanel = new LeftPanel(centerPanel); // потом левую панель
        TopPanel topPanel = new TopPanel(centerPanel, leftPanel);
        window.add(topPanel, BorderLayout.NORTH);
        window.add(leftPanel, BorderLayout.WEST);
        window.add(centerPanel, BorderLayout.CENTER);






        window.setVisible(true);
        topPanel.requestFocusInWindow();


    }

}
