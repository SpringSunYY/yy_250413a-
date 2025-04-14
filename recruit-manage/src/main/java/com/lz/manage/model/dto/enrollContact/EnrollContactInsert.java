package com.lz.manage.model.dto.enrollContact;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import com.lz.manage.model.domain.EnrollContact;
/**
 * 考生联络Vo对象 stu_enroll_contact
 *
 * @author YY
 * @date 2025-04-14
 */
@Data
public class EnrollContactInsert implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 考生ID */
    private String stuEnrollId;

    /** 联络人ID */
    private String taskUserId;

    /** 联络人姓名 */
    private String taskUserName;

    /** 是否联络 */
    private String taskUserExecute;

    /** 可否联络 */
    private String taskLock;

    /** 联络记录ID */
    private String contactId;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date contactStartTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date contactEndTime;

    /** 接听人 */
    private String answerPeopleName;

    /** 与考生关系 */
    private String answerPeopleRelation;

    /** 关系说明 */
    private String answerPeopleRemark;

    /** 回复内容 */
    private String replyContent;

    /** 回复说明 */
    private String replyContentRemark;

    /** 附件名称 */
    private String fileName;

    /** 附件后缀名 */
    private String fileSuffix;

    /** 附件路径 */
    private String fileUrl;

    /** 备注 */
    private String remark;

    /**
     * 对象转封装类
     *
     * @param enrollContactInsert 插入对象
     * @return EnrollContactInsert
     */
    public static EnrollContact insertToObj(EnrollContactInsert enrollContactInsert) {
        if (enrollContactInsert == null) {
            return null;
        }
        EnrollContact enrollContact = new EnrollContact();
        BeanUtils.copyProperties(enrollContactInsert, enrollContact);
        return enrollContact;
    }
}
