package com.lz.manage.service.impl;

import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lz.common.exception.ServiceException;
import com.lz.common.utils.SecurityUtils;
import com.lz.common.utils.StringUtils;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.common.utils.DateUtils;

import javax.annotation.Resource;

import com.lz.manage.model.domain.EnrollBasic;
import com.lz.manage.service.IEnrollBasicService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lz.manage.mapper.EnrollNoteMapper;
import com.lz.manage.model.domain.EnrollNote;
import com.lz.manage.service.IEnrollNoteService;
import com.lz.manage.model.dto.enrollNote.EnrollNoteQuery;
import com.lz.manage.model.vo.enrollNote.EnrollNoteVo;

/**
 * 通知书信息Service业务层处理
 *
 * @author YY
 * @date 2025-04-14
 */
@Service
public class EnrollNoteServiceImpl extends ServiceImpl<EnrollNoteMapper, EnrollNote> implements IEnrollNoteService {
    @Resource
    private EnrollNoteMapper enrollNoteMapper;

    @Resource
    private IEnrollBasicService enrollBasicService;

    //region mybatis代码

    /**
     * 查询通知书信息
     *
     * @param stuEnrollId 通知书信息主键
     * @return 通知书信息
     */
    @Override
    public EnrollNote selectEnrollNoteByStuEnrollId(String stuEnrollId) {
        return enrollNoteMapper.selectEnrollNoteByStuEnrollId(stuEnrollId);
    }

    /**
     * 查询通知书信息列表
     *
     * @param enrollNote 通知书信息
     * @return 通知书信息
     */
    @Override
    public List<EnrollNote> selectEnrollNoteList(EnrollNote enrollNote) {
        List<EnrollNote> enrollNotes = enrollNoteMapper.selectEnrollNoteList(enrollNote);
        for (EnrollNote info : enrollNotes) {
            EnrollBasic enrollBasic = enrollBasicService.selectEnrollBasicByStuEnrollId(info.getStuEnrollId());
            if (StringUtils.isNotNull(enrollBasic)) {
                info.setStuEnrollName(enrollBasic.getStuName());
            }
        }
        return enrollNotes;
    }

    /**
     * 新增通知书信息
     *
     * @param enrollNote 通知书信息
     * @return 结果
     */
    @Override
    public int insertEnrollNote(EnrollNote enrollNote) {
        //查询是否有此学生
        checkStudent(enrollNote);
        enrollNote.setCreateTime(DateUtils.getNowDate());
        return enrollNoteMapper.insertEnrollNote(enrollNote);
    }

    /**
     * 修改通知书信息
     *
     * @param enrollNote 通知书信息
     * @return 结果
     */
    @Override
    public int updateEnrollNote(EnrollNote enrollNote) {
        //校验学生
        checkStudent(enrollNote);
        //查询是否存在信息
        EnrollNote old = enrollNoteMapper.selectEnrollNoteByStuEnrollId(enrollNote.getStuEnrollId());
        if (StringUtils.isNull(old)) {
            enrollNote.setCreateBy(SecurityUtils.getUsername());
            enrollNote.setCreateTime(DateUtils.getNowDate());
        }
        enrollNote.setUpdateBy(SecurityUtils.getUsername());
        enrollNote.setUpdateTime(DateUtils.getNowDate());
        return this.saveOrUpdate(enrollNote) ? 1 : 0;
    }

    private void checkStudent(EnrollNote enrollNote) {
        //查询是否有此学生
        EnrollBasic enrollBasic = enrollBasicService.selectEnrollBasicByStuEnrollId(enrollNote.getStuEnrollId());
        if (StringUtils.isNull(enrollBasic)) {
            throw new RuntimeException("该考生不存在");
        }
        enrollNote.setStuName(enrollBasic.getStuName());
        enrollNote.setIsEnroll(enrollBasic.getDocStatus());
        enrollNote.setPlanYear(enrollBasic.getEnrollYear());
        enrollNote.setProvinceName(enrollBasic.getProvinceName());
        enrollNote.setStuDeptName(enrollBasic.getEnrollDeptName());
        enrollNote.setStuMajor(enrollBasic.getEnrollSpName());
    }

    /**
     * 批量删除通知书信息
     *
     * @param stuEnrollIds 需要删除的通知书信息主键
     * @return 结果
     */
    @Override
    public int deleteEnrollNoteByStuEnrollIds(String[] stuEnrollIds) {
        return enrollNoteMapper.deleteEnrollNoteByStuEnrollIds(stuEnrollIds);
    }

    /**
     * 删除通知书信息信息
     *
     * @param stuEnrollId 通知书信息主键
     * @return 结果
     */
    @Override
    public int deleteEnrollNoteByStuEnrollId(String stuEnrollId) {
        return enrollNoteMapper.deleteEnrollNoteByStuEnrollId(stuEnrollId);
    }

