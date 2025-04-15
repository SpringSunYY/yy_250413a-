package com.lz.manage.model.domain;

import java.io.Serializable;
import java.util.Map;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.lz.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 考生查询对象 stu_enroll_search
 *
 * @author YY
 * @date 2025-04-14
 */
@TableName("stu_enroll_search")
@Data
public class EnrollSearch implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 考生ID
     */
    @Excel(name = "考生ID")
    @TableId(value = "stu_enroll_id", type = IdType.ASSIGN_ID)
    private String stuEnrollId;
    @Excel(name = "考生姓名")
    @TableField(exist = false)
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
     * 其他说明
     */
    private String searchRemarks;

    /**
     * 请求参数
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private Map<String, Object> params;
}
