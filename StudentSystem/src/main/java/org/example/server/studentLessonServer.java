package org.example.server;

import org.example.domain.studentLesson;

import java.util.List;

public interface studentLessonServer {
    List<studentLesson> getStudentLesson(String studentId) throws Exception;
    //给课程数组表赋值
    String[][] createLessonTable(List<studentLesson> studentLessonList,String[][] lessonTable) throws Exception;

}
