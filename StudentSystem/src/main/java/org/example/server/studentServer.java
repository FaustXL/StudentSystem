package org.example.server;

import org.example.domain.student;

import java.util.List;

public interface studentServer {
    String[][] getStudentAll(String[][] tableData) throws Exception;
    student getStudentById(String id) throws Exception;
    boolean insertStudent(student s) throws Exception;
}
