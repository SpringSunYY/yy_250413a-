package com.lz.manage.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lz.common.annotation.Log;
import com.lz.common.core.controller.BaseController;
import com.lz.common.core.domain.AjaxResult;
import com.lz.common.enums.BusinessType;
import com.lz.manage.model.domain.EnrollContact;
import com.lz.manage.model.vo.enrollContact.EnrollContactVo;
import com.lz.manage.model.dto.enrollContact.EnrollContactQuery;
import com.lz.manage.model.dto.enrollContact.EnrollContactInsert;
import com.lz.manage.model.dto.enrollContact.EnrollContactEdit;
import com.lz.manage.service.IEnrollContactService;
import com.lz.common.utils.poi.ExcelUtil;
import com.lz.common.core.page.TableDataInfo;

/**
 * 考生联络Controller
 *
 * @author YY
 * @date 2025-04-14
 */
@RestController
@RequestMapping("/manage/enrollContact")
public class EnrollContactController extends BaseController
{
    @Resource
    private IEnrollContactService enrollContactService;

    /**
     * 查询考生联络列表
     */
    @PreAuthorize("@ss.hasPermi('manage:enrollContact:list')")
    @GetMapping("/list")
    public TableDataInfo list(EnrollContactQuery enrollContactQuery)
    {
        EnrollContact enrollContact = EnrollContactQuery.queryToObj(enrollContactQuery);
        startPage();
        List<EnrollContact> list = enrollContactService.selectEnrollContactList(enrollContact);
        List<EnrollContactVo> listVo= list.stream().map(EnrollContactVo::objToVo).collect(Collectors.toList());
        TableDataInfo table = getDataTable(list);
        table.setRows(listVo);
        return table;
    }

    /**
     * 导出考生联络列表
     */
    @PreAuthorize("@ss.hasPermi('manage:enrollContact:export')")
    @Log(title = "考生联络", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EnrollContactQuery enrollContactQuery)
    {
        EnrollContact enrollContact = EnrollContactQuery.queryToObj(enrollContactQuery);
        List<EnrollContact> list = enrollContactService.selectEnrollContactList(enrollContact);
        ExcelUtil<EnrollContact> util = new ExcelUtil<EnrollContact>(EnrollContact.class);
        util.exportExcel(response, list, "考生联络数据");
    }

    /**
     * 获取考生联络详细信息
     */
    @PreAuthorize("@ss.hasPermi('manage:enrollContact:query')")
    @GetMapping(value = "/{contactId}")
    public AjaxResult getInfo(@PathVariable("contactId") String contactId)
    {
        EnrollContact enrollContact = enrollContactService.selectEnrollContactByStuEnrollId(contactId);
        return success(EnrollContactVo.objToVo(enrollContact));
    }

    /**
     * 新增考生联络
     */
    @PreAuthorize("@ss.hasPermi('manage:enrollContact:add')")
    @Log(title = "考生联络", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EnrollContactInsert enrollContactInsert)
    {
        EnrollContact enrollContact = EnrollContactInsert.insertToObj(enrollContactInsert);
        return toAjax(enrollContactService.insertEnrollContact(enrollContact));
    }

    /**
     * 修改考生联络
     */
    @PreAuthorize("@ss.hasPermi('manage:enrollContact:edit')")
    @Log(title = "考生联络", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EnrollContactEdit enrollContactEdit)
    {
        EnrollContact enrollContact = EnrollContactEdit.editToObj(enrollContactEdit);
        return toAjax(enrollContactService.updateEnrollContact(enrollContact));
    }

    /**
     * 删除考生联络
     */
    @PreAuthorize("@ss.hasPermi('manage:enrollContact:remove')")
    @Log(title = "考生联络", businessType = BusinessType.DELETE)
	@DeleteMapping("/{contactIds}")
    public AjaxResult remove(@PathVariable String[] contactIds)
    {
        return toAjax(enrollContactService.deleteEnrollContactByStuEnrollIds(contactIds));
    }
}
