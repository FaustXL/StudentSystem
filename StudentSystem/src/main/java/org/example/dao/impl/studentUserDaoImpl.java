package org.example.dao.impl;

import org.example.dao.studentUserDao;
import org.example.domain.studentUser;
import org.example.utils.jdbcConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class studentUserDaoImpl implements studentUserDao {
    @Override
    public studentUser selectByUsername(String username) throws Exception {
        Connection connection = jdbcConfig.getConnection();
        String sql = "select * from student_user where student_username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,username);
        ResultSet resultSet = preparedStatement.executeQuery();
        studentUser studentUser = new studentUser();
        while (resultSet.next()){
            studentUser.setId(resultSet.getInt("user_id"));
            studentUser.setUsername(resultSet.getString("student_username"));
            studentUser.setPassword(resultSet.getString("student_password"));
        }
        System.out.println(studentUser);
        return studentUser;
    }
}