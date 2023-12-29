package org.example.UI;

import org.example.dao.impl.administratorDaoImpl;
import org.example.dao.impl.studentDaoImpl;
import org.example.dao.impl.studentUserDaoImpl;
import org.example.domain.administrator;
import org.example.domain.student;
import org.example.domain.studentUser;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.regex.Pattern;

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


    private studentUserDaoImpl studentUserDao = new studentUserDaoImpl();
    private studentDaoImpl studentDao = new studentDaoImpl();
    private administratorDaoImpl administratorDao = new administratorDaoImpl();

    public registeredFrame(){
        init();
    }

    public void init(){

        frame = new JFrame("registeredFrame");
        frame.setPreferredSize(new Dimension(800,530));
//        frame.setContentPane(Main);
        Image icon = Toolkit.getDefaultToolkit().getImage("image/Logo.png");
        frame.setIconImage(icon);
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
            String username = textField1.getText();
            String password = passwordField1.getText();
            if ("学生".equals(comboBox1.getSelectedItem())){
                try {
                    student student = studentDao.selectStudentById(username);
                    studentUser studentUser = studentUserDao.selectByUsername(username);
                    if (studentUser.getId() != 0 && studentUser.getStudentId() != null){
                        showJDialog("注册的账户已存在");
                    }
                    else if (student.getStudentId() == null && student.getStudentName() == null){
                        showJDialog("要注册的学生不存在");
                    }else {
                        boolean valid = Pattern.matches("^.{8,16}$", password);
                        if (valid) {
                            studentUser user = new studentUser();
                            user.setStudentId(username);
                            user.setPassword(password);
                            int i = studentUserDao.insertStudentUser(user);
                            if (i > 0) {
                                showJDialog("注册成功");
                                frame.setVisible(false);
                                new loginFrame();
                            } else {
                                showJDialog("注册失败");
                            }
                        } else {
                            showJDialog("密码格式填写有误：格式为8-16位");
                        }
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }else {
                try {
                    administrator administrator = administratorDao.selectAdministratorByUsername(username);
                    if (administrator.getAdministratorId() != null){
                        showJDialog("要注册的用户已存在");
                    }else {
                        boolean valid = Pattern.matches("^.{8,16}$", password);
                        if (valid){
                            administrator admin = new administrator();
                            admin.setAdministratorUsername(username);
                            admin.setAdministratorPassword(password);
                            int i = administratorDao.insertAdmin(admin);
                            if (i > 0) {
                                showJDialog("注册成功");
                                frame.setVisible(false);
                                new loginFrame();
                            } else {
                                showJDialog("注册失败");
                            }
                        }else {
                            showJDialog("密码格式填写有误：格式为8-16位");
                        }
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }else if (object == reTurnButton){
            frame.setVisible(false);
            new LoginJFrame();
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
    //只创建一个弹框对象
    JDialog jDialog = new JDialog();


    //因为展示弹框的代码，会被运行多次
    //所以，我们把展示弹框的代码，抽取到一个方法中。以后用到的时候，就不需要写了
    //直接调用就可以了。
    //展示弹框
    public void showJDialog(String content) {
        if(!jDialog.isVisible()){
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
            warning.setHorizontalAlignment(SwingConstants.CENTER);
            jDialog.getContentPane().add(warning);

            //让弹框展示出来
            jDialog.setVisible(true);
        }
    }
}
