package cc.mrbird.febs.shangpin.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.shangpin.entity.ShangpinXl;
import cc.mrbird.febs.shangpin.service.IShangpinXlService;
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
 * 小类 Controller
 *
 * @author MrBird
 * @date 2020-05-16 20:03:58
 */
@Slf4j
@Validated
@Controller
public class ShangpinXlController extends BaseController {

    @Autowired
    private IShangpinXlService shangpinXlService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "shangpinXl")
    public String shangpinXlIndex(){
        return FebsUtil.view("shangpinXl/shangpinXl");
    }

    @GetMapping("shangpinXl")
    @ResponseBody
    @RequiresPermissions("shangpinXl:list")
    public FebsResponse getAllShangpinXls(ShangpinXl shangpinXl) {
        return new FebsResponse().success().data(shangpinXlService.findShangpinXls(shangpinXl));
    }

    @GetMapping("shangpinXl/list")
    @ResponseBody
    @RequiresPermissions("shangpinXl:list")
    public FebsResponse shangpinXlList(QueryRequest request, ShangpinXl shangpinXl) {
        Map<String, Object> dataTable = getDataTable(this.shangpinXlService.findShangpinXls(request, shangpinXl));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增ShangpinXl", exceptionMessage = "新增ShangpinXl失败")
    @PostMapping("shangpinXl")
    @ResponseBody
    @RequiresPermissions("shangpinXl:add")
    public FebsResponse addShangpinXl(@Valid ShangpinXl shangpinXl) {
        this.shangpinXlService.createShangpinXl(shangpinXl);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除ShangpinXl", exceptionMessage = "删除ShangpinXl失败")
    @GetMapping("shangpinXl/delete")
    @ResponseBody
    @RequiresPermissions("shangpinXl:delete")
    public FebsResponse deleteShangpinXl(ShangpinXl shangpinXl) {
        this.shangpinXlService.deleteShangpinXl(shangpinXl);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改ShangpinXl", exceptionMessage = "修改ShangpinXl失败")
    @PostMapping("shangpinXl/update")
    @ResponseBody
    @RequiresPermissions("shangpinXl:update")
    public FebsResponse updateShangpinXl(ShangpinXl shangpinXl) {
        this.shangpinXlService.updateShangpinXl(shangpinXl);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改ShangpinXl", exceptionMessage = "导出Excel失败")
    @PostMapping("shangpinXl/excel")
    @ResponseBody
    @RequiresPermissions("shangpinXl:export")
    public void export(QueryRequest queryRequest, ShangpinXl shangpinXl, HttpServletResponse response) {
        List<ShangpinXl> shangpinXls = this.shangpinXlService.findShangpinXls(queryRequest, shangpinXl).getRecords();
        ExcelKit.$Export(ShangpinXl.class, response).downXlsx(shangpinXls, false);
    }
}
