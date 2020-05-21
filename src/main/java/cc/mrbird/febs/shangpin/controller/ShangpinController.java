package cc.mrbird.febs.shangpin.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.shangpin.entity.Shangpin;
import cc.mrbird.febs.shangpin.service.IShangpinService;
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
 * 商品 Controller
 *
 * @author MrBird
 * @date 2020-05-16 20:03:47
 */
@Slf4j
@Validated
@Controller
public class ShangpinController extends BaseController {

    @Autowired
    private IShangpinService shangpinService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "shangpin")
    public String shangpinIndex(){
        return FebsUtil.view("shangpin/shangpin");
    }

    @GetMapping("shangpin")
    @ResponseBody
    @RequiresPermissions("shangpin:list")
    public FebsResponse getAllShangpins(Shangpin shangpin) {
        return new FebsResponse().success().data(shangpinService.findShangpins(shangpin));
    }

    @GetMapping("shangpin/list")
    @ResponseBody
    @RequiresPermissions("shangpin:list")
    public FebsResponse shangpinList(QueryRequest request, Shangpin shangpin) {
        Map<String, Object> dataTable = getDataTable(this.shangpinService.findShangpins(request, shangpin));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增Shangpin", exceptionMessage = "新增Shangpin失败")
    @PostMapping("shangpin")
    @ResponseBody
    @RequiresPermissions("shangpin:add")
    public FebsResponse addShangpin(@Valid Shangpin shangpin) {
        this.shangpinService.createShangpin(shangpin);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除Shangpin", exceptionMessage = "删除Shangpin失败")
    @GetMapping("shangpin/delete")
    @ResponseBody
    @RequiresPermissions("shangpin:delete")
    public FebsResponse deleteShangpin(Shangpin shangpin) {
        this.shangpinService.deleteShangpin(shangpin);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改Shangpin", exceptionMessage = "修改Shangpin失败")
    @PostMapping("shangpin/update")
    @ResponseBody
    @RequiresPermissions("shangpin:update")
    public FebsResponse updateShangpin(Shangpin shangpin) {
        this.shangpinService.updateShangpin(shangpin);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改Shangpin", exceptionMessage = "导出Excel失败")
    @PostMapping("shangpin/excel")
    @ResponseBody
    @RequiresPermissions("shangpin:export")
    public void export(QueryRequest queryRequest, Shangpin shangpin, HttpServletResponse response) {
        List<Shangpin> shangpins = this.shangpinService.findShangpins(queryRequest, shangpin).getRecords();
        ExcelKit.$Export(Shangpin.class, response).downXlsx(shangpins, false);
    }
}
