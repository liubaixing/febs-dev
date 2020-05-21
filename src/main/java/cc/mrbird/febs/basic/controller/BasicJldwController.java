package cc.mrbird.febs.basic.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.basic.entity.BasicJldw;
import cc.mrbird.febs.basic.service.IBasicJldwService;
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
 * 计量单位 Controller
 *
 * @author MrBird
 * @date 2020-05-16 20:10:45
 */
@Slf4j
@Validated
@Controller
public class BasicJldwController extends BaseController {

    @Autowired
    private IBasicJldwService basicJldwService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "basicJldw")
    public String basicJldwIndex(){
        return FebsUtil.view("basicJldw/basicJldw");
    }

    @GetMapping("basicJldw")
    @ResponseBody
    @RequiresPermissions("basicJldw:list")
    public FebsResponse getAllBasicJldws(BasicJldw basicJldw) {
        return new FebsResponse().success().data(basicJldwService.findBasicJldws(basicJldw));
    }

    @GetMapping("basicJldw/list")
    @ResponseBody
    @RequiresPermissions("basicJldw:list")
    public FebsResponse basicJldwList(QueryRequest request, BasicJldw basicJldw) {
        Map<String, Object> dataTable = getDataTable(this.basicJldwService.findBasicJldws(request, basicJldw));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增BasicJldw", exceptionMessage = "新增BasicJldw失败")
    @PostMapping("basicJldw")
    @ResponseBody
    @RequiresPermissions("basicJldw:add")
    public FebsResponse addBasicJldw(@Valid BasicJldw basicJldw) {
        this.basicJldwService.createBasicJldw(basicJldw);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除BasicJldw", exceptionMessage = "删除BasicJldw失败")
    @GetMapping("basicJldw/delete")
    @ResponseBody
    @RequiresPermissions("basicJldw:delete")
    public FebsResponse deleteBasicJldw(BasicJldw basicJldw) {
        this.basicJldwService.deleteBasicJldw(basicJldw);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改BasicJldw", exceptionMessage = "修改BasicJldw失败")
    @PostMapping("basicJldw/update")
    @ResponseBody
    @RequiresPermissions("basicJldw:update")
    public FebsResponse updateBasicJldw(BasicJldw basicJldw) {
        this.basicJldwService.updateBasicJldw(basicJldw);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改BasicJldw", exceptionMessage = "导出Excel失败")
    @PostMapping("basicJldw/excel")
    @ResponseBody
    @RequiresPermissions("basicJldw:export")
    public void export(QueryRequest queryRequest, BasicJldw basicJldw, HttpServletResponse response) {
        List<BasicJldw> basicJldws = this.basicJldwService.findBasicJldws(queryRequest, basicJldw).getRecords();
        ExcelKit.$Export(BasicJldw.class, response).downXlsx(basicJldws, false);
    }
}
