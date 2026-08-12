package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CatalogPanel extends JPanel {
        private CenterPanel centerPanel;
        private subCategoryPanel subCategoryPanel;
    public CatalogPanel(CenterPanel cp) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);
        this.centerPanel = cp;
        this.subCategoryPanel = new subCategoryPanel(cp);
        // Создаём пункты
        JPanel SMARTPHONE = createMenuItem("Смартфоны и Гаджеты");
        JPanel TV = createMenuItem("ТВ, консоли и аудио");
        JPanel PC = createMenuItem("ПК, ноутбуки и периферия");
        JPanel PCtools = createMenuItem("Комплектующие для ПК");


        // Создаём панель для списка
        JPanel subMenuPanel = new JPanel();
        subMenuPanel.setOpaque(false);
        subMenuPanel.setLayout(new BoxLayout(subMenuPanel, BoxLayout.Y_AXIS));
        subMenuPanel.add(SMARTPHONE);
        subMenuPanel.add(Box.createVerticalStrut(5));
        subMenuPanel.add(TV);
        subMenuPanel.add(Box.createVerticalStrut(5));
        subMenuPanel.add(PC);
        subMenuPanel.add(Box.createVerticalStrut(5));
        subMenuPanel.add(PCtools);


        add(subMenuPanel);




    }


    private JPanel createMenuItem(String text) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setOpaque(false);
        // Внутренние отступы
        panel.setBorder(BorderFactory.createEmptyBorder(2, 8, 2, 0));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        panel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 30));


        JLabel textLabel = new JLabel(text);
        textLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        textLabel.setForeground(Theme.TEXT_MAIN);

        panel.add(textLabel);

        // Пока просто выводим в консоль, что нажали
        panel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String category = text;

                if (category.equals("Смартфоны и Гаджеты") ||
                        category.equals("ТВ, консоли и аудио") ||
                        category.equals("ПК, ноутбуки и периферия") ||
                        category.equals("Комплектующие для ПК")) {

                    subCategoryPanel.showSubCategories(category);
                    centerPanel.showContent(subCategoryPanel);
                    centerPanel.revalidate();
                    centerPanel.repaint();
                } else {
                    // Показываем обычные товары (если категория без подкатегорий)
                    java.util.List<ProductCard> products = ProductDataFactory.getProductsFor(category);
                    JPanel productsPanel = new JPanel();
                    productsPanel.setLayout(new BoxLayout(productsPanel, BoxLayout.Y_AXIS));
                    productsPanel.setBackground(Theme.BACKGROUND);
                    for (ProductCard card : products) {
                        productsPanel.add(card);
                        productsPanel.add(Box.createVerticalStrut(5));
                    }
                    centerPanel.showContent(productsPanel);
                    centerPanel.revalidate();
                    centerPanel.repaint();
                }
            }



            @Override
            public void mouseEntered(MouseEvent e) {
                textLabel.setForeground(Theme.ACCENT);
                panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                textLabel.setForeground(Theme.TEXT_MAIN );
                panel.setCursor(Cursor.getDefaultCursor());
            }
        });

        return panel;
    }

    private java.util.List<ProductCard> getProductsFor(String category) {
        java.util.List<ProductCard> products = new java.util.ArrayList<>();
        if (category.equals("Смартфоны")) {
            products.add(new ProductCard("Iphone 16 Pro Max", "145 000 р"));
            products.add(new ProductCard("Xiomi 15 Pro", "75 000 р"));
            products.add(new ProductCard("Iphone 11", "30 000 р"));
            products.add(new ProductCard("Samsung S25 Ultra", "75 000 р"));
            products.add(new ProductCard("Xiaomi Redmi 15", "16 999 р"));
        }
        if (category.equals("Телевизоры")) {
            products.add(new ProductCard("SAMSUNG 55", "169 999"));
        }
        if (category.equals("Консоли")) {
            products.add(new ProductCard("XBOX Series S", "30 000 р"));
        }
        if (category.equals("Аудио системы")) {
            products.add(new ProductCard("Яндекс станция миди", "16 299 р"));
        }
        if (category.equals("ПК")) {
            products.add(new ProductCard("ARDOR Gaming Rage H461", "121 999 р"));
        }
        if (category.equals("Ноутбуки")) {
            products.add(new ProductCard("HUAWEI MateBook D 16", "52 299 р"));
            products.add(new ProductCard("HOROR MagikBook X16 AMD", "57 299 р"));
        }
        if (category.equals("Периферия")) {
            products.add(new ProductCard("Logitec 435", "5000 р"));
            products.add(new ProductCard("Red Square TKL", "6000 р"));
        }
        if (category.equals("Процессоры")) {
            products.add(new ProductCard("Ryzen 5 5600", "10 200 р"));
            products.add(new ProductCard("Ryzen 7 5700X", "15 799 р"));
        }
        if (category.equals("Видеокарты")) {
            products.add(new ProductCard("RTX 5060 Dual", "43 299 р"));
            products.add(new ProductCard("RTX 4090", "160 000 р"));
            products.add(new ProductCard("AMD Radeon RX 9060 XT Reaper", "48 299 р"));
        }
        return products;
    }


}



