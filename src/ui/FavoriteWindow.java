package ui;

import javax.swing.*;
import java.awt.*;

public class FavoriteWindow extends JFrame {
        public FavoriteWindow() {
            setTitle("Избранное");
            setSize(900, 650);
            setBackground(new Color(28, 28, 28));
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


            // Создаём панель
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.setBackground(new Color(28, 28, 28));

            // Добавляем заглушку
            JLabel label = new JLabel("Избранное (в разработке)", SwingConstants.CENTER);
            label.setForeground(new Color(180, 175, 170));
            label.setFont(new Font("Arial", Font.BOLD, 16));
            label.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
            panel.add(label, BorderLayout.NORTH);

            // Добавляем панель в окно
            add(panel);

            // Показываем окно
            setVisible(true);

        }
}
