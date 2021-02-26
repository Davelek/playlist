package view;

import javax.swing.*;
import java.awt.*;

public class TopPanel {
    private JPanel mainPanel;

    // item panels
    private JPanel currentPanel;
    private JPanel nextPanel;

    // current
    private JLabel currTitle;
    private JLabel currArtist;
    private JLabel currSongLength;

    // next
    private JLabel nextTitle;
    private JLabel nextArtist;
    private JLabel nextSongLength;


    public TopPanel() {
        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(1200, 200));
        mainPanel.setSize(new Dimension(1200, 200));
        mainPanel.setLayout(new BorderLayout());

        // current
        currTitle = new JLabel("Title", SwingConstants.CENTER);
        currTitle.setFont(new Font("Calibri", Font.PLAIN, 80));

        currArtist = new JLabel("Artist", SwingConstants.CENTER);
        currArtist.setFont(new Font("Calibri", Font.PLAIN, 30));
        currArtist.setBorder(BorderFactory.createEmptyBorder(20, 0,0, 0)); // margin

        currSongLength = new JLabel("03:12", SwingConstants.CENTER);
        currSongLength.setFont(new Font("Calibri", Font.PLAIN, 20));
        currSongLength.setBorder(BorderFactory.createEmptyBorder(0, 0,20, 0));

        // next
        nextTitle = new JLabel("Title", SwingConstants.CENTER);
        nextTitle.setFont(new Font("Calibri", Font.PLAIN, 50));

        nextArtist = new JLabel("Artist", SwingConstants.CENTER);
        nextArtist.setFont(new Font("Calibri", Font.PLAIN, 20));
        nextArtist.setBorder(BorderFactory.createEmptyBorder(40, 0,0, 0));

        nextSongLength = new JLabel("03:12", SwingConstants.CENTER);
        nextSongLength.setFont(new Font("Calibri", Font.PLAIN, 15));
        nextSongLength.setBorder(BorderFactory.createEmptyBorder(0, 0,40, 0));


        this.setComponents();
        this.initComponents();
    }

    private void initComponents(){
        mainPanel.add(currentPanel, BorderLayout.CENTER);
        mainPanel.add(nextPanel, BorderLayout.EAST);
    }

    private void setComponents(){
        // current
        currentPanel = new JPanel();
        currentPanel.setLayout(new BorderLayout());
        currentPanel.add(currArtist, BorderLayout.NORTH);
        currentPanel.add(currTitle, BorderLayout.CENTER);
        currentPanel.add(currSongLength, BorderLayout.SOUTH);

        // next
        nextPanel = new JPanel();
        nextPanel.setPreferredSize(new Dimension((int)(mainPanel.getWidth() / 3.5), mainPanel.getHeight()));
        nextPanel.setLayout(new BorderLayout());
        nextPanel.add(nextArtist, BorderLayout.NORTH);
        nextPanel.add(nextTitle, BorderLayout.CENTER);
        nextPanel.add(nextSongLength, BorderLayout.SOUTH);
    }

    public void setCurrTitleValue(String value) {
        this.currTitle.setText(value);
    }

    public void setCurrArtistValue(String value) {
        this.currArtist.setText(value);
    }

    public void setCurrSongLengthValue(String value) {
        this.currSongLength.setText(value);
    }

    public void setNextTitleValue(String value) {
        this.nextTitle.setText(value);
    }

    public void setNextArtistValue(String value) {
        this.nextArtist.setText(value);
    }

    public void setNextSongLengthValue(String value) {
        this.nextSongLength.setText(value);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
