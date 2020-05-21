package cc.mrbird.febs.system.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.system.entity.Gongsi;
import cc.mrbird.febs.system.service.IGongsiService;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 公司管理 Controller
 *
 * @author lbx
 * @date 2020-05-16 19:58:39
 */
@Slf4j
@Validated
@Controller
public class GongsiController extends BaseController {

    @Autowired
    private IGongsiService gongsiService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "gongsi")
    public String gongsiIndex(){
        return FebsUtil.view("gongsi/gongsi");
    }

    @GetMapping("gongsi")
    @ResponseBody
    @RequiresPermissions("gongsi:list")
    public FebsResponse getAllGongsis(Gongsi gongsi) {
        return new FebsResponse().success().data(gongsiService.findGongsis(gongsi));
    }

    @GetMapping("gongsi/list")
    @ResponseBody
    @RequiresPermissions("gongsi:list")
    public FebsResponse gongsiList(QueryRequest request, Gongsi gongsi) {
        Map<String, Object> dataTable = getDataTable(this.gongsiService.findGongsis(request, gongsi));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增Gongsi", exceptionMessage = "新增Gongsi失败")
    @PostMapping("gongsi")
    @ResponseBody
    @RequiresPermissions("gongsi:add")
    public FebsResponse addGongsi(@Valid Gongsi gongsi) {
        this.gongsiService.createGongsi(gongsi);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除Gongsi", exceptionMessage = "删除Gongsi失败")
    @GetMapping("gongsi/delete")
    @ResponseBody
    @RequiresPermissions("gongsi:delete")
    public FebsResponse deleteGongsi(Gongsi gongsi) {
        this.gongsiService.deleteGongsi(gongsi);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改Gongsi", exceptionMessage = "修改Gongsi失败")
    @PostMapping("gongsi/update")
    @ResponseBody
    @RequiresPermissions("gongsi:update")
    public FebsResponse updateGongsi(Gongsi gongsi) {
        this.gongsiService.updateGongsi(gongsi);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改Gongsi", exceptionMessage = "导出Excel失败")
    @PostMapping("gongsi/excel")
    @ResponseBody
    @RequiresPermissions("gongsi:export")
    public void export(QueryRequest queryRequest, Gongsi gongsi, HttpServletResponse response) {
        List<Gongsi> gongsis = this.gongsiService.findGongsis(queryRequest, gongsi).getRecords();
        ExcelKit.$Export(Gongsi.class, response).downXlsx(gongsis, false);
    }
}
