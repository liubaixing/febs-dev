package cc.mrbird.febs.basic.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.basic.entity.BasicXsfs;
import cc.mrbird.febs.basic.service.IBasicXsfsService;
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
 * 销售方式 Controller
 *
 * @author MrBird
 * @date 2020-05-16 20:11:01
 */
@Slf4j
@Validated
@Controller
public class BasicXsfsController extends BaseController {

    @Autowired
    private IBasicXsfsService basicXsfsService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "basicXsfs")
    public String basicXsfsIndex(){
        return FebsUtil.view("basicXsfs/basicXsfs");
    }

    @GetMapping("basicXsfs")
    @ResponseBody
    @RequiresPermissions("basicXsfs:list")
    public FebsResponse getAllBasicXsfss(BasicXsfs basicXsfs) {
        return new FebsResponse().success().data(basicXsfsService.findBasicXsfss(basicXsfs));
    }

    @GetMapping("basicXsfs/list")
    @ResponseBody
    @RequiresPermissions("basicXsfs:list")
    public FebsResponse basicXsfsList(QueryRequest request, BasicXsfs basicXsfs) {
        Map<String, Object> dataTable = getDataTable(this.basicXsfsService.findBasicXsfss(request, basicXsfs));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增BasicXsfs", exceptionMessage = "新增BasicXsfs失败")
    @PostMapping("basicXsfs")
    @ResponseBody
    @RequiresPermissions("basicXsfs:add")
    public FebsResponse addBasicXsfs(@Valid BasicXsfs basicXsfs) {
        this.basicXsfsService.createBasicXsfs(basicXsfs);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除BasicXsfs", exceptionMessage = "删除BasicXsfs失败")
    @GetMapping("basicXsfs/delete")
    @ResponseBody
    @RequiresPermissions("basicXsfs:delete")
    public FebsResponse deleteBasicXsfs(BasicXsfs basicXsfs) {
        this.basicXsfsService.deleteBasicXsfs(basicXsfs);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改BasicXsfs", exceptionMessage = "修改BasicXsfs失败")
    @PostMapping("basicXsfs/update")
    @ResponseBody
    @RequiresPermissions("basicXsfs:update")
    public FebsResponse updateBasicXsfs(BasicXsfs basicXsfs) {
        this.basicXsfsService.updateBasicXsfs(basicXsfs);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改BasicXsfs", exceptionMessage = "导出Excel失败")
    @PostMapping("basicXsfs/excel")
    @ResponseBody
    @RequiresPermissions("basicXsfs:export")
    public void export(QueryRequest queryRequest, BasicXsfs basicXsfs, HttpServletResponse response) {
        List<BasicXsfs> basicXsfss = this.basicXsfsService.findBasicXsfss(queryRequest, basicXsfs).getRecords();
        ExcelKit.$Export(BasicXsfs.class, response).downXlsx(basicXsfss, false);
    }
}
