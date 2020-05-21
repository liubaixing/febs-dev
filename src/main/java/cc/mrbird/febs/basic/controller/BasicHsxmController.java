package cc.mrbird.febs.basic.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.basic.entity.BasicHsxm;
import cc.mrbird.febs.basic.service.IBasicHsxmService;
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
 * 核算项目 Controller
 *
 * @author MrBird
 * @date 2020-05-16 20:10:43
 */
@Slf4j
@Validated
@Controller
public class BasicHsxmController extends BaseController {

    @Autowired
    private IBasicHsxmService basicHsxmService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "basicHsxm")
    public String basicHsxmIndex(){
        return FebsUtil.view("basicHsxm/basicHsxm");
    }

    @GetMapping("basicHsxm")
    @ResponseBody
    @RequiresPermissions("basicHsxm:list")
    public FebsResponse getAllBasicHsxms(BasicHsxm basicHsxm) {
        return new FebsResponse().success().data(basicHsxmService.findBasicHsxms(basicHsxm));
    }

    @GetMapping("basicHsxm/list")
    @ResponseBody
    @RequiresPermissions("basicHsxm:list")
    public FebsResponse basicHsxmList(QueryRequest request, BasicHsxm basicHsxm) {
        Map<String, Object> dataTable = getDataTable(this.basicHsxmService.findBasicHsxms(request, basicHsxm));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增BasicHsxm", exceptionMessage = "新增BasicHsxm失败")
    @PostMapping("basicHsxm")
    @ResponseBody
    @RequiresPermissions("basicHsxm:add")
    public FebsResponse addBasicHsxm(@Valid BasicHsxm basicHsxm) {
        this.basicHsxmService.createBasicHsxm(basicHsxm);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除BasicHsxm", exceptionMessage = "删除BasicHsxm失败")
    @GetMapping("basicHsxm/delete")
    @ResponseBody
    @RequiresPermissions("basicHsxm:delete")
    public FebsResponse deleteBasicHsxm(BasicHsxm basicHsxm) {
        this.basicHsxmService.deleteBasicHsxm(basicHsxm);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改BasicHsxm", exceptionMessage = "修改BasicHsxm失败")
    @PostMapping("basicHsxm/update")
    @ResponseBody
    @RequiresPermissions("basicHsxm:update")
    public FebsResponse updateBasicHsxm(BasicHsxm basicHsxm) {
        this.basicHsxmService.updateBasicHsxm(basicHsxm);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改BasicHsxm", exceptionMessage = "导出Excel失败")
    @PostMapping("basicHsxm/excel")
    @ResponseBody
    @RequiresPermissions("basicHsxm:export")
    public void export(QueryRequest queryRequest, BasicHsxm basicHsxm, HttpServletResponse response) {
        List<BasicHsxm> basicHsxms = this.basicHsxmService.findBasicHsxms(queryRequest, basicHsxm).getRecords();
        ExcelKit.$Export(BasicHsxm.class, response).downXlsx(basicHsxms, false);
    }
}
