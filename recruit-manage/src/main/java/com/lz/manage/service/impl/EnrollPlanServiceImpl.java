package com.lz.manage.service.impl;

import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lz.common.exception.ServiceException;
import com.lz.common.utils.SecurityUtils;
import com.lz.common.utils.StringUtils;

import java.math.BigDecimal;
import java.util.Date;

import com.lz.common.utils.DateUtils;

import javax.annotation.Resource;

import com.lz.common.utils.uuid.IdUtils;
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
public class EnrollPlanServiceImpl extends ServiceImpl<EnrollPlanMapper, EnrollPlan> implements IEnrollPlanService {
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
    public EnrollPlan selectEnrollPlanByPlanId(String planId) {
        return enrollPlanMapper.selectEnrollPlanByPlanId(planId);
    }

    /**
     * 查询招生计划列表
     *
     * @param enrollPlan 招生计划
     * @return 招生计划
     */
    @Override
    public List<EnrollPlan> selectEnrollPlanList(EnrollPlan enrollPlan) {
        return enrollPlanMapper.selectEnrollPlanList(enrollPlan);
    }

    /**
     * 新增招生计划
     *
     * @param enrollPlan 招生计划
     * @return 结果
     */
    @Override
    public int insertEnrollPlan(EnrollPlan enrollPlan) {
        //校验招生计划年度和专业代码是否唯一
        EnrollPlan major = getEnrollPlanByMayor(enrollPlan);
        if (StringUtils.isNotNull(major)) {
            throw new ServiceException("该年度已经存在此专业");
        }
        //校验招生计划年度和科类ID是否唯一
        EnrollPlan eNrollPlanBySubject = getENrollPlanBySubject(enrollPlan);
        if (StringUtils.isNotNull(eNrollPlanBySubject)) {
            throw new ServiceException("该年度科类ID已经存在");
        }
        //校验招生计划年度和系部ID是否唯一
        EnrollPlan eNrollPlanByDept = getEnrollPlanByDept(enrollPlan);
        if (StringUtils.isNotNull(eNrollPlanByDept)) {
            throw new ServiceException("该年度系部ID已经存在");
        }
        enrollPlan.setPlanId(IdUtils.snowflakeId().toString());
        enrollPlan.setCreateBy(SecurityUtils.getUsername());
        enrollPlan.setCreateTime(DateUtils.getNowDate());
        return enrollPlanMapper.insertEnrollPlan(enrollPlan);
    }

    @Override
    public EnrollPlan getEnrollPlanByDept(EnrollPlan enrollPlan) {
        return this.getOne(new LambdaQueryWrapper<EnrollPlan>()
                .eq(EnrollPlan::getPlanYear, enrollPlan.getPlanYear())
                .eq(EnrollPlan::getStuDeptId, enrollPlan.getStuDeptId()));
    }

    @Override
    public EnrollPlan getENrollPlanBySubject(EnrollPlan enrollPlan) {
        return this.getOne(new LambdaQueryWrapper<EnrollPlan>()
                .eq(EnrollPlan::getPlanYear, enrollPlan.getPlanYear())
                .eq(EnrollPlan::getSubjectSortId, enrollPlan.getSubjectSortId()));
    }

    @Override
    public EnrollPlan getEnrollPlanByMayor(EnrollPlan enrollPlan) {
        return this.getOne(new LambdaQueryWrapper<EnrollPlan>()
                .eq(EnrollPlan::getPlanYear, enrollPlan.getPlanYear())
                .eq(EnrollPlan::getSpId, enrollPlan.getSpId()));
    }

