package com.lz.manage.model.dto.enrollNote;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import com.lz.manage.model.domain.EnrollNote;
/**
 * 通知书信息Vo对象 stu_enroll_note
 *
 * @author YY
 * @date 2025-04-14
 */
@Data
public class EnrollNoteEdit implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 考生ID */
    private String stuEnrollId;

    /** 通知书编号 */
    private String noteCode;

    /** 通知书编号-前缀 */
    private String noteCodePrefix;

    /** 通知书编号-后缀 */
    private String noteCodeSuffix;

    /** 招生年度 */
    private String planYear;

    /** 省份名称 */
    private String provinceName;

    /** 录取院校 */
    private String stuDeptName;

    /** 录取专业 */
    private String stuMajor;

    /** 学生姓名 */
    private String stuName;

    /** 是否录取 */
    private String isEnroll;

    /** 通知书防伪码 */
    private String noteSecurityCode;

    /** 通知书快递号 */
    private String noteExpCode;

    /** 通知书打印 */
    private String notePrintStatus;

    /** 通知书打印信息 */
    private String notePrintInfo;

    /** 通知书打印时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date notePrintTime;

    /** 备注 */
    private String remark;

    /**
     * 对象转封装类
     *
     * @param enrollNoteEdit 编辑对象
     * @return EnrollNote
     */
    public static EnrollNote editToObj(EnrollNoteEdit enrollNoteEdit) {
        if (enrollNoteEdit == null) {
            return null;
        }
        EnrollNote enrollNote = new EnrollNote();
        BeanUtils.copyProperties(enrollNoteEdit, enrollNote);
        return enrollNote;
    }
}
