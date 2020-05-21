package cc.mrbird.febs.basic.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.basic.entity.BasicLlly;
import cc.mrbird.febs.basic.service.IBasicLllyService;
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
 * 流量来源 Controller
 *
 * @author MrBird
 * @date 2020-05-16 20:10:56
 */
@Slf4j
@Validated
@Controller
public class BasicLllyController extends BaseController {

    @Autowired
    private IBasicLllyService basicLllyService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "basicLlly")
    public String basicLllyIndex(){
        return FebsUtil.view("basicLlly/basicLlly");
    }

    @GetMapping("basicLlly")
    @ResponseBody
    @RequiresPermissions("basicLlly:list")
    public FebsResponse getAllBasicLllys(BasicLlly basicLlly) {
        return new FebsResponse().success().data(basicLllyService.findBasicLllys(basicLlly));
    }

    @GetMapping("basicLlly/list")
    @ResponseBody
    @RequiresPermissions("basicLlly:list")
    public FebsResponse basicLllyList(QueryRequest request, BasicLlly basicLlly) {
        Map<String, Object> dataTable = getDataTable(this.basicLllyService.findBasicLllys(request, basicLlly));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增BasicLlly", exceptionMessage = "新增BasicLlly失败")
    @PostMapping("basicLlly")
    @ResponseBody
    @RequiresPermissions("basicLlly:add")
    public FebsResponse addBasicLlly(@Valid BasicLlly basicLlly) {
        this.basicLllyService.createBasicLlly(basicLlly);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除BasicLlly", exceptionMessage = "删除BasicLlly失败")
    @GetMapping("basicLlly/delete")
    @ResponseBody
    @RequiresPermissions("basicLlly:delete")
    public FebsResponse deleteBasicLlly(BasicLlly basicLlly) {
        this.basicLllyService.deleteBasicLlly(basicLlly);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改BasicLlly", exceptionMessage = "修改BasicLlly失败")
    @PostMapping("basicLlly/update")
    @ResponseBody
    @RequiresPermissions("basicLlly:update")
    public FebsResponse updateBasicLlly(BasicLlly basicLlly) {
        this.basicLllyService.updateBasicLlly(basicLlly);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改BasicLlly", exceptionMessage = "导出Excel失败")
    @PostMapping("basicLlly/excel")
    @ResponseBody
    @RequiresPermissions("basicLlly:export")
    public void export(QueryRequest queryRequest, BasicLlly basicLlly, HttpServletResponse response) {
        List<BasicLlly> basicLllys = this.basicLllyService.findBasicLllys(queryRequest, basicLlly).getRecords();
        ExcelKit.$Export(BasicLlly.class, response).downXlsx(basicLllys, false);
    }
}
