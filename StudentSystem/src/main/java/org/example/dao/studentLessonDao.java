package org.example.dao;

import org.example.domain.studentLesson;

import java.util.List;

public interface studentLessonDao {
    List<studentLesson> getStudentLesson(String id) throws Exception;
}
