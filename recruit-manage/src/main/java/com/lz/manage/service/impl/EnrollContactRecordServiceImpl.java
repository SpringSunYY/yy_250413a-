package com.lz.manage.service.impl;

import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import com.lz.common.utils.StringUtils;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.common.utils.DateUtils;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lz.manage.mapper.EnrollContactRecordMapper;
import com.lz.manage.model.domain.EnrollContactRecord;
import com.lz.manage.service.IEnrollContactRecordService;
import com.lz.manage.model.dto.enrollContactRecord.EnrollContactRecordQuery;
import com.lz.manage.model.vo.enrollContactRecord.EnrollContactRecordVo;

/**
 * 考生联络记录Service业务层处理
 * 
 * @author YY
 * @date 2025-04-14
 */
@Service
public class EnrollContactRecordServiceImpl extends ServiceImpl<EnrollContactRecordMapper, EnrollContactRecord> implements IEnrollContactRecordService
{
    @Resource
    private EnrollContactRecordMapper enrollContactRecordMapper;

    //region mybatis代码
    /**
     * 查询考生联络记录
     * 
     * @param contactId 考生联络记录主键
     * @return 考生联络记录
     */
    @Override
    public EnrollContactRecord selectEnrollContactRecordByContactId(String contactId)
    {
        return enrollContactRecordMapper.selectEnrollContactRecordByContactId(contactId);
    }

    /**
     * 查询考生联络记录列表
     * 
     * @param enrollContactRecord 考生联络记录
     * @return 考生联络记录
     */
    @Override
    public List<EnrollContactRecord> selectEnrollContactRecordList(EnrollContactRecord enrollContactRecord)
    {
        return enrollContactRecordMapper.selectEnrollContactRecordList(enrollContactRecord);
    }

    /**
     * 新增考生联络记录
     * 
     * @param enrollContactRecord 考生联络记录
     * @return 结果
     */
    @Override
    public int insertEnrollContactRecord(EnrollContactRecord enrollContactRecord)
    {
        enrollContactRecord.setCreateTime(DateUtils.getNowDate());
        return enrollContactRecordMapper.insertEnrollContactRecord(enrollContactRecord);
    }

    /**
     * 修改考生联络记录
     * 
     * @param enrollContactRecord 考生联络记录
     * @return 结果
     */
    @Override
    public int updateEnrollContactRecord(EnrollContactRecord enrollContactRecord)
    {
        enrollContactRecord.setUpdateTime(DateUtils.getNowDate());
        return enrollContactRecordMapper.updateEnrollContactRecord(enrollContactRecord);
    }

    /**
     * 批量删除考生联络记录
     * 
     * @param contactIds 需要删除的考生联络记录主键
     * @return 结果
     */
    @Override
    public int deleteEnrollContactRecordByContactIds(String[] contactIds)
    {
        return enrollContactRecordMapper.deleteEnrollContactRecordByContactIds(contactIds);
    }

    /**
     * 删除考生联络记录信息
     * 
     * @param contactId 考生联络记录主键
     * @return 结果
     */
    @Override
    public int deleteEnrollContactRecordByContactId(String contactId)
    {
        return enrollContactRecordMapper.deleteEnrollContactRecordByContactId(contactId);
    }
    //endregion
    @Override
    public QueryWrapper<EnrollContactRecord> getQueryWrapper(EnrollContactRecordQuery enrollContactRecordQuery){
        QueryWrapper<EnrollContactRecord> queryWrapper = new QueryWrapper<>();
        //如果不使用params可以删除
        Map<String, Object> params = enrollContactRecordQuery.getParams();
        if (StringUtils.isNull(params)) {
            params = new HashMap<>();
        }
        String contactId = enrollContactRecordQuery.getContactId();
        queryWrapper.eq(StringUtils.isNotEmpty(contactId) ,"contact_id",contactId);

        String stuEnrollId = enrollContactRecordQuery.getStuEnrollId();
        queryWrapper.eq(StringUtils.isNotEmpty(stuEnrollId) ,"stu_enroll_id",stuEnrollId);

        String answerPeopleName = enrollContactRecordQuery.getAnswerPeopleName();
        queryWrapper.like(StringUtils.isNotEmpty(answerPeopleName) ,"answer_people_name",answerPeopleName);

        String fileUrl = enrollContactRecordQuery.getFileUrl();
        queryWrapper.eq(StringUtils.isNotEmpty(fileUrl) ,"file_url",fileUrl);

        String createBy = enrollContactRecordQuery.getCreateBy();
        queryWrapper.like(StringUtils.isNotEmpty(createBy) ,"create_by",createBy);

        Date createTime = enrollContactRecordQuery.getCreateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginCreateTime"))&&StringUtils.isNotNull(params.get("endCreateTime")),"create_time",params.get("beginCreateTime"),params.get("endCreateTime"));

        String updateBy = enrollContactRecordQuery.getUpdateBy();
        queryWrapper.like(StringUtils.isNotEmpty(updateBy) ,"update_by",updateBy);

        Date updateTime = enrollContactRecordQuery.getUpdateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginUpdateTime"))&&StringUtils.isNotNull(params.get("endUpdateTime")),"update_time",params.get("beginUpdateTime"),params.get("endUpdateTime"));

        return queryWrapper;
    }

    @Override
    public List<EnrollContactRecordVo> convertVoList(List<EnrollContactRecord> enrollContactRecordList) {
        if (StringUtils.isEmpty(enrollContactRecordList)) {
            return Collections.emptyList();
        }
        return enrollContactRecordList.stream().map(EnrollContactRecordVo::objToVo).collect(Collectors.toList());
    }

}
