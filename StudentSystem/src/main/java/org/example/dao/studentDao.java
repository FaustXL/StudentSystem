package org.example.dao;

import org.example.domain.student;

import java.util.List;

public interface studentDao {
    List<student> selectStudentAll() throws Exception;
}
