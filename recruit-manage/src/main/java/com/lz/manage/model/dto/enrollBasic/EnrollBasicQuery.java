package com.lz.manage.model.dto.enrollBasic;

import java.util.Map;
import java.io.Serializable;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.BeanUtils;
import com.baomidou.mybatisplus.annotation.TableField;
import com.lz.manage.model.domain.EnrollBasic;
/**
 * 录取信息Query对象 stu_enroll_basic
 *
 * @author ruoyi
 * @date 2025-04-14
 */
@Data
public class EnrollBasicQuery implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 考生ID */
    private String stuEnrollId;

    /** 招生年度 */
    private String enrollYear;

    /** 考生号 */
    private String examNum;

    /** 准考证号 */
    private String examTicketNum;

    /** 姓名 */
    private String stuName;

    /** 省份名称 */
    private String provinceName;

    /** 身份证号 */
    private String idCard;

    /** 民族 */
    private String nationId;

    /** 政治面貌 */
    private String politicsId;

    /** 考生类别 */
    private String stuExamSort;

    /** 家庭电话 */
    private String homePhone;

    /** 外语语种 */
    private String foreignLanguage;

    /** 录取专业名称 */
    private String enrollSpName;

    /** 录取学院名称 */
    private String enrollDeptName;

    /** 投档志愿 */
    private String enrollNum;

    /** 服从调剂 */
    private String enrollAdjust;

    /** 录取批次 */
    private String enrollBatch;

    /** 档案状态 */
    private String docStatus;

    /** 学历等级 */
    private String eduLevel;

    /** 导入数据标记 */
    private String dataBatchFlag;

    /** 请求参数 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private Map<String, Object> params;

    /**
     * 对象转封装类
     *
     * @param enrollBasicQuery 查询对象
     * @return EnrollBasic
     */
    public static EnrollBasic queryToObj(EnrollBasicQuery enrollBasicQuery) {
        if (enrollBasicQuery == null) {
            return null;
        }
        EnrollBasic enrollBasic = new EnrollBasic();
        BeanUtils.copyProperties(enrollBasicQuery, enrollBasic);
        return enrollBasic;
    }
}
