package ui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class CompareWindow extends JFrame {
    private JComboBox<String> leftCombo;
    private JComboBox<String> rightCombo;
    private JPanel resultPanel;
    private JPanel leftResultPanel;
    private JPanel rightResultPanel;
    public CompareWindow() {
        setTitle("Сравнение");
        setSize(900, 650);
        getContentPane().setBackground(new Color(28,28, 28));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        UIManager.put("Button.select", new Color(60, 60, 60)); // Твой приглушённый серый при клике

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(28, 28, 28));


        // Добавляем заглушку
        JLabel label = new JLabel("Сравнение", SwingConstants.CENTER);
        label.setForeground(new Color(184, 115, 51));
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        panel.add(label, BorderLayout.NORTH);
        add(panel);




        JPanel topSection = new JPanel();
        topSection.setLayout(new BoxLayout(topSection, BoxLayout.Y_AXIS));
        topSection.setOpaque(false);
        topSection.setPreferredSize(new Dimension(300, 95));
        panel.add(topSection, BorderLayout.NORTH);


        // Создаём кнопку
        JButton compareButton = new JButton("Сравнить");

        // Твой фирменный янтарный цвет
        Color amberColor = new Color(35, 35, 35);
        Color darkBg = new Color(184, 115, 51);
        Color pressedColor = new Color(60, 60, 60);

        // Настройка шрифта
        compareButton.setFont(new Font("Arial", Font.BOLD, 14));

        // Цвета
        compareButton.setForeground(darkBg); // Тёмный текст на светлом фоне — будет круто
        compareButton.setBackground(amberColor);

        // Убираем стандартные "бордюры"
        compareButton.setOpaque(true);
        compareButton.setBorderPainted(false);
        compareButton.setFocusPainted(false);


        // Добавляем внутренние отступы (твой любимый EmptyBorder)
        compareButton.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));

        // Курсор "рука"
        compareButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        compareButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if (model.isPressed()) {
                compareButton.setBackground(pressedColor); // Нажата — ставим серый
            } else if (model.isRollover()) {
                compareButton.setBackground(amberColor.brighter()); // Наведена — чуть ярче
            } else {
                compareButton.setBackground(amberColor); // Обычное состояние — янтарный
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        buttonPanel.add(compareButton);




        leftResultPanel = new JPanel();
        leftResultPanel.setLayout(new BoxLayout(leftResultPanel, BoxLayout.Y_AXIS));
        leftResultPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftResultPanel.setOpaque(false);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setPreferredSize(new Dimension(200, 300));
        leftPanel.setOpaque(false);

        String[] item = {"Выберите товар", "RTX 4060", "RTX 4090", "Core i5 12400f", "Core i7 14700KF"};

        leftCombo = new JComboBox<>(item);
        leftCombo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        leftCombo.setFont(new Font("Arial", Font.PLAIN, 14));
        leftCombo.setForeground(new Color(184, 115, 51));
        leftCombo.setBackground(new Color(28, 28, 28));
        leftCombo.setRenderer(new DarkComboBoxRenderer());
        leftCombo.setFocusable(false);
        leftCombo.setUI(new DarkComboBoxUI(leftCombo));
        leftCombo.setBorder(BorderFactory.createLineBorder(new Color(65, 65, 65),1 ));
        leftPanel.add(leftCombo);


        rightResultPanel = new JPanel();
        rightResultPanel.setLayout(new BoxLayout(rightResultPanel, BoxLayout.Y_AXIS));
        rightResultPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        rightResultPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        rightResultPanel.setOpaque(false);



        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setPreferredSize(new Dimension(200, 300));
        rightPanel.setOpaque(false);

        String[] item1 = {"Выберите товар", "RTX 3060", "RTX 3090", "Ryzen 7 7800X3D", "Ryzen 5 5600 OEM"};

        rightCombo = new JComboBox<>(item1);
        rightCombo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        rightCombo.setFont(new Font("Arial", Font.PLAIN, 14));
        rightCombo.setForeground(new Color(184, 115, 51));
        rightCombo.setBackground(new Color(28, 28, 28));
        rightCombo.setRenderer(new DarkComboBoxRenderer());
        rightCombo.setFocusable(false);
        rightCombo.setUI(new DarkComboBoxUI(rightCombo));
        rightCombo.setBorder(BorderFactory.createLineBorder(new Color(65, 65, 65), 1));
        rightPanel.add(rightCombo);


        JPanel comparisonPanel = new JPanel();
        comparisonPanel.setLayout(new GridLayout(1, 2, 10, 0));
        comparisonPanel.setOpaque(false);
        comparisonPanel.add(leftPanel);
        comparisonPanel.add(rightPanel);
        topSection.add(comparisonPanel);
        topSection.add(Box.createVerticalStrut(5));
        topSection.add(buttonPanel);






         resultPanel = new JPanel();
         resultPanel.setLayout(new GridLayout(1, 2, 20, 0));
        resultPanel.add(leftResultPanel);
        resultPanel.add(rightResultPanel);
         resultPanel.setOpaque(false);
         panel.add(resultPanel, BorderLayout.CENTER);





        compareButton.addActionListener(e -> {
            String leftProduct = (String) leftCombo.getSelectedItem();
            String rightProduct = (String) rightCombo.getSelectedItem();

            java.util.List<String> leftChars = getCharacteristicsFor(leftProduct);
            java.util.List<String> rightChars = getCharacteristicsFor(rightProduct);



            leftResultPanel.removeAll();
            rightResultPanel.removeAll();

            for (String ch : leftChars) {
                JLabel labe1 = new JLabel(ch);
                labe1.setForeground(new Color(184, 115, 51));
                labe1.setFont(new Font("Arial", Font.PLAIN, 14));
                labe1.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
                labe1.setAlignmentX(Component.LEFT_ALIGNMENT);
                leftResultPanel.add(labe1);
            }

            for (String ch : rightChars) {
                JLabel label2 = new JLabel(ch);
                label2.setForeground(new Color(184, 115, 51));
                label2.setFont(new Font("Arial", Font.PLAIN, 14));
                label2.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
                label2.setAlignmentX(Component.RIGHT_ALIGNMENT);
                rightResultPanel.add(label2);
            }
                leftResultPanel.repaint();
                leftResultPanel.revalidate();
                rightResultPanel.repaint();
                rightResultPanel.revalidate();

                resultPanel.revalidate();
                panel.revalidate();
                panel.repaint();

        });





        // Показываем окно
        setVisible(true);


        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });

    }
    private class DarkComboBoxRenderer extends JLabel implements ListCellRenderer<String> {
        public DarkComboBoxRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends String> list, String value,
                                                      int index, boolean isSelected, boolean cellHasFocus) {
            setText(value);
            if (isSelected) {
                setBackground(new Color(60, 60, 60));
            } else {
                setBackground(new Color(40, 40, 40));
            }
            setForeground(new Color(180, 175, 170));
            setFont(new Font("Arial", Font.PLAIN, 14));
            return this;
        }
    }


    // Вставь этот класс внутрь CompareWindow
    private class DarkComboBoxUI extends BasicComboBoxUI {
        private JComboBox<?> comboBox;

        public DarkComboBoxUI(JComboBox<?> cb) {
            this.comboBox = cb;
        }

        @Override
        public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
            // Ничего не рисуем, чтобы убрать голубой фон
        }

        @Override
        public void paintCurrentValue(Graphics g, Rectangle bounds, boolean hasFocus) {
            Object selected = comboBox.getSelectedItem();
            if (selected == null) return;
            String text = selected.toString();

            g.setColor(new Color(180, 175, 170));
            g.setFont(new Font("Arial", Font.PLAIN, 14));

            FontMetrics fm = g.getFontMetrics();
            int textY = bounds.y + (bounds.height - fm.getHeight()) / 2 + fm.getAscent();
            g.drawString(text, 5, textY);
        }
        @Override
        protected JButton createArrowButton() {
            JButton button = new JButton(" ▾ ");
            button.setFont(new Font("Arial", Font.BOLD, 12));
            button.setForeground(new Color(180, 175, 170));
            button.setBackground(new Color(40, 40, 40));
            button.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
            button.setOpaque(true);
            return button;
        }
    }

    private java.util.List<String> getCharacteristicsFor(String productName) {
        List<String> characteristis = new ArrayList<>();
        if (productName.equals("RTX 3090")) {
            characteristis.add("Частота: 1395 МГц");
            characteristis.add("Память: 24 ГБ GDDR6X");
            characteristis.add("Цена: 60 000 р");
        }
        if (productName.equals("RTX 3060")) {
            characteristis.add("Частота:1320 МГц");
            characteristis.add("Память: 12 ГБ GDDR6");
            characteristis.add("Цена: 35 000 р");
        }
        if (productName.equals("Ryzen 7 7800X3D")) {
            characteristis.add("Частота: 4,2 ГГц");
            characteristis.add("Ядра:8/16");
            characteristis.add("Цена: 33 499 р");
        }
        if (productName.equals("Ryzen 5 5600 OEM")) {
            characteristis.add("Частота: 3.5 ГГц");
            characteristis.add("Ядра: 6/12");
            characteristis.add("Цена: 10 999 р");
        }
        if (productName.equals("RTX 4060")) {
            characteristis.add("Частота: 1830 МГц");
            characteristis.add("Память: 8 ГБ GDDR6");
            characteristis.add("Цена: 45 000");
        }
        if (productName.equals("RTX 4090")) {
            characteristis.add("Частота: 2235 МГц");
            characteristis.add("Память: 24 ГБ GDDR6X");
            characteristis.add("Цена: 160 000 р");
        }
        if (productName.equals("Core I5 12400F")) {
            characteristis.add("Частота: 2,5 ГГц");
            characteristis.add("Ядра: 6/12");
            characteristis.add("Цена: 13 499 р");
        }
        if (productName.equals("Core I7 14700KF")) {
            characteristis.add("Частота: 3.4 ГГц");
            characteristis.add("Ядра: 20/28");
            characteristis.add("Цена: 33 999 р");
        }

        return characteristis;
    }
}

