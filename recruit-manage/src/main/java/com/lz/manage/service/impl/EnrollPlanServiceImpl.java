package com.lz.manage.service.impl;

import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import com.lz.common.utils.StringUtils;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.common.utils.DateUtils;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lz.manage.mapper.EnrollPlanMapper;
import com.lz.manage.model.domain.EnrollPlan;
import com.lz.manage.service.IEnrollPlanService;
import com.lz.manage.model.dto.enrollPlan.EnrollPlanQuery;
import com.lz.manage.model.vo.enrollPlan.EnrollPlanVo;

/**
 * 招生计划Service业务层处理
 * 
 * @author YY
 * @date 2025-04-14
 */
@Service
public class EnrollPlanServiceImpl extends ServiceImpl<EnrollPlanMapper, EnrollPlan> implements IEnrollPlanService
{
    @Resource
    private EnrollPlanMapper enrollPlanMapper;

    //region mybatis代码
    /**
     * 查询招生计划
     * 
     * @param planId 招生计划主键
     * @return 招生计划
     */
    @Override
    public EnrollPlan selectEnrollPlanByPlanId(String planId)
    {
        return enrollPlanMapper.selectEnrollPlanByPlanId(planId);
    }

    /**
     * 查询招生计划列表
     * 
     * @param enrollPlan 招生计划
     * @return 招生计划
     */
    @Override
    public List<EnrollPlan> selectEnrollPlanList(EnrollPlan enrollPlan)
    {
        return enrollPlanMapper.selectEnrollPlanList(enrollPlan);
    }

    /**
     * 新增招生计划
     * 
     * @param enrollPlan 招生计划
     * @return 结果
     */
    @Override
    public int insertEnrollPlan(EnrollPlan enrollPlan)
    {
        enrollPlan.setCreateTime(DateUtils.getNowDate());
        return enrollPlanMapper.insertEnrollPlan(enrollPlan);
    }

    /**
     * 修改招生计划
     * 
     * @param enrollPlan 招生计划
     * @return 结果
     */
    @Override
    public int updateEnrollPlan(EnrollPlan enrollPlan)
    {
        enrollPlan.setUpdateTime(DateUtils.getNowDate());
        return enrollPlanMapper.updateEnrollPlan(enrollPlan);
    }

    /**
     * 批量删除招生计划
     * 
     * @param planIds 需要删除的招生计划主键
     * @return 结果
     */
    @Override
    public int deleteEnrollPlanByPlanIds(String[] planIds)
    {
        return enrollPlanMapper.deleteEnrollPlanByPlanIds(planIds);
    }

    /**
     * 删除招生计划信息
     * 
     * @param planId 招生计划主键
     * @return 结果
     */
    @Override
    public int deleteEnrollPlanByPlanId(String planId)
    {
        return enrollPlanMapper.deleteEnrollPlanByPlanId(planId);
    }
    //endregion
    @Override
    public QueryWrapper<EnrollPlan> getQueryWrapper(EnrollPlanQuery enrollPlanQuery){
        QueryWrapper<EnrollPlan> queryWrapper = new QueryWrapper<>();
        //如果不使用params可以删除
        Map<String, Object> params = enrollPlanQuery.getParams();
        if (StringUtils.isNull(params)) {
            params = new HashMap<>();
        }
        String planId = enrollPlanQuery.getPlanId();
        queryWrapper.eq(StringUtils.isNotEmpty(planId) ,"plan_id",planId);

        String planYear = enrollPlanQuery.getPlanYear();
        queryWrapper.eq(StringUtils.isNotEmpty(planYear) ,"plan_year",planYear);

        String spId = enrollPlanQuery.getSpId();
        queryWrapper.eq(StringUtils.isNotEmpty(spId) ,"sp_id",spId);

        String spName = enrollPlanQuery.getSpName();
        queryWrapper.like(StringUtils.isNotEmpty(spName) ,"sp_name",spName);

        String stuDeptName = enrollPlanQuery.getStuDeptName();
        queryWrapper.like(StringUtils.isNotEmpty(stuDeptName) ,"stu_dept_name",stuDeptName);

        String subjectSortName = enrollPlanQuery.getSubjectSortName();
        queryWrapper.like(StringUtils.isNotEmpty(subjectSortName) ,"subject_sort_name",subjectSortName);

        String provinceName = enrollPlanQuery.getProvinceName();
        queryWrapper.like(StringUtils.isNotEmpty(provinceName) ,"province_name",provinceName);

        Long planNum = enrollPlanQuery.getPlanNum();
        queryWrapper.eq( StringUtils.isNotNull(planNum),"plan_num",planNum);

        BigDecimal tuitionFee = enrollPlanQuery.getTuitionFee();
        queryWrapper.eq( StringUtils.isNotNull(tuitionFee),"tuition_fee",tuitionFee);

        String createBy = enrollPlanQuery.getCreateBy();
        queryWrapper.like(StringUtils.isNotEmpty(createBy) ,"create_by",createBy);

        Date createTime = enrollPlanQuery.getCreateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginCreateTime"))&&StringUtils.isNotNull(params.get("endCreateTime")),"create_time",params.get("beginCreateTime"),params.get("endCreateTime"));

        String updateBy = enrollPlanQuery.getUpdateBy();
        queryWrapper.like(StringUtils.isNotEmpty(updateBy) ,"update_by",updateBy);

        Date updateTime = enrollPlanQuery.getUpdateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginUpdateTime"))&&StringUtils.isNotNull(params.get("endUpdateTime")),"update_time",params.get("beginUpdateTime"),params.get("endUpdateTime"));

        return queryWrapper;
    }

    @Override
    public List<EnrollPlanVo> convertVoList(List<EnrollPlan> enrollPlanList) {
        if (StringUtils.isEmpty(enrollPlanList)) {
            return Collections.emptyList();
        }
        return enrollPlanList.stream().map(EnrollPlanVo::objToVo).collect(Collectors.toList());
    }

}
