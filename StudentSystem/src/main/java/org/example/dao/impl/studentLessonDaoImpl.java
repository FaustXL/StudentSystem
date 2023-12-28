package org.example.dao.impl;

import org.example.dao.studentLessonDao;
import org.example.domain.student;
import org.example.domain.studentLesson;
import org.example.utils.jdbcConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class studentLessonDaoImpl implements studentLessonDao {
    @Override
    public List<studentLesson> getStudentLesson(String id) throws Exception {
        Connection connection = jdbcConfig.getConnection();
        PreparedStatement prep = connection.prepareStatement("select * from student where student_id = ?");
        prep.setString(1,id);
        ResultSet resultSet = prep.executeQuery();
        //获取到这个学生的班级
        String classes = "";
        while (resultSet.next()) {
            classes = resultSet.getString("class");
        }

        List<studentLesson> slList = new ArrayList<>();
        PreparedStatement prep1 = connection.prepareStatement
                ("SELECT * FROM student_lesson WHERE `class` = ?");
        prep1.setString(1,classes);
        ResultSet resultSet1 = prep1.executeQuery();
        while (resultSet1.next()) {
            studentLesson sl = new studentLesson();
            sl.setStudentLessonId(resultSet1.getInt("student_lesson_id"));
            sl.setLessonId(resultSet1.getString("lesson_id"));
            sl.setOrdered(resultSet1.getFloat("order"));
            slList.add(sl);
        }
        return slList;
    }
}
