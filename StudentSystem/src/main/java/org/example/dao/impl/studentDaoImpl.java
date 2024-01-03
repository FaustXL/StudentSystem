package org.example.dao.impl;

import org.example.dao.studentDao;
import org.example.domain.student;
import org.example.utils.jdbcConfig;

import java.security.SecureRandom;
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

    @Override
    public List<student> selectAffiliationAndProfessionalName() throws Exception {
        String sql = "SELECT affiliation,professional_name FROM student GROUP BY affiliation,professional_name;";
        Connection connection = jdbcConfig.getConnection();
        List<student> list = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            student student = new student();
            student.setAffiliation(resultSet.getString("affiliation"));
            student.setProfessionalName(resultSet.getString("professional_name"));
            list.add(student);
        }
        return list;
    }

    @Override
    public List<student> selectAllByCondition(student s) throws Exception {
        String sql = "select * from `student` where ";
        if (s.getStudentId() != null && !s.getStudentId().equals("")){
            sql += " and student_id = "+"'"+s.getStudentId()+"'";
        }if (s.getStudentName() != null && !s.getStudentName().equals("")){
            sql += " and student_name like "+"'%"+s.getStudentName()+"%'";
        }if (s.getStudentGender() != null && !s.getStudentGender().equals("")){
            sql += " and student_gender = "+"'"+s.getStudentGender()+"'";
        }if (s.getStudentAge() != null){
            sql += " and student_age = "+s.getStudentAge();
        }if (s.getIdCardNumber() != null && !s.getIdCardNumber().equals("")){
            sql += " and id_card_number = "+"'"+s.getIdCardNumber()+"'";
        }if (s.getAffiliation() != null && !s.getAffiliation().equals("")){
            sql += " and affiliation = "+"'"+s.getAffiliation()+"'";
        }if (s.getClasses() != null && !s.getClasses().equals("")){
            sql += " and class like "+"'%"+s.getClasses()+"%'";
        }if (s.getProfessionalName() != null && !s.getProfessionalName().equals("")){
            sql += " and professional_name = "+"'"+s.getProfessionalName()+"'";
        }if (s.getStudentAddress() != null && !s.getStudentAddress().equals("")){
            sql += " and student_address like "+"'%"+s.getStudentAddress()+"%'";
        }if (s.getStudentTel() != null && !s.getStudentTel().equals("")){
            sql += " and student_tel = "+"'"+s.getStudentTel()+"'";
        }
        String sql1 = sql.replaceFirst("and", "");
        System.out.println(sql1);
        Connection connection = jdbcConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql1);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<student> studentList = new ArrayList<>();
        while (resultSet.next()) {
            student stu = new student();
            stu.setStudentId(resultSet.getString("student_id"));
            stu.setStudentName(resultSet.getString("student_name"));
            stu.setStudentGender(resultSet.getString("student_gender"));
            stu.setStudentAge(resultSet.getInt("student_age"));
            stu.setIdCardNumber(resultSet.getString("id_card_number"));
            stu.setAffiliation(resultSet.getString("affiliation"));
            stu.setClasses(resultSet.getString("class"));
            stu.setProfessionalName(resultSet.getString("professional_name"));
            stu.setStudentAddress(resultSet.getString("student_address"));
            stu.setStudentTel(resultSet.getString("student_tel"));
            studentList.add(stu);
        }
        return studentList;
    }

    @Override
    public int deleteStudentById(String id) throws Exception {
        String sql = "DELETE FROM `student_system`.`student` WHERE (`student_id` = ?);";
        Connection connection = jdbcConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,id);
        int i = preparedStatement.executeUpdate();
        return i;
    }

    @Override
    public int updateStudent(student s) throws Exception {
        String sql = "UPDATE `student` SET `student_name` = ?, `student_gender` = ?, `student_age` = ?, " +
                "`id_card_number` = ?, `affiliation` = ?, `class` = ?, `professional_name` = ?, " +
                "`student_address` = ?, `student_tel` = ? WHERE (`student_id` = ?);";
        Connection connection = jdbcConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,s.getStudentName());
        preparedStatement.setString(2,s.getStudentGender());
        preparedStatement.setInt(3,s.getStudentAge());
        preparedStatement.setString(4,s.getIdCardNumber());
        preparedStatement.setString(5,s.getAffiliation());
        preparedStatement.setString(6,s.getClasses());
        preparedStatement.setString(7,s.getProfessionalName());
        preparedStatement.setString(8,s.getStudentAddress());
        preparedStatement.setString(9,s.getStudentTel());
        preparedStatement.setString(10,s.getStudentId());
        System.out.println(s);
        return preparedStatement.executeUpdate();
    }

    @Override
    public List<String> selectClassByProfessionalName(String professionalName) throws Exception {
        Connection connection = jdbcConfig.getConnection();
        PreparedStatement preparedStatement =
                connection.prepareStatement("select `class` from student where `professional_name` = ?");
        preparedStatement.setString(1,professionalName);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<String> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(resultSet.getString("class"));
        }
        System.out.println(professionalName);
        return list;
    }
}