    //endregion
    @Override
    public QueryWrapper<EnrollNote> getQueryWrapper(EnrollNoteQuery enrollNoteQuery) {
        QueryWrapper<EnrollNote> queryWrapper = new QueryWrapper<>();
        //如果不使用params可以删除
        Map<String, Object> params = enrollNoteQuery.getParams();
        if (StringUtils.isNull(params)) {
            params = new HashMap<>();
        }
        String stuEnrollId = enrollNoteQuery.getStuEnrollId();
        queryWrapper.eq(StringUtils.isNotEmpty(stuEnrollId), "stu_enroll_id", stuEnrollId);

        String noteCode = enrollNoteQuery.getNoteCode();
        queryWrapper.eq(StringUtils.isNotEmpty(noteCode), "note_code", noteCode);

        String planYear = enrollNoteQuery.getPlanYear();
        queryWrapper.eq(StringUtils.isNotEmpty(planYear), "plan_year", planYear);

        String provinceName = enrollNoteQuery.getProvinceName();
        queryWrapper.like(StringUtils.isNotEmpty(provinceName), "province_name", provinceName);

        String stuDeptName = enrollNoteQuery.getStuDeptName();
        queryWrapper.like(StringUtils.isNotEmpty(stuDeptName), "stu_dept_name", stuDeptName);

        String stuMajor = enrollNoteQuery.getStuMajor();
        queryWrapper.eq(StringUtils.isNotEmpty(stuMajor), "stu_major", stuMajor);

        String stuName = enrollNoteQuery.getStuName();
        queryWrapper.like(StringUtils.isNotEmpty(stuName), "stu_name", stuName);

        String isEnroll = enrollNoteQuery.getIsEnroll();
        queryWrapper.eq(StringUtils.isNotEmpty(isEnroll), "is_enroll", isEnroll);

        String notePrintStatus = enrollNoteQuery.getNotePrintStatus();
        queryWrapper.eq(StringUtils.isNotEmpty(notePrintStatus), "note_print_status", notePrintStatus);

        String notePrintInfo = enrollNoteQuery.getNotePrintInfo();
        queryWrapper.eq(StringUtils.isNotEmpty(notePrintInfo), "note_print_info", notePrintInfo);

        String createBy = enrollNoteQuery.getCreateBy();
        queryWrapper.like(StringUtils.isNotEmpty(createBy), "create_by", createBy);

        Date createTime = enrollNoteQuery.getCreateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginCreateTime")) && StringUtils.isNotNull(params.get("endCreateTime")), "create_time", params.get("beginCreateTime"), params.get("endCreateTime"));

        String updateBy = enrollNoteQuery.getUpdateBy();
        queryWrapper.like(StringUtils.isNotEmpty(updateBy), "update_by", updateBy);

        Date updateTime = enrollNoteQuery.getUpdateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginUpdateTime")) && StringUtils.isNotNull(params.get("endUpdateTime")), "update_time", params.get("beginUpdateTime"), params.get("endUpdateTime"));

        return queryWrapper;
    }

    @Override
    public List<EnrollNoteVo> convertVoList(List<EnrollNote> enrollNoteList) {
        if (StringUtils.isEmpty(enrollNoteList)) {
            return Collections.emptyList();
        }
        return enrollNoteList.stream().map(EnrollNoteVo::objToVo).collect(Collectors.toList());
    }

    @Override
    public EnrollNote stuQuery(EnrollBasic enrollBasic) {
        String stuEnrollId = "";
        try {
            EnrollBasic basic = enrollBasicService.getOne(new LambdaQueryWrapper<EnrollBasic>()
                    .eq(EnrollBasic::getStuName, enrollBasic.getStuName())
                    .eq(EnrollBasic::getExamNum, enrollBasic.getExamNum())
                    .eq(EnrollBasic::getIdCard, enrollBasic.getIdCard()));
            stuEnrollId = basic.getStuEnrollId();
        } catch (Exception e) {
            throw new ServiceException("查询失败，没有此学生");
        }
        return enrollNoteMapper.selectEnrollNoteByStuEnrollId(stuEnrollId);
    }

    @Override
    public String importEnrollNode(List<EnrollNote> basicList) {
        // 校验导入数据
        if (StringUtils.isEmpty(basicList)) {
            return "数据为空";
        }
        //判断数据是否重复
        Set<String> examNumList = basicList.stream().map(EnrollNote::getStuEnrollId).collect(Collectors.toSet());
        if (examNumList.size() != basicList.size()) {
            return "数据重复,请检查";
        }
        for (int i = 0; i < basicList.size(); i++) {
            int index = i + 1;
            EnrollNote info = basicList.get(i);
            if (StringUtils.isNull(info.getStuEnrollId())) {
                return "第" + index + "行,考生ID不能为空";
            }
        }
        Date nowDate = DateUtils.getNowDate();
        String username = SecurityUtils.getUsername();
        for (int i = 0; i < basicList.size(); i++) {
            int index = i + 1;
            EnrollNote info = basicList.get(i);
            EnrollNote enrollNote = this.selectEnrollNoteByStuEnrollId(info.getStuEnrollId());
            if (StringUtils.isNotNull(enrollNote)) {
                return "第" + index + "行,考生ID已存在";
            }
            EnrollBasic enrollBasic = enrollBasicService.selectEnrollBasicByStuEnrollId(info.getStuEnrollId());
            info.setStuName(enrollBasic.getStuName());
            info.setIsEnroll(enrollBasic.getDocStatus());
            info.setPlanYear(enrollBasic.getEnrollYear());
            info.setProvinceName(enrollBasic.getProvinceName());
            info.setStuDeptName(enrollBasic.getEnrollDeptName());
            info.setStuMajor(enrollBasic.getEnrollSpName());
            info.setCreateBy(username);
            info.setCreateTime(nowDate);
        }
        this.saveBatch(basicList);
        return StringUtils.format("新增{}条数据成功", basicList.size());
    }

}
