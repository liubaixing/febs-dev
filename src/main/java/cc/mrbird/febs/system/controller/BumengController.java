package cc.mrbird.febs.system.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.system.entity.Bumeng;
import cc.mrbird.febs.system.service.IBumengService;
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
 * 部门管理 Controller
 *
 * @author lbx
 * @date 2020-05-16 19:50:02
 */
@Slf4j
@Validated
@Controller
public class BumengController extends BaseController {

    @Autowired
    private IBumengService bumengService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "bumeng")
    public String bumengIndex(){
        return FebsUtil.view("bumeng/bumeng");
    }

    @GetMapping("bumeng")
    @ResponseBody
    @RequiresPermissions("bumeng:list")
    public FebsResponse getAllBumengs(Bumeng bumeng) {
        return new FebsResponse().success().data(bumengService.findBumengs(bumeng));
    }

    @GetMapping("bumeng/list")
    @ResponseBody
    @RequiresPermissions("bumeng:list")
    public FebsResponse bumengList(QueryRequest request, Bumeng bumeng) {
        Map<String, Object> dataTable = getDataTable(this.bumengService.findBumengs(request, bumeng));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增Bumeng", exceptionMessage = "新增Bumeng失败")
    @PostMapping("bumeng")
    @ResponseBody
    @RequiresPermissions("bumeng:add")
    public FebsResponse addBumeng(@Valid Bumeng bumeng) {
        this.bumengService.createBumeng(bumeng);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除Bumeng", exceptionMessage = "删除Bumeng失败")
    @GetMapping("bumeng/delete")
    @ResponseBody
    @RequiresPermissions("bumeng:delete")
    public FebsResponse deleteBumeng(Bumeng bumeng) {
        this.bumengService.deleteBumeng(bumeng);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改Bumeng", exceptionMessage = "修改Bumeng失败")
    @PostMapping("bumeng/update")
    @ResponseBody
    @RequiresPermissions("bumeng:update")
    public FebsResponse updateBumeng(Bumeng bumeng) {
        this.bumengService.updateBumeng(bumeng);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改Bumeng", exceptionMessage = "导出Excel失败")
    @PostMapping("bumeng/excel")
    @ResponseBody
    @RequiresPermissions("bumeng:export")
    public void export(QueryRequest queryRequest, Bumeng bumeng, HttpServletResponse response) {
        List<Bumeng> bumengs = this.bumengService.findBumengs(queryRequest, bumeng).getRecords();
        ExcelKit.$Export(Bumeng.class, response).downXlsx(bumengs, false);
    }
}
