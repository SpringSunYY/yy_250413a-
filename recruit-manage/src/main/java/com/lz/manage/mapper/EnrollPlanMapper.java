package com.lz.manage.mapper;

import java.util.List;
import com.lz.manage.model.domain.EnrollPlan;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 招生计划Mapper接口
 * 
 * @author YY
 * @date 2025-04-14
 */
public interface EnrollPlanMapper extends BaseMapper<EnrollPlan>
{
    /**
     * 查询招生计划
     * 
     * @param planId 招生计划主键
     * @return 招生计划
     */
    public EnrollPlan selectEnrollPlanByPlanId(String planId);

    /**
     * 查询招生计划列表
     * 
     * @param enrollPlan 招生计划
     * @return 招生计划集合
     */
    public List<EnrollPlan> selectEnrollPlanList(EnrollPlan enrollPlan);

    /**
     * 新增招生计划
     * 
     * @param enrollPlan 招生计划
     * @return 结果
     */
    public int insertEnrollPlan(EnrollPlan enrollPlan);

    /**
     * 修改招生计划
     * 
     * @param enrollPlan 招生计划
     * @return 结果
     */
    public int updateEnrollPlan(EnrollPlan enrollPlan);

    /**
     * 删除招生计划
     * 
     * @param planId 招生计划主键
     * @return 结果
     */
    public int deleteEnrollPlanByPlanId(String planId);

    /**
     * 批量删除招生计划
     * 
     * @param planIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEnrollPlanByPlanIds(String[] planIds);
}
