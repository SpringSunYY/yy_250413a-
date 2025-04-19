package com.lz.manage.mapper;

import java.util.List;

import com.lz.manage.model.domain.EnrollSearch;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lz.manage.model.dto.enrollSearch.EnrollJoinSearch;
import com.lz.manage.model.vo.enrollSearch.EnrollJoinResultVo;

/**
 * 考生查询Mapper接口
 *
 * @author YY
 * @date 2025-04-14
 */
public interface EnrollSearchMapper extends BaseMapper<EnrollSearch> {
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
     * 删除考生查询
     *
     * @param stuEnrollId 考生查询主键
     * @return 结果
     */
    public int deleteEnrollSearchByStuEnrollId(String stuEnrollId);

    /**
     * 批量删除考生查询
     *
     * @param stuEnrollIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEnrollSearchByStuEnrollIds(String[] stuEnrollIds);

    /**
     * 连表查询通知书和搜索信息
     *
     * @param enrollJoinSearch
     * @return java.util.List<com.lz.manage.model.vo.enrollSearch.EnrollJoinResultVo>
     * @author YY
     * @method selectJoinEnrollList
     * @date 2025/4/19 20:55
     **/
    public List<EnrollJoinResultVo> selectJoinEnrollList(EnrollJoinSearch enrollJoinSearch);
}
