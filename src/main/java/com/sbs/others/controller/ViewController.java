package com.sbs.others.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sbs.common.entity.SysConstant;
import com.sbs.common.utils.SysUtil;

@Controller("othersView")
@RequestMapping(SysConstant.VIEW_PREFIX + "others")
public class ViewController {

    @GetMapping("sys/form")
    @RequiresPermissions("sys:form:view")
    public String sysForm() {
        return SysUtil.view("others/sys/form");
    }

    @GetMapping("sys/form/group")
    @RequiresPermissions("sys:formgroup:view")
    public String sysFormGroup() {
        return SysUtil.view("others/sys/formGroup");
    }

    @GetMapping("sys/tools")
    @RequiresPermissions("sys:tools:view")
    public String sysTools() {
        return SysUtil.view("others/sys/tools");
    }

    @GetMapping("sys/icon")
    @RequiresPermissions("sys:icons:view")
    public String sysIcon() {
        return SysUtil.view("others/sys/icon");
    }

    @GetMapping("sys/others")
    @RequiresPermissions("others:sys:others")
    public String sysOthers() {
        return SysUtil.view("others/sys/others");
    }

    @GetMapping("apex/line")
    @RequiresPermissions("apex:line:view")
    public String apexLine() {
        return SysUtil.view("others/apex/line");
    }

    @GetMapping("apex/area")
    @RequiresPermissions("apex:area:view")
    public String apexArea() {
        return SysUtil.view("others/apex/area");
    }

    @GetMapping("apex/column")
    @RequiresPermissions("apex:column:view")
    public String apexColumn() {
        return SysUtil.view("others/apex/column");
    }

    @GetMapping("apex/radar")
    @RequiresPermissions("apex:radar:view")
    public String apexRadar() {
        return SysUtil.view("others/apex/radar");
    }

    @GetMapping("apex/bar")
    @RequiresPermissions("apex:bar:view")
    public String apexBar() {
        return SysUtil.view("others/apex/bar");
    }

    @GetMapping("apex/mix")
    @RequiresPermissions("apex:mix:view")
    public String apexMix() {
        return SysUtil.view("others/apex/mix");
    }

    @GetMapping("map")
    @RequiresPermissions("map:view")
    public String map() {
        return SysUtil.view("others/map/gaodeMap");
    }

    @GetMapping("eximport")
    @RequiresPermissions("others:eximport:view")
    public String eximport() {
        return SysUtil.view("others/eximport/eximport");
    }

    @GetMapping("eximport/result")
    public String eximportResult() {
        return SysUtil.view("others/eximport/eximportResult");
    }
}
