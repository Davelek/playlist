package view;

import javax.swing.*;
import java.awt.*;

public class BottomPanel {
    private JPanel mainPanel;
    private JButton btnUp;
    private JButton btnDown;
    private JLabel lbTotal;

    public BottomPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        btnUp = new JButton("Move song Up");
        btnDown = new JButton("Move song Down");
        lbTotal = new JLabel("text");

        this.initComponents();
    }

    public void setLbTotalValue(String value) {
        lbTotal.setText(value);
    }

    private void initComponents() {
        mainPanel.add(lbTotal, BorderLayout.NORTH);
        mainPanel.add(btnUp, BorderLayout.CENTER);
        mainPanel.add(btnDown, BorderLayout.SOUTH);
    }

    public JButton getBtnUp() {
        return btnUp;
    }

    public JButton getBtnDown() {
        return btnDown;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
