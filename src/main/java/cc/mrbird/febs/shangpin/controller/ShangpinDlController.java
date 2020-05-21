package cc.mrbird.febs.shangpin.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.shangpin.entity.ShangpinDl;
import cc.mrbird.febs.shangpin.service.IShangpinDlService;
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
 * 大类 Controller
 *
 * @author MrBird
 * @date 2020-05-16 20:03:49
 */
@Slf4j
@Validated
@Controller
public class ShangpinDlController extends BaseController {

    @Autowired
    private IShangpinDlService shangpinDlService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "shangpinDl")
    public String shangpinDlIndex(){
        return FebsUtil.view("shangpinDl/shangpinDl");
    }

    @GetMapping("shangpinDl")
    @ResponseBody
    @RequiresPermissions("shangpinDl:list")
    public FebsResponse getAllShangpinDls(ShangpinDl shangpinDl) {
        return new FebsResponse().success().data(shangpinDlService.findShangpinDls(shangpinDl));
    }

    @GetMapping("shangpinDl/list")
    @ResponseBody
    @RequiresPermissions("shangpinDl:list")
    public FebsResponse shangpinDlList(QueryRequest request, ShangpinDl shangpinDl) {
        Map<String, Object> dataTable = getDataTable(this.shangpinDlService.findShangpinDls(request, shangpinDl));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增ShangpinDl", exceptionMessage = "新增ShangpinDl失败")
    @PostMapping("shangpinDl")
    @ResponseBody
    @RequiresPermissions("shangpinDl:add")
    public FebsResponse addShangpinDl(@Valid ShangpinDl shangpinDl) {
        this.shangpinDlService.createShangpinDl(shangpinDl);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除ShangpinDl", exceptionMessage = "删除ShangpinDl失败")
    @GetMapping("shangpinDl/delete")
    @ResponseBody
    @RequiresPermissions("shangpinDl:delete")
    public FebsResponse deleteShangpinDl(ShangpinDl shangpinDl) {
        this.shangpinDlService.deleteShangpinDl(shangpinDl);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改ShangpinDl", exceptionMessage = "修改ShangpinDl失败")
    @PostMapping("shangpinDl/update")
    @ResponseBody
    @RequiresPermissions("shangpinDl:update")
    public FebsResponse updateShangpinDl(ShangpinDl shangpinDl) {
        this.shangpinDlService.updateShangpinDl(shangpinDl);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改ShangpinDl", exceptionMessage = "导出Excel失败")
    @PostMapping("shangpinDl/excel")
    @ResponseBody
    @RequiresPermissions("shangpinDl:export")
    public void export(QueryRequest queryRequest, ShangpinDl shangpinDl, HttpServletResponse response) {
        List<ShangpinDl> shangpinDls = this.shangpinDlService.findShangpinDls(queryRequest, shangpinDl).getRecords();
        ExcelKit.$Export(ShangpinDl.class, response).downXlsx(shangpinDls, false);
    }
}
