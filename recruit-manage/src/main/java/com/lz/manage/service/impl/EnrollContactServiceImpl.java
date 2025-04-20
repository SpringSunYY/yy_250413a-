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
import com.lz.manage.service.IEnrollBasicService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lz.manage.mapper.EnrollContactMapper;
import com.lz.manage.model.domain.EnrollContact;
import com.lz.manage.service.IEnrollContactService;
import com.lz.manage.model.dto.enrollContact.EnrollContactQuery;
import com.lz.manage.model.vo.enrollContact.EnrollContactVo;

/**
 * 考生联络Service业务层处理
 *
 * @author YY
 * @date 2025-04-14
 */
@Service
public class EnrollContactServiceImpl extends ServiceImpl<EnrollContactMapper, EnrollContact> implements IEnrollContactService {
    @Resource
    private EnrollContactMapper enrollContactMapper;

    @Resource
    private IEnrollBasicService enrollBasicService;

    //region mybatis代码

    /**
     * 查询考生联络
     *
     * @param contactId 考生联络主键
     * @return 考生联络
     */
    @Override
    public EnrollContact selectEnrollContactByStuEnrollId(String contactId) {
        return enrollContactMapper.selectEnrollContactByStuEnrollId(contactId);
    }

    /**
     * 查询考生联络列表
     *
     * @param enrollContact 考生联络
     * @return 考生联络
     */
    @Override
    public List<EnrollContact> selectEnrollContactList(EnrollContact enrollContact) {
        if (!SecurityUtils.hasPermi("manage:enrollContact:all")) {
            enrollContact.setCreateBy(SecurityUtils.getUsername());
        }
        List<EnrollContact> enrollContacts = enrollContactMapper.selectEnrollContactList(enrollContact);
        for (EnrollContact info : enrollContacts) {
            EnrollBasic enrollBasic = enrollBasicService.selectEnrollBasicByStuEnrollId(info.getStuEnrollId());
            if (StringUtils.isNotNull(enrollBasic)) {
                info.setStuEnrollName(enrollBasic.getStuName());
            }
        }
        return enrollContacts;
    }

    /**
     * 新增考生联络
     *
     * @param enrollContact 考生联络
     * @return 结果
     */
    @Override
    public int insertEnrollContact(EnrollContact enrollContact) {
        //校验是否开始时间到结束时间之后
        if (enrollContact.getContactStartTime().after(enrollContact.getContactEndTime())) {
            throw new RuntimeException("开始时间不能大于结束时间");
        }
        //判断是否上传了文件
        getFileInfo(enrollContact);
        //查询考生是否存在
        EnrollBasic enrollBasic = enrollBasicService.selectEnrollBasicByStuEnrollId(enrollContact.getStuEnrollId());
        if (StringUtils.isNull(enrollBasic)) {
            throw new RuntimeException("该考生不存在");
        }
        enrollContact.setTaskUserId(SecurityUtils.getUserId().toString());
        enrollContact.setTaskUserName(SecurityUtils.getUsername());
        enrollContact.setCreateBy(SecurityUtils.getUsername());
        enrollContact.setCreateTime(DateUtils.getNowDate());
        return enrollContactMapper.insertEnrollContact(enrollContact);
    }

