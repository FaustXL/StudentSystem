package org.example.dao.impl;

import org.example.dao.studentDao;
import org.example.domain.student;
import org.example.utils.jdbcConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class studentDaoImpl implements studentDao {
    @Override
    public List<student> selectStudentAll() throws Exception {
        List<student> studentList = new ArrayList<>();
        Connection connection = jdbcConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from student");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            student s = new student();
            s.setStudentId(resultSet.getString("student_id"));
            s.setStudentName(resultSet.getString("student_name"));
            s.setStudentGender(resultSet.getString("student_gender"));
            s.setStudentAge(resultSet.getInt("student_age"));
            s.setIdCardNumber(resultSet.getString("id_card_number"));
            s.setAffiliation(resultSet.getString("affiliation"));
            s.setClasses(resultSet.getString("class"));
            s.setProfessionalName(resultSet.getString("professional_name"));
            s.setStudentAddress(resultSet.getString("student_address"));
            s.setStudentTel(resultSet.getString("student_tel"));
            studentList.add(s);
        }
        return studentList;
    }

    @Override
    public student selectStudentById(String id) throws Exception {
        Connection connection = jdbcConfig.getConnection();
        String sql = "select * from student where student_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        student s = new student();
        while (resultSet.next()) {
            s.setStudentId(resultSet.getString("student_id"));
            s.setStudentName(resultSet.getString("student_name"));
            s.setStudentGender(resultSet.getString("student_gender"));
            s.setStudentAge(resultSet.getInt("student_age"));
            s.setIdCardNumber(resultSet.getString("id_card_number"));
            s.setAffiliation(resultSet.getString("affiliation"));
            s.setClasses(resultSet.getString("class"));
            s.setProfessionalName(resultSet.getString("professional_name"));
            s.setStudentAddress(resultSet.getString("student_address"));
            s.setStudentTel(resultSet.getString("student_tel"));
        }
        return s;
    }

    @Override
    public int insertStudent(student s) throws Exception {
        Connection connection = jdbcConfig.getConnection();
        PreparedStatement prep = connection.prepareStatement("INSERT INTO `student` (`student_id`, `student_name`," +
                " `student_gender`, `student_age`, `id_card_number`, `affiliation`, `class`, `professional_name`, " +
                "`student_address`,`student_tel`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
        prep.setString(1,s.getStudentId());
        prep.setString(2,s.getStudentName());
        prep.setString(3,s.getStudentGender());
        prep.setInt(4,s.getStudentAge());
        prep.setString(5,s.getIdCardNumber());
        prep.setString(6,s.getAffiliation());
        prep.setString(7,s.getClasses());
        prep.setString(8,s.getProfessionalName());
        prep.setString(9,s.getStudentAddress());
        prep.setString(10,s.getStudentTel());

        return prep.executeUpdate();
    }

    @Override
    public List<student> selectAffiliation() throws Exception {
        String sql = "SELECT affiliation FROM student GROUP BY affiliation;";
        Connection connection = jdbcConfig.getConnection();
        List<student> list = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            student student = new student();
            student.setAffiliation(resultSet.getString("affiliation"));
            list.add(student);
        }
        return list;
    }


}
