package org.example.UI;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;

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
    private JPanel PerformanceManage;
    private JPanel CGradeManage;
    private JTable CGradeTable;
    private JTable PerformanceTable;
    private JButton MajorAddButton;
    private JButton MajorReviseButton;
    private JButton MajorInquireButton;
    private JButton MajorAllButton;
    private JButton MajorCounterButton;
    private JButton MajorDelButton;
    private JButton PAddButton;
    private JButton PReviseButton;
    private JButton PInquireButton;
    private JButton PAllButton;
    private JButton PCounterButton;
    private JButton PDelButton;
    private JButton CGAddbutton;
    private JButton CGReviseButton;
    private JButton CGInquireButton;
    private JButton CGAllButton;
    private JButton CGCounterButton;
    private JButton CGDelButton;
    private JButton LogOut;

    public MainJFrame() {
        /*try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        frame = new JFrame("MainJFrame");
        frame.setPreferredSize(new Dimension(1000,800));
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        //设置窗口居中
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);*/

        init();

        tabbedPane.addMouseListener(new MouseAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param e
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedIndex = tabbedPane.getSelectedIndex();
                String selectedTab = tabbedPane.getTitleAt(selectedIndex);
                System.out.println("Clicked: " + selectedTab);

                if (selectedTab.equals("学生管理")){
                    Object[] tableTitles = {"编号", "标题", "正文"};
                    String[][] tabledatas = {};
                    TableModel data = new DefaultTableModel(tabledatas,tableTitles);
                    StudentTable.setModel(data);
                }
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
        //设置页面
        themeComboBox.addActionListener(this);
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

    public void init(){

        frame = new JFrame("主界面");
        frame.setPreferredSize(new Dimension(1000,800));
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        //设置窗口居中
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        try {
            UIManager.setLookAndFeel( new FlatIntelliJLaf());
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        SwingUtilities.updateComponentTreeUI(frame);

        themeComboBox.addItem("IntelliJ");
        themeComboBox.addItem("Darcula");
        themeComboBox.addItem("Light");
        themeComboBox.addItem("Dark");
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object object = e.getSource();
        System.out.println(e.getSource());
        if (object == DelButton){
            //学生管理页面的删除按钮点击事件
            int i = StudentTable.getSelectedRow();
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
