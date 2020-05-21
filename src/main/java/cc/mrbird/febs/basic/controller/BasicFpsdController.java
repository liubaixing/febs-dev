package cc.mrbird.febs.basic.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.basic.entity.BasicFpsd;
import cc.mrbird.febs.basic.service.IBasicFpsdService;
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
 * 发票税点 Controller
 *
 * @author MrBird
 * @date 2020-05-16 20:10:40
 */
@Slf4j
@Validated
@Controller
public class BasicFpsdController extends BaseController {

    @Autowired
    private IBasicFpsdService basicFpsdService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "basicFpsd")
    public String basicFpsdIndex(){
        return FebsUtil.view("basicFpsd/basicFpsd");
    }

    @GetMapping("basicFpsd")
    @ResponseBody
    @RequiresPermissions("basicFpsd:list")
    public FebsResponse getAllBasicFpsds(BasicFpsd basicFpsd) {
        return new FebsResponse().success().data(basicFpsdService.findBasicFpsds(basicFpsd));
    }

    @GetMapping("basicFpsd/list")
    @ResponseBody
    @RequiresPermissions("basicFpsd:list")
    public FebsResponse basicFpsdList(QueryRequest request, BasicFpsd basicFpsd) {
        Map<String, Object> dataTable = getDataTable(this.basicFpsdService.findBasicFpsds(request, basicFpsd));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增BasicFpsd", exceptionMessage = "新增BasicFpsd失败")
    @PostMapping("basicFpsd")
    @ResponseBody
    @RequiresPermissions("basicFpsd:add")
    public FebsResponse addBasicFpsd(@Valid BasicFpsd basicFpsd) {
        this.basicFpsdService.createBasicFpsd(basicFpsd);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除BasicFpsd", exceptionMessage = "删除BasicFpsd失败")
    @GetMapping("basicFpsd/delete")
    @ResponseBody
    @RequiresPermissions("basicFpsd:delete")
    public FebsResponse deleteBasicFpsd(BasicFpsd basicFpsd) {
        this.basicFpsdService.deleteBasicFpsd(basicFpsd);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改BasicFpsd", exceptionMessage = "修改BasicFpsd失败")
    @PostMapping("basicFpsd/update")
    @ResponseBody
    @RequiresPermissions("basicFpsd:update")
    public FebsResponse updateBasicFpsd(BasicFpsd basicFpsd) {
        this.basicFpsdService.updateBasicFpsd(basicFpsd);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改BasicFpsd", exceptionMessage = "导出Excel失败")
    @PostMapping("basicFpsd/excel")
    @ResponseBody
    @RequiresPermissions("basicFpsd:export")
    public void export(QueryRequest queryRequest, BasicFpsd basicFpsd, HttpServletResponse response) {
        List<BasicFpsd> basicFpsds = this.basicFpsdService.findBasicFpsds(queryRequest, basicFpsd).getRecords();
        ExcelKit.$Export(BasicFpsd.class, response).downXlsx(basicFpsds, false);
    }
}
