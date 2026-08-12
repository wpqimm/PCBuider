package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;


public class subCategoryPanel extends JPanel {
    private CenterPanel centerPanel;


    public subCategoryPanel(CenterPanel centerPanel) {
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        setBackground(Theme.BACKGROUND);
        this.centerPanel = centerPanel;
    }

    // Метод, который создает одну карточку по названию
    private JPanel createCategoryCard(String title) {
        RoundedPanel card = new RoundedPanel(25);

        card.setPreferredSize(new Dimension(150, 100));
        card.setBackground(Theme.CARD_BG);
        card.setLayout(new BorderLayout());

        JLabel label = new JLabel(title, SwingConstants.CENTER);
        label.setForeground(Theme.TEXT_MAIN);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        card.add(label, BorderLayout.CENTER);



        // Добавляем слушатель мыши, чтобы по клику показывать товары
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                List<ProductCard> products = ProductDataFactory.getProductsFor(title);
                JPanel productsPanel = new JPanel();
                productsPanel.setLayout(new BoxLayout(productsPanel, BoxLayout.Y_AXIS));
                productsPanel.setBackground(new Color(28, 28, 28));
                for (ProductCard card : products) {
                    productsPanel.add(card);
                    productsPanel.add(Box.createVerticalStrut(5));
                }
                centerPanel.showContent(productsPanel);
                centerPanel.revalidate();
                centerPanel.repaint();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(Cursor.getDefaultCursor());
            }
        });

        return card;
    }

    // Метод, который наполняет панель карточками для указанной категории
    public void showSubCategories(String category) {
        this.removeAll(); // очищаем старые карточки
        if (category.equals("Смартфоны и Гаджеты")) {
            this.add(createCategoryCard("Смартфоны"));
            this.add(createCategoryCard("Аксессуары"));
            this.add(createCategoryCard("Смарт-Часы"));
        }

        if (category.equals("ТВ, консоли и аудио")) {
            this.add(createCategoryCard("Телевизоры"));
            this.add(createCategoryCard("Консоли"));
            this.add(createCategoryCard("Аудио системы"));
        }

        if (category.equals("ПК, ноутбуки и периферия")) {
            this.add(createCategoryCard("ПК"));
            this.add(createCategoryCard("Ноутбуки"));
            this.add(createCategoryCard("Периферия"));
        }

        if (category.equals("Комплектующие для ПК")) {
            this.add(createCategoryCard("Видеокарты"));
            this.add(createCategoryCard("Процессоры"));
            this.add(createCategoryCard("Оперативная память"));

        }

        this.revalidate();
        this.repaint();
    }
}
