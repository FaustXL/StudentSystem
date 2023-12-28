package org.example.UI.Teacher.Student;

import com.formdev.flatlaf.FlatIntelliJLaf;
import org.example.UI.MainJFrame;
import org.example.domain.student;
import org.example.server.impl.studentLessonServeImpl;
import org.example.server.impl.studentServerImpl;
import org.example.utils.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class SAddJFrame{
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

    private studentServerImpl studentServer = new studentServerImpl();

    public SAddJFrame(MainJFrame mainJFrame){

        JFrame frame = new JFrame("SAddJFrame");
        frame.setPreferredSize(new Dimension(500,400));
        frame.setContentPane(SAdd);
        frame.pack();
        frame.setLocationRelativeTo(null);
        getItem();
        try {
            getAffiliation();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        frame.setVisible(true);
        PushButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                student s = new student();
                s.setStudentTel(Phone.getText());
                s.setIdCardNumber(IDCard.getText());
                s.setStudentAddress(Address.getText());
                s.setClasses(ClassTextField.getText());
                s.setProfessionalName(MajorTextField.getText());
                s.setStudentName(Name.getText());
                if (AgeComboBox.getSelectedItem() != null){
                s.setStudentAge((int)AgeComboBox.getSelectedItem());
                }
                s.setStudentGender(String.valueOf(SexComboBox.getSelectedItem()));
                s.setAffiliation(String.valueOf(AcademyComboBox.getSelectedItem()));

                int i = 0;
                try {
                    i = studentServer.insertStudent(s);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                System.out.println(s);
                if (i== -2){
                    showJDialog("填写的数据不能为空");
                } else if (i == -1){
                    showJDialog("身份证或电话号码格式错误");
                }else if (i == -3){
                    showJDialog("班级填写错误");
                }else if (i == 0){
                    showJDialog("插入失败");
                }else {
                    showJDialog("插入成功");
                    frame.setVisible(false);
                    mainJFrame.getAllStudent();
                }
            }
        });
    }

    public SAddJFrame(String id){

        JFrame frame = new JFrame(id);
        frame.setPreferredSize(new Dimension(500,400));
        frame.setContentPane(SAdd);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public void getAffiliation() throws Exception {
        List<student> affiliation = studentServer.getAffiliation();
        for (student student : affiliation) {
            AcademyComboBox.addItem(student.getAffiliation());
        }
    }

    public void getItem(){
        //如果是修改，在获取数据之前添加完选项
        SexComboBox.addItem("男");
        SexComboBox.addItem("女");

        for (int i = 15; i < 50; i++) {
            AgeComboBox.addItem(i);
        }
    }

    JDialog jDialog = new JDialog();
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
