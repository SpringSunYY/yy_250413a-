package com.lz.manage.service;

import java.util.List;
import com.lz.manage.model.domain.EnrollSearch;
import com.lz.manage.model.dto.enrollSearch.EnrollJoinSearch;
import com.lz.manage.model.vo.enrollSearch.EnrollJoinResultVo;
import com.lz.manage.model.vo.enrollSearch.EnrollSearchVo;
import com.lz.manage.model.dto.enrollSearch.EnrollSearchQuery;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
/**
 * 考生查询Service接口
 * 
 * @author YY
 * @date 2025-04-14
 */
public interface IEnrollSearchService extends IService<EnrollSearch>
{
    //region mybatis代码
    /**
     * 查询考生查询
     * 
     * @param stuEnrollId 考生查询主键
     * @return 考生查询
     */
    public EnrollSearch selectEnrollSearchByStuEnrollId(String stuEnrollId);

    /**
     * 查询考生查询列表
     * 
     * @param enrollSearch 考生查询
     * @return 考生查询集合
     */
    public List<EnrollSearch> selectEnrollSearchList(EnrollSearch enrollSearch);

    /**
     * 新增考生查询
     * 
     * @param enrollSearch 考生查询
     * @return 结果
     */
    public int insertEnrollSearch(EnrollSearch enrollSearch);

    /**
     * 修改考生查询
     * 
     * @param enrollSearch 考生查询
     * @return 结果
     */
    public int updateEnrollSearch(EnrollSearch enrollSearch);

    /**
     * 批量删除考生查询
     * 
     * @param stuEnrollIds 需要删除的考生查询主键集合
     * @return 结果
     */
    public int deleteEnrollSearchByStuEnrollIds(String[] stuEnrollIds);

    /**
     * 删除考生查询信息
     * 
     * @param stuEnrollId 考生查询主键
     * @return 结果
     */
    public int deleteEnrollSearchByStuEnrollId(String stuEnrollId);
    //endregion
    /**
     * 获取查询条件
     *
     * @param enrollSearchQuery 查询条件对象
     * @return 查询条件
     */
    QueryWrapper<EnrollSearch> getQueryWrapper(EnrollSearchQuery enrollSearchQuery);

    /**
     * 转换vo
     *
     * @param enrollSearchList EnrollSearch集合
     * @return EnrollSearchVO集合
     */
    List<EnrollSearchVo> convertVoList(List<EnrollSearch> enrollSearchList);

    /**
     * 连表查询通知书和搜索信息
     * @author YY
     * @method  selectJoinEnrollList
     * @date  2025/4/19 20:57
     * @param enrollSearch 查询条件
     * @return java.util.List<com.lz.manage.model.vo.enrollSearch.EnrollJoinResultVo>
     **/
    List<EnrollJoinResultVo> selectJoinEnrollList(EnrollJoinSearch enrollSearch);
}
