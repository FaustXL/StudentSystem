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
        String sql = "select * from student_user where student_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,username);
        ResultSet resultSet = preparedStatement.executeQuery();
        studentUser studentUser = new studentUser();
        while (resultSet.next()){
            studentUser.setId(resultSet.getInt("user_id"));
            studentUser.setStudentId(resultSet.getString("student_id"));
            studentUser.setPassword(resultSet.getString("student_password"));
        }
        System.out.println(studentUser);
        return studentUser;
    }

    @Override
    public int insertStudentUser(studentUser user) throws Exception {
        String sql = "INSERT INTO `student_user` (`student_id`, `student_password`) VALUES (?, ?)";
        Connection connection = jdbcConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,user.getStudentId());
        preparedStatement.setString(2,user.getPassword());
        int i = preparedStatement.executeUpdate();
        return i;
    }
}
