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
import com.sbs.system.entity.DictItem;
import com.sbs.system.service.IDictItemService;

import lombok.extern.slf4j.Slf4j;

/**
 *  Controller
 *
 * @author test
 * @date 2020-02-13 14:34:53
 */
@Slf4j
@RestController
@RequestMapping("dictitem")
public class DictItemController extends BaseController {

    @Autowired
    private IDictItemService dictItemService;

    @GetMapping
    public SysResponse getAllDictItems(DictItem dictItem) {
        return new SysResponse().success().data(dictItemService.findDictItems(dictItem));
    }

    @GetMapping("list")
    @RequiresPermissions("dictitem:view")
    public SysResponse dictItemList(QueryRequest request, DictItem dictItem) {
        Map<String, Object> dataTable = getDataTable(this.dictItemService.findDictItems(request, dictItem));
        return new SysResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增Dict", exceptionMessage = "新增Dict失败")
    @PostMapping
    @RequiresPermissions("dictitem:add")
    public SysResponse addDictItem(@Valid DictItem dictItem) {
        this.dictItemService.createDictItem(dictItem);
        return new SysResponse().success();
    }

    @ControllerEndpoint(operation = "删除Dict", exceptionMessage = "删除Dict失败")
    @GetMapping("delete/{dictItemIds}")
    @RequiresPermissions("dictitem:delete")
    public SysResponse deleteDictItem(@NotBlank(message = "{required}") @PathVariable String dictItemIds) {
        this.dictItemService.deleteDictItems(dictItemIds);
        return new SysResponse().success();
    }

    @ControllerEndpoint(operation = "修改Dict", exceptionMessage = "修改Dict失败")
    @PostMapping("update")
    @RequiresPermissions("dictitem:update")
    public SysResponse updateDictItem(DictItem dictItem) {
        this.dictItemService.updateDictItem(dictItem);
        return new SysResponse().success();
    }

    @ControllerEndpoint(operation = "导出字典", exceptionMessage = "导出Excel失败")
    @GetMapping("excel")
    @RequiresPermissions("dictitem:export")
    public void export(QueryRequest queryRequest, DictItem dictItem, HttpServletResponse response) {
        List<DictItem> dicts = this.dictItemService.findDictItems(queryRequest, dictItem).getRecords();
        ExcelKit.$Export(DictItem.class, response).downXlsx(dicts, false);
    }
}
