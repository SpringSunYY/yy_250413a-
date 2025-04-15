package com.lz.manage.model.dto.enrollNote;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.lz.manage.model.domain.EnrollBasic;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Map;

/**
 * 录取信息Query对象 stu_enroll_basic
 *
 * @author ruoyi
 * @date 2025-04-14
 */
@Data
public class StuNoteQuery implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 考生号
     */
    @NotEmpty(message = "考生号不能为空")
    private String examNum;


    /**
     * 姓名
     */
    @NotEmpty(message = "姓名不能为空")
    private String stuName;

    /**
     * 身份证号
     */
    @NotEmpty(message = "身份证号不能为空")
    private String idCard;

    /**
     * 验证码
     */
    private String code;

    private String uuid;

    /**
     * 对象转封装类
     *
     * @param enrollBasicQuery 查询对象
     * @return EnrollBasic
     */
    public static EnrollBasic queryToObj(StuNoteQuery enrollBasicQuery) {
        if (enrollBasicQuery == null) {
            return null;
        }
        EnrollBasic enrollBasic = new EnrollBasic();
        BeanUtils.copyProperties(enrollBasicQuery, enrollBasic);
        return enrollBasic;
    }
}
