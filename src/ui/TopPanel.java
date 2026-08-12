package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.RenderingHints;

public class TopPanel extends JPanel {

    private LeftPanel leftPanel;
    private CenterPanel centerPanel;

    public TopPanel(CenterPanel cp, LeftPanel lp) {
        this.centerPanel = cp;
        this.leftPanel = lp;
        // 1. Настраиваем панель
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS)); // Горизонтальный ряд кнопок
        setBackground(Theme.BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Небольшие отступы

        // 2. Добавляем кнопки-вкладки с помощью твоего метода createMenuItem
        // Кнопка "Каталог" с закруглёнными углами
        JPanel catalogButton = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                int arc = 25; // радиус закругления
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        catalogButton.setLayout(new BoxLayout(catalogButton, BoxLayout.X_AXIS));
        catalogButton.setOpaque(false); // обязательно для скруглений
        catalogButton.setBackground(Theme.SHADOW); // фон кнопки
        catalogButton.setBorder(BorderFactory.createEmptyBorder(6, 15, 6, 15)); // внутренние отступы

        // Текст кнопки
        JLabel catalogLabel = new JLabel("Каталог");
        catalogLabel.setFont(new Font("Arial", Font.BOLD, 14));
        catalogLabel.setForeground(Theme.TEXT_MAIN);
        catalogButton.add(catalogLabel);

        // Добавляем стрелку (необязательно)
        JLabel arrowLabel = new JLabel(" ▾ ");
        arrowLabel.setFont(new Font("Arial", Font.BOLD, 12));
        arrowLabel.setForeground(Theme.ACCENT);
        catalogButton.add(arrowLabel);

        // Слушатель клика (твой старый код)
        catalogButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (leftPanel.isExpanded()) {
                    leftPanel.collapse();
                } else {
                    leftPanel.expand();
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                catalogButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                catalogButton.setBackground(Theme.CARD_HOVER); // чуть светлее при наведении
            }
            @Override
            public void mouseExited(MouseEvent e) {
                catalogButton.setCursor(Cursor.getDefaultCursor());
                catalogButton.setBackground(Theme.MENU_HOVER); // возвращаем фон
            }
        });

            // Добавляем кнопку в TopPanel
        add(catalogButton);


            JPanel compareButton = new JPanel();
            compareButton.setLayout(new BoxLayout(compareButton, BoxLayout.X_AXIS));
            compareButton.setBackground(new Color(35, 35, 35));
            compareButton.setOpaque(false);
            compareButton.setBorder(BorderFactory.createEmptyBorder(6, 12, 6,  12));

            JLabel compare = new JLabel("Сравнение");
            compare.setFont(new Font("Arial", Font.PLAIN, 14));
            compare.setForeground(Theme.TEXT_MAIN);
            compareButton.add(compare);
            add(compareButton);


        compareButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new CompareWindow().setVisible(true);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                compareButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                compareButton.setCursor(Cursor.getDefaultCursor());
            }
        });








        JTextField searchField = new JTextField();
        searchField.setHorizontalAlignment(JTextField.LEFT);
        searchField.setPreferredSize(new Dimension(250, 30));
        searchField.setMaximumSize(new Dimension(200, 30));
        searchField.setForeground(Color.WHITE);
        searchField.setBackground(new Color(0, 0, 0, 0));
        searchField.setOpaque(false);
        searchField.setBorder(BorderFactory.createEmptyBorder(0, 35, 10, 10));
        String placeholder = "Поиск";
        searchField.setText(placeholder);
        searchField.setForeground(Color.WHITE);






        JPanel searchWrapper = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                int arc = 20;
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        searchWrapper.setBackground(Theme.SHADOW);
        searchWrapper.setOpaque(false);
        searchWrapper.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 10));
        searchWrapper.add(searchField, BorderLayout.CENTER);
        searchWrapper.setPreferredSize(new Dimension(195, 30));
        searchWrapper.setMaximumSize(new Dimension(195, 30));



        searchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals(placeholder))
                searchField.setText("");
                searchField.setForeground(Color.WHITE);
            }

                @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty())
                searchField.setForeground(Color.WHITE);
                searchField.setText(placeholder);
                }
        });





        add(Box.createHorizontalStrut(10)); // Отступ между кнопками
        JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
        separator.setForeground(new Color(100, 95, 90));
        separator.setMaximumSize(new Dimension(1, 25));
        separator.setPreferredSize(new Dimension(1, 25));

        add(separator);
        add(Box.createHorizontalGlue());
        add(searchWrapper);
        add(Box.createHorizontalGlue());


        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.X_AXIS));
        rightPanel.setOpaque(false);

        rightPanel.add(compareButton);
        rightPanel.add(Box.createHorizontalStrut(10));
        rightPanel.add(createMenuItem("Избранное"));
        rightPanel.add(Box.createHorizontalStrut(10));
        rightPanel.add(createMenuItem("Профиль"));
        add(rightPanel);






    }



    // 3. Твой проверенный метод для создания стилизованной панели под каждую вкладку
    private JPanel createMenuItem(String text) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));

        JLabel textLabel = new JLabel(text);
        textLabel.setFont(new Font("Arial", Font.BOLD, 13));
        textLabel.setForeground(Theme.TEXT_MAIN); // Твой тёплый серый цвет

        panel.add(textLabel);


        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (text.equals("Каталог")) {
                    leftPanel.showContent(new CatalogPanel(centerPanel));
                }
                if (text.equals("Избранное")) {
                        // Здесь открываем окно избранное
                        FavoriteWindow favorite = new FavoriteWindow();
                        favorite.setVisible(true);
                }
                if (text.equals("Сравнение")) {
                    CompareWindow compare = new CompareWindow();
                    compare.setVisible(true);
                }
                System.out.println("клик по:" + text);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                textLabel.setForeground(Theme.ACCENT);
                panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                textLabel.setForeground(Theme.TEXT_MAIN);
                panel.setCursor(Cursor.getDefaultCursor());
            }
        });




        return panel;
    }

}
