package org.example.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class util {

    public static String createStudentId(String classes) throws Exception {
        String substring = classes.substring(0, 2);
        Connection connection = jdbcConfig.getConnection();
        substring = substring +"%";
        String sql = "SELECT MAX(student_id) FROM student where student_id like ? ";
        PreparedStatement prep = connection.prepareStatement(sql);
        prep.setString(1,substring);
        ResultSet resultSet = prep.executeQuery();
        String studentId = null;
        while (resultSet.next()) {
            studentId = resultSet.getString("MAX(student_id)");
            System.out.println(studentId);
        }
        return studentId;
    }
}
