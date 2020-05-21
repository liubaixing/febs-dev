package cc.mrbird.febs.basic.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.basic.entity.BasicKhlb;
import cc.mrbird.febs.basic.service.IBasicKhlbService;
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
 * 客户类别 Controller
 *
 * @author MrBird
 * @date 2020-05-16 20:10:49
 */
@Slf4j
@Validated
@Controller
public class BasicKhlbController extends BaseController {

    @Autowired
    private IBasicKhlbService basicKhlbService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "basicKhlb")
    public String basicKhlbIndex(){
        return FebsUtil.view("basicKhlb/basicKhlb");
    }

    @GetMapping("basicKhlb")
    @ResponseBody
    @RequiresPermissions("basicKhlb:list")
    public FebsResponse getAllBasicKhlbs(BasicKhlb basicKhlb) {
        return new FebsResponse().success().data(basicKhlbService.findBasicKhlbs(basicKhlb));
    }

    @GetMapping("basicKhlb/list")
    @ResponseBody
    @RequiresPermissions("basicKhlb:list")
    public FebsResponse basicKhlbList(QueryRequest request, BasicKhlb basicKhlb) {
        Map<String, Object> dataTable = getDataTable(this.basicKhlbService.findBasicKhlbs(request, basicKhlb));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增BasicKhlb", exceptionMessage = "新增BasicKhlb失败")
    @PostMapping("basicKhlb")
    @ResponseBody
    @RequiresPermissions("basicKhlb:add")
    public FebsResponse addBasicKhlb(@Valid BasicKhlb basicKhlb) {
        this.basicKhlbService.createBasicKhlb(basicKhlb);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除BasicKhlb", exceptionMessage = "删除BasicKhlb失败")
    @GetMapping("basicKhlb/delete")
    @ResponseBody
    @RequiresPermissions("basicKhlb:delete")
    public FebsResponse deleteBasicKhlb(BasicKhlb basicKhlb) {
        this.basicKhlbService.deleteBasicKhlb(basicKhlb);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改BasicKhlb", exceptionMessage = "修改BasicKhlb失败")
    @PostMapping("basicKhlb/update")
    @ResponseBody
    @RequiresPermissions("basicKhlb:update")
    public FebsResponse updateBasicKhlb(BasicKhlb basicKhlb) {
        this.basicKhlbService.updateBasicKhlb(basicKhlb);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改BasicKhlb", exceptionMessage = "导出Excel失败")
    @PostMapping("basicKhlb/excel")
    @ResponseBody
    @RequiresPermissions("basicKhlb:export")
    public void export(QueryRequest queryRequest, BasicKhlb basicKhlb, HttpServletResponse response) {
        List<BasicKhlb> basicKhlbs = this.basicKhlbService.findBasicKhlbs(queryRequest, basicKhlb).getRecords();
        ExcelKit.$Export(BasicKhlb.class, response).downXlsx(basicKhlbs, false);
    }
}
