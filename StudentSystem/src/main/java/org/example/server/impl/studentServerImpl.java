package org.example.server.impl;

import org.example.dao.impl.studentDaoImpl;
import org.example.domain.student;
import org.example.server.studentServer;
import org.example.utils.util;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class studentServerImpl implements studentServer {

    private studentDaoImpl studentDao = new studentDaoImpl();

    @Override
    public String[][] getStudentAll(String[][] tableData) throws Exception {
        List<student> studentList = studentDao.selectStudentAll();
        tableData = new String[studentList.size()][10];
            for (int i = 0; i < tableData.length; i++) {
                student student = studentList.get(i);
                tableData[i][0] = student.getStudentId();
                tableData[i][1] = student.getStudentName();
                tableData[i][2] = student.getStudentGender();
                tableData[i][3] = student.getStudentAge().toString();
                tableData[i][4] = student.getIdCardNumber();
                tableData[i][5] = student.getAffiliation();
                tableData[i][6] = student.getClasses();
                tableData[i][7] = student.getProfessionalName();
                tableData[i][8] = student.getStudentAddress();
                tableData[i][9] = student.getStudentTel();
            }
        return tableData;
    }

    @Override
    public student getStudentById(String id) throws Exception {
        student student = studentDao.selectStudentById(id);
        return student;
    }

    @Override
    public int insertStudent(student s) throws Exception {
        if (s.getClasses() == null || s.getStudentTel() == null || s.getStudentAge() == null ||
                s.getProfessionalName() == null || s.getAffiliation() == null || s.getStudentAddress() == null ||
                s.getStudentGender() == null || s.getIdCardNumber() == null || s.getStudentName() == null){
            return -2;
        }

        String regexIdCard = "^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$";
        String regexPhoneNumber = "^1[3456789]\\d{9}$";

        if (!s.getIdCardNumber().matches(regexIdCard) || !s.getStudentTel().matches(regexPhoneNumber)){
            return -1;
        }

        //当填写的班级不是以1-9开头的两个数字
        String regex = "^[1-9]{2}$";
        if (!s.getClasses().substring(0,2).matches(regex)){
            return -3;
        }
        int i1 = Integer.parseInt(util.createStudentId(s.getClasses()));
        s.setStudentId(String.valueOf(i1 + 1));
        int i = 0;
        try {
            i = studentDao.insertStudent(s);
        } catch (SQLIntegrityConstraintViolationException exception) {
            System.out.println("服务器繁忙,请稍后再进行插入学生操作");
        }
        return i;
    }

    @Override
    public List<student> getAffiliation() throws Exception {
        return studentDao.selectAffiliation();
    }

    @Override
    public List<student> getAffiliationAndProfessionalName() throws Exception {
        return studentDao.selectAffiliationAndProfessionalName();
    }

    @Override
    public String[][] dealWithAffiliationAndProfessionalName(String[][] tableData) throws Exception {
        List<student> studentList = studentDao.selectAffiliationAndProfessionalName();
        tableData = new String[studentList.size()][2];
        for (int i = 0; i < tableData.length; i++) {
            tableData[i][0] = studentList.get(i).getAffiliation();
            tableData[i][1] = studentList.get(i).getProfessionalName();
        }
        return tableData;
    }

    @Override
    public String[][] selectAllByCondition(student s) throws Exception {
        List<student> studentList = studentDao.selectAllByCondition(s);
        System.out.println(studentList);
        String[][] tableData = new String[studentList.size()][10];
        for (int i = 0; i < tableData.length; i++) {
            student student = studentList.get(i);
            tableData[i][0] = student.getStudentId();
            tableData[i][1] = student.getStudentName();
            tableData[i][2] = student.getStudentGender();
            tableData[i][3] = student.getStudentAge().toString();
            tableData[i][4] = student.getIdCardNumber();
            tableData[i][5] = student.getAffiliation();
            tableData[i][6] = student.getClasses();
            tableData[i][7] = student.getProfessionalName();
            tableData[i][8] = student.getStudentAddress();
            tableData[i][9] = student.getStudentTel();
        }
        return tableData;
    }

    @Override
    public int deleteStudentByList(List<String> list) throws Exception {
        for (String s : list) {
            studentDao.deleteStudentById(s);
        }
        return 1;
    }
}
