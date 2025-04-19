package com.lz.manage.model.vo.enrollSearch;

import lombok.Data;

import java.util.Date;

@Data
public class EnrollJoinResultVo {
    private String stuEnrollId;
    private String stuEnrollName;
    private String stuNo;
    private String classId;
    private String dormId;
    private String classTeacher;
    private String classTeacherContact;
    private String classQq;
    private String searchRemarks;
    private String noteCode;
    private String noteCodePrefix;
    private String noteCodeSuffix;
    private String planYear;
    private String provinceName;
    private String stuDeptName;
    private String stuMajor;
    private String stuName;
    private String isEnroll;
    private String noteSecurityCode;
    private String noteExpCode;
    private String notePrintStatus;
    private String notePrintInfo;
    private Date notePrintTime;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
    private String remark;
}
