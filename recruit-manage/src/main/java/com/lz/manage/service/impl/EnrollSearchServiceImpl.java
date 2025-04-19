package com.lz.manage.service.impl;

import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

import com.lz.common.utils.SecurityUtils;
import com.lz.common.utils.StringUtils;

import javax.annotation.Resource;

import com.lz.manage.model.domain.EnrollBasic;
import com.lz.manage.model.dto.enrollSearch.EnrollJoinSearch;
import com.lz.manage.model.vo.enrollSearch.EnrollJoinResultVo;
import com.lz.manage.service.IEnrollBasicService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lz.manage.mapper.EnrollSearchMapper;
import com.lz.manage.model.domain.EnrollSearch;
import com.lz.manage.service.IEnrollSearchService;
import com.lz.manage.model.dto.enrollSearch.EnrollSearchQuery;
import com.lz.manage.model.vo.enrollSearch.EnrollSearchVo;

/**
 * 考生查询Service业务层处理
 *
 * @author YY
 * @date 2025-04-14
 */
@Service
public class EnrollSearchServiceImpl extends ServiceImpl<EnrollSearchMapper, EnrollSearch> implements IEnrollSearchService {
    @Resource
    private EnrollSearchMapper enrollSearchMapper;

    @Resource
    private IEnrollBasicService enrollBasicService;


    //region mybatis代码

    /**
     * 查询考生查询
     *
     * @param stuEnrollId 考生查询主键
     * @return 考生查询
     */
    @Override
    public EnrollSearch selectEnrollSearchByStuEnrollId(String stuEnrollId) {
        return enrollSearchMapper.selectEnrollSearchByStuEnrollId(stuEnrollId);
    }

    /**
     * 查询考生查询列表
     *
     * @param enrollSearch 考生查询
     * @return 考生查询
     */
    @Override
    public List<EnrollSearch> selectEnrollSearchList(EnrollSearch enrollSearch) {
        List<EnrollSearch> enrollSearches = enrollSearchMapper.selectEnrollSearchList(enrollSearch);
        for (EnrollSearch info : enrollSearches) {
            EnrollBasic enrollBasic = enrollBasicService.selectEnrollBasicByStuEnrollId(info.getStuEnrollId());
            if (StringUtils.isNotNull(enrollBasic)) {
                info.setStuEnrollName(enrollBasic.getStuName());
            }
        }
        return enrollSearches;
    }

    /**
     * 新增考生查询
     *
     * @param enrollSearch 考生查询
     * @return 结果
     */
    @Override
    public int insertEnrollSearch(EnrollSearch enrollSearch) {
        //查询是否有此学生
        EnrollBasic enrollBasic = enrollBasicService.selectEnrollBasicByStuEnrollId(enrollSearch.getStuEnrollId());
        if (StringUtils.isNull(enrollBasic)) {
            throw new RuntimeException("该考生不存在");
        }
        //查询是否重复
        EnrollSearch search = enrollSearchMapper.selectEnrollSearchByStuEnrollId(enrollSearch.getStuEnrollId());
        if (StringUtils.isNotNull(search)) {
            throw new RuntimeException("该查询已存在");
        }
        return enrollSearchMapper.insertEnrollSearch(enrollSearch);
    }

    /**
     * 修改考生查询
     *
     * @param enrollSearch 考生查询
     * @return 结果
     */
    @Override
    public int updateEnrollSearch(EnrollSearch enrollSearch) {
        //查询是否有此学生
        EnrollBasic enrollBasic = enrollBasicService.selectEnrollBasicByStuEnrollId(enrollSearch.getStuEnrollId());
        if (StringUtils.isNull(enrollBasic)) {
            throw new RuntimeException("该考生不存在");
        }
        return this.saveOrUpdate(enrollSearch) ? 1 : 0;
    }

    /**
     * 批量删除考生查询
     *
     * @param stuEnrollIds 需要删除的考生查询主键
     * @return 结果
     */
    @Override
    public int deleteEnrollSearchByStuEnrollIds(String[] stuEnrollIds) {
        return enrollSearchMapper.deleteEnrollSearchByStuEnrollIds(stuEnrollIds);
    }

    /**
     * 删除考生查询信息
     *
     * @param stuEnrollId 考生查询主键
     * @return 结果
     */
    @Override
    public int deleteEnrollSearchByStuEnrollId(String stuEnrollId) {
        return enrollSearchMapper.deleteEnrollSearchByStuEnrollId(stuEnrollId);
    }

    //endregion
    @Override
    public QueryWrapper<EnrollSearch> getQueryWrapper(EnrollSearchQuery enrollSearchQuery) {
        QueryWrapper<EnrollSearch> queryWrapper = new QueryWrapper<>();
        //如果不使用params可以删除
        Map<String, Object> params = enrollSearchQuery.getParams();
        if (StringUtils.isNull(params)) {
            params = new HashMap<>();
        }
        String stuEnrollId = enrollSearchQuery.getStuEnrollId();
        queryWrapper.eq(StringUtils.isNotEmpty(stuEnrollId), "stu_enroll_id", stuEnrollId);

        String stuNo = enrollSearchQuery.getStuNo();
        queryWrapper.eq(StringUtils.isNotEmpty(stuNo), "stu_no", stuNo);

        String classId = enrollSearchQuery.getClassId();
        queryWrapper.eq(StringUtils.isNotEmpty(classId), "class_id", classId);

        String dormId = enrollSearchQuery.getDormId();
        queryWrapper.eq(StringUtils.isNotEmpty(dormId), "dorm_id", dormId);

        String classTeacher = enrollSearchQuery.getClassTeacher();
        queryWrapper.eq(StringUtils.isNotEmpty(classTeacher), "class_teacher", classTeacher);

        String classTeacherContact = enrollSearchQuery.getClassTeacherContact();
        queryWrapper.eq(StringUtils.isNotEmpty(classTeacherContact), "class_teacher_contact", classTeacherContact);

        String classQq = enrollSearchQuery.getClassQq();
        queryWrapper.eq(StringUtils.isNotEmpty(classQq), "class_qq", classQq);

        return queryWrapper;
    }

    @Override
    public List<EnrollSearchVo> convertVoList(List<EnrollSearch> enrollSearchList) {
        if (StringUtils.isEmpty(enrollSearchList)) {
            return Collections.emptyList();
        }
        return enrollSearchList.stream().map(EnrollSearchVo::objToVo).collect(Collectors.toList());
    }

    @Override
    public List<EnrollJoinResultVo> selectJoinEnrollList(EnrollJoinSearch enrollSearch) {
        List<EnrollJoinResultVo> enrollJoinResultVos = enrollSearchMapper.selectJoinEnrollList(enrollSearch);
        for (EnrollJoinResultVo info : enrollJoinResultVos) {
            EnrollBasic enrollBasic = enrollBasicService.selectEnrollBasicByStuEnrollId(info.getStuEnrollId());
            if (StringUtils.isNotNull(enrollBasic)) {
                info.setStuEnrollName(enrollBasic.getStuName());
            }
        }
        return enrollJoinResultVos;
    }

}
