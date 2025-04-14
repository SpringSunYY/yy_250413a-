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
import com.lz.manage.model.domain.EnrollBasic;
import com.lz.manage.model.vo.enrollBasic.EnrollBasicVo;
import com.lz.manage.model.dto.enrollBasic.EnrollBasicQuery;
import com.lz.manage.model.dto.enrollBasic.EnrollBasicInsert;
import com.lz.manage.model.dto.enrollBasic.EnrollBasicEdit;
import com.lz.manage.service.IEnrollBasicService;
import com.lz.common.utils.poi.ExcelUtil;
import com.lz.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 录取信息Controller
 *
 * @author ruoyi
 * @date 2025-04-14
 */
@RestController
@RequestMapping("/manage/enrollBasic")
public class EnrollBasicController extends BaseController {
    @Resource
    private IEnrollBasicService enrollBasicService;

    /**
     * 查询录取信息列表
     */
    @PreAuthorize("@ss.hasPermi('manage:enrollBasic:list')")
    @GetMapping("/list")
    public TableDataInfo list(EnrollBasicQuery enrollBasicQuery) {
        EnrollBasic enrollBasic = EnrollBasicQuery.queryToObj(enrollBasicQuery);
        startPage();
        List<EnrollBasic> list = enrollBasicService.selectEnrollBasicList(enrollBasic);
        List<EnrollBasicVo> listVo = list.stream().map(EnrollBasicVo::objToVo).collect(Collectors.toList());
        TableDataInfo table = getDataTable(list);
        table.setRows(listVo);
        return table;
    }

    /**
     * 导出录取信息列表
     */
    @PreAuthorize("@ss.hasPermi('manage:enrollBasic:export')")
    @Log(title = "录取信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EnrollBasicQuery enrollBasicQuery) {
        EnrollBasic enrollBasic = EnrollBasicQuery.queryToObj(enrollBasicQuery);
        List<EnrollBasic> list = enrollBasicService.selectEnrollBasicList(enrollBasic);
        ExcelUtil<EnrollBasic> util = new ExcelUtil<EnrollBasic>(EnrollBasic.class);
        util.exportExcel(response, list, "录取信息数据");
    }

    /**
     * 获取录取信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('manage:enrollBasic:query')")
    @GetMapping(value = "/{stuEnrollId}")
    public AjaxResult getInfo(@PathVariable("stuEnrollId") String stuEnrollId) {
        EnrollBasic enrollBasic = enrollBasicService.selectEnrollBasicByStuEnrollId(stuEnrollId);
        return success(EnrollBasicVo.objToVo(enrollBasic));
    }

    /**
     * 新增录取信息
     */
    @PreAuthorize("@ss.hasPermi('manage:enrollBasic:add')")
    @Log(title = "录取信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EnrollBasicInsert enrollBasicInsert) {
        EnrollBasic enrollBasic = EnrollBasicInsert.insertToObj(enrollBasicInsert);
        return toAjax(enrollBasicService.insertEnrollBasic(enrollBasic));
    }

    /**
     * 修改录取信息
     */
    @PreAuthorize("@ss.hasPermi('manage:enrollBasic:edit')")
    @Log(title = "录取信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EnrollBasicEdit enrollBasicEdit) {
        EnrollBasic enrollBasic = EnrollBasicEdit.editToObj(enrollBasicEdit);
        return toAjax(enrollBasicService.updateEnrollBasic(enrollBasic));
    }

    /**
     * 删除录取信息
     */
    @PreAuthorize("@ss.hasPermi('manage:enrollBasic:remove')")
    @Log(title = "录取信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{stuEnrollIds}")
    public AjaxResult remove(@PathVariable String[] stuEnrollIds) {
        return toAjax(enrollBasicService.deleteEnrollBasicByStuEnrollIds(stuEnrollIds));
    }

    @PreAuthorize("@ss.hasPermi('manage:enrollBasic:import')")
    @Log(title = "导入录取信息", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file) throws Exception {
        ExcelUtil<EnrollBasic> util = new ExcelUtil<EnrollBasic>(EnrollBasic.class);
        List<EnrollBasic> basicList = util.importExcel(file.getInputStream());
        String message = enrollBasicService.importEnrollBasic(basicList);
        return success(message);
    }

    @PreAuthorize("@ss.hasPermi('manage:enrollBasic:import')")
    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response) {
        ExcelUtil<EnrollBasic> util = new ExcelUtil<EnrollBasic>(EnrollBasic.class);
        util.importTemplateExcel(response, "录取数据");
    }
}
