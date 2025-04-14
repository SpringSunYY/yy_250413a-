package com.lz.manage.mapper;

import java.util.List;
import com.lz.manage.model.domain.EnrollContactRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 考生联络记录Mapper接口
 * 
 * @author YY
 * @date 2025-04-14
 */
public interface EnrollContactRecordMapper extends BaseMapper<EnrollContactRecord>
{
    /**
     * 查询考生联络记录
     * 
     * @param contactId 考生联络记录主键
     * @return 考生联络记录
     */
    public EnrollContactRecord selectEnrollContactRecordByContactId(String contactId);

    /**
     * 查询考生联络记录列表
     * 
     * @param enrollContactRecord 考生联络记录
     * @return 考生联络记录集合
     */
    public List<EnrollContactRecord> selectEnrollContactRecordList(EnrollContactRecord enrollContactRecord);

    /**
     * 新增考生联络记录
     * 
     * @param enrollContactRecord 考生联络记录
     * @return 结果
     */
    public int insertEnrollContactRecord(EnrollContactRecord enrollContactRecord);

    /**
     * 修改考生联络记录
     * 
     * @param enrollContactRecord 考生联络记录
     * @return 结果
     */
    public int updateEnrollContactRecord(EnrollContactRecord enrollContactRecord);

    /**
     * 删除考生联络记录
     * 
     * @param contactId 考生联络记录主键
     * @return 结果
     */
    public int deleteEnrollContactRecordByContactId(String contactId);

    /**
     * 批量删除考生联络记录
     * 
     * @param contactIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEnrollContactRecordByContactIds(String[] contactIds);
}
