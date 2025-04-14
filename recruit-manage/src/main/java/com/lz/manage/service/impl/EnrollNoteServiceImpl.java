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
public class EnrollNoteServiceImpl extends ServiceImpl<EnrollNoteMapper, EnrollNote> implements IEnrollNoteService
{
    @Resource
    private EnrollNoteMapper enrollNoteMapper;

    //region mybatis代码
    /**
     * 查询通知书信息
     * 
     * @param stuEnrollId 通知书信息主键
     * @return 通知书信息
     */
    @Override
    public EnrollNote selectEnrollNoteByStuEnrollId(String stuEnrollId)
    {
        return enrollNoteMapper.selectEnrollNoteByStuEnrollId(stuEnrollId);
    }

    /**
     * 查询通知书信息列表
     * 
     * @param enrollNote 通知书信息
     * @return 通知书信息
     */
    @Override
    public List<EnrollNote> selectEnrollNoteList(EnrollNote enrollNote)
    {
        return enrollNoteMapper.selectEnrollNoteList(enrollNote);
    }

    /**
     * 新增通知书信息
     * 
     * @param enrollNote 通知书信息
     * @return 结果
     */
    @Override
    public int insertEnrollNote(EnrollNote enrollNote)
    {
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
    public int updateEnrollNote(EnrollNote enrollNote)
    {
        enrollNote.setUpdateTime(DateUtils.getNowDate());
        return enrollNoteMapper.updateEnrollNote(enrollNote);
    }

    /**
     * 批量删除通知书信息
     * 
     * @param stuEnrollIds 需要删除的通知书信息主键
     * @return 结果
     */
    @Override
    public int deleteEnrollNoteByStuEnrollIds(String[] stuEnrollIds)
    {
        return enrollNoteMapper.deleteEnrollNoteByStuEnrollIds(stuEnrollIds);
    }

    /**
     * 删除通知书信息信息
     * 
     * @param stuEnrollId 通知书信息主键
     * @return 结果
     */
    @Override
    public int deleteEnrollNoteByStuEnrollId(String stuEnrollId)
    {
        return enrollNoteMapper.deleteEnrollNoteByStuEnrollId(stuEnrollId);
    }
    //endregion
    @Override
    public QueryWrapper<EnrollNote> getQueryWrapper(EnrollNoteQuery enrollNoteQuery){
        QueryWrapper<EnrollNote> queryWrapper = new QueryWrapper<>();
        //如果不使用params可以删除
        Map<String, Object> params = enrollNoteQuery.getParams();
        if (StringUtils.isNull(params)) {
            params = new HashMap<>();
        }
        String stuEnrollId = enrollNoteQuery.getStuEnrollId();
        queryWrapper.eq(StringUtils.isNotEmpty(stuEnrollId) ,"stu_enroll_id",stuEnrollId);

        String noteCode = enrollNoteQuery.getNoteCode();
        queryWrapper.eq(StringUtils.isNotEmpty(noteCode) ,"note_code",noteCode);

        String planYear = enrollNoteQuery.getPlanYear();
        queryWrapper.eq(StringUtils.isNotEmpty(planYear) ,"plan_year",planYear);

        String provinceName = enrollNoteQuery.getProvinceName();
        queryWrapper.like(StringUtils.isNotEmpty(provinceName) ,"province_name",provinceName);

        String stuDeptName = enrollNoteQuery.getStuDeptName();
        queryWrapper.like(StringUtils.isNotEmpty(stuDeptName) ,"stu_dept_name",stuDeptName);

        String stuMajor = enrollNoteQuery.getStuMajor();
        queryWrapper.eq(StringUtils.isNotEmpty(stuMajor) ,"stu_major",stuMajor);

        String stuName = enrollNoteQuery.getStuName();
        queryWrapper.like(StringUtils.isNotEmpty(stuName) ,"stu_name",stuName);

        String isEnroll = enrollNoteQuery.getIsEnroll();
        queryWrapper.eq(StringUtils.isNotEmpty(isEnroll) ,"is_enroll",isEnroll);

        String notePrintStatus = enrollNoteQuery.getNotePrintStatus();
        queryWrapper.eq(StringUtils.isNotEmpty(notePrintStatus) ,"note_print_status",notePrintStatus);

        String notePrintInfo = enrollNoteQuery.getNotePrintInfo();
        queryWrapper.eq(StringUtils.isNotEmpty(notePrintInfo) ,"note_print_info",notePrintInfo);

        String createBy = enrollNoteQuery.getCreateBy();
        queryWrapper.like(StringUtils.isNotEmpty(createBy) ,"create_by",createBy);

        Date createTime = enrollNoteQuery.getCreateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginCreateTime"))&&StringUtils.isNotNull(params.get("endCreateTime")),"create_time",params.get("beginCreateTime"),params.get("endCreateTime"));

        String updateBy = enrollNoteQuery.getUpdateBy();
        queryWrapper.like(StringUtils.isNotEmpty(updateBy) ,"update_by",updateBy);

        Date updateTime = enrollNoteQuery.getUpdateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginUpdateTime"))&&StringUtils.isNotNull(params.get("endUpdateTime")),"update_time",params.get("beginUpdateTime"),params.get("endUpdateTime"));

        return queryWrapper;
    }

    @Override
    public List<EnrollNoteVo> convertVoList(List<EnrollNote> enrollNoteList) {
        if (StringUtils.isEmpty(enrollNoteList)) {
            return Collections.emptyList();
        }
        return enrollNoteList.stream().map(EnrollNoteVo::objToVo).collect(Collectors.toList());
    }

}
