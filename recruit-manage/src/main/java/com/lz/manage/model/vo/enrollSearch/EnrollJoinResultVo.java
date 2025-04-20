package com.lz.manage.model.vo.enrollSearch;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.common.annotation.Excel;
import lombok.Data;

import java.util.Date;

@Data
public class EnrollJoinResultVo {
    /**
     * 考生ID
     */
    @Excel(name = "考生ID")
    private String stuEnrollId;
    @Excel(name = "考生姓名")
    private String stuEnrollName;
    /**
     * 学号
     */
    @Excel(name = "学号")
    private String stuNo;

    /**
     * 班级
     */
    @Excel(name = "班级")
    private String classId;

    /**
     * 宿舍号
     */
    @Excel(name = "宿舍号")
    private String dormId;

    /**
     * 辅导员
     */
    @Excel(name = "辅导员")
    private String classTeacher;

    /**
     * 辅导员联系方式
     */
    @Excel(name = "辅导员联系方式")
    private String classTeacherContact;

    /**
     * QQ群
     */
    @Excel(name = "QQ群")
    private String classQq;

    /**
     * 通知书编号
     */
    @Excel(name = "通知书编号")
    private String noteCode;

    /**
     * 通知书编号-前缀
     */
    @Excel(name = "通知书编号-前缀")
    private String noteCodePrefix;

    /**
     * 通知书编号-后缀
     */
    @Excel(name = "通知书编号-后缀")
    private String noteCodeSuffix;

    /**
     * 招生年度
     */
    @Excel(name = "招生年度")
    private String planYear;

    /**
     * 省份名称
     */
    @Excel(name = "省份名称")
    private String provinceName;

    /**
     * 录取院校
     */
    @Excel(name = "录取院校")
    private String stuDeptName;

    /**
     * 录取专业
     */
    @Excel(name = "录取专业")
    private String stuMajor;

    /**
     * 学生姓名
     */
    @Excel(name = "学生姓名")
    private String stuName;

    /**
     * 是否录取
     */
    @Excel(name = "是否录取")
    private String isEnroll;

    /**
     * 通知书防伪码
     */
    @Excel(name = "通知书防伪码")
    private String noteSecurityCode;

    /**
     * 通知书快递号
     */
    @Excel(name = "通知书快递号")
    private String noteExpCode;

    /**
     * 通知书打印
     */
    @Excel(name = "通知书打印")
    private String notePrintStatus;

    /**
     * 通知书打印信息
     */
    @Excel(name = "通知书打印信息")
    private String notePrintInfo;

    /**
     * 通知书打印时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "通知书打印时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date notePrintTime;

    /**
     * 创建者
     */
    @Excel(name = "创建者")
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createTime;

    /**
     * 更新者
     */
    @Excel(name = "更新者")
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateTime;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remark;
    private String searchRemarks;
}
