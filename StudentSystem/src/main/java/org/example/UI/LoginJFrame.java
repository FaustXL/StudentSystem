package org.example.UI;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.text.DefaultHighlighter;
import java.awt.*;

public class LoginJFrame {
    private JPanel Main;
    private JPasswordField passwordField1;
    private JButton button1;
    private JButton button2;
    private JTextField textField1;

    public LoginJFrame(){
        JFrame frame = new JFrame("LoginJFrame");
        frame.setPreferredSize(new Dimension(400,270));
        frame.setContentPane(Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        init();
        frame.setVisible(true);
    }

    public void init(){
        System.out.println("666");
        textField1.setBorder(new MatteBorder(0,0,1,0,Color.BLACK));
        passwordField1.setBorder(new MatteBorder(0,0,1,0,Color.BLACK));
        passwordField1.setOpaque(false);
    }
}
