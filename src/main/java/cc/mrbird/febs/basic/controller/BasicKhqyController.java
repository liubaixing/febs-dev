package cc.mrbird.febs.basic.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.basic.entity.BasicKhqy;
import cc.mrbird.febs.basic.service.IBasicKhqyService;
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
 * 客户区域 Controller
 *
 * @author MrBird
 * @date 2020-05-16 20:10:52
 */
@Slf4j
@Validated
@Controller
public class BasicKhqyController extends BaseController {

    @Autowired
    private IBasicKhqyService basicKhqyService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "basicKhqy")
    public String basicKhqyIndex(){
        return FebsUtil.view("basicKhqy/basicKhqy");
    }

    @GetMapping("basicKhqy")
    @ResponseBody
    @RequiresPermissions("basicKhqy:list")
    public FebsResponse getAllBasicKhqys(BasicKhqy basicKhqy) {
        return new FebsResponse().success().data(basicKhqyService.findBasicKhqys(basicKhqy));
    }

    @GetMapping("basicKhqy/list")
    @ResponseBody
    @RequiresPermissions("basicKhqy:list")
    public FebsResponse basicKhqyList(QueryRequest request, BasicKhqy basicKhqy) {
        Map<String, Object> dataTable = getDataTable(this.basicKhqyService.findBasicKhqys(request, basicKhqy));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增BasicKhqy", exceptionMessage = "新增BasicKhqy失败")
    @PostMapping("basicKhqy")
    @ResponseBody
    @RequiresPermissions("basicKhqy:add")
    public FebsResponse addBasicKhqy(@Valid BasicKhqy basicKhqy) {
        this.basicKhqyService.createBasicKhqy(basicKhqy);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除BasicKhqy", exceptionMessage = "删除BasicKhqy失败")
    @GetMapping("basicKhqy/delete")
    @ResponseBody
    @RequiresPermissions("basicKhqy:delete")
    public FebsResponse deleteBasicKhqy(BasicKhqy basicKhqy) {
        this.basicKhqyService.deleteBasicKhqy(basicKhqy);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改BasicKhqy", exceptionMessage = "修改BasicKhqy失败")
    @PostMapping("basicKhqy/update")
    @ResponseBody
    @RequiresPermissions("basicKhqy:update")
    public FebsResponse updateBasicKhqy(BasicKhqy basicKhqy) {
        this.basicKhqyService.updateBasicKhqy(basicKhqy);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改BasicKhqy", exceptionMessage = "导出Excel失败")
    @PostMapping("basicKhqy/excel")
    @ResponseBody
    @RequiresPermissions("basicKhqy:export")
    public void export(QueryRequest queryRequest, BasicKhqy basicKhqy, HttpServletResponse response) {
        List<BasicKhqy> basicKhqys = this.basicKhqyService.findBasicKhqys(queryRequest, basicKhqy).getRecords();
        ExcelKit.$Export(BasicKhqy.class, response).downXlsx(basicKhqys, false);
    }
}
