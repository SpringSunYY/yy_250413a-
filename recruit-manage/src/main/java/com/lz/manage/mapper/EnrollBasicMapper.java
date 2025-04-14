package com.lz.manage.mapper;

import java.util.List;
import com.lz.manage.model.domain.EnrollBasic;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 录取信息Mapper接口
 * 
 * @author ruoyi
 * @date 2025-04-14
 */
public interface EnrollBasicMapper extends BaseMapper<EnrollBasic>
{
    /**
     * 查询录取信息
     * 
     * @param stuEnrollId 录取信息主键
     * @return 录取信息
     */
    public EnrollBasic selectEnrollBasicByStuEnrollId(String stuEnrollId);

    /**
     * 查询录取信息列表
     * 
     * @param enrollBasic 录取信息
     * @return 录取信息集合
     */
    public List<EnrollBasic> selectEnrollBasicList(EnrollBasic enrollBasic);

    /**
     * 新增录取信息
     * 
     * @param enrollBasic 录取信息
     * @return 结果
     */
    public int insertEnrollBasic(EnrollBasic enrollBasic);

    /**
     * 修改录取信息
     * 
     * @param enrollBasic 录取信息
     * @return 结果
     */
    public int updateEnrollBasic(EnrollBasic enrollBasic);

    /**
     * 删除录取信息
     * 
     * @param stuEnrollId 录取信息主键
     * @return 结果
     */
    public int deleteEnrollBasicByStuEnrollId(String stuEnrollId);

    /**
     * 批量删除录取信息
     * 
     * @param stuEnrollIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEnrollBasicByStuEnrollIds(String[] stuEnrollIds);
}
