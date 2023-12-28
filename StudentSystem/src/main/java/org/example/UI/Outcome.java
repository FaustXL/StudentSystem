package org.example.UI;

import javax.swing.*;
import java.awt.*;

public class Outcome {
    private JPanel Main;
    private JButton ReviseButton;
    private JButton DelButton;
    private JTable OutcomeTable;

    public Outcome(){
        JFrame frame = new JFrame("Outcome");
        frame.setPreferredSize(new Dimension(1000,800));
        frame.setContentPane(Main);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
