package org.example.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loginFrame extends JFrame implements ActionListener {


    private final Container CONTAINER = this.getContentPane();

    private String usernameStr = "学生账号";

    public loginFrame(){
        createInterface();
        setInterface();
        setMenu();
        this.setVisible(true);
    }

    public void createInterface(){
        this.setSize(800,550);
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置界面关闭后代码结束运行功能
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
    }

    //设置界面
    public void setInterface(){
        CONTAINER.removeAll();

        //admin图片
        JLabel admin = new JLabel(new ImageIcon("image\\login\\admin.png"));
        admin.setBounds(300,30,180,180);
        CONTAINER.add(admin);

        //设置字体
        Font font = new Font("微软雅黑",Font.BOLD,15);

        //账号文字
        JLabel username = new JLabel(usernameStr);
        username.setBounds(200,210,100,100);
        username.setFont(font);
        username.setForeground(Color.WHITE);
        CONTAINER.add(username);

        //账号文本框
        JTextField usernameText = new JTextField();
        usernameText.setBounds(280,245,280,30);
        usernameText.setFont(new Font("微软雅黑",Font.PLAIN,15));
        CONTAINER.add(usernameText);

        //密码文字
        JLabel password = new JLabel("密码");
        password.setBounds(220,280,100,100);
        password.setForeground(Color.WHITE);
        password.setFont(font);
        CONTAINER.add(password);

        //密码文本框
        JPasswordField passwordText = new JPasswordField();
        passwordText.setBounds(280,315,280,30);
        passwordText.setFont(new Font("微软雅黑",Font.BOLD,20));
        passwordText.setEchoChar('·');
        CONTAINER.add(passwordText);

        //登录注册按钮字体
        Font loginFont = new Font("宋体",Font.PLAIN,15);

        //登录按钮
        JButton loginButton = new JButton("登录");
        loginButton.setBounds(235,400,150,40);
        loginButton.setFont(loginFont);
        CONTAINER.add(loginButton);

        //注册按钮
        JButton registeredButton = new JButton("注册");
        registeredButton.setBounds(410,400,150,40);
        registeredButton.setFont(loginFont);
        CONTAINER.add(registeredButton);

        //插入登录主界面
        JLabel jLogin = new JLabel(new ImageIcon("image\\login\\loginbackground.jpg"));
        jLogin.setBounds(2,1,800,500);
        this.getContentPane().add(jLogin);



        CONTAINER.repaint();
    }

    //设置菜单
    public void setMenu(){
        JMenuBar jMenuBar = new JMenuBar();
        JMenu change = new JMenu("点击切换");
        JMenuItem student = new JMenuItem("学生登录");
        JMenuItem teacher = new JMenuItem("教师登录");
        change.add(student);
        change.add(teacher);
        jMenuBar.add(change);

        student.addActionListener(this);
        teacher.addActionListener(this);

        //显示菜单
        this.setJMenuBar(jMenuBar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //当点击学生登陆菜单时执行以下操作
        if (e.getActionCommand().equals("学生登录")){
            if (usernameStr.equals("教师账号")){
                usernameStr = "学生账号";
                setInterface();
            }
        }else if (e.getActionCommand().equals("教师登录")){
            if (usernameStr.equals("学生账号")){
                usernameStr = "教师账号";
                setInterface();
            }
        }
    }
}
