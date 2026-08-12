package ui;

import javax.swing.*;
import java.awt. *;

public class CenterPanel extends JPanel {



    public CenterPanel() {
        setLayout(new BorderLayout());
        setBackground(Theme.BACKGROUND);
        setOpaque(true);






    }

    public void showContent(JPanel content) {
        setLayout(new BorderLayout());
        removeAll();
        add(content, BorderLayout.NORTH);
        revalidate();
        repaint();
    }

    public void clearContent() {
        removeAll();
        revalidate();
        repaint();
    }


}
