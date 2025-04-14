package com.lz.manage.model.dto.enrollSearch;

import java.io.Serializable;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import com.lz.manage.model.domain.EnrollSearch;
/**
 * 考生查询Vo对象 stu_enroll_search
 *
 * @author YY
 * @date 2025-04-14
 */
@Data
public class EnrollSearchEdit implements Serializable
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

    /** 其他说明 */
    private String searchRemarks;

    /**
     * 对象转封装类
     *
     * @param enrollSearchEdit 编辑对象
     * @return EnrollSearch
     */
    public static EnrollSearch editToObj(EnrollSearchEdit enrollSearchEdit) {
        if (enrollSearchEdit == null) {
            return null;
        }
        EnrollSearch enrollSearch = new EnrollSearch();
        BeanUtils.copyProperties(enrollSearchEdit, enrollSearch);
        return enrollSearch;
    }
}
