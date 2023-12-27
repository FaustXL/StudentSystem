package org.example.dao;

import org.example.domain.lesson;

import java.util.List;

public interface lessonDao {
    List<lesson> selectLessonAll() throws Exception;
}
