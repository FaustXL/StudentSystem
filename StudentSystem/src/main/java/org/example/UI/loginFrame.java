package org.example.UI;

import com.formdev.flatlaf.FlatIntelliJLaf;
import lombok.SneakyThrows;
import org.example.domain.administrator;
import org.example.domain.studentUser;
import org.example.server.impl.administratorServerImpl;
import org.example.server.impl.studentUserServerImpl;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loginFrame extends JFrame implements ActionListener {


    private final Container CONTAINER = this.getContentPane();

    private String usernameStr = "学生账号";

    private JPasswordField passwordText = null;
    private JTextField usernameText = null;

    public loginFrame(){
        createInterface();
        setInterface();
        setMenu();
        this.setVisible(true);
    }

    public void createInterface(){
        /*try {
            UIManager.setLookAndFeel( new FlatIntelliJLaf());
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }*/

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
//        JLabel admin = new JLabel(new ImageIcon("image\\login\\admin2.png"));
        admin.setBounds(300,30,160,160);
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
        usernameText = new JTextField();
        usernameText.setBounds(280,245,280,30);
        usernameText.setFont(new Font("微软雅黑",Font.PLAIN,15));
        usernameText.setOpaque(false);
        usernameText.setBorder(new MatteBorder(0,0,1,0,Color.WHITE));
        CONTAINER.add(usernameText);

        //密码文字
        JLabel password = new JLabel("密码");
        password.setBounds(220,280,100,100);
        password.setForeground(Color.WHITE);
        password.setFont(font);
        CONTAINER.add(password);

        //密码文本框
        passwordText = new JPasswordField();
        passwordText.setBounds(280,315,280,30);
        passwordText.setFont(new Font("微软雅黑",Font.BOLD,20));
        passwordText.setEchoChar('·');
        passwordText.setOpaque(false);
        passwordText.setBorder(new MatteBorder(0,0,1,0,Color.WHITE));
        CONTAINER.add(passwordText);

        //登录注册按钮字体
        Font loginFont = new Font("宋体",Font.PLAIN,15);

        //登录按钮
        JButton loginButton = new JButton("登录");
        loginButton.setBounds(235,400,150,40);
        loginButton.setFont(loginFont);
        loginButton.setBorderPainted(false);
        loginButton.setBackground(new Color(64,158,255));
        loginButton.setForeground(Color.white);
        loginButton.setFont(new Font("黑体",Font.BOLD,15));
        CONTAINER.add(loginButton);

        loginButton.addActionListener(this);

        //注册按钮
        JButton registeredButton = new JButton("注册");
        registeredButton.setBounds(410,400,150,40);
        registeredButton.setFont(loginFont);
        registeredButton.setBorderPainted(false);
        registeredButton.setBackground(Color.white);
        registeredButton.setForeground(Color.BLACK);
        registeredButton.setFont(new Font("黑体",Font.BOLD,15));
        CONTAINER.add(registeredButton);

        //插入登录主界面
        JLabel jLogin = new JLabel(new ImageIcon("image\\login\\loginbackground2.jpg"));
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

    @SneakyThrows
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
        }else if (e.getActionCommand().equals("登录")){
            String username = usernameText.getText();
            String password = passwordText.getText();
            if (usernameStr.equals("学生账号")){
                if (username.equals("") || password.equals("")){
                    showJDialog("填写的密码或者账号不能为空");
                }else{
                    studentUser studentUser = new studentUser();
                    studentUserServerImpl studentUserServer = new studentUserServerImpl();
                    studentUser.setStudentId(username);
                    studentUser.setPassword(password);
                    boolean loginSucceed = studentUserServer.isLoginSucceed(studentUser);
                    if (loginSucceed){
                        this.setVisible(false);
                        new SMain();
                    }else {
                        showJDialog("账号或密码不正确");
                    }
                }
            }else if (usernameStr.equals("教师账号")){
                administratorServerImpl administratorServer = new administratorServerImpl();

                if (username.equals("") || password.equals("")){
                    showJDialog("填写的密码或者账号不能为空");
                }else {
                    administrator administrator = new administrator();
                    administrator.setAdministratorUsername(username);
                    administrator.setAdministratorPassword(password);
                    boolean b = administratorServer.adminLogin(administrator);
                    if (b){
                        this.setVisible(false);
                        new MainJFrame();
                    }else {
                        showJDialog("账号或密码不正确");
                    }
                }

            }
        }
    }

    public void showJDialog(String content) {
        //创建一个弹框对象
        JDialog jDialog = new JDialog();
        //给弹框设置大小
        jDialog.setSize(200, 150);
        //让弹框置顶
        jDialog.setAlwaysOnTop(true);
        //让弹框居中
        jDialog.setLocationRelativeTo(null);
        //弹框不关闭永远无法操作下面的界面
        jDialog.setModal(true);

        //创建Jlabel对象管理文字并添加到弹框当中
        JLabel warning = new JLabel(content);
        warning.setBounds(0, 0, 200, 150);
        jDialog.getContentPane().add(warning);

        //让弹框展示出来
        jDialog.setVisible(true);
    }
}
