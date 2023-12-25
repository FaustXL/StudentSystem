package org.example.UI;

import com.formdev.flatlaf.FlatDarculaLaf;
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

    public MainJFrame(int num){
        init();
    }

    public MainJFrame() {

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
                    String[][] tabledatas = {
                            {"编号1", "标题1", "正文1"},
                            {"编号2", "标题2", "正文2"},
                            {"编号3", "标题3", "正文3"},
                            {"编号4", "标题4", "正文4"},
                            {"编号5", "标题5", "正文5"},
                            {"编号6", "标题6", "正文6"},
                            {"编号7", "标题7", "正文7"},
                            {"编号8", "标题8", "正文8"},
                            {"编号9", "标题9", "正文9"},
                            {"编号10", "标题10", "正文10"},
                            {"编号11", "标题11", "正文11"},
                            {"编号12", "标题12", "正文12"},
                            {"编号13", "标题13", "正文13"},
                            {"编号14", "标题14", "正文14"},
                            {"编号15", "标题15", "正文15"},
                            {"编号16", "标题16", "正文16"},
                            {"编号17", "标题17", "正文17"},
                            {"编号18", "标题18", "正文18"},
                            {"编号19", "标题19", "正文19"},
                            {"编号20", "标题20", "正文20"},
                            {"编号21", "标题21", "正文21"},
                            {"编号22", "标题22", "正文22"},
                            {"编号23", "标题23", "正文23"},
                            {"编号24", "标题24", "正文24"},
                            {"编号25", "标题25", "正文25"},
                            {"编号26", "标题26", "正文26"},
                            {"编号27", "标题27", "正文27"},
                            {"编号28", "标题28", "正文28"},
                            {"编号29", "标题29", "正文29"},
                    };
                    TableModel data = new DefaultTableModel(tabledatas,tableTitles);
                    StudentTable.setModel(data);
                    StudentTable.setForeground(Color.white);
                    StudentTable.setGridColor(Color.yellow);
                    System.out.println("GET");
                }else if (selectedTab.equals("设置")) {
                    themeComboBox.addItem("Darcu");
                    themeComboBox.addItem("Light");
                }
            }
        });

        DelButton.addActionListener(e -> {
            int i = StudentTable.getSelectedRow();
            System.out.println(i);
            if (i<0){
                showJDialog("未选择");
            }else {
                this.setVisible(false);
                showChooseJDialog();
            }
        });

        DelButton.addActionListener(this);

        themeComboBox.addActionListener(e -> {
            String theme = (String) themeComboBox.getSelectedItem();
            assert theme != null;
            if (theme.equals("Darcu")){
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
            }
            SwingUtilities.updateComponentTreeUI(frame);
        });
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
        try {
            UIManager.setLookAndFeel( new FlatDarculaLaf());
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        frame = new JFrame("MainJFrame");
        frame.setPreferredSize(new Dimension(1000,800));
        frame.setContentPane(new MainJFrame().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        //设置窗口居中
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
