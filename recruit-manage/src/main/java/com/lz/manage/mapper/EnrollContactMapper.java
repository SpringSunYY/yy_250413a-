package com.lz.manage.mapper;

import java.util.List;
import com.lz.manage.model.domain.EnrollContact;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 考生联络Mapper接口
 * 
 * @author YY
 * @date 2025-04-14
 */
public interface EnrollContactMapper extends BaseMapper<EnrollContact>
{
    /**
     * 查询考生联络
     * 
     * @param contactId 考生联络主键
     * @return 考生联络
     */
    public EnrollContact selectEnrollContactByStuEnrollId(String contactId);

    /**
     * 查询考生联络列表
     * 
     * @param enrollContact 考生联络
     * @return 考生联络集合
     */
    public List<EnrollContact> selectEnrollContactList(EnrollContact enrollContact);

    /**
     * 新增考生联络
     * 
     * @param enrollContact 考生联络
     * @return 结果
     */
    public int insertEnrollContact(EnrollContact enrollContact);

    /**
     * 修改考生联络
     * 
     * @param enrollContact 考生联络
     * @return 结果
     */
    public int updateEnrollContact(EnrollContact enrollContact);

    /**
     * 删除考生联络
     * 
     * @param stuEnrollId 考生联络主键
     * @return 结果
     */
    public int deleteEnrollContactByStuEnrollId(String stuEnrollId);

    /**
     * 批量删除考生联络
     * 
     * @param stuEnrollIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEnrollContactByStuEnrollIds(String[] stuEnrollIds);
}
