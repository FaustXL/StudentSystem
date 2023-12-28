package org.example.UI;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class registeredFrame extends JFrame implements ActionListener , FocusListener {
    private static JFrame frame;
    private JPanel Main;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton ActionButton;
    private JLabel text1;
    private JLabel text2;
    private JComboBox comboBox1;
    private JButton reTurnButton;

    public registeredFrame(){
        init();
    }

    public void init(){

        frame = new JFrame("registeredFrame");
        frame.setPreferredSize(new Dimension(800,530));
//        frame.setContentPane(Main);
        frame.getContentPane().add(Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        comboBox1.addItem("学生");
        comboBox1.addItem("教师");

        frame.setLayout(null);
        Main.setLayout(null);

        text1.setBounds(210,115,100,130);
        text2.setBounds(210,190,100,130);
        Main.add(text1);
        Main.add(text2);


        textField1.setOpaque(false);
        textField1.setBorder(new MatteBorder(0,0,1,0,Color.WHITE));

        passwordField1.setOpaque(false);
        passwordField1.setBorder(new MatteBorder(0,0,1,0,Color.WHITE));

        JLabel jLogin = new JLabel(new ImageIcon("image\\login\\loginbackground2.jpg"));
        jLogin.setBounds(2,1,800,500);
//        frame.getContentPane().add(jLogin);
//        frame.repaint();
        Main.add(jLogin);

        ActionButton.addActionListener(this);
        reTurnButton.addActionListener(this);

        ActionButton.requestFocus();

        textField1.addFocusListener(this);
        passwordField1.addFocusListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object object = e.getSource();
        if (object == ActionButton){
            System.out.println("Summit");
        }else if (object == reTurnButton){
            frame.setVisible(false);
            new loginFrame();
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        Object object = e.getSource();
        if (object == textField1){
            text1.setText("");
        }else if (object == passwordField1){
            text2.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        Object object = e.getSource();
        if (object == textField1){
            if (textField1.getText().isEmpty()){
                text1.setText("账号");
            }
        }else if (object == passwordField1){
            if (String.valueOf(passwordField1.getPassword()).isEmpty()){
                text2.setText("密码");
            }
        }
    }
}