package org.example.UI;

import org.example.server.impl.studentServerImpl;
import org.example.server.studentServer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;

public class Outcome {
    private JPanel Main;
    private JButton ReviseButton;
    private JButton DelButton;
    private JTable OutcomeTable;

    //存放学生数据
    private String[][] tabledatas = null;

    private studentServerImpl studentServer = new studentServerImpl();
    public Outcome(String[][] tabledatas){
        this.tabledatas = tabledatas;
        JFrame frame = new JFrame("Outcome");
        frame.setPreferredSize(new Dimension(1000,800));
        frame.setContentPane(Main);

        frame.pack();
        frame.setLocationRelativeTo(null);
        getSelectConditionData();
        frame.setVisible(true);
    }

    public void getSelectConditionData(){
        Object[] tableTitles = {"学号", "姓名", "性别", "年龄", "身份证号", "所属院系", "班级", "专业名"
                , "地址", "电话号码"};

        TableModel data = new DefaultTableModel(tabledatas,tableTitles);

        // 文本居中
        DefaultTableCellRenderer dc = new DefaultTableCellRenderer();
        dc.setHorizontalAlignment(JLabel.CENTER);
        OutcomeTable.setDefaultRenderer(Object.class, dc);

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
    }
}
