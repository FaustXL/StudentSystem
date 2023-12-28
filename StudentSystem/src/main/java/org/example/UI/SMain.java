package org.example.UI;

import com.formdev.flatlaf.FlatIntelliJLaf;
import org.example.domain.student;
import org.example.server.impl.studentServerImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;

public class SMain {
    private String id;

    private JPanel SMain;
    private JTabbedPane tabbedPane1;
    private JPanel Myself;
    private JPanel Class;
    private JPanel Homework;
    private JPanel Setting;
    private JLabel Sname;
    private JLabel Ssex;
    private JLabel Smajor;
    private JLabel SclassAndGrade;
    private JLabel Saffiliation;
    private JTable ClassTable;
    private JTable HomeWorkTable;
    private JLabel theme;
    private JComboBox themeComboBox;
    private JButton LogOut;
    private JLabel Saddress;
    private JLabel Stel;
    private JLabel SID;

    private studentServerImpl studentServer = new studentServerImpl();

    public SMain(String id){
        this.id = id;

        try {
            UIManager.setLookAndFeel( new FlatIntelliJLaf());
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }

        JFrame frame = new JFrame("SMain");
        frame.setPreferredSize(new Dimension(1000,800));
        frame.setContentPane(SMain);
        SwingUtilities.updateComponentTreeUI(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        try {
            getData(this.id);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        frame.setVisible(true);

        String[] tableTitles = {"星期一","星期二","星期三","星期四","星期五","星期六","星期日"};
        String[][] tabledatas = new String[10][7];
        TableModel data = new DefaultTableModel(tabledatas,tableTitles);
        DefaultTableCellRenderer dc=new DefaultTableCellRenderer();
        dc.setHorizontalAlignment(JLabel.CENTER);
        ClassTable.setDefaultRenderer(String.class, dc);
        ClassTable.setModel(data);
    }

    public SMain() {
    }

    //给元素赋值上学生的信息
    public void getData(String id) throws Exception {
        student studentById = studentServer.getStudentById(id);
        Sname.setText(studentById.getStudentName());
        Ssex.setText(studentById.getStudentGender());
        Smajor.setText(studentById.getProfessionalName());
        SclassAndGrade.setText(studentById.getClasses());
        Saffiliation.setText(studentById.getAffiliation());
        Saddress.setText(studentById.getStudentAddress());
        Stel.setText(studentById.getStudentTel());
        SID.setText(studentById.getStudentId());
    }
}
