package com.lz.manage.model.vo.enrollSearch;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import com.lz.common.annotation.Excel;
import org.springframework.beans.BeanUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.manage.model.domain.EnrollSearch;

/**
 * 考生查询Vo对象 stu_enroll_search
 *
 * @author YY
 * @date 2025-04-14
 */
@Data
public class EnrollSearchVo implements Serializable {
    private static final long serialVersionUID = 1L;

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
     * 对象转封装类
     *
     * @param enrollSearch EnrollSearch实体对象
     * @return EnrollSearchVo
     */
    public static EnrollSearchVo objToVo(EnrollSearch enrollSearch) {
        if (enrollSearch == null) {
            return null;
        }
        EnrollSearchVo enrollSearchVo = new EnrollSearchVo();
        BeanUtils.copyProperties(enrollSearch, enrollSearchVo);
        return enrollSearchVo;
    }
}
