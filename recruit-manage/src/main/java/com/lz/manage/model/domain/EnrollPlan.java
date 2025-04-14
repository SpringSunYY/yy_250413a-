package com.lz.manage.model.domain;

import java.io.Serializable;
import java.util.Map;
import java.util.Date;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.lz.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
/**
 * 招生计划对象 stu_enroll_plan
 *
 * @author YY
 * @date 2025-04-14
 */
@TableName("stu_enroll_plan")
@Data
public class EnrollPlan implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 计划ID */
    @Excel(name = "计划ID")
    @TableId(value = "plan_id", type = IdType.ASSIGN_ID)
    private String planId;

    /** 计划年度 */
    @Excel(name = "计划年度")
    private String planYear;

    /** 专业代码 */
    @Excel(name = "专业代码")
    private String spId;

    /** 专业名称 */
    @Excel(name = "专业名称")
    private String spName;

    /** 系部id */
    @Excel(name = "系部id")
    private String stuDeptId;

    /** 系部名称 */
    @Excel(name = "系部名称")
    private String stuDeptName;

    /** 学制 */
    @Excel(name = "学制")
    private Long spLength;

    /** 科类ID */
    @Excel(name = "科类ID")
    private String subjectSortId;

    /** 科类名称 */
    @Excel(name = "科类名称")
    private String subjectSortName;

    /** 省份编码 */
    @Excel(name = "省份编码")
    private String provinceCode;

    /** 省份名称 */
    @Excel(name = "省份名称")
    private String provinceName;

    /** 省份简称 */
    @Excel(name = "省份简称")
    private String provinceNameShort;

    /** 招生人数 */
    @Excel(name = "招生人数")
    private Long planNum;

    /** 学费 */
    @Excel(name = "学费")
    private BigDecimal tuitionFee;

    /** 创建者 */
    @Excel(name = "创建者")
    private String createBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createTime;

    /** 更新者 */
    @Excel(name = "更新者")
    private String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateTime;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;

    /** 请求参数 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private Map<String, Object> params;
}
