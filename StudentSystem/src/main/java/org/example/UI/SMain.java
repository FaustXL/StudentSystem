package org.example.UI;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import org.example.domain.student;
import org.example.server.impl.studentServerImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SMain extends JFrame implements ActionListener {
    private static JFrame frame;
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

        frame = new JFrame("SMain");
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

        Object[] tableTitles = {"星期一","星期二","星期三","星期四","星期五","星期六","星期日"};
        String[][] tabledatas = null;
        TableModel data = new DefaultTableModel(tabledatas,tableTitles);
        DefaultTableCellRenderer dc=new DefaultTableCellRenderer();
        dc.setHorizontalAlignment(JLabel.CENTER);
        ClassTable.setDefaultRenderer(Object.class, dc);

        ClassTable.setModel(data);

        LogOut.addActionListener(this);

        themeComboBox.addItem("IntelliJ");
        themeComboBox.addItem("Darcula");
        themeComboBox.addItem("Light");
        themeComboBox.addItem("Dark");

        themeComboBox.addActionListener(this);
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

    public int showLoginOutJDialog() {
        return JOptionPane.showConfirmDialog(this, "是否退出登录？","退出登录确认",JOptionPane.YES_NO_OPTION);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object object = e.getSource();
        if (object == LogOut){
            this.setVisible(false);
            int n = showLoginOutJDialog();
            if (n == 0){
                frame.setVisible(false);
                new loginFrame();
            }
        } else if (object == themeComboBox) {

            //主题选择
            String theme = (String) themeComboBox.getSelectedItem();
            assert theme != null;
            if (theme.equals("Darcula")){
                try {
                    UIManager.setLookAndFeel( new FlatDarculaLaf());
                } catch( Exception ex ) {
                    System.err.println( "Failed to initialize LaF" );
                }
                frame.getContentPane().repaint();
            }else if (theme.equals("Light")){
                try {
                    UIManager.setLookAndFeel( new FlatLightLaf());
                } catch( Exception ex ) {
                    System.err.println( "Failed to initialize LaF" );
                }
            }else if (theme.equals("Dark")){
                try {
                    UIManager.setLookAndFeel( new FlatDarkLaf());
                } catch( Exception ex ) {
                    System.err.println( "Failed to initialize LaF" );
                }
            }else if (theme.equals("IntelliJ")){
                try {
                    UIManager.setLookAndFeel( new FlatIntelliJLaf());
                } catch( Exception ex ) {
                    System.err.println( "Failed to initialize LaF" );
                }
            }
            SwingUtilities.updateComponentTreeUI(frame);

        }
    }
}
