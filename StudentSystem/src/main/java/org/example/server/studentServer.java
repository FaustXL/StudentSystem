package org.example.server;

import org.example.domain.student;

import java.util.List;

public interface studentServer {
    String[][] getStudentAll(String[][] tableData) throws Exception;
}
