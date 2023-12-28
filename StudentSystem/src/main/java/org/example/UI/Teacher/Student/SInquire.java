package org.example.UI.Teacher.Student;

import org.example.UI.Outcome;
import org.example.dao.impl.studentDaoImpl;
import org.example.domain.student;
import org.example.server.impl.studentServerImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SInquire implements ActionListener {
    private JButton PushButton;
    private JPanel Main;
    private JTextField Name;
    private JComboBox AgeComboBox;
    private JComboBox SexComboBox;
    private JTextField IDCard;
    private JTextField Phone;
    private JComboBox AcademyComboBox;
    private JButton ResetButton;
    private JTextField ClassTextField;
    private JTextField Address;
    private JTextField MajorTextField;
    private JTextField ID;

    private studentServerImpl studentServer = new studentServerImpl();

    public SInquire() throws Exception {
        JFrame frame = new JFrame("SInquire");
        frame.setPreferredSize(new Dimension(500,400));
        frame.setContentPane(Main);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        getJComboBoxData();
        PushButton.addActionListener(this);
    }

    //设置下拉框数据
    public void getJComboBoxData() throws Exception {
        for (int i = 17; i < 30; i++) {
            AgeComboBox.addItem(i);
        }
        List<student> affiliation = studentServer.getAffiliation();
        for (student student : affiliation) {
            AcademyComboBox.addItem(student.getAffiliation());
        }
        SexComboBox.addItem("男");
        SexComboBox.addItem("女");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object object = e.getSource();
        if (object == PushButton){
            student s = new student();
//            s.setStudentId();
            s.setStudentName(Name.getText());
            s.setStudentGender((String) SexComboBox.getSelectedItem());
            s.setStudentAge((Integer) AgeComboBox.getSelectedItem());
            s.setIdCardNumber(IDCard.getText());
            s.setAffiliation((String) AcademyComboBox.getSelectedItem());
            s.setClasses(ClassTextField.getText());
            s.setProfessionalName(MajorTextField.getText());
            s.setStudentAddress(Address.getText());
            s.setStudentTel(Phone.getText());
            try {
                String[][] strings = studentServer.selectAllByCondition(s);
                new Outcome(strings);
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        }
    }
}
