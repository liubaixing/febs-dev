package cc.mrbird.febs.shangpin.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.shangpin.entity.ShangpinGys;
import cc.mrbird.febs.shangpin.service.IShangpinGysService;
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
 * 供应商商品 Controller
 *
 * @author MrBird
 * @date 2020-05-16 20:03:51
 */
@Slf4j
@Validated
@Controller
public class ShangpinGysController extends BaseController {

    @Autowired
    private IShangpinGysService shangpinGysService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "shangpinGys")
    public String shangpinGysIndex(){
        return FebsUtil.view("shangpinGys/shangpinGys");
    }

    @GetMapping("shangpinGys")
    @ResponseBody
    @RequiresPermissions("shangpinGys:list")
    public FebsResponse getAllShangpinGyss(ShangpinGys shangpinGys) {
        return new FebsResponse().success().data(shangpinGysService.findShangpinGyss(shangpinGys));
    }

    @GetMapping("shangpinGys/list")
    @ResponseBody
    @RequiresPermissions("shangpinGys:list")
    public FebsResponse shangpinGysList(QueryRequest request, ShangpinGys shangpinGys) {
        Map<String, Object> dataTable = getDataTable(this.shangpinGysService.findShangpinGyss(request, shangpinGys));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增ShangpinGys", exceptionMessage = "新增ShangpinGys失败")
    @PostMapping("shangpinGys")
    @ResponseBody
    @RequiresPermissions("shangpinGys:add")
    public FebsResponse addShangpinGys(@Valid ShangpinGys shangpinGys) {
        this.shangpinGysService.createShangpinGys(shangpinGys);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除ShangpinGys", exceptionMessage = "删除ShangpinGys失败")
    @GetMapping("shangpinGys/delete")
    @ResponseBody
    @RequiresPermissions("shangpinGys:delete")
    public FebsResponse deleteShangpinGys(ShangpinGys shangpinGys) {
        this.shangpinGysService.deleteShangpinGys(shangpinGys);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改ShangpinGys", exceptionMessage = "修改ShangpinGys失败")
    @PostMapping("shangpinGys/update")
    @ResponseBody
    @RequiresPermissions("shangpinGys:update")
    public FebsResponse updateShangpinGys(ShangpinGys shangpinGys) {
        this.shangpinGysService.updateShangpinGys(shangpinGys);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改ShangpinGys", exceptionMessage = "导出Excel失败")
    @PostMapping("shangpinGys/excel")
    @ResponseBody
    @RequiresPermissions("shangpinGys:export")
    public void export(QueryRequest queryRequest, ShangpinGys shangpinGys, HttpServletResponse response) {
        List<ShangpinGys> shangpinGyss = this.shangpinGysService.findShangpinGyss(queryRequest, shangpinGys).getRecords();
        ExcelKit.$Export(ShangpinGys.class, response).downXlsx(shangpinGyss, false);
    }
}
