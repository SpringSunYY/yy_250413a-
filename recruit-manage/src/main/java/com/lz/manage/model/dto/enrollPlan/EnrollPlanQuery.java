package com.lz.manage.model.dto.enrollPlan;

import java.util.Map;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.BeanUtils;
import com.baomidou.mybatisplus.annotation.TableField;
import com.lz.manage.model.domain.EnrollPlan;
/**
 * 招生计划Query对象 stu_enroll_plan
 *
 * @author YY
 * @date 2025-04-14
 */
@Data
public class EnrollPlanQuery implements Serializable
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

    /** 系部名称 */
    private String stuDeptName;

    /** 科类名称 */
    private String subjectSortName;

    /** 省份名称 */
    private String provinceName;

    /** 招生人数 */
    private Long planNum;

    /** 学费 */
    private BigDecimal tuitionFee;

    /** 创建者 */
    private String createBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;

    /** 请求参数 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private Map<String, Object> params;

    /**
     * 对象转封装类
     *
     * @param enrollPlanQuery 查询对象
     * @return EnrollPlan
     */
    public static EnrollPlan queryToObj(EnrollPlanQuery enrollPlanQuery) {
        if (enrollPlanQuery == null) {
            return null;
        }
        EnrollPlan enrollPlan = new EnrollPlan();
        BeanUtils.copyProperties(enrollPlanQuery, enrollPlan);
        return enrollPlan;
    }
}
