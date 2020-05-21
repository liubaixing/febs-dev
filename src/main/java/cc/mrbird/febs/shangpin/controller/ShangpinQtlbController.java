package cc.mrbird.febs.shangpin.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.shangpin.entity.ShangpinQtlb;
import cc.mrbird.febs.shangpin.service.IShangpinQtlbService;
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
 * 其他类别 Controller
 *
 * @author MrBird
 * @date 2020-05-16 20:03:57
 */
@Slf4j
@Validated
@Controller
public class ShangpinQtlbController extends BaseController {

    @Autowired
    private IShangpinQtlbService shangpinQtlbService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "shangpinQtlb")
    public String shangpinQtlbIndex(){
        return FebsUtil.view("shangpinQtlb/shangpinQtlb");
    }

    @GetMapping("shangpinQtlb")
    @ResponseBody
    @RequiresPermissions("shangpinQtlb:list")
    public FebsResponse getAllShangpinQtlbs(ShangpinQtlb shangpinQtlb) {
        return new FebsResponse().success().data(shangpinQtlbService.findShangpinQtlbs(shangpinQtlb));
    }

    @GetMapping("shangpinQtlb/list")
    @ResponseBody
    @RequiresPermissions("shangpinQtlb:list")
    public FebsResponse shangpinQtlbList(QueryRequest request, ShangpinQtlb shangpinQtlb) {
        Map<String, Object> dataTable = getDataTable(this.shangpinQtlbService.findShangpinQtlbs(request, shangpinQtlb));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增ShangpinQtlb", exceptionMessage = "新增ShangpinQtlb失败")
    @PostMapping("shangpinQtlb")
    @ResponseBody
    @RequiresPermissions("shangpinQtlb:add")
    public FebsResponse addShangpinQtlb(@Valid ShangpinQtlb shangpinQtlb) {
        this.shangpinQtlbService.createShangpinQtlb(shangpinQtlb);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除ShangpinQtlb", exceptionMessage = "删除ShangpinQtlb失败")
    @GetMapping("shangpinQtlb/delete")
    @ResponseBody
    @RequiresPermissions("shangpinQtlb:delete")
    public FebsResponse deleteShangpinQtlb(ShangpinQtlb shangpinQtlb) {
        this.shangpinQtlbService.deleteShangpinQtlb(shangpinQtlb);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改ShangpinQtlb", exceptionMessage = "修改ShangpinQtlb失败")
    @PostMapping("shangpinQtlb/update")
    @ResponseBody
    @RequiresPermissions("shangpinQtlb:update")
    public FebsResponse updateShangpinQtlb(ShangpinQtlb shangpinQtlb) {
        this.shangpinQtlbService.updateShangpinQtlb(shangpinQtlb);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改ShangpinQtlb", exceptionMessage = "导出Excel失败")
    @PostMapping("shangpinQtlb/excel")
    @ResponseBody
    @RequiresPermissions("shangpinQtlb:export")
    public void export(QueryRequest queryRequest, ShangpinQtlb shangpinQtlb, HttpServletResponse response) {
        List<ShangpinQtlb> shangpinQtlbs = this.shangpinQtlbService.findShangpinQtlbs(queryRequest, shangpinQtlb).getRecords();
        ExcelKit.$Export(ShangpinQtlb.class, response).downXlsx(shangpinQtlbs, false);
    }
}
