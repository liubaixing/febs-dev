package cc.mrbird.febs.basic.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.basic.entity.BasicFklx;
import cc.mrbird.febs.basic.service.IBasicFklxService;
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
 * 付款类型 Controller
 *
 * @author MrBird
 * @date 2020-05-16 20:10:33
 */
@Slf4j
@Validated
@Controller
public class BasicFklxController extends BaseController {

    @Autowired
    private IBasicFklxService basicFklxService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "basicFklx")
    public String basicFklxIndex(){
        return FebsUtil.view("basicFklx/basicFklx");
    }

    @GetMapping("basicFklx")
    @ResponseBody
    @RequiresPermissions("basicFklx:list")
    public FebsResponse getAllBasicFklxs(BasicFklx basicFklx) {
        return new FebsResponse().success().data(basicFklxService.findBasicFklxs(basicFklx));
    }

    @GetMapping("basicFklx/list")
    @ResponseBody
    @RequiresPermissions("basicFklx:list")
    public FebsResponse basicFklxList(QueryRequest request, BasicFklx basicFklx) {
        Map<String, Object> dataTable = getDataTable(this.basicFklxService.findBasicFklxs(request, basicFklx));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增BasicFklx", exceptionMessage = "新增BasicFklx失败")
    @PostMapping("basicFklx")
    @ResponseBody
    @RequiresPermissions("basicFklx:add")
    public FebsResponse addBasicFklx(@Valid BasicFklx basicFklx) {
        this.basicFklxService.createBasicFklx(basicFklx);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除BasicFklx", exceptionMessage = "删除BasicFklx失败")
    @GetMapping("basicFklx/delete")
    @ResponseBody
    @RequiresPermissions("basicFklx:delete")
    public FebsResponse deleteBasicFklx(BasicFklx basicFklx) {
        this.basicFklxService.deleteBasicFklx(basicFklx);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改BasicFklx", exceptionMessage = "修改BasicFklx失败")
    @PostMapping("basicFklx/update")
    @ResponseBody
    @RequiresPermissions("basicFklx:update")
    public FebsResponse updateBasicFklx(BasicFklx basicFklx) {
        this.basicFklxService.updateBasicFklx(basicFklx);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改BasicFklx", exceptionMessage = "导出Excel失败")
    @PostMapping("basicFklx/excel")
    @ResponseBody
    @RequiresPermissions("basicFklx:export")
    public void export(QueryRequest queryRequest, BasicFklx basicFklx, HttpServletResponse response) {
        List<BasicFklx> basicFklxs = this.basicFklxService.findBasicFklxs(queryRequest, basicFklx).getRecords();
        ExcelKit.$Export(BasicFklx.class, response).downXlsx(basicFklxs, false);
    }
}
