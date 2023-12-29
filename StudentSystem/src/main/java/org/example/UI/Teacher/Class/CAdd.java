package org.example.UI.Teacher.Class;

import org.example.UI.MainJFrame;
import org.example.domain.lesson;
import org.example.server.impl.lessonServerImpl;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CAdd {
    private JTextField ClassNum;
    private JTextField ClassSorce;
    private JButton PushButton;
    private JButton ResetButton;
    private JTextField ClassName;
    private JTextField ClassTime;
    private JPanel Main;

    private lessonServerImpl lessonServer = new lessonServerImpl();
    public CAdd(MainJFrame mainJFrame){
        JFrame frame = new JFrame("CAdd");
        frame.setContentPane(Main);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        PushButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                String classNum = ClassNum.getText();
                String classSorce = ClassSorce.getText();
                String className = ClassName.getText();
                String classTime = ClassTime.getText();
                if (classNum.equals("")||classSorce.equals("")||className.equals("")||classTime.equals("")){
                    showJDialog("填写的数据不能为空");
                }else {
                    lesson lesson = new lesson();
                    lesson.setLessonId(classNum);
                    lesson.setLessonName(className);
                    lesson.setStudyHours(Integer.parseInt(classTime));
                    lesson.setCredits(Integer.parseInt(classSorce));
                    try {
                        int i = lessonServer.insertLesson(lesson);
                        if (i > 0){
                            showJDialog("添加成功");
                            frame.setVisible(false);
                            mainJFrame.getLessonAll();
                        }else {
                            showJDialog("插入失败");
                        }
                    } catch (Exception exception) {
                        showJDialog("插入失败");
                        exception.printStackTrace();
                    }
                }
            }
        });
    }

    public CAdd(String num){
        JFrame frame = new JFrame("CAdd");
        frame.setContentPane(Main);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

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
}
