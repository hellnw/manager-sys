package com.sbs.system.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wuwenze.poi.ExcelKit;

import com.sbs.common.annotation.ControllerEndpoint;
import com.sbs.common.controller.BaseController;
import com.sbs.common.entity.SysResponse;
import com.sbs.common.entity.QueryRequest;
import com.sbs.system.entity.Dict;
import com.sbs.system.service.IDictService;

import lombok.extern.slf4j.Slf4j;

/**
 *  Controller
 *
 * @author test
 * @date 2020-02-13 14:34:53
 */
@Slf4j
@RestController
@RequestMapping("dict")
public class DictController extends BaseController {

    @Autowired
    private IDictService dictService;

    @GetMapping
    public SysResponse getAllDicts(Dict dict) {
        return new SysResponse().success().data(dictService.findDicts(dict));
    }

    @GetMapping("list")
    @RequiresPermissions("dict:view")
    public SysResponse dictList(QueryRequest request, Dict dict) {
        Map<String, Object> dataTable = getDataTable(this.dictService.findDicts(request, dict));
        return new SysResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增Dict", exceptionMessage = "新增Dict失败")
    @PostMapping
    @RequiresPermissions("dict:add")
    public SysResponse addDict(@Valid Dict dict) {
    	log.info("测试字典取值:" + dictService.findDictItemName("编码", "code2"));
        this.dictService.createDict(dict);
        return new SysResponse().success();
    }

    @ControllerEndpoint(operation = "删除Dict", exceptionMessage = "删除Dict失败")
    @GetMapping("delete/{dictIds}")
    @RequiresPermissions("dict:delete")
    public SysResponse deleteDict(@NotBlank(message = "{required}") @PathVariable String dictIds) {
        this.dictService.deleteDicts(dictIds);
        return new SysResponse().success();
    }

    @ControllerEndpoint(operation = "修改Dict", exceptionMessage = "修改Dict失败")
    @PostMapping("update")
    @RequiresPermissions("dict:update")
    public SysResponse updateDict(Dict dict) {
        this.dictService.updateDict(dict);
        return new SysResponse().success();
    }

    @ControllerEndpoint(operation = "导出字典", exceptionMessage = "导出Excel失败")
    @GetMapping("excel")
    @RequiresPermissions("dict:export")
    public void export(QueryRequest queryRequest, Dict dict, HttpServletResponse response) {
        List<Dict> dicts = this.dictService.findDicts(queryRequest, dict).getRecords();
        ExcelKit.$Export(Dict.class, response).downXlsx(dicts, false);
    }
}