    /**
     * 修改考生联络
     *
     * @param enrollContact 考生联络
     * @return 结果
     */
    @Override
    public int updateEnrollContact(EnrollContact enrollContact) {
        //校验是否开始时间到结束时间之后
        if (enrollContact.getContactStartTime().after(enrollContact.getContactEndTime())) {
            throw new RuntimeException("开始时间不能大于结束时间");
        }
        getFileInfo(enrollContact);
        EnrollContact contact = this.selectEnrollContactByStuEnrollId(enrollContact.getContactId());
        if (StringUtils.isNull(contact)) {
            throw new RuntimeException("该记录不存在");
        }
        if (!contact.getStuEnrollId().equals(enrollContact.getStuEnrollId())) {
            throw new RuntimeException("不能修改学生信息！！！");
        }
        enrollContact.setUpdateBy(SecurityUtils.getUsername());
        enrollContact.setUpdateTime(DateUtils.getNowDate());
        return enrollContactMapper.updateEnrollContact(enrollContact);
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
    private static void getFileInfo(EnrollContact enrollContact) {
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
     * 批量删除考生联络
     *
     * @param stuEnrollIds 需要删除的考生联络主键
     * @return 结果
     */
    @Override
    public int deleteEnrollContactByStuEnrollIds(String[] stuEnrollIds) {
        return enrollContactMapper.deleteEnrollContactByStuEnrollIds(stuEnrollIds);
    }

    /**
     * 删除考生联络信息
     *
     * @param stuEnrollId 考生联络主键
     * @return 结果
     */
    @Override
    public int deleteEnrollContactByStuEnrollId(String stuEnrollId) {
        return enrollContactMapper.deleteEnrollContactByStuEnrollId(stuEnrollId);
    }

    //endregion
    @Override
    public QueryWrapper<EnrollContact> getQueryWrapper(EnrollContactQuery enrollContactQuery) {
        QueryWrapper<EnrollContact> queryWrapper = new QueryWrapper<>();
        //如果不使用params可以删除
        Map<String, Object> params = enrollContactQuery.getParams();
        if (StringUtils.isNull(params)) {
            params = new HashMap<>();
        }
        String stuEnrollId = enrollContactQuery.getStuEnrollId();
        queryWrapper.eq(StringUtils.isNotEmpty(stuEnrollId), "stu_enroll_id", stuEnrollId);

        String taskUserName = enrollContactQuery.getTaskUserName();
        queryWrapper.like(StringUtils.isNotEmpty(taskUserName), "task_user_name", taskUserName);

        String taskUserExecute = enrollContactQuery.getTaskUserExecute();
        queryWrapper.eq(StringUtils.isNotEmpty(taskUserExecute), "task_user_execute", taskUserExecute);

        String taskLock = enrollContactQuery.getTaskLock();
        queryWrapper.eq(StringUtils.isNotEmpty(taskLock), "task_lock", taskLock);

        String answerPeopleName = enrollContactQuery.getAnswerPeopleName();
        queryWrapper.like(StringUtils.isNotEmpty(answerPeopleName), "answer_people_name", answerPeopleName);

        String answerPeopleRelation = enrollContactQuery.getAnswerPeopleRelation();
        queryWrapper.eq(StringUtils.isNotEmpty(answerPeopleRelation), "answer_people_relation", answerPeopleRelation);

        String fileUrl = enrollContactQuery.getFileUrl();
        queryWrapper.eq(StringUtils.isNotEmpty(fileUrl), "file_url", fileUrl);

        String createBy = enrollContactQuery.getCreateBy();
        queryWrapper.like(StringUtils.isNotEmpty(createBy), "create_by", createBy);

        Date createTime = enrollContactQuery.getCreateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginCreateTime")) && StringUtils.isNotNull(params.get("endCreateTime")), "create_time", params.get("beginCreateTime"), params.get("endCreateTime"));

        String updateBy = enrollContactQuery.getUpdateBy();
        queryWrapper.like(StringUtils.isNotEmpty(updateBy), "update_by", updateBy);

        Date updateTime = enrollContactQuery.getUpdateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginUpdateTime")) && StringUtils.isNotNull(params.get("endUpdateTime")), "update_time", params.get("beginUpdateTime"), params.get("endUpdateTime"));

        return queryWrapper;
    }

    @Override
    public List<EnrollContactVo> convertVoList(List<EnrollContact> enrollContactList) {
        if (StringUtils.isEmpty(enrollContactList)) {
            return Collections.emptyList();
        }
        return enrollContactList.stream().map(EnrollContactVo::objToVo).collect(Collectors.toList());
    }

}
