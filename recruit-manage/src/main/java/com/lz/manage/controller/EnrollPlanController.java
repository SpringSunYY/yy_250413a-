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
import com.lz.manage.model.domain.EnrollPlan;
import com.lz.manage.model.vo.enrollPlan.EnrollPlanVo;
import com.lz.manage.model.dto.enrollPlan.EnrollPlanQuery;
import com.lz.manage.model.dto.enrollPlan.EnrollPlanInsert;
import com.lz.manage.model.dto.enrollPlan.EnrollPlanEdit;
import com.lz.manage.service.IEnrollPlanService;
import com.lz.common.utils.poi.ExcelUtil;
import com.lz.common.core.page.TableDataInfo;

/**
 * 招生计划Controller
 *
 * @author YY
 * @date 2025-04-14
 */
@RestController
@RequestMapping("/manage/enrollPlan")
public class EnrollPlanController extends BaseController
{
    @Resource
    private IEnrollPlanService enrollPlanService;

    /**
     * 查询招生计划列表
     */
    @PreAuthorize("@ss.hasPermi('manage:enrollPlan:list')")
    @GetMapping("/list")
    public TableDataInfo list(EnrollPlanQuery enrollPlanQuery)
    {
        EnrollPlan enrollPlan = EnrollPlanQuery.queryToObj(enrollPlanQuery);
        startPage();
        List<EnrollPlan> list = enrollPlanService.selectEnrollPlanList(enrollPlan);
        List<EnrollPlanVo> listVo= list.stream().map(EnrollPlanVo::objToVo).collect(Collectors.toList());
        TableDataInfo table = getDataTable(list);
        table.setRows(listVo);
        return table;
    }

    /**
     * 导出招生计划列表
     */
    @PreAuthorize("@ss.hasPermi('manage:enrollPlan:export')")
    @Log(title = "招生计划", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EnrollPlanQuery enrollPlanQuery)
    {
        EnrollPlan enrollPlan = EnrollPlanQuery.queryToObj(enrollPlanQuery);
        List<EnrollPlan> list = enrollPlanService.selectEnrollPlanList(enrollPlan);
        ExcelUtil<EnrollPlan> util = new ExcelUtil<EnrollPlan>(EnrollPlan.class);
        util.exportExcel(response, list, "招生计划数据");
    }

    /**
     * 获取招生计划详细信息
     */
    @PreAuthorize("@ss.hasPermi('manage:enrollPlan:query')")
    @GetMapping(value = "/{planId}")
    public AjaxResult getInfo(@PathVariable("planId") String planId)
    {
        EnrollPlan enrollPlan = enrollPlanService.selectEnrollPlanByPlanId(planId);
        return success(EnrollPlanVo.objToVo(enrollPlan));
    }

    /**
     * 新增招生计划
     */
    @PreAuthorize("@ss.hasPermi('manage:enrollPlan:add')")
    @Log(title = "招生计划", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EnrollPlanInsert enrollPlanInsert)
    {
        EnrollPlan enrollPlan = EnrollPlanInsert.insertToObj(enrollPlanInsert);
        return toAjax(enrollPlanService.insertEnrollPlan(enrollPlan));
    }

    /**
     * 修改招生计划
     */
    @PreAuthorize("@ss.hasPermi('manage:enrollPlan:edit')")
    @Log(title = "招生计划", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EnrollPlanEdit enrollPlanEdit)
    {
        EnrollPlan enrollPlan = EnrollPlanEdit.editToObj(enrollPlanEdit);
        return toAjax(enrollPlanService.updateEnrollPlan(enrollPlan));
    }

    /**
     * 删除招生计划
     */
    @PreAuthorize("@ss.hasPermi('manage:enrollPlan:remove')")
    @Log(title = "招生计划", businessType = BusinessType.DELETE)
	@DeleteMapping("/{planIds}")
    public AjaxResult remove(@PathVariable String[] planIds)
    {
        return toAjax(enrollPlanService.deleteEnrollPlanByPlanIds(planIds));
    }
}
