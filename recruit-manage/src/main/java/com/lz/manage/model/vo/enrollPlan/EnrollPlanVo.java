package com.lz.manage.model.vo.enrollPlan;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.lz.common.annotation.Excel;
import org.springframework.beans.BeanUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.manage.model.domain.EnrollPlan;
/**
 * 招生计划Vo对象 stu_enroll_plan
 *
 * @author YY
 * @date 2025-04-14
 */
@Data
public class EnrollPlanVo implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 计划ID */
    @Excel(name = "计划ID")
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


     /**
     * 对象转封装类
     *
     * @param enrollPlan EnrollPlan实体对象
     * @return EnrollPlanVo
     */
    public static EnrollPlanVo objToVo(EnrollPlan enrollPlan) {
        if (enrollPlan == null) {
            return null;
        }
        EnrollPlanVo enrollPlanVo = new EnrollPlanVo();
        BeanUtils.copyProperties(enrollPlan, enrollPlanVo);
        return enrollPlanVo;
    }
}
