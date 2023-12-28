package org.example.server.impl;

import org.example.dao.impl.lessonDaoImpl;
import org.example.dao.impl.studentLessonDaoImpl;
import org.example.domain.lesson;
import org.example.domain.studentLesson;
import org.example.server.studentLessonServer;

import java.util.List;

public class studentLessonServeImpl implements studentLessonServer {
    private studentLessonDaoImpl studentLessonDao = new studentLessonDaoImpl();
    private lessonDaoImpl lessonDao = new lessonDaoImpl();
    @Override
    public List<studentLesson> getStudentLesson(String studentId) throws Exception {
        return studentLessonDao.getStudentLesson(studentId);
    }

    @Override
    public String[][] createLessonTable(List<studentLesson> studentLessonList,String[][] lessonTable) throws Exception {
        lessonTable = new String[4][8];
        lessonTable[0][0] = "第一节课";
        lessonTable[1][0] = "第二节课";
        lessonTable[2][0] = "第三节课";
        lessonTable[3][0] = "第四节课";

        for (studentLesson studentLesson : studentLessonList) {
            lesson lesson = lessonDao.selectLessonByLessonId(studentLesson.getLessonId());
            Float ordered = studentLesson.getOrdered();
            String s = String.valueOf(ordered);
            String[] split = s.split("\\.");
            lessonTable[Integer.parseInt(split[1]) - 1][Integer.parseInt(split[0])] = lesson.getLessonName();
        }
        return lessonTable;
    }
}
