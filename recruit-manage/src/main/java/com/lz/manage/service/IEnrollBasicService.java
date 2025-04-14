package com.lz.manage.service;

import java.util.List;

import com.lz.manage.model.domain.EnrollBasic;
import com.lz.manage.model.vo.enrollBasic.EnrollBasicVo;
import com.lz.manage.model.dto.enrollBasic.EnrollBasicQuery;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * 录取信息Service接口
 *
 * @author ruoyi
 * @date 2025-04-14
 */
public interface IEnrollBasicService extends IService<EnrollBasic> {
    //region mybatis代码

    /**
     * 查询录取信息
     *
     * @param stuEnrollId 录取信息主键
     * @return 录取信息
     */
    public EnrollBasic selectEnrollBasicByStuEnrollId(String stuEnrollId);

    /**
     * 查询录取信息列表
     *
     * @param enrollBasic 录取信息
     * @return 录取信息集合
     */
    public List<EnrollBasic> selectEnrollBasicList(EnrollBasic enrollBasic);

    /**
     * 新增录取信息
     *
     * @param enrollBasic 录取信息
     * @return 结果
     */
    public int insertEnrollBasic(EnrollBasic enrollBasic);

    /**
     * 修改录取信息
     *
     * @param enrollBasic 录取信息
     * @return 结果
     */
    public int updateEnrollBasic(EnrollBasic enrollBasic);

    /**
     * 批量删除录取信息
     *
     * @param stuEnrollIds 需要删除的录取信息主键集合
     * @return 结果
     */
    public int deleteEnrollBasicByStuEnrollIds(String[] stuEnrollIds);

    /**
     * 删除录取信息信息
     *
     * @param stuEnrollId 录取信息主键
     * @return 结果
     */
    public int deleteEnrollBasicByStuEnrollId(String stuEnrollId);
    //endregion

    /**
     * 获取查询条件
     *
     * @param enrollBasicQuery 查询条件对象
     * @return 查询条件
     */
    QueryWrapper<EnrollBasic> getQueryWrapper(EnrollBasicQuery enrollBasicQuery);

    /**
     * 转换vo
     *
     * @param enrollBasicList EnrollBasic集合
     * @return EnrollBasicVO集合
     */
    List<EnrollBasicVo> convertVoList(List<EnrollBasic> enrollBasicList);

    /**
     * description: 导入录取信息
     * author: YY
     * method: importEnrollBasic
     * date: 2025/4/14 22:45
     * param:
     * param: basicList
     * return: java.lang.String
     **/
    String importEnrollBasic(List<EnrollBasic> basicList);
}
