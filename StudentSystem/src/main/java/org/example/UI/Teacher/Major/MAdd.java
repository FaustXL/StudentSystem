package org.example.UI.Teacher.Major;

import javax.swing.*;

public class MAdd {
    private JTextField MajorName;
    private JButton PushButton;
    private JButton ResetButton;
    private JTextField SchoolName;
    private JPanel Main;

    public MAdd(){
        JFrame frame = new JFrame("MAdd");
        frame.setContentPane(Main);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public MAdd(String num){
        JFrame frame = new JFrame("MAdd");
        frame.setContentPane(Main);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
