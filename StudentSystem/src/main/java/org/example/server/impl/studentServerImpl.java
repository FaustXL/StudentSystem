package org.example.server.impl;

import org.example.dao.impl.studentDaoImpl;
import org.example.domain.student;
import org.example.server.studentServer;
import org.example.utils.util;

import java.sql.SQLIntegrityConstraintViolationException;
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
    public boolean insertStudent(student s) throws Exception {
        int i1 = Integer.parseInt(util.createStudentId(s.getClasses()));
        s.setStudentId(String.valueOf(i1 + 1));
        int i = 0;
        try {
            i = studentDao.insertStudent(s);
        } catch (SQLIntegrityConstraintViolationException exception) {
            System.out.println("服务器繁忙,请稍后再进行插入学生操作");
        }
        return i > 0;
    }
}
