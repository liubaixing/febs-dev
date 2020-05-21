package cc.mrbird.febs.shangpin.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.shangpin.entity.ShangpinPpgl;
import cc.mrbird.febs.shangpin.service.IShangpinPpglService;
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
 * 品牌管理 Controller
 *
 * @author MrBird
 * @date 2020-05-16 20:03:55
 */
@Slf4j
@Validated
@Controller
public class ShangpinPpglController extends BaseController {

    @Autowired
    private IShangpinPpglService shangpinPpglService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "shangpinPpgl")
    public String shangpinPpglIndex(){
        return FebsUtil.view("shangpinPpgl/shangpinPpgl");
    }

    @GetMapping("shangpinPpgl")
    @ResponseBody
    @RequiresPermissions("shangpinPpgl:list")
    public FebsResponse getAllShangpinPpgls(ShangpinPpgl shangpinPpgl) {
        return new FebsResponse().success().data(shangpinPpglService.findShangpinPpgls(shangpinPpgl));
    }

    @GetMapping("shangpinPpgl/list")
    @ResponseBody
    @RequiresPermissions("shangpinPpgl:list")
    public FebsResponse shangpinPpglList(QueryRequest request, ShangpinPpgl shangpinPpgl) {
        Map<String, Object> dataTable = getDataTable(this.shangpinPpglService.findShangpinPpgls(request, shangpinPpgl));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增ShangpinPpgl", exceptionMessage = "新增ShangpinPpgl失败")
    @PostMapping("shangpinPpgl")
    @ResponseBody
    @RequiresPermissions("shangpinPpgl:add")
    public FebsResponse addShangpinPpgl(@Valid ShangpinPpgl shangpinPpgl) {
        this.shangpinPpglService.createShangpinPpgl(shangpinPpgl);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除ShangpinPpgl", exceptionMessage = "删除ShangpinPpgl失败")
    @GetMapping("shangpinPpgl/delete")
    @ResponseBody
    @RequiresPermissions("shangpinPpgl:delete")
    public FebsResponse deleteShangpinPpgl(ShangpinPpgl shangpinPpgl) {
        this.shangpinPpglService.deleteShangpinPpgl(shangpinPpgl);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改ShangpinPpgl", exceptionMessage = "修改ShangpinPpgl失败")
    @PostMapping("shangpinPpgl/update")
    @ResponseBody
    @RequiresPermissions("shangpinPpgl:update")
    public FebsResponse updateShangpinPpgl(ShangpinPpgl shangpinPpgl) {
        this.shangpinPpglService.updateShangpinPpgl(shangpinPpgl);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改ShangpinPpgl", exceptionMessage = "导出Excel失败")
    @PostMapping("shangpinPpgl/excel")
    @ResponseBody
    @RequiresPermissions("shangpinPpgl:export")
    public void export(QueryRequest queryRequest, ShangpinPpgl shangpinPpgl, HttpServletResponse response) {
        List<ShangpinPpgl> shangpinPpgls = this.shangpinPpglService.findShangpinPpgls(queryRequest, shangpinPpgl).getRecords();
        ExcelKit.$Export(ShangpinPpgl.class, response).downXlsx(shangpinPpgls, false);
    }
}
