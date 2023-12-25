package com.lego.crm.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lego.core.dto.LegoPage;
import com.lego.core.util.ExcelUtil;
import com.lego.core.vo.GenericSearchVO;
import com.lego.core.vo.JsonResponse;
import com.lego.core.web.BaseController;
import com.lego.crm.dto.CrmLeadInfo;
import com.lego.crm.service.ICrmLeadService;
import com.lego.crm.vo.CrmLeadCreateVO;
import com.lego.crm.vo.CrmLeadModifyVO;

import cn.dev33.satoken.annotation.SaCheckPermission;

@RestController
@RequestMapping("/back-end/crm-lead")
public class CrmLeadController extends BaseController {

    @Autowired
    private ICrmLeadService leadService;

    @PostMapping("/add")
    @SaCheckPermission("crm:lead:add")
    public JsonResponse<Object> add(@RequestBody CrmLeadCreateVO vo) {
        leadService.add(getLoginCode(), vo);
        return JsonResponse.success();
    }

    @PostMapping("/update")
    @SaCheckPermission("crm:lead:update")
    public JsonResponse<Object> update(@RequestBody CrmLeadModifyVO vo) {
        leadService.update(getLoginCode(), vo);
        return JsonResponse.success();
    }

    @PostMapping("/delete")
    @SaCheckPermission("crm:lead:delete")
    public JsonResponse<Object> delete(@RequestBody List<String> codes) {
        leadService.delete(getLoginCode(), codes);
        return JsonResponse.success();
    }

    @PostMapping("/list")
    @SaCheckPermission("crm:lead:read")
    public JsonResponse<LegoPage<CrmLeadInfo>> list(@RequestBody GenericSearchVO vo) {
        return JsonResponse.success(leadService.findPageBy(vo));
    }

    @GetMapping("/get/{code}")
    @SaCheckPermission("crm:lead:detail")
    public JsonResponse<CrmLeadInfo> getByCode(@PathVariable String code) {
        return JsonResponse.success(leadService.findBy(code));
    }

    @PostMapping("/export")
    @SaCheckPermission("crm:lead:export")
    public void exportAll(@RequestBody List<String> codes, HttpServletResponse response) {
        List<CrmLeadInfo> datas = leadService.findBy(codes);
        ExcelUtil.exportExcel(datas, "线索数据", CrmLeadInfo.class, response);
    }

    @PostMapping("/export-all")
    @SaCheckPermission("crm:lead:export")
    public void exportAll(@RequestBody GenericSearchVO vo, HttpServletResponse response) {
        List<CrmLeadInfo> datas = leadService.findBy(vo);
        ExcelUtil.exportExcel(datas, "线索数据", CrmLeadInfo.class, response);
    }

}
