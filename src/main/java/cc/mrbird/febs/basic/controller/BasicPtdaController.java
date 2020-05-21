package cc.mrbird.febs.basic.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.basic.entity.BasicPtda;
import cc.mrbird.febs.basic.service.IBasicPtdaService;
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
 * 平台档案 Controller
 *
 * @author MrBird
 * @date 2020-05-16 20:10:58
 */
@Slf4j
@Validated
@Controller
public class BasicPtdaController extends BaseController {

    @Autowired
    private IBasicPtdaService basicPtdaService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "basicPtda")
    public String basicPtdaIndex(){
        return FebsUtil.view("basicPtda/basicPtda");
    }

    @GetMapping("basicPtda")
    @ResponseBody
    @RequiresPermissions("basicPtda:list")
    public FebsResponse getAllBasicPtdas(BasicPtda basicPtda) {
        return new FebsResponse().success().data(basicPtdaService.findBasicPtdas(basicPtda));
    }

    @GetMapping("basicPtda/list")
    @ResponseBody
    @RequiresPermissions("basicPtda:list")
    public FebsResponse basicPtdaList(QueryRequest request, BasicPtda basicPtda) {
        Map<String, Object> dataTable = getDataTable(this.basicPtdaService.findBasicPtdas(request, basicPtda));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增BasicPtda", exceptionMessage = "新增BasicPtda失败")
    @PostMapping("basicPtda")
    @ResponseBody
    @RequiresPermissions("basicPtda:add")
    public FebsResponse addBasicPtda(@Valid BasicPtda basicPtda) {
        this.basicPtdaService.createBasicPtda(basicPtda);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除BasicPtda", exceptionMessage = "删除BasicPtda失败")
    @GetMapping("basicPtda/delete")
    @ResponseBody
    @RequiresPermissions("basicPtda:delete")
    public FebsResponse deleteBasicPtda(BasicPtda basicPtda) {
        this.basicPtdaService.deleteBasicPtda(basicPtda);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改BasicPtda", exceptionMessage = "修改BasicPtda失败")
    @PostMapping("basicPtda/update")
    @ResponseBody
    @RequiresPermissions("basicPtda:update")
    public FebsResponse updateBasicPtda(BasicPtda basicPtda) {
        this.basicPtdaService.updateBasicPtda(basicPtda);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改BasicPtda", exceptionMessage = "导出Excel失败")
    @PostMapping("basicPtda/excel")
    @ResponseBody
    @RequiresPermissions("basicPtda:export")
    public void export(QueryRequest queryRequest, BasicPtda basicPtda, HttpServletResponse response) {
        List<BasicPtda> basicPtdas = this.basicPtdaService.findBasicPtdas(queryRequest, basicPtda).getRecords();
        ExcelKit.$Export(BasicPtda.class, response).downXlsx(basicPtdas, false);
    }
}
