package org.example.UI;

import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;

public class LoginJFrame extends JFrame implements ActionListener, FocusListener {

    private static JFrame frame;
    private JPanel Main;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JComboBox comboBox1;
    private JButton ActionButton;
    private JButton RegisteredButton;
    private JLabel text1;
    private JLabel text2;
    private JLabel Title;
    private JLabel text3;

    public LoginJFrame(){
        init();
    }

    public void init(){

        try {
            UIManager.setLookAndFeel( new FlatIntelliJLaf());
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }

        UIManager.put("Button.arc",15);
        UIManager.put("Button.arc",15);

        // 创建字体对象
        Font customFont = null;
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("font/江城黑体 900W.ttf"));
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 设置字体大小
        customFont = customFont.deriveFont(14f); // 14f 是字体大小
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("font/江城黑体 900W.ttf")));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        frame = new JFrame("Login");
        frame.setPreferredSize(new Dimension(800,530));
        frame.getContentPane().add(Main);
        SwingUtilities.updateComponentTreeUI(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        comboBox1.addItem("学生");
        comboBox1.addItem("教师");

        frame.setLayout(null);
        Main.setLayout(null);


        Font font = new Font("江城黑体 900W",Font.BOLD,18);

//        Title.setMinimumSize(new Dimension(300,80));
        Title.setFont(new Font("江城黑体 900W",Font.BOLD,48));

        text1.setBounds(210,115,100,130);
        text2.setBounds(210,190,100,130);
        text1.setFont(font);
        text2.setFont(font);
        Main.add(text1);
        Main.add(text2);

        text3.setFont(new Font("江城黑体 900W",Font.BOLD,18));
//        text3.setBounds(400,300,200,50);
//        text3.setMinimumSize(new Dimension(400,60));

        textField1.setOpaque(false);
        textField1.setBorder(new MatteBorder(0,0,1,0,Color.WHITE));
        textField1.setFont(font);
        textField1.setMinimumSize(new Dimension(-1,40));

        passwordField1.setOpaque(false);
        passwordField1.setBorder(new MatteBorder(0,0,1,0,Color.WHITE));
        passwordField1.setFont(font);
        passwordField1.setMinimumSize(new Dimension(-1,40));

        comboBox1.setSize(new Dimension(250,40));

        JLabel jLogin = new JLabel(new ImageIcon("image\\login\\loginbackground2.jpg"));
        jLogin.setBounds(2,1,800,500);
        Main.add(jLogin);

        ActionButton.setFont(font);
        ActionButton.setForeground(Color.WHITE);
        ActionButton.setSize(new Dimension(300,40));
        ActionButton.setBackground(new Color(64,158,255));

        RegisteredButton.setFont(font);
        RegisteredButton.setForeground(new Color(64,158,255));


        ActionButton.addActionListener(this);
        RegisteredButton.addActionListener(this);

        ActionButton.requestFocus();

        textField1.addFocusListener(this);
        passwordField1.addFocusListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object object = e.getSource();
        if (object == ActionButton){
            System.out.println("Summit");
        }else if (object == RegisteredButton){
            frame.setVisible(false);
            new registeredFrame();
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
