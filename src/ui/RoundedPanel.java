package ui;

import javax.swing.*;
import java.awt.*;

public class RoundedPanel extends JPanel {
    private int cornerRadius;

    public RoundedPanel(int radius) {
        this.cornerRadius = radius;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Рисуем твою идеальную тень
        int shadowOffset = 5;
        g2.setColor(Theme.SHADOW);
        g2.fillRoundRect(shadowOffset, shadowOffset, getWidth() - shadowOffset, getHeight() - shadowOffset, cornerRadius, cornerRadius);

        // Рисуем основной фон панели
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - shadowOffset, getHeight() - shadowOffset, cornerRadius, cornerRadius);

        g2.dispose();
        super.paintComponent(g);
    }
}