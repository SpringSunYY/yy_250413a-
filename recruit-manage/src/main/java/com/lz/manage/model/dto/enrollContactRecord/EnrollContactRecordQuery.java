package com.lz.manage.model.dto.enrollContactRecord;

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
import com.lz.manage.model.domain.EnrollContactRecord;

/**
 * 考生联络记录Query对象 stu_enroll_contact_record
 *
 * @author YY
 * @date 2025-04-14
 */
@Data
public class EnrollContactRecordQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 记录ID
     */
    private String recordId;

    /**
     * 联络记录ID
     */
    private String contactId;

    /**
     * 考生ID
     */
    private String stuEnrollId;

    /**
     * 接听人
     */
    private String answerPeopleName;

    /**
     * 附件路径
     */
    private String fileUrl;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;

    /**
     * 请求参数
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private Map<String, Object> params;

    /**
     * 对象转封装类
     *
     * @param enrollContactRecordQuery 查询对象
     * @return EnrollContactRecord
     */
    public static EnrollContactRecord queryToObj(EnrollContactRecordQuery enrollContactRecordQuery) {
        if (enrollContactRecordQuery == null) {
            return null;
        }
        EnrollContactRecord enrollContactRecord = new EnrollContactRecord();
        BeanUtils.copyProperties(enrollContactRecordQuery, enrollContactRecord);
        return enrollContactRecord;
    }
}
