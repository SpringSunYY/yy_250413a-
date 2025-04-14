package com.lz.manage.model.dto.enrollSearch;

import java.util.Map;
import java.io.Serializable;
import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.BeanUtils;
import com.baomidou.mybatisplus.annotation.TableField;
import com.lz.manage.model.domain.EnrollSearch;
/**
 * 考生查询Query对象 stu_enroll_search
 *
 * @author YY
 * @date 2025-04-14
 */
@Data
public class EnrollSearchQuery implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 考生ID */
    private String stuEnrollId;

    /** 学号 */
    private String stuNo;

    /** 班级 */
    private String classId;

    /** 宿舍号 */
    private String dormId;

    /** 辅导员 */
    private String classTeacher;

    /** 辅导员联系方式 */
    private String classTeacherContact;

    /** QQ群 */
    private String classQq;

    /** 请求参数 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private Map<String, Object> params;

    /**
     * 对象转封装类
     *
     * @param enrollSearchQuery 查询对象
     * @return EnrollSearch
     */
    public static EnrollSearch queryToObj(EnrollSearchQuery enrollSearchQuery) {
        if (enrollSearchQuery == null) {
            return null;
        }
        EnrollSearch enrollSearch = new EnrollSearch();
        BeanUtils.copyProperties(enrollSearchQuery, enrollSearch);
        return enrollSearch;
    }
}
