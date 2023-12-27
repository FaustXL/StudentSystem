package org.example.UI.Teacher.Student;

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
    private JTextField ClassTextField;
    private JTextField MajorTextField;

    public SAddJFrame(){

        JFrame frame = new JFrame("SAddJFrame");
        frame.setPreferredSize(new Dimension(500,400));
        frame.setContentPane(SAdd);
        frame.pack();
        frame.setLocationRelativeTo(null);
        getItem();
        frame.setVisible(true);

    }

    public SAddJFrame(String id){

        JFrame frame = new JFrame(id);
        frame.setPreferredSize(new Dimension(500,400));
        frame.setContentPane(SAdd);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public void getItem(){
        //如果是修改，在获取数据之前添加完选项
        SexComboBox.addItem("男");
        SexComboBox.addItem("女");

        for (int i = 15; i < 50; i++) {
            AgeComboBox.addItem(i);
        }
    }
}
