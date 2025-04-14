package com.lz.manage.service.impl;

import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

import com.lz.common.utils.StringUtils;

import javax.annotation.Resource;

import com.lz.common.utils.uuid.IdUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lz.manage.mapper.EnrollBasicMapper;
import com.lz.manage.model.domain.EnrollBasic;
import com.lz.manage.service.IEnrollBasicService;
import com.lz.manage.model.dto.enrollBasic.EnrollBasicQuery;
import com.lz.manage.model.vo.enrollBasic.EnrollBasicVo;
import org.springframework.transaction.annotation.Transactional;

/**
 * 录取信息Service业务层处理
 *
 * @author ruoyi
 * @date 2025-04-14
 */
@Service
public class EnrollBasicServiceImpl extends ServiceImpl<EnrollBasicMapper, EnrollBasic> implements IEnrollBasicService {
    @Resource
    private EnrollBasicMapper enrollBasicMapper;

    //region mybatis代码

    /**
     * 查询录取信息
     *
     * @param stuEnrollId 录取信息主键
     * @return 录取信息
     */
    @Override
    public EnrollBasic selectEnrollBasicByStuEnrollId(String stuEnrollId) {
        return enrollBasicMapper.selectEnrollBasicByStuEnrollId(stuEnrollId);
    }

    /**
     * 查询录取信息列表
     *
     * @param enrollBasic 录取信息
     * @return 录取信息
     */
    @Override
    public List<EnrollBasic> selectEnrollBasicList(EnrollBasic enrollBasic) {
        return enrollBasicMapper.selectEnrollBasicList(enrollBasic);
    }

    /**
     * 新增录取信息
     *
     * @param enrollBasic 录取信息
     * @return 结果
     */
    @Override
    public int insertEnrollBasic(EnrollBasic enrollBasic) {
        Long id = IdUtils.snowflakeId();
        enrollBasic.setStuEnrollId(id.toString());
        return enrollBasicMapper.insertEnrollBasic(enrollBasic);
    }

    /**
     * 修改录取信息
     *
     * @param enrollBasic 录取信息
     * @return 结果
     */
    @Override
    public int updateEnrollBasic(EnrollBasic enrollBasic) {
        return enrollBasicMapper.updateEnrollBasic(enrollBasic);
    }

    /**
     * 批量删除录取信息
     *
     * @param stuEnrollIds 需要删除的录取信息主键
     * @return 结果
     */
    @Override
    public int deleteEnrollBasicByStuEnrollIds(String[] stuEnrollIds) {
        return enrollBasicMapper.deleteEnrollBasicByStuEnrollIds(stuEnrollIds);
    }

    /**
     * 删除录取信息信息
     *
     * @param stuEnrollId 录取信息主键
     * @return 结果
     */
    @Override
    public int deleteEnrollBasicByStuEnrollId(String stuEnrollId) {
        return enrollBasicMapper.deleteEnrollBasicByStuEnrollId(stuEnrollId);
    }

