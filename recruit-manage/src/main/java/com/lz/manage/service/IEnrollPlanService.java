package com.lz.manage.service;

import java.util.List;
import com.lz.manage.model.domain.EnrollPlan;
import com.lz.manage.model.vo.enrollPlan.EnrollPlanVo;
import com.lz.manage.model.dto.enrollPlan.EnrollPlanQuery;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
/**
 * 招生计划Service接口
 * 
 * @author YY
 * @date 2025-04-14
 */
public interface IEnrollPlanService extends IService<EnrollPlan>
{
    //region mybatis代码
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
     * 根据年度和省份ID查询招生计划，省份、专业、系部、年度
     * @author YY
     * @method  getEnrollPlanByProvince
     * @date  2025/4/20 21:27
     * @param enrollPlan
     * @return com.lz.manage.model.domain.EnrollPlan
     **/
    EnrollPlan getEnrollPlanByProvince(EnrollPlan enrollPlan);


    /**
     * 修改招生计划
     * 
     * @param enrollPlan 招生计划
     * @return 结果
     */
    public int updateEnrollPlan(EnrollPlan enrollPlan);

    /**
     * 批量删除招生计划
     * 
     * @param planIds 需要删除的招生计划主键集合
     * @return 结果
     */
    public int deleteEnrollPlanByPlanIds(String[] planIds);

    /**
     * 删除招生计划信息
     * 
     * @param planId 招生计划主键
     * @return 结果
     */
    public int deleteEnrollPlanByPlanId(String planId);
    //endregion
    /**
     * 获取查询条件
     *
     * @param enrollPlanQuery 查询条件对象
     * @return 查询条件
     */
    QueryWrapper<EnrollPlan> getQueryWrapper(EnrollPlanQuery enrollPlanQuery);

    /**
     * 转换vo
     *
     * @param enrollPlanList EnrollPlan集合
     * @return EnrollPlanVO集合
     */
    List<EnrollPlanVo> convertVoList(List<EnrollPlan> enrollPlanList);
}
