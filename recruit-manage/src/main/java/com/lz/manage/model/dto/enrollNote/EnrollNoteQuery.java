package com.lz.manage.model.dto.enrollNote;

import java.util.Map;
import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.BeanUtils;
import com.baomidou.mybatisplus.annotation.TableField;
import com.lz.manage.model.domain.EnrollNote;
/**
 * 通知书信息Query对象 stu_enroll_note
 *
 * @author YY
 * @date 2025-04-14
 */
@Data
public class EnrollNoteQuery implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 考生ID */
    private String stuEnrollId;

    /** 通知书编号 */
    private String noteCode;

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

    /** 通知书打印 */
    private String notePrintStatus;

    /** 通知书打印信息 */
    private String notePrintInfo;

    /** 创建者 */
    private String createBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;

    /** 请求参数 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private Map<String, Object> params;

    /**
     * 对象转封装类
     *
     * @param enrollNoteQuery 查询对象
     * @return EnrollNote
     */
    public static EnrollNote queryToObj(EnrollNoteQuery enrollNoteQuery) {
        if (enrollNoteQuery == null) {
            return null;
        }
        EnrollNote enrollNote = new EnrollNote();
        BeanUtils.copyProperties(enrollNoteQuery, enrollNote);
        return enrollNote;
    }
}
