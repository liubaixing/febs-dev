package cc.mrbird.febs.basic.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.basic.entity.BasicKhyh;
import cc.mrbird.febs.basic.service.IBasicKhyhService;
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
 * 开户银行 Controller
 *
 * @author MrBird
 * @date 2020-05-16 20:10:54
 */
@Slf4j
@Validated
@Controller
public class BasicKhyhController extends BaseController {

    @Autowired
    private IBasicKhyhService basicKhyhService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "basicKhyh")
    public String basicKhyhIndex(){
        return FebsUtil.view("basicKhyh/basicKhyh");
    }

    @GetMapping("basicKhyh")
    @ResponseBody
    @RequiresPermissions("basicKhyh:list")
    public FebsResponse getAllBasicKhyhs(BasicKhyh basicKhyh) {
        return new FebsResponse().success().data(basicKhyhService.findBasicKhyhs(basicKhyh));
    }

    @GetMapping("basicKhyh/list")
    @ResponseBody
    @RequiresPermissions("basicKhyh:list")
    public FebsResponse basicKhyhList(QueryRequest request, BasicKhyh basicKhyh) {
        Map<String, Object> dataTable = getDataTable(this.basicKhyhService.findBasicKhyhs(request, basicKhyh));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增BasicKhyh", exceptionMessage = "新增BasicKhyh失败")
    @PostMapping("basicKhyh")
    @ResponseBody
    @RequiresPermissions("basicKhyh:add")
    public FebsResponse addBasicKhyh(@Valid BasicKhyh basicKhyh) {
        this.basicKhyhService.createBasicKhyh(basicKhyh);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除BasicKhyh", exceptionMessage = "删除BasicKhyh失败")
    @GetMapping("basicKhyh/delete")
    @ResponseBody
    @RequiresPermissions("basicKhyh:delete")
    public FebsResponse deleteBasicKhyh(BasicKhyh basicKhyh) {
        this.basicKhyhService.deleteBasicKhyh(basicKhyh);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改BasicKhyh", exceptionMessage = "修改BasicKhyh失败")
    @PostMapping("basicKhyh/update")
    @ResponseBody
    @RequiresPermissions("basicKhyh:update")
    public FebsResponse updateBasicKhyh(BasicKhyh basicKhyh) {
        this.basicKhyhService.updateBasicKhyh(basicKhyh);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改BasicKhyh", exceptionMessage = "导出Excel失败")
    @PostMapping("basicKhyh/excel")
    @ResponseBody
    @RequiresPermissions("basicKhyh:export")
    public void export(QueryRequest queryRequest, BasicKhyh basicKhyh, HttpServletResponse response) {
        List<BasicKhyh> basicKhyhs = this.basicKhyhService.findBasicKhyhs(queryRequest, basicKhyh).getRecords();
        ExcelKit.$Export(BasicKhyh.class, response).downXlsx(basicKhyhs, false);
    }
}
