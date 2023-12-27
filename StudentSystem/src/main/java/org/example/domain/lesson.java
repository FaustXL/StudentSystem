package org.example.domain;

import lombok.Data;

@Data
public class lesson {
    private String lessonId;
    private String lessonName;
    private Integer studyHours; //学时
    private Integer credits; //学分
}
