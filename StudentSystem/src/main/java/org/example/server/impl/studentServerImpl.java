package org.example.server.impl;

import org.example.dao.impl.studentDaoImpl;
import org.example.domain.student;
import org.example.server.studentServer;

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
}
