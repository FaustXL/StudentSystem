package org.example.dao;

import org.example.domain.studentUser;

import java.util.List;

public interface studentUserDao {
    List<studentUser> selectAll();
}
