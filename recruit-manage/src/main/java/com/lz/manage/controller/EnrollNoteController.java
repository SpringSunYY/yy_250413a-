package com.lz.manage.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.lz.common.constant.CacheConstants;
import com.lz.common.constant.Constants;
import com.lz.common.core.redis.RedisCache;
import com.lz.common.exception.user.CaptchaException;
import com.lz.common.exception.user.CaptchaExpireException;
import com.lz.common.utils.MessageUtils;
import com.lz.common.utils.StringUtils;
import com.lz.manage.model.domain.EnrollBasic;
import com.lz.manage.model.dto.enrollNote.StuNoteQuery;
import com.lz.system.service.ISysConfigService;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;
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
import com.lz.manage.model.domain.EnrollNote;
import com.lz.manage.model.vo.enrollNote.EnrollNoteVo;
import com.lz.manage.model.dto.enrollNote.EnrollNoteQuery;
import com.lz.manage.model.dto.enrollNote.EnrollNoteInsert;
import com.lz.manage.model.dto.enrollNote.EnrollNoteEdit;
import com.lz.manage.service.IEnrollNoteService;
import com.lz.common.utils.poi.ExcelUtil;
import com.lz.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 通知书信息Controller
 *
 * @author YY
 * @date 2025-04-14
 */
@RestController
@RequestMapping("/manage/enrollNote")
public class EnrollNoteController extends BaseController {
    @Resource
    private IEnrollNoteService enrollNoteService;

    @Resource
    private ISysConfigService configService;

    @Resource
    private RedisCache redisCache;

    /**
     * 查询通知书信息列表
     */
    @PreAuthorize("@ss.hasPermi('manage:enrollNote:list')")
    @GetMapping("/list")
    public TableDataInfo list(EnrollNoteQuery enrollNoteQuery) {
        EnrollNote enrollNote = EnrollNoteQuery.queryToObj(enrollNoteQuery);
        startPage();
        List<EnrollNote> list = enrollNoteService.selectEnrollNoteList(enrollNote);
        List<EnrollNoteVo> listVo = list.stream().map(EnrollNoteVo::objToVo).collect(Collectors.toList());
        TableDataInfo table = getDataTable(list);
        table.setRows(listVo);
        return table;
    }

    /**
     * 导出通知书信息列表
     */
    @PreAuthorize("@ss.hasPermi('manage:enrollNote:export')")
    @Log(title = "通知书信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EnrollNoteQuery enrollNoteQuery) {
        EnrollNote enrollNote = EnrollNoteQuery.queryToObj(enrollNoteQuery);
        List<EnrollNote> list = enrollNoteService.selectEnrollNoteList(enrollNote);
        ExcelUtil<EnrollNote> util = new ExcelUtil<EnrollNote>(EnrollNote.class);
        util.exportExcel(response, list, "通知书信息数据");
    }

    /**
     * 获取通知书信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('manage:enrollNote:query')")
    @GetMapping(value = "/{stuEnrollId}")
    public AjaxResult getInfo(@PathVariable("stuEnrollId") String stuEnrollId) {
        EnrollNote enrollNote = enrollNoteService.selectEnrollNoteByStuEnrollId(stuEnrollId);
        return success(EnrollNoteVo.objToVo(enrollNote));
    }

    /**
     * 新增通知书信息
     */
    @PreAuthorize("@ss.hasPermi('manage:enrollNote:add')")
    @Log(title = "通知书信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EnrollNoteInsert enrollNoteInsert) {
        EnrollNote enrollNote = EnrollNoteInsert.insertToObj(enrollNoteInsert);
        return toAjax(enrollNoteService.insertEnrollNote(enrollNote));
    }

    /**
     * 修改通知书信息
     */
    @PreAuthorize("@ss.hasPermi('manage:enrollNote:edit')")
    @Log(title = "通知书信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EnrollNoteEdit enrollNoteEdit) {
        EnrollNote enrollNote = EnrollNoteEdit.editToObj(enrollNoteEdit);
        return toAjax(enrollNoteService.updateEnrollNote(enrollNote));
    }

    /**
     * 删除通知书信息
     */
    @PreAuthorize("@ss.hasPermi('manage:enrollNote:remove')")
    @Log(title = "通知书信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{stuEnrollIds}")
    public AjaxResult remove(@PathVariable String[] stuEnrollIds) {
        return toAjax(enrollNoteService.deleteEnrollNoteByStuEnrollIds(stuEnrollIds));
    }

    @GetMapping("/query")
    public AjaxResult query(@Validated StuNoteQuery stuNoteQuery) {
        //校验验证码
        validateCaptcha(stuNoteQuery.getCode(), stuNoteQuery.getUuid());
        EnrollBasic enrollBasic = StuNoteQuery.queryToObj(stuNoteQuery);
        return success(enrollNoteService.stuQuery(enrollBasic));
    }

    /**
     * 校验验证码
     *
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public void validateCaptcha(String code, String uuid) {
        boolean captchaEnabled = configService.selectCaptchaEnabled();
        if (captchaEnabled) {
            String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");
            String captcha = redisCache.getCacheObject(verifyKey);
            if (captcha == null) {
                throw new CaptchaExpireException();
            }
            redisCache.deleteObject(verifyKey);
            if (!code.equalsIgnoreCase(captcha)) {
                throw new CaptchaException();
            }
        }
    }

    @PreAuthorize("@ss.hasPermi('manage:enrollNote:import')")
    @Log(title = "导入通知书信息", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file) throws Exception {
        ExcelUtil<EnrollNote> util = new ExcelUtil<EnrollNote>(EnrollNote.class);
        List<EnrollNote> basicList = util.importExcel(file.getInputStream());
        String message = enrollNoteService.importEnrollNode(basicList);
        return success(message);
    }

    @PreAuthorize("@ss.hasPermi('manage:enrollNote:import')")
    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response) {
        ExcelUtil<EnrollNote> util = new ExcelUtil<EnrollNote>(EnrollNote.class);
        util.importTemplateExcel(response, "通知书数据");
    }
}
