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
}
