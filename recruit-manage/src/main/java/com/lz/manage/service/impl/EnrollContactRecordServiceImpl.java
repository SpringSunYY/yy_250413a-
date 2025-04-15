package com.lz.manage.service.impl;

import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

import com.lz.common.utils.SecurityUtils;
import com.lz.common.utils.StringUtils;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.common.utils.DateUtils;

import javax.annotation.Resource;

import com.lz.common.utils.file.FileUtils;
import com.lz.common.utils.uuid.IdUtils;
import com.lz.manage.model.domain.EnrollBasic;
import com.lz.manage.model.domain.EnrollContact;
import com.lz.manage.service.IEnrollBasicService;
import com.lz.manage.service.IEnrollContactService;
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
public class EnrollContactRecordServiceImpl extends ServiceImpl<EnrollContactRecordMapper, EnrollContactRecord> implements IEnrollContactRecordService {
    @Resource
    private EnrollContactRecordMapper enrollContactRecordMapper;

    @Resource
    private IEnrollContactService enrollContactService;

    @Resource
    private IEnrollBasicService enrollBasicService;


    //region mybatis代码

    /**
     * 查询考生联络记录
     *
     * @param contactId 考生联络记录主键
     * @return 考生联络记录
     */
    @Override
    public EnrollContactRecord selectEnrollContactRecordByContactId(String contactId) {
        return enrollContactRecordMapper.selectEnrollContactRecordByRecordId(contactId);
    }

    /**
     * 查询考生联络记录列表
     *
     * @param enrollContactRecord 考生联络记录
     * @return 考生联络记录
     */
    @Override
    public List<EnrollContactRecord> selectEnrollContactRecordList(EnrollContactRecord enrollContactRecord) {
        List<EnrollContactRecord> enrollContactRecords = enrollContactRecordMapper.selectEnrollContactRecordList(enrollContactRecord);
        for (EnrollContactRecord info : enrollContactRecords) {
            EnrollBasic enrollBasic = enrollBasicService.selectEnrollBasicByStuEnrollId(info.getStuEnrollId());
            if (StringUtils.isNotNull(enrollBasic)) {
                info.setStuEnrollName(enrollBasic.getStuName());
            }
        }
        return enrollContactRecords;
    }

    /**
     * 新增考生联络记录
     *
     * @param enrollContactRecord 考生联络记录
     * @return 结果
     */
    @Override
    public int insertEnrollContactRecord(EnrollContactRecord enrollContactRecord) {
        //校验是否开始时间到结束时间之后
        if (enrollContactRecord.getContactStartTime().after(enrollContactRecord.getContactEndTime())) {
            throw new RuntimeException("开始时间不能大于结束时间");
        }
        //判断是否上传了文件
        getFileInfo(enrollContactRecord);
        //查询联络是否存在
        EnrollContact enrollContact = enrollContactService.selectEnrollContactByStuEnrollId(enrollContactRecord.getContactId());
        if (StringUtils.isNull(enrollContact)) {
            throw new RuntimeException("联络不存在！！！");
        }
        enrollContactRecord.setCreateBy(SecurityUtils.getUsername());
        enrollContactRecord.setRecordId(IdUtils.snowflakeId().toString());
        enrollContactRecord.setStuEnrollId(enrollContact.getStuEnrollId());
        enrollContactRecord.setCreateTime(DateUtils.getNowDate());
        return enrollContactRecordMapper.insertEnrollContactRecord(enrollContactRecord);
    }

    /**
     * description: 获取文件信息
     * author: YY
     * method: getFileInfo
     * date: 2025/4/15 08:48
     * param:
     * param: enrollContact
     * return: void
     **/
    private static void getFileInfo(EnrollContactRecord enrollContact) {
        //判断是否上传了文件
        if (StringUtils.isNotEmpty(enrollContact.getFileUrl())) {
            //获取文件名
            String fileName = FileUtils.getName(enrollContact.getFileUrl());
            enrollContact.setFileName(fileName);
            if (StringUtils.isNotEmpty(fileName)) {
                //获取文件后缀名
                String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
                enrollContact.setFileSuffix(suffix);
            }
        }
    }

    /**
     * 修改考生联络记录
     *
     * @param enrollContactRecord 考生联络记录
     * @return 结果
     */
    @Override
    public int updateEnrollContactRecord(EnrollContactRecord enrollContactRecord) {
        //校验是否开始时间到结束时间之后
        if (enrollContactRecord.getContactStartTime().after(enrollContactRecord.getContactEndTime())) {
            throw new RuntimeException("开始时间不能大于结束时间");
        }
        //判断是否上传了文件
        getFileInfo(enrollContactRecord);
        //查询联络是否存在
        EnrollContact enrollContact = enrollContactService.selectEnrollContactByStuEnrollId(enrollContactRecord.getContactId());
        if (StringUtils.isNull(enrollContact)) {
            throw new RuntimeException("联络不存在！！！");
        }
        enrollContactRecord.setUpdateBy(SecurityUtils.getUsername());
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
    public int deleteEnrollContactRecordByContactIds(String[] contactIds) {
        return enrollContactRecordMapper.deleteEnrollContactRecordByContactIds(contactIds);
    }

    /**
     * 删除考生联络记录信息
     *
     * @param contactId 考生联络记录主键
     * @return 结果
     */
    @Override
    public int deleteEnrollContactRecordByContactId(String contactId) {
        return enrollContactRecordMapper.deleteEnrollContactRecordByContactId(contactId);
    }

    //endregion
    @Override
    public QueryWrapper<EnrollContactRecord> getQueryWrapper(EnrollContactRecordQuery enrollContactRecordQuery) {
        QueryWrapper<EnrollContactRecord> queryWrapper = new QueryWrapper<>();
        //如果不使用params可以删除
        Map<String, Object> params = enrollContactRecordQuery.getParams();
        if (StringUtils.isNull(params)) {
            params = new HashMap<>();
        }
        String contactId = enrollContactRecordQuery.getContactId();
        queryWrapper.eq(StringUtils.isNotEmpty(contactId), "contact_id", contactId);

        String stuEnrollId = enrollContactRecordQuery.getStuEnrollId();
        queryWrapper.eq(StringUtils.isNotEmpty(stuEnrollId), "stu_enroll_id", stuEnrollId);

        String answerPeopleName = enrollContactRecordQuery.getAnswerPeopleName();
        queryWrapper.like(StringUtils.isNotEmpty(answerPeopleName), "answer_people_name", answerPeopleName);

        String fileUrl = enrollContactRecordQuery.getFileUrl();
        queryWrapper.eq(StringUtils.isNotEmpty(fileUrl), "file_url", fileUrl);

        String createBy = enrollContactRecordQuery.getCreateBy();
        queryWrapper.like(StringUtils.isNotEmpty(createBy), "create_by", createBy);

        Date createTime = enrollContactRecordQuery.getCreateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginCreateTime")) && StringUtils.isNotNull(params.get("endCreateTime")), "create_time", params.get("beginCreateTime"), params.get("endCreateTime"));

        String updateBy = enrollContactRecordQuery.getUpdateBy();
        queryWrapper.like(StringUtils.isNotEmpty(updateBy), "update_by", updateBy);

        Date updateTime = enrollContactRecordQuery.getUpdateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginUpdateTime")) && StringUtils.isNotNull(params.get("endUpdateTime")), "update_time", params.get("beginUpdateTime"), params.get("endUpdateTime"));

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
