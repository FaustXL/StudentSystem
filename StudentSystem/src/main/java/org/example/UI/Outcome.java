package org.example.UI;

import org.example.server.impl.studentServerImpl;
import org.example.server.studentServer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Outcome implements ActionListener {
    private JPanel Main;
    private JButton ReviseButton;
    private JButton DelButton;
    private JTable OutcomeTable;
    private int CODE = 0;

    //存放学生数据
    private String[][] tabledatas = null;

    private studentServerImpl studentServer = new studentServerImpl();
    public Outcome(String[][] tabledatas){
        this.tabledatas = tabledatas;
        JFrame frame = new JFrame("Outcome");
        frame.setPreferredSize(new Dimension(1000,800));

        Image icon = Toolkit.getDefaultToolkit().getImage("image/Logo.png");
        frame.setIconImage(icon);

        frame.setContentPane(Main);

        frame.pack();
        frame.setLocationRelativeTo(null);
        getSelectConditionData();
        frame.setVisible(true);
        ReviseButton.addActionListener(this);
    }

    public void getSelectConditionData(){
        Object[] tableTitles = {"学号", "姓名", "性别", "年龄", "身份证号", "所属院系", "班级", "专业名"
                , "地址", "电话号码"};

        TableModel data = new DefaultTableModel(tabledatas,tableTitles);

        OutcomeTable.setModel(data);

        // 设置行高
        OutcomeTable.setRowHeight(25);

        OutcomeTable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS); // 以下设置表格列宽
        TableColumn column = OutcomeTable.getColumnModel().getColumn(0);
        column.setPreferredWidth(80);
        column.setMaxWidth(100);
        column = OutcomeTable.getColumnModel().getColumn(1);
        column.setPreferredWidth(10);
        column = OutcomeTable.getColumnModel().getColumn(2);
        column.setPreferredWidth(50);
        column.setMaxWidth(80);
        column = OutcomeTable.getColumnModel().getColumn(3);
        column.setPreferredWidth(50);
        column.setMaxWidth(80);
        column = OutcomeTable.getColumnModel().getColumn(6);
        column.setPreferredWidth(100);
        column.setMaxWidth(200);
        column = OutcomeTable.getColumnModel().getColumn(7);
        column.setPreferredWidth(100);
        column.setMaxWidth(200);

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (UIManager.getLookAndFeel().toString().equals("[Flat IntelliJ Look and Feel - com.formdev.flatlaf.FlatIntelliJLaf]")
                        ||UIManager.getLookAndFeel().toString().equals("[Flat IntelliJ Look and Feel - com.formdev.flatlaf.FlatLightLaf]")){
                    if (row % 2 == 1) {
                        setBackground(new Color(247,249,252));
//                    setForeground(Color.WHITE);
                    }else{
                        setBackground(Color.WHITE);
//                    setForeground(Color.WHITE);
                    }
                }else {
                    if (row % 2 == 1) {
                        setBackground(new Color(40, 40, 40));
                    }else {
                        setBackground(new Color( 60,  63,  65));
                    }
                }

                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };
        tcr.setHorizontalAlignment(JLabel.CENTER);
        int columnCount = OutcomeTable.getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            OutcomeTable.getColumn(OutcomeTable.getColumnName(i)).setCellRenderer(tcr);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object object = e.getSource();
        if (CODE == 0){
            //此时为学生管理的查询结果
            if (object == ReviseButton){
                //修改
                int i = OutcomeTable.getSelectedRow();
                if (i<0){
                    System.out.println("未选中");
                    showJDialog("未选择");
                }else {
                    System.out.println("调用修改");
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
        warning.setHorizontalAlignment(SwingConstants.CENTER);
        jDialog.getContentPane().add(warning);

        //让弹框展示出来
        jDialog.setVisible(true);
    }
}
