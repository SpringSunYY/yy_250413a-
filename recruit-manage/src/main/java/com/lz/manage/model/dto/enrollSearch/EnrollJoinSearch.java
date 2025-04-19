package com.lz.manage.model.dto.enrollSearch;

import lombok.Data;

import java.util.Map;

@Data
public class EnrollJoinSearch {
    private String stuEnrollId;
    private String stuNo;
    private String classId;
    private String dormId;
    private String classTeacher;
    private String classTeacherContact;
    private String classQq;

    private String noteCode;
    private String planYear;
    private String provinceName;
    private String stuDeptName;
    private String stuMajor;
    private String stuName;
    private String isEnroll;
    private String notePrintStatus;
    private String notePrintInfo;
    private String createBy;
    private String updateBy;

    private Map<String, Object> params; // 用于时间筛选等扩展字段
}
