package com.lz.manage.model.domain;

import java.io.Serializable;
import java.util.Map;
import java.util.Date;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.lz.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 考生联络记录对象 stu_enroll_contact_record
 *
 * @author YY
 * @date 2025-04-14
 */
@TableName("stu_enroll_contact_record")
@Data
public class EnrollContactRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 记录ID
     */
    @Excel(name = "记录ID")
    @TableId(value = "record_id", type = IdType.ASSIGN_ID)
    private String recordId;

    /**
     * 联络记录ID
     */
    @Excel(name = "联络记录ID")
    private String contactId;

    /**
     * 考生ID
     */
    @Excel(name = "考生ID")
    private String stuEnrollId;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date contactStartTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date contactEndTime;

    /**
     * 接听人
     */
    @Excel(name = "接听人")
    private String answerPeopleName;

    /**
     * 与考生关系
     */
    @Excel(name = "与考生关系")
    private String answerPeopleRelation;

    /**
     * 关系说明
     */
    @Excel(name = "关系说明")
    private String answerPeopleRemark;

    /**
     * 回复内容
     */
    @Excel(name = "回复内容")
    private String replyContent;

    /**
     * 回复说明
     */
    @Excel(name = "回复说明")
    private String replyContentRemark;

    /**
     * 附件名称
     */
    @Excel(name = "附件名称")
    private String fileName;

    /**
     * 附件后缀名
     */
    @Excel(name = "附件后缀名")
    private String fileSuffix;

    /**
     * 附件路径
     */
    @Excel(name = "附件路径")
    private String fileUrl;

    /**
     * 创建者
     */
    @Excel(name = "创建者")
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createTime;

    /**
     * 更新者
     */
    @Excel(name = "更新者")
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateTime;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remark;

    /**
     * 请求参数
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private Map<String, Object> params;
}
