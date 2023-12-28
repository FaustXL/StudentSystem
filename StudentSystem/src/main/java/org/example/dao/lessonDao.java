package org.example.dao;

import org.example.domain.lesson;

import java.util.List;

public interface lessonDao {
    List<lesson> selectLessonAll() throws Exception;
    lesson selectLessonByLessonId(String lessonId) throws Exception;
}
