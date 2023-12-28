package org.example.UI.Teacher.Student;

import org.example.UI.Outcome;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SInquire implements ActionListener {
    private JButton PushButton;
    private JPanel Main;
    private JTextField Name;
    private JComboBox AgeComboBox;
    private JComboBox SexComboBox;
    private JTextField IDCard;
    private JTextField Phone;
    private JComboBox AcademyComboBox;
    private JButton ResetButton;
    private JTextField ClassTextField;
    private JTextField Address;
    private JTextField MajorTextField;
    private JTextField ID;


    public SInquire(){
        JFrame frame = new JFrame("SInquire");
        frame.setPreferredSize(new Dimension(500,400));
        frame.setContentPane(Main);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        PushButton.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object object = e.getSource();
        if (object == PushButton){
            new Outcome();
        }
    }
}
