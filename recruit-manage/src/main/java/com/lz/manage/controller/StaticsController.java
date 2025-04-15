package com.lz.manage.controller;

import com.lz.common.annotation.Log;
import com.lz.common.core.controller.BaseController;
import com.lz.common.core.domain.AjaxResult;
import com.lz.common.core.page.TableDataInfo;
import com.lz.common.enums.BusinessType;
import com.lz.common.utils.poi.ExcelUtil;
import com.lz.manage.model.domain.EnrollSearch;
import com.lz.manage.model.dto.enrollSearch.EnrollSearchEdit;
import com.lz.manage.model.dto.enrollSearch.EnrollSearchInsert;
import com.lz.manage.model.dto.enrollSearch.EnrollSearchQuery;
import com.lz.manage.model.statics.StaticsDto;
import com.lz.manage.model.vo.enrollSearch.EnrollSearchVo;
import com.lz.manage.service.IEnrollSearchService;
import com.lz.manage.service.IStaticsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 考生查询Controller
 *
 * @author YY
 * @date 2025-04-14
 */
@RestController
@RequestMapping("/manage/statics")
public class StaticsController extends BaseController {
    @Resource
    private IStaticsService staticsService;

    @GetMapping
    public AjaxResult getStaticsPie(StaticsDto staticsDto) {
        return AjaxResult.success(staticsService.getStaticsPie(staticsDto));
    }

}
