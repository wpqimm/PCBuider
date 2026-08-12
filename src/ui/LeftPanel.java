package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;

public class LeftPanel extends JPanel {
    private CenterPanel centerPanel;


    public LeftPanel(CenterPanel cp) {
        // Выводим каталог на левую панель
        this.centerPanel = cp;
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(COLLAPSED_WIDTH, 0));
        setBackground(Theme.BACKGROUND);


        RoundedPanel menuPanel = new RoundedPanel(25);
        menuPanel.setOpaque(false);

        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));


        add(menuPanel, BorderLayout.CENTER);



    }
    public void showContent(JPanel content) {
        setLayout(new BorderLayout());
        removeAll();
        add(content, BorderLayout.CENTER);
        revalidate();
        repaint();
    }


    public void clearContent() {
        removeAll();
        revalidate();
        repaint();
    }
    public void expand() {
        if (getWidth() >= EXPANDED_WIDTH) return;

        Timer timer = new Timer(5, e -> {
            int currentWidth = getWidth();
            if (currentWidth >= EXPANDED_WIDTH) {
                setPreferredSize(new Dimension(EXPANDED_WIDTH, getHeight()));
                ((Timer) e.getSource()).stop();
                showContent(new CatalogPanel(this.centerPanel));
                revalidate();
                repaint();
                SwingUtilities.getWindowAncestor(this).revalidate();
                return;
            }
            int step = Math.min(5, EXPANDED_WIDTH - currentWidth); // шаг 5 пикселей
            setPreferredSize(new Dimension(currentWidth + step, getHeight()));
            setMaximumSize(new Dimension(currentWidth + step, Integer.MAX_VALUE));
            revalidate();
            repaint();
            SwingUtilities.getWindowAncestor(this).revalidate();
        });
        timer.start();
    }
    public void collapse() {
        if (getWidth() <= COLLAPSED_WIDTH) return;

        Timer timer = new Timer(5, e -> {
            int currentWidth = getWidth();
            if (currentWidth <= COLLAPSED_WIDTH) {
                setPreferredSize(new Dimension(COLLAPSED_WIDTH, getHeight()));
                ((Timer) e.getSource()).stop();
                clearContent();
                revalidate();
                repaint();
                SwingUtilities.getWindowAncestor(this).revalidate();
                return;
            }
            int step = Math.max(-5, COLLAPSED_WIDTH - currentWidth); // шаг -5 пикселей
            setPreferredSize(new Dimension(currentWidth + step, getHeight()));
            setMaximumSize(new Dimension(currentWidth + step, Integer.MAX_VALUE));
            revalidate();
            repaint();
            SwingUtilities.getWindowAncestor(this).revalidate();
        });
        timer.start();
    }
        public boolean isExpanded() {
        return getWidth() >= EXPANDED_WIDTH;
        }


    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());

        int arc = 65; // Радиус закругления
        int width = getWidth();
        int height = getHeight();

        // Рисуем закругленный прямоугольник точно по размеру панели
        g2.fillRoundRect(0, 0, width, height, arc, arc);

        // Обрезаем правую часть, чтобы правый край стал прямым
        g2.setClip(new java.awt.geom.Rectangle2D.Double(0, 0, width - arc, height));
        g2.fillRoundRect(0, 0, width, height, arc, arc);

        g2.dispose();
        super.paintComponent(g);
    }
    private static final int EXPANDED_WIDTH = 200; // полная ширина панели
    private static final int COLLAPSED_WIDTH = 0; // ширина в скрытом состоянии
}





