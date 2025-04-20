package com.lz.manage.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.lz.manage.model.dto.enrollSearch.EnrollJoinSearch;
import com.lz.manage.model.vo.enrollSearch.EnrollJoinResultVo;
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
import com.lz.manage.model.domain.EnrollSearch;
import com.lz.manage.model.vo.enrollSearch.EnrollSearchVo;
import com.lz.manage.model.dto.enrollSearch.EnrollSearchQuery;
import com.lz.manage.model.dto.enrollSearch.EnrollSearchInsert;
import com.lz.manage.model.dto.enrollSearch.EnrollSearchEdit;
import com.lz.manage.service.IEnrollSearchService;
import com.lz.common.utils.poi.ExcelUtil;
import com.lz.common.core.page.TableDataInfo;

/**
 * 考生查询Controller
 *
 * @author YY
 * @date 2025-04-14
 */
@RestController
@RequestMapping("/manage/enrollSearch")
public class EnrollSearchController extends BaseController
{
    @Resource
    private IEnrollSearchService enrollSearchService;

    /**
     * 查询考生查询列表
     */
    @PreAuthorize("@ss.hasPermi('manage:enrollSearch:list')")
    @GetMapping("/list")
    public TableDataInfo list(EnrollJoinSearch enrollSearch)
    {
//        EnrollSearch enrollSearch = EnrollSearchQuery.queryToObj(enrollSearchQuery);
        startPage();
//        List<EnrollSearch> list = enrollSearchService.selectEnrollSearchList(enrollSearch);
//        List<EnrollSearchVo> listVo= list.stream().map(EnrollSearchVo::objToVo).collect(Collectors.toList());
//        TableDataInfo table = getDataTable(list);
//        table.setRows(listVo);
//        return table;
        return getDataTable(enrollSearchService.selectJoinEnrollList(enrollSearch));
    }

    /**
     * 导出考生查询列表
     */
    @PreAuthorize("@ss.hasPermi('manage:enrollSearch:export')")
    @Log(title = "考生查询", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EnrollJoinSearch enrollSearch)
    {
//        EnrollSearch enrollSearch = EnrollSearchQuery.queryToObj(enrollSearchQuery);
        List<EnrollJoinResultVo> list = enrollSearchService.selectJoinEnrollList(enrollSearch);
        ExcelUtil<EnrollJoinResultVo> util = new ExcelUtil<EnrollJoinResultVo>(EnrollJoinResultVo.class);
        util.exportExcel(response, list, "考生查询数据");
    }

    /**
     * 获取考生查询详细信息
     */
    @PreAuthorize("@ss.hasPermi('manage:enrollSearch:query')")
    @GetMapping(value = "/{stuEnrollId}")
    public AjaxResult getInfo(@PathVariable("stuEnrollId") String stuEnrollId)
    {
        EnrollSearch enrollSearch = enrollSearchService.selectEnrollSearchByStuEnrollId(stuEnrollId);
        return success(EnrollSearchVo.objToVo(enrollSearch));
    }

    /**
     * 新增考生查询
     */
    @PreAuthorize("@ss.hasPermi('manage:enrollSearch:add')")
    @Log(title = "考生查询", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EnrollSearchInsert enrollSearchInsert)
    {
        EnrollSearch enrollSearch = EnrollSearchInsert.insertToObj(enrollSearchInsert);
        return toAjax(enrollSearchService.insertEnrollSearch(enrollSearch));
    }

    /**
     * 修改考生查询
     */
    @PreAuthorize("@ss.hasPermi('manage:enrollSearch:edit')")
    @Log(title = "考生查询", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EnrollSearchEdit enrollSearchEdit)
    {
        EnrollSearch enrollSearch = EnrollSearchEdit.editToObj(enrollSearchEdit);
        return toAjax(enrollSearchService.updateEnrollSearch(enrollSearch));
    }

    /**
     * 删除考生查询
     */
    @PreAuthorize("@ss.hasPermi('manage:enrollSearch:remove')")
    @Log(title = "考生查询", businessType = BusinessType.DELETE)
	@DeleteMapping("/{stuEnrollIds}")
    public AjaxResult remove(@PathVariable String[] stuEnrollIds)
    {
        return toAjax(enrollSearchService.deleteEnrollSearchByStuEnrollIds(stuEnrollIds));
    }
}
