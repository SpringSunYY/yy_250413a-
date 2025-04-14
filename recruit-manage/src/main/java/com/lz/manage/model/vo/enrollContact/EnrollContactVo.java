package com.lz.manage.model.vo.enrollContact;

import java.io.Serializable;
import java.util.Date;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.lz.common.annotation.Excel;
import org.springframework.beans.BeanUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.manage.model.domain.EnrollContact;
/**
 * 考生联络Vo对象 stu_enroll_contact
 *
 * @author YY
 * @date 2025-04-14
 */
@Data
public class EnrollContactVo implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 考生ID */
    @Excel(name = "考生ID")
    private String stuEnrollId;

    /** 联络人ID */
    @Excel(name = "联络人ID")
    private String taskUserId;

    /** 联络人姓名 */
    @Excel(name = "联络人姓名")
    private String taskUserName;

    /** 是否联络 */
    @Excel(name = "是否联络")
    private String taskUserExecute;

    /** 可否联络 */
    @Excel(name = "可否联络")
    private String taskLock;

    /** 联络记录ID */
    @Excel(name = "联络记录ID")
    private String contactId;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date contactStartTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date contactEndTime;

    /** 接听人 */
    @Excel(name = "接听人")
    private String answerPeopleName;

    /** 与考生关系 */
    @Excel(name = "与考生关系")
    private String answerPeopleRelation;

    /** 关系说明 */
    @Excel(name = "关系说明")
    private String answerPeopleRemark;

    /** 回复内容 */
    @Excel(name = "回复内容")
    private String replyContent;

    /** 回复说明 */
    @Excel(name = "回复说明")
    private String replyContentRemark;

    /** 附件名称 */
    @Excel(name = "附件名称")
    private String fileName;

    /** 附件后缀名 */
    @Excel(name = "附件后缀名")
    private String fileSuffix;

    /** 附件路径 */
    @Excel(name = "附件路径")
    private String fileUrl;

    /** 创建者 */
    @Excel(name = "创建者")
    private String createBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createTime;

    /** 更新者 */
    @Excel(name = "更新者")
    private String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateTime;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;


     /**
     * 对象转封装类
     *
     * @param enrollContact EnrollContact实体对象
     * @return EnrollContactVo
     */
    public static EnrollContactVo objToVo(EnrollContact enrollContact) {
        if (enrollContact == null) {
            return null;
        }
        EnrollContactVo enrollContactVo = new EnrollContactVo();
        BeanUtils.copyProperties(enrollContact, enrollContactVo);
        return enrollContactVo;
    }
}
