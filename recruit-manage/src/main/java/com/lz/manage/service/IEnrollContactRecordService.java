package com.lz.manage.service;

import java.util.List;
import com.lz.manage.model.domain.EnrollContactRecord;
import com.lz.manage.model.vo.enrollContactRecord.EnrollContactRecordVo;
import com.lz.manage.model.dto.enrollContactRecord.EnrollContactRecordQuery;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
/**
 * 考生联络记录Service接口
 * 
 * @author YY
 * @date 2025-04-14
 */
public interface IEnrollContactRecordService extends IService<EnrollContactRecord>
{
    //region mybatis代码
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
     * 批量删除考生联络记录
     * 
     * @param contactIds 需要删除的考生联络记录主键集合
     * @return 结果
     */
    public int deleteEnrollContactRecordByContactIds(String[] contactIds);

    /**
     * 删除考生联络记录信息
     * 
     * @param contactId 考生联络记录主键
     * @return 结果
     */
    public int deleteEnrollContactRecordByContactId(String contactId);
    //endregion
    /**
     * 获取查询条件
     *
     * @param enrollContactRecordQuery 查询条件对象
     * @return 查询条件
     */
    QueryWrapper<EnrollContactRecord> getQueryWrapper(EnrollContactRecordQuery enrollContactRecordQuery);

    /**
     * 转换vo
     *
     * @param enrollContactRecordList EnrollContactRecord集合
     * @return EnrollContactRecordVO集合
     */
    List<EnrollContactRecordVo> convertVoList(List<EnrollContactRecord> enrollContactRecordList);
}
