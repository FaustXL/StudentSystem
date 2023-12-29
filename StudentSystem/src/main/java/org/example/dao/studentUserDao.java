package org.example.dao;

import org.example.domain.studentUser;

import java.util.List;

public interface studentUserDao {
    studentUser selectByUsername(String username) throws Exception;
    int insertStudentUser(studentUser user) throws Exception;
}
