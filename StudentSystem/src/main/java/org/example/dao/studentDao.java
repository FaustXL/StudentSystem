package org.example.dao;

import org.example.domain.student;

import java.util.List;

public interface studentDao {
    List<student> selectStudentAll() throws Exception;
    student selectStudentById(String id) throws Exception;
    int insertStudent(student s) throws Exception;
    List<student> selectAffiliation() throws Exception;
    List<student> selectAffiliationAndProfessionalName() throws Exception;
    List<student> selectAllByCondition(student s) throws Exception;
}