    /**
     * 修改招生计划
     *
     * @param enrollPlan 招生计划
     * @return 结果
     */
    @Override
    public int updateEnrollPlan(EnrollPlan enrollPlan) {
        EnrollPlan enrollPlanDb = this.selectEnrollPlanByPlanId(enrollPlan.getPlanId());
        if (StringUtils.isNull(enrollPlanDb)) {
            throw new ServiceException("数据不存在");
        }
        EnrollPlan planByDept = this.getEnrollPlanByDept(enrollPlan);
        if (StringUtils.isNotNull(planByDept) && !planByDept.getPlanId().equals(enrollPlan.getPlanId())) {
            throw new ServiceException("该年度系部ID已经存在");
        }
        EnrollPlan planByMayor = this.getEnrollPlanByMayor(enrollPlan);
        if (StringUtils.isNotNull(planByMayor) && !planByMayor.getPlanId().equals(enrollPlan.getPlanId())) {
            throw new ServiceException("该年度已经存在此专业");
        }
        EnrollPlan bySubject = this.getENrollPlanBySubject(enrollPlan);
        if (StringUtils.isNotNull(bySubject) && !bySubject.getPlanId().equals(enrollPlan.getPlanId())) {
            throw new ServiceException("该年度科类ID已经存在");
        }
        enrollPlan.setUpdateBy(SecurityUtils.getUsername());
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
    public int deleteEnrollPlanByPlanIds(String[] planIds) {
        return enrollPlanMapper.deleteEnrollPlanByPlanIds(planIds);
    }

    /**
     * 删除招生计划信息
     *
     * @param planId 招生计划主键
     * @return 结果
     */
    @Override
    public int deleteEnrollPlanByPlanId(String planId) {
        return enrollPlanMapper.deleteEnrollPlanByPlanId(planId);
    }

    //endregion
    @Override
    public QueryWrapper<EnrollPlan> getQueryWrapper(EnrollPlanQuery enrollPlanQuery) {
        QueryWrapper<EnrollPlan> queryWrapper = new QueryWrapper<>();
        //如果不使用params可以删除
        Map<String, Object> params = enrollPlanQuery.getParams();
        if (StringUtils.isNull(params)) {
            params = new HashMap<>();
        }
        String planId = enrollPlanQuery.getPlanId();
        queryWrapper.eq(StringUtils.isNotEmpty(planId), "plan_id", planId);

        String planYear = enrollPlanQuery.getPlanYear();
        queryWrapper.eq(StringUtils.isNotEmpty(planYear), "plan_year", planYear);

        String spId = enrollPlanQuery.getSpId();
        queryWrapper.eq(StringUtils.isNotEmpty(spId), "sp_id", spId);

        String spName = enrollPlanQuery.getSpName();
        queryWrapper.like(StringUtils.isNotEmpty(spName), "sp_name", spName);

        String stuDeptName = enrollPlanQuery.getStuDeptName();
        queryWrapper.like(StringUtils.isNotEmpty(stuDeptName), "stu_dept_name", stuDeptName);

        String subjectSortName = enrollPlanQuery.getSubjectSortName();
        queryWrapper.like(StringUtils.isNotEmpty(subjectSortName), "subject_sort_name", subjectSortName);

        String provinceName = enrollPlanQuery.getProvinceName();
        queryWrapper.like(StringUtils.isNotEmpty(provinceName), "province_name", provinceName);

        Long planNum = enrollPlanQuery.getPlanNum();
        queryWrapper.eq(StringUtils.isNotNull(planNum), "plan_num", planNum);

        BigDecimal tuitionFee = enrollPlanQuery.getTuitionFee();
        queryWrapper.eq(StringUtils.isNotNull(tuitionFee), "tuition_fee", tuitionFee);

        String createBy = enrollPlanQuery.getCreateBy();
        queryWrapper.like(StringUtils.isNotEmpty(createBy), "create_by", createBy);

        Date createTime = enrollPlanQuery.getCreateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginCreateTime")) && StringUtils.isNotNull(params.get("endCreateTime")), "create_time", params.get("beginCreateTime"), params.get("endCreateTime"));

        String updateBy = enrollPlanQuery.getUpdateBy();
        queryWrapper.like(StringUtils.isNotEmpty(updateBy), "update_by", updateBy);

        Date updateTime = enrollPlanQuery.getUpdateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginUpdateTime")) && StringUtils.isNotNull(params.get("endUpdateTime")), "update_time", params.get("beginUpdateTime"), params.get("endUpdateTime"));

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
