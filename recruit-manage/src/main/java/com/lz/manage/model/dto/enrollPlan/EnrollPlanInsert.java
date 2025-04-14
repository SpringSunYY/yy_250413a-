package com.lz.manage.model.dto.enrollPlan;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import com.lz.manage.model.domain.EnrollPlan;
/**
 * 招生计划Vo对象 stu_enroll_plan
 *
 * @author YY
 * @date 2025-04-14
 */
@Data
public class EnrollPlanInsert implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 计划ID */
    private String planId;

    /** 计划年度 */
    private String planYear;

    /** 专业代码 */
    private String spId;

    /** 专业名称 */
    private String spName;

    /** 系部id */
    private String stuDeptId;

    /** 系部名称 */
    private String stuDeptName;

    /** 学制 */
    private Long spLength;

    /** 科类ID */
    private String subjectSortId;

    /** 科类名称 */
    private String subjectSortName;

    /** 省份编码 */
    private String provinceCode;

    /** 省份名称 */
    private String provinceName;

    /** 省份简称 */
    private String provinceNameShort;

    /** 招生人数 */
    private Long planNum;

    /** 学费 */
    private BigDecimal tuitionFee;

    /** 备注 */
    private String remark;

    /**
     * 对象转封装类
     *
     * @param enrollPlanInsert 插入对象
     * @return EnrollPlanInsert
     */
    public static EnrollPlan insertToObj(EnrollPlanInsert enrollPlanInsert) {
        if (enrollPlanInsert == null) {
            return null;
        }
        EnrollPlan enrollPlan = new EnrollPlan();
        BeanUtils.copyProperties(enrollPlanInsert, enrollPlan);
        return enrollPlan;
    }
}
