package org.example.UI;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import org.example.UI.Teacher.Class.CAdd;
import org.example.UI.Teacher.Major.MAdd;
import org.example.UI.Teacher.Student.SAddJFrame;
import org.example.UI.Teacher.Student.SInquire;
import org.example.dao.impl.studentDaoImpl;
import org.example.server.impl.lessonServerImpl;
import org.example.server.impl.studentServerImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class MainJFrame extends JFrame implements ActionListener{
    private static JFrame frame;
    private JTabbedPane tabbedPane;
    private JPanel panel1;
    private JPanel StudentManage;
    private JPanel ClassManage;
    private JTable StudentTable;
    private JButton AddButton;
    private JButton DelButton;
    private JButton ReviseButton;
    private JButton InquireButton;
    private JButton AllButton;
    private JButton CounterButton;
    private JTable ClassTable;
    private JButton ClassAddButton;
    private JButton ClassDelButton;
    private JButton ClassReviseButton;
    private JButton ClassInquireButton;
    private JButton ClassAllButton;
    private JButton ClassCounterButton;
    private JPanel setting;
    private JComboBox themeComboBox;
    private JLabel theme;
    private JPanel MajorManage;
    private JTable MajorTable;
    private JButton MajorAddButton;
    private JButton MajorReviseButton;
    private JButton MajorInquireButton;
    private JButton MajorAllButton;
    private JButton MajorCounterButton;
    private JButton MajorDelButton;
    private JButton LogOut;
    private JList list1;
    private JLabel ClassNum;
    private JLabel ClassTime;
    private JLabel ClassName;
    private JLabel ClassSorce;
    private JLabel ClassPeople;

    //存放学生数据二维数组
    private static String[][] tabledatas = null;

    //存放专业数据的二维数组
    private static String[][] tableDataOfProfessional_name = null;

    //学生表业务
    private static studentServerImpl studentServer = new studentServerImpl();

    //课程表业务
    private lessonServerImpl lessonServer = new lessonServerImpl();

    private studentDaoImpl studentDao = new studentDaoImpl();

    public MainJFrame(){

        //主界面
        init();
        //查询所有学生
        getAllStudent();

        //标题点击事件
        tabbedPane.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                int selectedIndex = tabbedPane.getSelectedIndex();
                String selectedTab = tabbedPane.getTitleAt(selectedIndex);
                System.out.println("Clicked: " + selectedTab);

                if (selectedTab.equals("学生管理")){
                    getAllStudent();

                }else if(selectedTab.equals("课程管理")){

                    //点击课程管理标题查询所有课程
                    String[] tableTitles = {"课程号","课程名称","学时","学分"};
                    try {
                        tabledatas = lessonServer.getsLessonAll(tabledatas);
                        TableModel data = new DefaultTableModel(tabledatas,tableTitles);
                        //文本居中
                        DefaultTableCellRenderer dc=new DefaultTableCellRenderer();
                        dc.setHorizontalAlignment(JLabel.CENTER);
                        ClassTable.setDefaultRenderer(Object.class, dc);
                        ClassTable.setModel(data);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    ClassTable.setRowHeight(25);

                }else if (selectedTab.equals("专业管理")){

                    String[] tableTitles = {"专业所属院校","专业名称"};
                    try {
                        tableDataOfProfessional_name =
                                studentServer.dealWithAffiliationAndProfessionalName(tableDataOfProfessional_name);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    TableModel data = new DefaultTableModel(tableDataOfProfessional_name,tableTitles);
                    //文本居中
                    DefaultTableCellRenderer dc=new DefaultTableCellRenderer();
                    dc.setHorizontalAlignment(JLabel.CENTER);
                    MajorTable.setDefaultRenderer(Object.class, dc);
                    MajorTable.setModel(data);

                }
            }
        });

        ClassTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = ClassTable.getSelectedRow();
                System.out.println(row);
            }
        });

        MajorTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = MajorTable.getSelectedRow();
                System.out.println(row);
            }
        });


        /*按钮监听*/
        //学生信息管理页面
        AddButton.addActionListener(this);
        DelButton.addActionListener(this);
        ReviseButton.addActionListener(this);
        InquireButton.addActionListener(this);
        AllButton.addActionListener(this);
        CounterButton.addActionListener(this);

        //课程管理页面
        ClassAddButton.addActionListener(this);
        ClassDelButton.addActionListener(this);
        ClassReviseButton.addActionListener(this);
        ClassInquireButton.addActionListener(this);
        ClassAllButton.addActionListener(this);
        ClassCounterButton.addActionListener(this);

        //专业管理页面
        MajorAddButton.addActionListener(this);
        MajorDelButton.addActionListener(this);
        MajorReviseButton.addActionListener(this);
        MajorInquireButton.addActionListener(this);
        MajorAllButton.addActionListener(this);
        MajorCounterButton.addActionListener(this);


        //设置页面
        themeComboBox.addActionListener(this);
        LogOut.addActionListener(this);
    }

    public void getAllStudent(){
        Object[] tableTitles = {"学号", "姓名", "性别", "年龄", "身份证号", "所属院系", "班级", "专业名"
                , "地址", "电话号码"};
        try {
            tabledatas = studentServer.getStudentAll(tabledatas);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        TableModel data = new DefaultTableModel(tabledatas,tableTitles);

        //文本居中
        DefaultTableCellRenderer dc=new DefaultTableCellRenderer();
        dc.setHorizontalAlignment(JLabel.CENTER);
        StudentTable.setDefaultRenderer(Object.class, dc);

        StudentTable.setModel(data);

        //设置行高
        StudentTable.setRowHeight(25);

        StudentTable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);// 以下设置表格列宽
        TableColumn column = StudentTable.getColumnModel().getColumn(0);
        column.setPreferredWidth(80);
        column.setMaxWidth(100);
        column = StudentTable.getColumnModel().getColumn(1);
        column.setPreferredWidth(10);
        column = StudentTable.getColumnModel().getColumn(2);
        column.setPreferredWidth(50);
        column.setMaxWidth(80);
        column = StudentTable.getColumnModel().getColumn(3);
        column.setPreferredWidth(50);
        column.setMaxWidth(80);
        column = StudentTable.getColumnModel().getColumn(6);
        column.setPreferredWidth(100);
        column.setMaxWidth(200);
        column = StudentTable.getColumnModel().getColumn(7);
        column.setPreferredWidth(100);
        column.setMaxWidth(200);
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

    /*
     *  作用：展示一个带有确定和取消按钮的弹框
     *
     *  方法的返回值：
     *       0 表示用户点击了确定
     *       1 表示用户点击了取消
     *       该弹框用于用户删除时候，询问用户是否确定删除
     * */
    public int showChooseJDialog() {
        return JOptionPane.showConfirmDialog(this, "是否删除选中数据？","删除信息确认",JOptionPane.YES_NO_OPTION);
    }

    public int showLoginOutJDialog() {
        return JOptionPane.showConfirmDialog(this, "是否退出登录？","退出登录确认",JOptionPane.YES_NO_OPTION);
    }

    public void init(){

        try {
            UIManager.setLookAndFeel( new FlatIntelliJLaf());
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }



        frame = new JFrame("主界面");
        frame.setPreferredSize(new Dimension(1500,800));
        frame.setContentPane(panel1);
        SwingUtilities.updateComponentTreeUI(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        //设置窗口居中
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        themeComboBox.addItem("IntelliJ");
        themeComboBox.addItem("Darcula");
        themeComboBox.addItem("Light");
        themeComboBox.addItem("Dark");

        getAllStudent();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object object = e.getSource();
        System.out.println(e.getSource());
        if (object == DelButton){

            //学生管理页面的删除按钮点击事件
            int i = StudentTable.getSelectedRow();
            for (int i1 = 0; i1 < tabledatas[i - 1].length; i1++) {
                System.out.println(tabledatas[i][i1]);
            }

            System.out.println(i);
            if (i<0){
                showJDialog("未选择");
            }else {
                this.setVisible(false);
                showChooseJDialog();
            }

        } else if (object == AddButton) {

            //学生管理页面的添加点击事件
            System.out.println(object);
            new SAddJFrame(this);

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

        } else if (object == LogOut) {

            this.setVisible(false);
            int n = showLoginOutJDialog();
            if (n == 0){
                frame.setVisible(false);
                new LoginJFrame();
            }

        } else if (object == ReviseButton) {

            //点击了学生管理-修改
            System.out.println(StudentTable.getSelectedRow());

        } else if (object == ClassAddButton) {

            new CAdd();

        } else if (object == ClassReviseButton) {

            //点击了课程管理-修改
            System.out.println(ClassTable.getSelectedRow());

        } else if (object == ClassDelButton) {

            //课程管理页面的删除按钮点击事件
            int i = ClassTable.getSelectedRow();
            System.out.println(i);
            if (i<0){
                showJDialog("未选择");
            }else {
                this.setVisible(false);
                showChooseJDialog();
            }

        } else if (object == MajorAddButton) {

            new MAdd();

        }else if (object == MajorReviseButton){

            //点击了专业管理-修改
            System.out.println(MajorTable.getSelectedRow());

        } else if (object == MajorDelButton) {

            //专业管理页面的删除按钮点击事件
            int i = MajorTable.getSelectedRow();
            System.out.println(i);
            if (i<0){
                showJDialog("未选择");
            }else {
                this.setVisible(false);
                showChooseJDialog();
            }

        } else if (object == AllButton) {

            StudentTable.selectAll();

        } else if (object == CounterButton) {

            StudentTable.clearSelection();

        } else if (object == ClassAllButton){

            ClassTable.selectAll();

        } else if (object == ClassCounterButton) {

            ClassTable.clearSelection();

        } else if (object == MajorAllButton) {

            MajorTable.selectAll();

        } else if (object == MajorCounterButton){

            MajorTable.clearSelection();

        } else if (object == InquireButton) {

            //点击了学生管理-查询按钮
            try {
                new SInquire();
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        }
    }
}
