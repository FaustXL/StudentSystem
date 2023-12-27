package org.example.UI;

import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;
import java.awt.*;

public class SMain {
    private JPanel SMain;
    private JTabbedPane tabbedPane1;
    private JPanel Myself;
    private JPanel Class;
    private JPanel Homework;
    private JPanel Setting;
    private JLabel Sname;
    private JLabel Ssex;
    private JLabel Smajor;
    private JLabel SclassAndGrade;
    private JLabel Saffiliation;
    private JTable ClassTable;
    private JTable AllClassTable;
    private JTable HomeWorkTable;
    private JLabel theme;
    private JComboBox themeComboBox;
    private JButton LogOut;
    private JLabel Sadress;
    private JLabel Stel;
    private JLabel SID;

    public SMain(){
        try {
            UIManager.setLookAndFeel( new FlatIntelliJLaf());
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        JFrame frame = new JFrame("SMain");
        frame.setPreferredSize(new Dimension(1000,800));
        frame.setContentPane(SMain);
        SwingUtilities.updateComponentTreeUI(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
