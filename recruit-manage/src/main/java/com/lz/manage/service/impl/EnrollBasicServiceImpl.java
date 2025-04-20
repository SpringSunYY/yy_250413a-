package com.lz.manage.service.impl;

import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

import com.lz.common.exception.ServiceException;
import com.lz.common.utils.SecurityUtils;
import com.lz.common.utils.StringUtils;

import javax.annotation.Resource;

import com.lz.common.utils.uuid.IdUtils;
import com.lz.manage.model.domain.EnrollNote;
import com.lz.manage.model.domain.EnrollPlan;
import com.lz.manage.service.IEnrollNoteService;
import com.lz.manage.service.IEnrollPlanService;
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

    @Resource
    private IEnrollPlanService enrollPlanService;

    @Resource
    private IEnrollNoteService enrollNoteService;

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
        checkMajor(enrollBasic);
        Long id = IdUtils.snowflakeId();
        enrollBasic.setStuEnrollId(id.toString());
        return enrollBasicMapper.insertEnrollBasic(enrollBasic);
    }

    private void checkMajor(EnrollBasic enrollBasic) {
        //如果省份编码、专业、学院、科类、年度存在
        if (StringUtils.isNotEmpty(enrollBasic.getEnrollSpId())) {
            EnrollPlan enrollPlan = enrollPlanService.selectEnrollPlanByPlanId(enrollBasic.getEnrollSpId());
            enrollBasic.setProvinceName(enrollPlan.getProvinceName());
            enrollBasic.setProvinceCode(enrollPlan.getProvinceCode());
            enrollBasic.setEnrollDeptName(enrollPlan.getStuDeptName());
            enrollBasic.setEnrollDeptId(enrollPlan.getStuDeptId());
            enrollBasic.setSubjectSortName(enrollPlan.getSubjectSortName());
            enrollBasic.setSubjectSort(enrollPlan.getSubjectSortId());
            enrollBasic.setEnrollSpName(enrollPlan.getSpName());
            enrollBasic.setEnrollSpId(enrollPlan.getSpId());
            enrollBasic.setEnrollYear(enrollPlan.getPlanYear());
        }
        //如果第一志愿存在
        if (StringUtils.isNotEmpty(enrollBasic.getApplySp1Id())) {
            EnrollPlan enrollPlanBySp1 = enrollPlanService.selectEnrollPlanByPlanId(enrollBasic.getApplySp1Id());
            if (StringUtils.isNull(enrollPlanBySp1)) {
                throw new ServiceException("第一志愿专业不存在");
            }
            enrollBasic.setApplySp1Id(enrollPlanBySp1.getSpId());
            enrollBasic.setApplySp1Name(enrollPlanBySp1.getSpName());
        }
        //如果第二志愿存在
        if (StringUtils.isNotEmpty(enrollBasic.getApplySp2Id())) {
            EnrollPlan enrollPlanBySp2 = enrollPlanService.selectEnrollPlanByPlanId(enrollBasic.getApplySp2Id());
            if (StringUtils.isNull(enrollPlanBySp2)) {
                throw new ServiceException("第二志愿专业不存在");
            }
            enrollBasic.setApplySp2Id(enrollPlanBySp2.getSpId());
            enrollBasic.setApplySp2Name(enrollPlanBySp2.getSpName());
        }
        //如果第三志愿存在
        if (StringUtils.isNotEmpty(enrollBasic.getApplySp3Id())) {
            EnrollPlan enrollPlanBySp3 = enrollPlanService.selectEnrollPlanByPlanId(enrollBasic.getApplySp3Id());
            if (StringUtils.isNull(enrollPlanBySp3)) {
                throw new ServiceException("第三志愿专业不存在");
            }
            enrollBasic.setApplySp3Id(enrollPlanBySp3.getSpId());
            enrollBasic.setApplySp3Name(enrollPlanBySp3.getSpName());
        }
        //如果第四志愿存在
        if (StringUtils.isNotEmpty(enrollBasic.getApplySp4Id())) {
            EnrollPlan enrollPlanBySp4 = enrollPlanService.selectEnrollPlanByPlanId(enrollBasic.getApplySp4Id());
            if (StringUtils.isNull(enrollPlanBySp4)) {
                throw new ServiceException("第四志愿专业不存在");
            }
            enrollBasic.setApplySp4Id(enrollPlanBySp4.getSpId());
            enrollBasic.setApplySp4Name(enrollPlanBySp4.getSpName());
        }
        //如果第五志愿存在
        if (StringUtils.isNotEmpty(enrollBasic.getApplySp5Id())) {
            EnrollPlan enrollPlanBySp5 = enrollPlanService.selectEnrollPlanByPlanId(enrollBasic.getApplySp5Id());
            if (StringUtils.isNull(enrollPlanBySp5)) {
                throw new ServiceException("第五志愿专业不存在");
            }
            enrollBasic.setApplySp5Id(enrollPlanBySp5.getSpId());
            enrollBasic.setApplySp5Name(enrollPlanBySp5.getSpName());
        }
        //如果第六志愿存在
        if (StringUtils.isNotEmpty(enrollBasic.getApplySp6Id())) {
            EnrollPlan enrollPlanBySp6 = enrollPlanService.selectEnrollPlanByPlanId(enrollBasic.getApplySp6Id());
            if (StringUtils.isNull(enrollPlanBySp6)) {
                throw new ServiceException("第六志愿专业不存在");
            }
            enrollBasic.setApplySp6Id(enrollPlanBySp6.getSpId());
            enrollBasic.setApplySp6Name(enrollPlanBySp6.getSpName());
        }
    }

    /**
     * 修改录取信息
     *
     * @param enrollBasic 录取信息
     * @return 结果
     */
    @Override
    public int updateEnrollBasic(EnrollBasic enrollBasic) {
        checkMajor(enrollBasic);
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
            EnrollBasic info = basicList.get(i);
            Long id = IdUtils.snowflakeId();
            info.setStuEnrollId(id.toString());

            if (StringUtils.isEmpty(info.getEnrollYear())) {
                return StringUtils.format("第{}条数据,年份不能为空", i + 1);
            }
            if (StringUtils.isEmpty(info.getExamNum())) {
                return StringUtils.format("第{}条数据,考生号不能为空", i + 1);
            }
            if (StringUtils.isEmpty(info.getStuName())) {
                return StringUtils.format("第{}条数据,姓名不能为空", i + 1);
            }
            if (StringUtils.isEmpty(info.getSubjectSort())) {
                return StringUtils.format("第{}条数据,科类不能为空", i + 1);
            }
            if (StringUtils.isEmpty(info.getProvinceCode())) {
                return StringUtils.format("第{}条数据,省份不能为空", i + 1);
            }
            if (StringUtils.isEmpty(info.getExamTicketNum())) {
                return StringUtils.format("第{}条数据,准考证号不能为空", i + 1);
            }
            if (StringUtils.isEmpty(info.getEnrollDeptId())) {
                return StringUtils.format("第{}条数据,录取学院不能为空", i + 1);
            }
            if (StringUtils.isEmpty(info.getEnrollSpId())) {
                return StringUtils.format("第{}条数据,录取专业不能为空", i + 1);
            }
            if (StringUtils.isEmpty(info.getDocStatus())) {
                return StringUtils.format("第{}条数据,档案状态不能为空", i + 1);
            }
            if (StringUtils.isEmpty(info.getSubjectSort())) {
                return StringUtils.format("第{}条数据,科类不能为空", i + 1);
            }
        }
        for (int i = 0; i < basicList.size(); i++) {
            int index = i + 1;
            EnrollBasic info = basicList.get(i);
            //查询是否有此专业
            EnrollPlan enrollPlan = new EnrollPlan();
            enrollPlan.setPlanYear(info.getEnrollYear());
            enrollPlan.setSpId(info.getEnrollSpId());
            enrollPlan.setProvinceCode(info.getProvinceCode());
            enrollPlan.setStuDeptId(info.getEnrollDeptId());
            enrollPlan.setSubjectSortId(info.getSubjectSort());
            EnrollPlan enrollPlanByProvince = enrollPlanService.getEnrollPlanByProvince(enrollPlan);
            if (StringUtils.isNull(enrollPlanByProvince)) {
                return StringUtils.format("第{}条数据,录取专业不存在", index);
            }
            info.setProvinceName(enrollPlanByProvince.getProvinceName());
            info.setProvinceCode(enrollPlanByProvince.getProvinceCode());
            info.setEnrollDeptName(enrollPlanByProvince.getStuDeptName());
            info.setEnrollDeptId(enrollPlanByProvince.getStuDeptId());
            info.setSubjectSortName(enrollPlanByProvince.getSubjectSortName());
            info.setSubjectSort(enrollPlanByProvince.getSubjectSortId());
            info.setEnrollSpName(enrollPlanByProvince.getSpName());
            info.setEnrollSpId(enrollPlanByProvince.getSpId());
            info.setEnrollYear(enrollPlanByProvince.getPlanYear());
        }
        //查询数据库是否有此数据
        List<EnrollBasic> enrollBasicList = this.list(new QueryWrapper<EnrollBasic>().in("exam_num", examNumList));
        if (StringUtils.isNotEmpty(enrollBasicList)) {
            return StringUtils.format("数据重复,考生号{}", enrollBasicList.get(0).getExamNum());
        }
        Date createTime = new Date();
        String username = SecurityUtils.getUsername();
        List<EnrollNote> enrollNotes = basicList.stream()
                .map(info -> {
                    EnrollNote enrollNote = new EnrollNote();
                    enrollNote.setStuEnrollId(info.getStuEnrollId());
                    enrollNote.setProvinceName(info.getProvinceName());
                    enrollNote.setStuDeptName(info.getEnrollDeptName());
                    enrollNote.setStuMajor(info.getEnrollSpName());
                    enrollNote.setStuName(info.getStuName());
                    enrollNote.setIsEnroll(info.getDocStatus());
                    enrollNote.setPlanYear(info.getEnrollYear());
                    enrollNote.setCreateTime(createTime);
                    enrollNote.setCreateBy(username);
                    return enrollNote;
                })
                .collect(Collectors.toList());
        this.saveBatch(basicList);
        //新增通知书
        enrollNoteService.saveOrUpdateBatch(enrollNotes);
        return StringUtils.format("新增{}条数据成功", basicList.size());
    }

}