    //endregion
    @Override
    public QueryWrapper<EnrollBasic> getQueryWrapper(EnrollBasicQuery enrollBasicQuery) {
        QueryWrapper<EnrollBasic> queryWrapper = new QueryWrapper<>();
        //如果不使用params可以删除
        Map<String, Object> params = enrollBasicQuery.getParams();
        if (StringUtils.isNull(params)) {
            params = new HashMap<>();
        }
        String stuEnrollId = enrollBasicQuery.getStuEnrollId();
        queryWrapper.eq(StringUtils.isNotEmpty(stuEnrollId), "stu_enroll_id", stuEnrollId);

        String enrollYear = enrollBasicQuery.getEnrollYear();
        queryWrapper.eq(StringUtils.isNotEmpty(enrollYear), "enroll_year", enrollYear);

        String examNum = enrollBasicQuery.getExamNum();
        queryWrapper.eq(StringUtils.isNotEmpty(examNum), "exam_num", examNum);

        String examTicketNum = enrollBasicQuery.getExamTicketNum();
        queryWrapper.eq(StringUtils.isNotEmpty(examTicketNum), "exam_ticket_num", examTicketNum);

        String stuName = enrollBasicQuery.getStuName();
        queryWrapper.like(StringUtils.isNotEmpty(stuName), "stu_name", stuName);

        String provinceName = enrollBasicQuery.getProvinceName();
        queryWrapper.like(StringUtils.isNotEmpty(provinceName), "province_name", provinceName);

        String idCard = enrollBasicQuery.getIdCard();
        queryWrapper.eq(StringUtils.isNotEmpty(idCard), "id_card", idCard);

        String nationId = enrollBasicQuery.getNationId();
        queryWrapper.eq(StringUtils.isNotEmpty(nationId), "nation_id", nationId);

        String politicsId = enrollBasicQuery.getPoliticsId();
        queryWrapper.eq(StringUtils.isNotEmpty(politicsId), "politics_id", politicsId);

        String stuExamSort = enrollBasicQuery.getStuExamSort();
        queryWrapper.eq(StringUtils.isNotEmpty(stuExamSort), "stu_exam_sort", stuExamSort);

        String homePhone = enrollBasicQuery.getHomePhone();
        queryWrapper.eq(StringUtils.isNotEmpty(homePhone), "home_phone", homePhone);

        String foreignLanguage = enrollBasicQuery.getForeignLanguage();
        queryWrapper.eq(StringUtils.isNotEmpty(foreignLanguage), "foreign_language", foreignLanguage);

        String enrollSpName = enrollBasicQuery.getEnrollSpName();
        queryWrapper.like(StringUtils.isNotEmpty(enrollSpName), "enroll_sp_name", enrollSpName);

        String enrollDeptName = enrollBasicQuery.getEnrollDeptName();
        queryWrapper.like(StringUtils.isNotEmpty(enrollDeptName), "enroll_dept_name", enrollDeptName);

        String enrollNum = enrollBasicQuery.getEnrollNum();
        queryWrapper.eq(StringUtils.isNotEmpty(enrollNum), "enroll_num", enrollNum);

        String enrollAdjust = enrollBasicQuery.getEnrollAdjust();
        queryWrapper.eq(StringUtils.isNotEmpty(enrollAdjust), "enroll_adjust", enrollAdjust);

        String enrollBatch = enrollBasicQuery.getEnrollBatch();
        queryWrapper.eq(StringUtils.isNotEmpty(enrollBatch), "enroll_batch", enrollBatch);

        String docStatus = enrollBasicQuery.getDocStatus();
        queryWrapper.eq(StringUtils.isNotEmpty(docStatus), "doc_status", docStatus);

        String eduLevel = enrollBasicQuery.getEduLevel();
        queryWrapper.eq(StringUtils.isNotEmpty(eduLevel), "edu_level", eduLevel);

        String dataBatchFlag = enrollBasicQuery.getDataBatchFlag();
        queryWrapper.eq(StringUtils.isNotEmpty(dataBatchFlag), "data_batch_flag", dataBatchFlag);

        return queryWrapper;
    }

    @Override
    public List<EnrollBasicVo> convertVoList(List<EnrollBasic> enrollBasicList) {
        if (StringUtils.isEmpty(enrollBasicList)) {
            return Collections.emptyList();
        }
        return enrollBasicList.stream().map(EnrollBasicVo::objToVo).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public String importEnrollBasic(List<EnrollBasic> basicList) {
        //校验数据是否为空
        if (StringUtils.isEmpty(basicList)) {
            return "数据为空";
        }
        //校验数据是否重复考生号
        Set<String> examNumList = basicList.stream().map(EnrollBasic::getExamNum).collect(Collectors.toSet());
        if (examNumList.size() != basicList.size()) {
            return "数据重复,请检查";
        }

        //生成数据id
        for (int i = 0; i < basicList.size(); i++) {
            EnrollBasic enrollBasic = basicList.get(i);
            Long id = IdUtils.snowflakeId();
            enrollBasic.setStuEnrollId(id.toString());
        }
        //查询数据库是否有此数据
        List<EnrollBasic> enrollBasicList = this.list(new QueryWrapper<EnrollBasic>().in("exam_num", examNumList));
        if (StringUtils.isNotEmpty(enrollBasicList)) {
            return StringUtils.format("数据重复,考生号{}", enrollBasicList.get(0).getExamNum());
        }
        this.saveBatch(basicList);
        return StringUtils.format("新增{}条数据成功", basicList.size());
    }

}
