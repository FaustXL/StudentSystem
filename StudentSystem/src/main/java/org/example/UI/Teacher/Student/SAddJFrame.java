package org.example.UI.Teacher.Student;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;
import java.awt.*;

public class SAddJFrame {
    private JPanel SAdd;

    private JTextField Name;
    private JComboBox AgeComboBox;
    private JComboBox SexComboBox;
    private JTextField IDCard;
    private JComboBox AcademyComboBox;
    private JTextField Phone;
    private JTextField Address;
    private JButton ResetButton;
    private JButton PushButton;

    public SAddJFrame(){

        try {
            UIManager.setLookAndFeel( new FlatIntelliJLaf());
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }

        JFrame frame = new JFrame("SAddJFrame");
        frame.setPreferredSize(new Dimension(500,400));
        frame.setContentPane(SAdd);
        SwingUtilities.updateComponentTreeUI(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }
}
