package cc.mrbird.febs.system.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.system.entity.Kehu;
import cc.mrbird.febs.system.service.IKehuService;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * 客户管理 Controller
 *
 * @author lbx
 * @date 2020-05-16 19:44:24
 */
@Slf4j
@Validated
@ResponseBody
@Controller
public class KehuController extends BaseController {

    @Autowired
    private IKehuService kehuService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "kehu")
    public String kehuIndex(){
        return FebsUtil.view("kehu/kehu");
    }

    @GetMapping("kehu")
    @RequiresPermissions("kehu:list")
    public FebsResponse getAllKehus(Kehu kehu) {
        return new FebsResponse().success().data(kehuService.findKehus(kehu));
    }

    @GetMapping("kehu/list")
    @RequiresPermissions("kehu:list")
    public FebsResponse kehuList(QueryRequest request, Kehu kehu) {
        Map<String, Object> dataTable = getDataTable(this.kehuService.findKehus(request, kehu));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增Kehu", exceptionMessage = "新增Kehu失败")
    @PostMapping("kehu")
    @RequiresPermissions("kehu:add")
    public FebsResponse addKehu(@Valid Kehu kehu) {
        this.kehuService.createKehu(kehu);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除Kehu", exceptionMessage = "删除Kehu失败")
    @GetMapping("kehu/delete/{ids}")
    @RequiresPermissions("kehu:delete")
    public FebsResponse deleteKehu(@NotBlank(message = "{required}") @PathVariable String ids) {
        String[] id = ids.split(StringPool.COMMA);
        this.kehuService.deleteKehu(id);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改Kehu", exceptionMessage = "修改Kehu失败")
    @PostMapping("kehu/update")
    @RequiresPermissions("kehu:update")
    public FebsResponse updateKehu(Kehu kehu) {
        this.kehuService.updateKehu(kehu);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改Kehu", exceptionMessage = "导出Excel失败")
    @GetMapping("kehu/excel")
    @RequiresPermissions("kehu:export")
    public void export(QueryRequest queryRequest, Kehu kehu, HttpServletResponse response) {
        List<Kehu> kehus = this.kehuService.findKehus(queryRequest, kehu).getRecords();
        ExcelKit.$Export(Kehu.class, response).downXlsx(kehus, false);
    }
}
