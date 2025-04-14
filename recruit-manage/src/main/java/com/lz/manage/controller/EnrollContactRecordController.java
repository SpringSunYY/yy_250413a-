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
import com.lz.manage.model.domain.EnrollContactRecord;
import com.lz.manage.model.vo.enrollContactRecord.EnrollContactRecordVo;
import com.lz.manage.model.dto.enrollContactRecord.EnrollContactRecordQuery;
import com.lz.manage.model.dto.enrollContactRecord.EnrollContactRecordInsert;
import com.lz.manage.model.dto.enrollContactRecord.EnrollContactRecordEdit;
import com.lz.manage.service.IEnrollContactRecordService;
import com.lz.common.utils.poi.ExcelUtil;
import com.lz.common.core.page.TableDataInfo;

/**
 * 考生联络记录Controller
 *
 * @author YY
 * @date 2025-04-14
 */
@RestController
@RequestMapping("/manage/enrollContactRecord")
public class EnrollContactRecordController extends BaseController
{
    @Resource
    private IEnrollContactRecordService enrollContactRecordService;

    /**
     * 查询考生联络记录列表
     */
    @PreAuthorize("@ss.hasPermi('manage:enrollContactRecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(EnrollContactRecordQuery enrollContactRecordQuery)
    {
        EnrollContactRecord enrollContactRecord = EnrollContactRecordQuery.queryToObj(enrollContactRecordQuery);
        startPage();
        List<EnrollContactRecord> list = enrollContactRecordService.selectEnrollContactRecordList(enrollContactRecord);
        List<EnrollContactRecordVo> listVo= list.stream().map(EnrollContactRecordVo::objToVo).collect(Collectors.toList());
        TableDataInfo table = getDataTable(list);
        table.setRows(listVo);
        return table;
    }

    /**
     * 导出考生联络记录列表
     */
    @PreAuthorize("@ss.hasPermi('manage:enrollContactRecord:export')")
    @Log(title = "考生联络记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EnrollContactRecordQuery enrollContactRecordQuery)
    {
        EnrollContactRecord enrollContactRecord = EnrollContactRecordQuery.queryToObj(enrollContactRecordQuery);
        List<EnrollContactRecord> list = enrollContactRecordService.selectEnrollContactRecordList(enrollContactRecord);
        ExcelUtil<EnrollContactRecord> util = new ExcelUtil<EnrollContactRecord>(EnrollContactRecord.class);
        util.exportExcel(response, list, "考生联络记录数据");
    }

    /**
     * 获取考生联络记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('manage:enrollContactRecord:query')")
    @GetMapping(value = "/{contactId}")
    public AjaxResult getInfo(@PathVariable("contactId") String contactId)
    {
        EnrollContactRecord enrollContactRecord = enrollContactRecordService.selectEnrollContactRecordByContactId(contactId);
        return success(EnrollContactRecordVo.objToVo(enrollContactRecord));
    }

    /**
     * 新增考生联络记录
     */
    @PreAuthorize("@ss.hasPermi('manage:enrollContactRecord:add')")
    @Log(title = "考生联络记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EnrollContactRecordInsert enrollContactRecordInsert)
    {
        EnrollContactRecord enrollContactRecord = EnrollContactRecordInsert.insertToObj(enrollContactRecordInsert);
        return toAjax(enrollContactRecordService.insertEnrollContactRecord(enrollContactRecord));
    }

    /**
     * 修改考生联络记录
     */
    @PreAuthorize("@ss.hasPermi('manage:enrollContactRecord:edit')")
    @Log(title = "考生联络记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EnrollContactRecordEdit enrollContactRecordEdit)
    {
        EnrollContactRecord enrollContactRecord = EnrollContactRecordEdit.editToObj(enrollContactRecordEdit);
        return toAjax(enrollContactRecordService.updateEnrollContactRecord(enrollContactRecord));
    }

    /**
     * 删除考生联络记录
     */
    @PreAuthorize("@ss.hasPermi('manage:enrollContactRecord:remove')")
    @Log(title = "考生联络记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{contactIds}")
    public AjaxResult remove(@PathVariable String[] contactIds)
    {
        return toAjax(enrollContactRecordService.deleteEnrollContactRecordByContactIds(contactIds));
    }
}
