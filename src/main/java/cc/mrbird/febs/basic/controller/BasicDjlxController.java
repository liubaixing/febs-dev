package cc.mrbird.febs.basic.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.basic.entity.BasicDjlx;
import cc.mrbird.febs.basic.service.IBasicDjlxService;
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
 * 单据类型 Controller
 *
 * @author MrBird
 * @date 2020-05-16 20:10:31
 */
@Slf4j
@Validated
@Controller
public class BasicDjlxController extends BaseController {

    @Autowired
    private IBasicDjlxService basicDjlxService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "basicDjlx")
    public String basicDjlxIndex(){
        return FebsUtil.view("basicDjlx/basicDjlx");
    }

    @GetMapping("basicDjlx")
    @ResponseBody
    @RequiresPermissions("basicDjlx:list")
    public FebsResponse getAllBasicDjlxs(BasicDjlx basicDjlx) {
        return new FebsResponse().success().data(basicDjlxService.findBasicDjlxs(basicDjlx));
    }

    @GetMapping("basicDjlx/list")
    @ResponseBody
    @RequiresPermissions("basicDjlx:list")
    public FebsResponse basicDjlxList(QueryRequest request, BasicDjlx basicDjlx) {
        Map<String, Object> dataTable = getDataTable(this.basicDjlxService.findBasicDjlxs(request, basicDjlx));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增BasicDjlx", exceptionMessage = "新增BasicDjlx失败")
    @PostMapping("basicDjlx")
    @ResponseBody
    @RequiresPermissions("basicDjlx:add")
    public FebsResponse addBasicDjlx(@Valid BasicDjlx basicDjlx) {
        this.basicDjlxService.createBasicDjlx(basicDjlx);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除BasicDjlx", exceptionMessage = "删除BasicDjlx失败")
    @GetMapping("basicDjlx/delete")
    @ResponseBody
    @RequiresPermissions("basicDjlx:delete")
    public FebsResponse deleteBasicDjlx(BasicDjlx basicDjlx) {
        this.basicDjlxService.deleteBasicDjlx(basicDjlx);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改BasicDjlx", exceptionMessage = "修改BasicDjlx失败")
    @PostMapping("basicDjlx/update")
    @ResponseBody
    @RequiresPermissions("basicDjlx:update")
    public FebsResponse updateBasicDjlx(BasicDjlx basicDjlx) {
        this.basicDjlxService.updateBasicDjlx(basicDjlx);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改BasicDjlx", exceptionMessage = "导出Excel失败")
    @PostMapping("basicDjlx/excel")
    @ResponseBody
    @RequiresPermissions("basicDjlx:export")
    public void export(QueryRequest queryRequest, BasicDjlx basicDjlx, HttpServletResponse response) {
        List<BasicDjlx> basicDjlxs = this.basicDjlxService.findBasicDjlxs(queryRequest, basicDjlx).getRecords();
        ExcelKit.$Export(BasicDjlx.class, response).downXlsx(basicDjlxs, false);
    }
}
