package cc.mrbird.febs.shangpin.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.shangpin.entity.ShangpinZl;
import cc.mrbird.febs.shangpin.service.IShangpinZlService;
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
 * 总类 Controller
 *
 * @author MrBird
 * @date 2020-05-16 20:04:00
 */
@Slf4j
@Validated
@Controller
public class ShangpinZlController extends BaseController {

    @Autowired
    private IShangpinZlService shangpinZlService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "shangpinZl")
    public String shangpinZlIndex(){
        return FebsUtil.view("shangpinZl/shangpinZl");
    }

    @GetMapping("shangpinZl")
    @ResponseBody
    @RequiresPermissions("shangpinZl:list")
    public FebsResponse getAllShangpinZls(ShangpinZl shangpinZl) {
        return new FebsResponse().success().data(shangpinZlService.findShangpinZls(shangpinZl));
    }

    @GetMapping("shangpinZl/list")
    @ResponseBody
    @RequiresPermissions("shangpinZl:list")
    public FebsResponse shangpinZlList(QueryRequest request, ShangpinZl shangpinZl) {
        Map<String, Object> dataTable = getDataTable(this.shangpinZlService.findShangpinZls(request, shangpinZl));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增ShangpinZl", exceptionMessage = "新增ShangpinZl失败")
    @PostMapping("shangpinZl")
    @ResponseBody
    @RequiresPermissions("shangpinZl:add")
    public FebsResponse addShangpinZl(@Valid ShangpinZl shangpinZl) {
        this.shangpinZlService.createShangpinZl(shangpinZl);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除ShangpinZl", exceptionMessage = "删除ShangpinZl失败")
    @GetMapping("shangpinZl/delete")
    @ResponseBody
    @RequiresPermissions("shangpinZl:delete")
    public FebsResponse deleteShangpinZl(ShangpinZl shangpinZl) {
        this.shangpinZlService.deleteShangpinZl(shangpinZl);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改ShangpinZl", exceptionMessage = "修改ShangpinZl失败")
    @PostMapping("shangpinZl/update")
    @ResponseBody
    @RequiresPermissions("shangpinZl:update")
    public FebsResponse updateShangpinZl(ShangpinZl shangpinZl) {
        this.shangpinZlService.updateShangpinZl(shangpinZl);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改ShangpinZl", exceptionMessage = "导出Excel失败")
    @PostMapping("shangpinZl/excel")
    @ResponseBody
    @RequiresPermissions("shangpinZl:export")
    public void export(QueryRequest queryRequest, ShangpinZl shangpinZl, HttpServletResponse response) {
        List<ShangpinZl> shangpinZls = this.shangpinZlService.findShangpinZls(queryRequest, shangpinZl).getRecords();
        ExcelKit.$Export(ShangpinZl.class, response).downXlsx(shangpinZls, false);
    }
}
