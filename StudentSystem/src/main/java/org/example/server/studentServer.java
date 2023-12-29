package org.example.server;

import org.example.domain.student;

import java.util.List;

public interface studentServer {
    String[][] getStudentAll(String[][] tableData) throws Exception;
    student getStudentById(String id) throws Exception;
    int insertStudent(student s) throws Exception;
    List<student> getAffiliation() throws Exception;
    List<student> getAffiliationAndProfessionalName() throws Exception;
    String[][] dealWithAffiliationAndProfessionalName(String[][] tableData) throws Exception;
    String[][] selectAllByCondition(student s) throws Exception;
    int deleteStudentByList(List<String> list) throws Exception;
}
