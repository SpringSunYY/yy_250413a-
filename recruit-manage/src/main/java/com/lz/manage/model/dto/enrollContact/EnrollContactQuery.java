package com.lz.manage.model.dto.enrollContact;

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
import com.lz.manage.model.domain.EnrollContact;
/**
 * 考生联络Query对象 stu_enroll_contact
 *
 * @author YY
 * @date 2025-04-14
 */
@Data
public class EnrollContactQuery implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 考生ID */
    private String stuEnrollId;

    /** 联络人姓名 */
    private String taskUserName;

    /** 是否联络 */
    private String taskUserExecute;

    /** 可否联络 */
    private String taskLock;

    /** 接听人 */
    private String answerPeopleName;

    /** 与考生关系 */
    private String answerPeopleRelation;

    /** 附件路径 */
    private String fileUrl;

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
     * @param enrollContactQuery 查询对象
     * @return EnrollContact
     */
    public static EnrollContact queryToObj(EnrollContactQuery enrollContactQuery) {
        if (enrollContactQuery == null) {
            return null;
        }
        EnrollContact enrollContact = new EnrollContact();
        BeanUtils.copyProperties(enrollContactQuery, enrollContact);
        return enrollContact;
    }
}
