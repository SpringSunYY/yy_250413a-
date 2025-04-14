package com.lz.manage.service;

import java.util.List;
import com.lz.manage.model.domain.EnrollContact;
import com.lz.manage.model.vo.enrollContact.EnrollContactVo;
import com.lz.manage.model.dto.enrollContact.EnrollContactQuery;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
/**
 * 考生联络Service接口
 * 
 * @author YY
 * @date 2025-04-14
 */
public interface IEnrollContactService extends IService<EnrollContact>
{
    //region mybatis代码
    /**
     * 查询考生联络
     * 
     * @param contactId 考生联络主键
     * @return 考生联络
     */
    public EnrollContact selectEnrollContactByStuEnrollId(String contactId);

    /**
     * 查询考生联络列表
     * 
     * @param enrollContact 考生联络
     * @return 考生联络集合
     */
    public List<EnrollContact> selectEnrollContactList(EnrollContact enrollContact);

    /**
     * 新增考生联络
     * 
     * @param enrollContact 考生联络
     * @return 结果
     */
    public int insertEnrollContact(EnrollContact enrollContact);

    /**
     * 修改考生联络
     * 
     * @param enrollContact 考生联络
     * @return 结果
     */
    public int updateEnrollContact(EnrollContact enrollContact);

    /**
     * 批量删除考生联络
     * 
     * @param stuEnrollIds 需要删除的考生联络主键集合
     * @return 结果
     */
    public int deleteEnrollContactByStuEnrollIds(String[] stuEnrollIds);

    /**
     * 删除考生联络信息
     * 
     * @param stuEnrollId 考生联络主键
     * @return 结果
     */
    public int deleteEnrollContactByStuEnrollId(String stuEnrollId);
    //endregion
    /**
     * 获取查询条件
     *
     * @param enrollContactQuery 查询条件对象
     * @return 查询条件
     */
    QueryWrapper<EnrollContact> getQueryWrapper(EnrollContactQuery enrollContactQuery);

    /**
     * 转换vo
     *
     * @param enrollContactList EnrollContact集合
     * @return EnrollContactVO集合
     */
    List<EnrollContactVo> convertVoList(List<EnrollContact> enrollContactList);
}
