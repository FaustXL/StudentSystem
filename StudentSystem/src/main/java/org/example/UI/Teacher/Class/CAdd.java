package org.example.UI.Teacher.Class;

import javax.swing.*;

public class CAdd {
    private JTextField ClassNum;
    private JTextField ClassSorce;
    private JButton PushButton;
    private JButton ResetButton;
    private JTextField ClassName;
    private JTextField ClassTime;
    private JPanel Main;
    public CAdd(){
        JFrame frame = new JFrame("CAdd");
        frame.setContentPane(Main);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public CAdd(String num){
        JFrame frame = new JFrame("CAdd");
        frame.setContentPane(Main);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
