package org.example.UI;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import org.example.domain.student;
import org.example.domain.studentLesson;
import org.example.server.impl.studentLessonServeImpl;
import org.example.server.impl.studentServerImpl;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class SMain extends JFrame implements ActionListener {
    private static JFrame frame;
    private String id;

    private JPanel SMain;
    private JTabbedPane tabbedPane1;
    private JPanel Myself;
    private JPanel Class;
    private JPanel Setting;
    private JLabel Sname;
    private JLabel Ssex;
    private JLabel Smajor;
    private JLabel SclassAndGrade;
    private JLabel Saffiliation;
    private JTable ClassTable;
    private JLabel theme;
    private JComboBox themeComboBox;
    private JButton LogOut;
    private JLabel Saddress;
    private JLabel Stel;
    private JLabel SID;

    private studentServerImpl studentServer = new studentServerImpl();
    private studentLessonServeImpl studentLessonServe = new studentLessonServeImpl();

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

        addNaviAction();

    }

    public SMain() {
    }

    //添加导航栏点击事件
    public void addNaviAction(){
        tabbedPane1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedIndex = tabbedPane1.getSelectedIndex();
                String selectedTab = tabbedPane1.getTitleAt(selectedIndex);
                if (selectedTab.equals("课程管理")){
                    try {
                        getLessonData();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }else if (selectedTab.equals("作业")){
                    System.out.println("作业");
                }
            }
        });
    }

    //获取课程表信息
    public void getLessonData() throws Exception {
        String[] tableTitles = {"","星期一","星期二","星期三","星期四","星期五","星期六","星期日"};
        String[][] tabledatas = null;

        List<studentLesson> studentLesson = studentLessonServe.getStudentLesson(id);
        System.out.println(studentLesson);
        tabledatas = studentLessonServe.createLessonTable(studentLesson, tabledatas);

        TableModel data = new DefaultTableModel(tabledatas,tableTitles);

        DefaultTableCellRenderer cellRenderer=new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        ClassTable.setDefaultRenderer(Object.class, cellRenderer);

        ClassTable.setModel(data);
        ClassTable.setRowHeight(70);

        ClassTable.getTableHeader().setPreferredSize(new Dimension(-1,50));
        ClassTable.getTableHeader().setBackground(new Color(239, 247, 253));

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (row % 2 == 1) {
                    setBackground(new Color(247,249,252));
//                    setForeground(Color.WHITE);
                }else{
                    setBackground(Color.WHITE);
//                    setForeground(Color.WHITE);
                }

                return super.getTableCellRendererComponent(table, value,
                        isSelected, hasFocus, row, column);
            }
        };
        int columnCount = ClassTable.getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            ClassTable.getColumn(ClassTable.getColumnName(i)).setCellRenderer(tcr);
        }
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
                new LoginJFrame();
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
