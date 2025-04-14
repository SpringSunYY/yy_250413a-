package com.lz.manage.mapper;

import java.util.List;
import com.lz.manage.model.domain.EnrollNote;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 通知书信息Mapper接口
 * 
 * @author YY
 * @date 2025-04-14
 */
public interface EnrollNoteMapper extends BaseMapper<EnrollNote>
{
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
     * 删除通知书信息
     * 
     * @param stuEnrollId 通知书信息主键
     * @return 结果
     */
    public int deleteEnrollNoteByStuEnrollId(String stuEnrollId);

    /**
     * 批量删除通知书信息
     * 
     * @param stuEnrollIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEnrollNoteByStuEnrollIds(String[] stuEnrollIds);
}
