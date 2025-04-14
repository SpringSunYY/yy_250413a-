package com.lz.manage.model.dto.enrollContactRecord;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import com.lz.manage.model.domain.EnrollContactRecord;

/**
 * 考生联络记录Vo对象 stu_enroll_contact_record
 *
 * @author YY
 * @date 2025-04-14
 */
@Data
public class EnrollContactRecordInsert implements Serializable {
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
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date contactStartTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date contactEndTime;

    /**
     * 接听人
     */
    private String answerPeopleName;

    /**
     * 与考生关系
     */
    private String answerPeopleRelation;

    /**
     * 关系说明
     */
    private String answerPeopleRemark;

    /**
     * 回复内容
     */
    private String replyContent;

    /**
     * 回复说明
     */
    private String replyContentRemark;

    /**
     * 附件名称
     */
    private String fileName;

    /**
     * 附件后缀名
     */
    private String fileSuffix;

    /**
     * 附件路径
     */
    private String fileUrl;

    /**
     * 备注
     */
    private String remark;

    /**
     * 对象转封装类
     *
     * @param enrollContactRecordInsert 插入对象
     * @return EnrollContactRecordInsert
     */
    public static EnrollContactRecord insertToObj(EnrollContactRecordInsert enrollContactRecordInsert) {
        if (enrollContactRecordInsert == null) {
            return null;
        }
        EnrollContactRecord enrollContactRecord = new EnrollContactRecord();
        BeanUtils.copyProperties(enrollContactRecordInsert, enrollContactRecord);
        return enrollContactRecord;
    }
}
