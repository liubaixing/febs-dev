package cc.mrbird.febs.basic.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.basic.entity.BasicCklb;
import cc.mrbird.febs.basic.service.IBasicCklbService;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 仓库类别 Controller
 *
 * @author MrBird
 * @date 2020-05-16 20:10:27
 */
@Slf4j
@Validated
@Controller
@RequestMapping("basicCklb")
public class BasicCklbController extends BaseController {

    @Autowired
    private IBasicCklbService basicCklbService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "basicCklb")
    public String basicCklbIndex(){
        return FebsUtil.view("basicCklb/basicCklb");
    }

    @GetMapping("basicCklb")
    @ResponseBody
    @RequiresPermissions("basicCklb:list")
    public FebsResponse getAllBasicCklbs(BasicCklb basicCklb) {
        return new FebsResponse().success().data(basicCklbService.findBasicCklbs(basicCklb));
    }

    @GetMapping("/list")
    @ResponseBody
    @RequiresPermissions("basicCklb:list")
    public FebsResponse basicCklbList(QueryRequest request, BasicCklb basicCklb) {
        Map<String, Object> dataTable = getDataTable(this.basicCklbService.findBasicCklbs(request, basicCklb));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增BasicCklb", exceptionMessage = "新增BasicCklb失败")
    @PostMapping("")
    @ResponseBody
    @RequiresPermissions("basicCklb:add")
    public FebsResponse addBasicCklb(@Valid BasicCklb basicCklb) {
        this.basicCklbService.createBasicCklb(basicCklb);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除BasicCklb", exceptionMessage = "删除BasicCklb失败")
    @GetMapping("/delete")
    @ResponseBody
    @RequiresPermissions("basicCklb:delete")
    public FebsResponse deleteBasicCklb(BasicCklb basicCklb) {
        this.basicCklbService.deleteBasicCklb(basicCklb);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改BasicCklb", exceptionMessage = "修改BasicCklb失败")
    @PostMapping("/update")
    @ResponseBody
    @RequiresPermissions("basicCklb:update")
    public FebsResponse updateBasicCklb(BasicCklb basicCklb) {
        this.basicCklbService.updateBasicCklb(basicCklb);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改BasicCklb", exceptionMessage = "导出Excel失败")
    @PostMapping("/excel")
    @ResponseBody
    @RequiresPermissions("basicCklb:export")
    public void export(QueryRequest queryRequest, BasicCklb basicCklb, HttpServletResponse response) {
        List<BasicCklb> basicCklbs = this.basicCklbService.findBasicCklbs(queryRequest, basicCklb).getRecords();
        ExcelKit.$Export(BasicCklb.class, response).downXlsx(basicCklbs, false);
    }
}
