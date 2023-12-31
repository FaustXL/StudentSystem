package org.example.server.impl;

import org.example.dao.impl.lessonDaoImpl;
import org.example.domain.lesson;
import org.example.server.lessonServer;

import java.util.List;

public class lessonServerImpl implements lessonServer {

    private lessonDaoImpl lessonDao = new lessonDaoImpl();

    @Override
    public String[][] getsLessonAll(String[][] tableData) throws Exception {
        List<lesson> lessonList = lessonDao.selectLessonAll();
        tableData = new String[lessonList.size()][4];
            for (int i = 0; i < tableData.length; i++) {
                lesson lesson = lessonList.get(i);
                tableData[i][0] = lesson.getLessonId();
                tableData[i][1] = lesson.getLessonName();
                tableData[i][2] = lesson.getStudyHours().toString();
                tableData[i][3] = lesson.getCredits().toString();
            }
        return tableData;
    }

    @Override
    public int insertLesson(lesson l) throws Exception {
        return lessonDao.insertLesson(l);
    }

    @Override
    public List<String> getLessonPeople(String lessonId) throws Exception {
        return lessonDao.getLessonPeople(lessonId);
    }

    @Override
    public int deleteLessonByList(List<String> list) throws Exception {
        for (String s : list) {
            lessonDao.deleteLessonById(s);
        }
        return 1;
    }

    @Override
    public int updateLesson(lesson l) throws Exception {
        return lessonDao.updateLesson(l);
    }
}
