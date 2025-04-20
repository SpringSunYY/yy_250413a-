package com.lz.manage.service;

import java.util.List;

import com.lz.manage.model.domain.EnrollBasic;
import com.lz.manage.model.domain.EnrollNote;
import com.lz.manage.model.vo.enrollNote.EnrollNoteVo;
import com.lz.manage.model.dto.enrollNote.EnrollNoteQuery;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * 通知书信息Service接口
 *
 * @author YY
 * @date 2025-04-14
 */
public interface IEnrollNoteService extends IService<EnrollNote> {
    //region mybatis代码

    /**
     * 查询通知书信息
     *
     * @param stuEnrollId 通知书信息主键
     * @return 通知书信息
     */
    public EnrollNote selectEnrollNoteByStuEnrollId(String stuEnrollId);

    /**
     * 查询通知书信息列表
     *
     * @param enrollNote 通知书信息
     * @return 通知书信息集合
     */
    public List<EnrollNote> selectEnrollNoteList(EnrollNote enrollNote);

    /**
     * 新增通知书信息
     *
     * @param enrollNote 通知书信息
     * @return 结果
     */
    public int insertEnrollNote(EnrollNote enrollNote);

    /**
     * 修改通知书信息
     *
     * @param enrollNote 通知书信息
     * @return 结果
     */
    public int updateEnrollNote(EnrollNote enrollNote);

    /**
     * 批量删除通知书信息
     *
     * @param stuEnrollIds 需要删除的通知书信息主键集合
     * @return 结果
     */
    public int deleteEnrollNoteByStuEnrollIds(String[] stuEnrollIds);

    /**
     * 删除通知书信息信息
     *
     * @param stuEnrollId 通知书信息主键
     * @return 结果
     */
    public int deleteEnrollNoteByStuEnrollId(String stuEnrollId);
    //endregion

    /**
     * 获取查询条件
     *
     * @param enrollNoteQuery 查询条件对象
     * @return 查询条件
     */
    QueryWrapper<EnrollNote> getQueryWrapper(EnrollNoteQuery enrollNoteQuery);

    /**
     * 转换vo
     *
     * @param enrollNoteList EnrollNote集合
     * @return EnrollNoteVO集合
     */
    List<EnrollNoteVo> convertVoList(List<EnrollNote> enrollNoteList);

    /**
     * description: 查询学生录取信息
     * author: YY
     * method: stuQuery
     * date: 2025/4/15 20:55
     * param:
     * param: enrollBasic
     * return: com.lz.manage.model.domain.EnrollNote
     **/
    EnrollNote stuQuery(EnrollBasic enrollBasic);

    /**
     * 导入通知书信息
     *
     * @param basicList
     * @return java.lang.String
     * @author YY
     * @method importEnrollNode
     * @date 2025/4/20 23:44
     **/
    String importEnrollNode(List<EnrollNote> basicList);
}
