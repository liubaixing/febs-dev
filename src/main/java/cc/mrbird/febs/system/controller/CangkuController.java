package cc.mrbird.febs.system.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.system.entity.Cangku;
import cc.mrbird.febs.system.service.ICangkuService;
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
 * 仓库管理 Controller
 *
 * @author lbx
 * @date 2020-05-16 19:57:45
 */
@Slf4j
@Validated
@Controller
public class CangkuController extends BaseController {

    @Autowired
    private ICangkuService cangkuService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "cangku")
    public String cangkuIndex(){
        return FebsUtil.view("cangku/cangku");
    }

    @GetMapping("cangku")
    @ResponseBody
    @RequiresPermissions("cangku:list")
    public FebsResponse getAllCangkus(Cangku cangku) {
        return new FebsResponse().success().data(cangkuService.findCangkus(cangku));
    }

    @GetMapping("cangku/list")
    @ResponseBody
    @RequiresPermissions("cangku:list")
    public FebsResponse cangkuList(QueryRequest request, Cangku cangku) {
        Map<String, Object> dataTable = getDataTable(this.cangkuService.findCangkus(request, cangku));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增Cangku", exceptionMessage = "新增Cangku失败")
    @PostMapping("cangku")
    @ResponseBody
    @RequiresPermissions("cangku:add")
    public FebsResponse addCangku(@Valid Cangku cangku) {
        this.cangkuService.createCangku(cangku);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除Cangku", exceptionMessage = "删除Cangku失败")
    @GetMapping("cangku/delete")
    @ResponseBody
    @RequiresPermissions("cangku:delete")
    public FebsResponse deleteCangku(Cangku cangku) {
        this.cangkuService.deleteCangku(cangku);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改Cangku", exceptionMessage = "修改Cangku失败")
    @PostMapping("cangku/update")
    @ResponseBody
    @RequiresPermissions("cangku:update")
    public FebsResponse updateCangku(Cangku cangku) {
        this.cangkuService.updateCangku(cangku);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改Cangku", exceptionMessage = "导出Excel失败")
    @PostMapping("cangku/excel")
    @ResponseBody
    @RequiresPermissions("cangku:export")
    public void export(QueryRequest queryRequest, Cangku cangku, HttpServletResponse response) {
        List<Cangku> cangkus = this.cangkuService.findCangkus(queryRequest, cangku).getRecords();
        ExcelKit.$Export(Cangku.class, response).downXlsx(cangkus, false);
    }
}
