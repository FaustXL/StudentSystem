package org.example.server;

import org.example.domain.lesson;

import java.util.List;

public interface lessonServer {
    String[][] getsLessonAll(String[][] tableData) throws Exception;
    int insertLesson(lesson l) throws Exception;
    List<String> getLessonPeople(String lessonId) throws Exception;
    int deleteLessonByList(List<String> list) throws Exception;
    int updateLesson(lesson l) throws Exception;
}
