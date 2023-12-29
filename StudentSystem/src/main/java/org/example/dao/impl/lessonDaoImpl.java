package org.example.dao.impl;

import org.example.dao.lessonDao;
import org.example.domain.lesson;
import org.example.domain.student;
import org.example.utils.jdbcConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class lessonDaoImpl implements lessonDao {
    @Override
    public List<lesson> selectLessonAll() throws Exception {
        List<lesson> lessonList = new ArrayList<>();
        Connection connection = jdbcConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from lesson");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            lesson l = new lesson();
            l.setLessonId(resultSet.getString("lesson_id"));
            l.setLessonName(resultSet.getString("lesson_name"));
            l.setStudyHours(resultSet.getInt("study_hours"));
            l.setCredits(resultSet.getInt("credits"));
            lessonList.add(l);
        }
        return lessonList;
    }

    @Override
    public lesson selectLessonByLessonId(String lessonId) throws Exception {

        List<lesson> lessonList = new ArrayList<>();
        Connection connection = jdbcConfig.getConnection();
        PreparedStatement preparedStatement =
                connection.prepareStatement("select * from lesson where lesson_id = ?");
        preparedStatement.setString(1,lessonId);
        ResultSet resultSet = preparedStatement.executeQuery();

        lesson l = new lesson();
        while (resultSet.next()) {

            l.setLessonId(resultSet.getString("lesson_id"));
            l.setLessonName(resultSet.getString("lesson_name"));
            l.setStudyHours(resultSet.getInt("study_hours"));
            l.setCredits(resultSet.getInt("credits"));
            lessonList.add(l);
        }
        return l;
    }

    @Override
    public int insertLesson(lesson l) throws Exception {
        Connection connection = jdbcConfig.getConnection();
        String sql = "INSERT INTO `lesson` (`lesson_id`, `lesson_name`, `study_hours`, `credits`) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,l.getLessonId());
        preparedStatement.setString(2,l.getLessonName());
        preparedStatement.setInt(3,l.getStudyHours());
        preparedStatement.setInt(4,l.getCredits());
        int i = preparedStatement.executeUpdate();
        return i;
    }

    @Override
    public List<String> getLessonPeople(String lessonId) throws Exception {
        Connection connection = jdbcConfig.getConnection();
        List<String> list = new ArrayList<>();
        String sql = "select `class` from student_lesson  where lesson_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,lessonId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            list.add(resultSet.getString("class"));
        }
        String sql1 = "SELECT * FROM student_system.student where `class` = ?";
        List<String> nameList = new ArrayList<>();
        PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
        for (String s : list) {
            preparedStatement1.setString(1,s);
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            while (resultSet1.next()) {
                String student_name = resultSet1.getString("student_name");
                nameList.add(student_name);
            }
        }
        return nameList;
    }

    @Override
    public int deleteLessonById(String id) throws Exception {
        String sql = "DELETE FROM `lesson` WHERE (`lesson_id` = ?);";
        Connection connection = jdbcConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,id);
        int i = preparedStatement.executeUpdate();
        return i;
    }

    @Override
    public int updateLesson(lesson l) throws Exception {
        String sql = "UPDATE `student_system`.`lesson` SET `lesson_name` = ?, " +
                "`study_hours` = ?, `credits` = ? WHERE (`lesson_id` = ?);";
        Connection connection = jdbcConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,l.getLessonName());
        preparedStatement.setInt(2,l.getStudyHours());
        preparedStatement.setInt(3,l.getCredits());
        preparedStatement.setString(4,l.getLessonId());

        return preparedStatement.executeUpdate();
    }
}
