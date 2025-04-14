package com.lz.manage.model.dto.enrollBasic;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import com.lz.manage.model.domain.EnrollBasic;
/**
 * 录取信息Vo对象 stu_enroll_basic
 *
 * @author ruoyi
 * @date 2025-04-14
 */
@Data
public class EnrollBasicEdit implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 考生ID */
    private String stuEnrollId;

    /** 招生年度 */
    private String enrollYear;

    /** 考生号 */
    private String examNum;

    /** 准考证号 */
    private String examTicketNum;

    /** 姓名 */
    private String stuName;

    /** 科类 */
    private String subjectSort;

    /** 科类名称 */
    private String subjectSortName;

    /** 省份编码 */
    private String provinceCode;

    /** 省份名称 */
    private String provinceName;

    /** 地区代码 */
    private String areaCode;

    /** 地区名称 */
    private String areaName;

    /** 身份证号 */
    private String idCard;

    /** 性别 */
    private String gender;

    /** 出生年月 */
    private String birthDate;

    /** 民族 */
    private String nationId;

    /** 政治面貌 */
    private String politicsId;

    /** 考生类别 */
    private String stuExamSort;

    /** 中学名称 */
    private String middleSchool;

    /** 家庭电话 */
    private String homePhone;

    /** 家庭备用电话 */
    private String homePhoneRes;

    /** 家庭联系人 */
    private String homeLink;

    /** 家庭行政区划 */
    private String homeAreaCode;

    /** 家庭地址 */
    private String homeAddr;

    /** 家庭邮编 */
    private String homeZip;

    /** 考生特征 */
    private String stuFeature;

    /** 获奖情况 */
    private String stuHonor;

    /** 外语语种 */
    private String foreignLanguage;

    /** 语文成绩 */
    private BigDecimal coreChinese;

    /** 数学成绩 */
    private BigDecimal coreMath;

    /** 外语成绩 */
    private BigDecimal coreForeign;

    /** 综合成绩 */
    private BigDecimal coreSyn;

    /** 总成绩 */
    private BigDecimal coreSum;

    /** 加分 */
    private BigDecimal coreAward;

    /** 专业成绩 */
    private BigDecimal coreSpecialty;

    /** 投档成绩 */
    private String coreArchive;

    /** 位次 */
    private String coreSortNum;

    /** 录取专业ID */
    private String enrollSpId;

    /** 录取专业名称 */
    private String enrollSpName;

    /** 录取学院ID */
    private String enrollDeptId;

    /** 录取学院名称 */
    private String enrollDeptName;

    /** 投档志愿 */
    private String enrollNum;

    /** 服从调剂 */
    private String enrollAdjust;

    /** 录取批次 */
    private String enrollBatch;

    /** 档案状态 */
    private String docStatus;

    /** 学历等级 */
    private String eduLevel;

    /** 第一志愿编码 */
    private String applySp1Id;

    /** 第一志愿名称 */
    private String applySp1Name;

    /** 第二志愿编码 */
    private String applySp2Id;

    /** 第二志愿名称 */
    private String applySp2Name;

    /** 第三志愿编码 */
    private String applySp3Id;

    /** 第三志愿名称 */
    private String applySp3Name;

    /** 第四志愿编码 */
    private String applySp4Id;

    /** 第四志愿名称 */
    private String applySp4Name;

    /** 第五志愿编码 */
    private String applySp5Id;

    /** 第五志愿名称 */
    private String applySp5Name;

    /** 第六志愿编码 */
    private String applySp6Id;

    /** 第六志愿名称 */
    private String applySp6Name;

    /** 录取备注 */
    private String enrollRemarks;

    /** 导入数据标记 */
    private String dataBatchFlag;

    /**
     * 对象转封装类
     *
     * @param enrollBasicEdit 编辑对象
     * @return EnrollBasic
     */
    public static EnrollBasic editToObj(EnrollBasicEdit enrollBasicEdit) {
        if (enrollBasicEdit == null) {
            return null;
        }
        EnrollBasic enrollBasic = new EnrollBasic();
        BeanUtils.copyProperties(enrollBasicEdit, enrollBasic);
        return enrollBasic;
    }
}
