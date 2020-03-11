package com.sbs.system.controller;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.ExpiredSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sbs.common.authentication.ShiroHelper;
import com.sbs.common.controller.BaseController;
import com.sbs.common.entity.SysConstant;
import com.sbs.common.utils.DateUtil;
import com.sbs.common.utils.SysUtil;
import com.sbs.system.entity.User;
import com.sbs.system.service.IUserService;

import javax.servlet.http.HttpServletRequest;

@Controller("systemView")
public class ViewController extends BaseController {

    @Autowired
    private IUserService userService;
    @Autowired
    private ShiroHelper shiroHelper;

    @GetMapping("login")
    @ResponseBody
    public Object login(HttpServletRequest request) {
        if (SysUtil.isAjaxRequest(request)) {
            throw new ExpiredSessionException();
        } else {
            ModelAndView mav = new ModelAndView();
            mav.setViewName(SysUtil.view("login"));
            return mav;
        }
    }

    @GetMapping("unauthorized")
    public String unauthorized() {
        return SysUtil.view("error/403");
    }


    @GetMapping("/")
    public String redirectIndex() {
        return "redirect:/index";
    }

    @GetMapping("index")
    public String index(Model model) {
        AuthorizationInfo authorizationInfo = shiroHelper.getCurrentuserAuthorizationInfo();
        User user = super.getCurrentUser();
        User currentUserDetail = userService.findByName(user.getUsername());
        currentUserDetail.setPassword("It's a secret");
        model.addAttribute("user", currentUserDetail);
        model.addAttribute("permissions", authorizationInfo.getStringPermissions());
        model.addAttribute("roles", authorizationInfo.getRoles());
        return "index";
    }

    @GetMapping(SysConstant.VIEW_PREFIX + "layout")
    public String layout() {
        return SysUtil.view("layout");
    }

    @GetMapping(SysConstant.VIEW_PREFIX + "password/update")
    public String passwordUpdate() {
        return SysUtil.view("system/user/passwordUpdate");
    }

    @GetMapping(SysConstant.VIEW_PREFIX + "user/profile")
    public String userProfile() {
        return SysUtil.view("system/user/userProfile");
    }

    @GetMapping(SysConstant.VIEW_PREFIX + "user/avatar")
    public String userAvatar() {
        return SysUtil.view("system/user/avatar");
    }

    @GetMapping(SysConstant.VIEW_PREFIX + "user/profile/update")
    public String profileUpdate() {
        return SysUtil.view("system/user/profileUpdate");
    }

    @GetMapping(SysConstant.VIEW_PREFIX + "system/user")
    @RequiresPermissions("user:view")
    public String systemUser() {
        return SysUtil.view("system/user/user");
    }

    @GetMapping(SysConstant.VIEW_PREFIX + "system/user/add")
    @RequiresPermissions("user:add")
    public String systemUserAdd() {
        return SysUtil.view("system/user/userAdd");
    }

    @GetMapping(SysConstant.VIEW_PREFIX + "system/user/detail/{username}")
    @RequiresPermissions("user:view")
    public String systemUserDetail(@PathVariable String username, Model model) {
        resolveUserModel(username, model, true);
        return SysUtil.view("system/user/userDetail");
    }

    @GetMapping(SysConstant.VIEW_PREFIX + "system/user/update/{username}")
    @RequiresPermissions("user:update")
    public String systemUserUpdate(@PathVariable String username, Model model) {
        resolveUserModel(username, model, false);
        return SysUtil.view("system/user/userUpdate");
    }

    @GetMapping(SysConstant.VIEW_PREFIX + "system/role")
    @RequiresPermissions("role:view")
    public String systemRole() {
        return SysUtil.view("system/role/role");
    }

    @GetMapping(SysConstant.VIEW_PREFIX + "system/menu")
    @RequiresPermissions("menu:view")
    public String systemMenu() {
        return SysUtil.view("system/menu/menu");
    }
    
    @RequestMapping(SysConstant.VIEW_PREFIX + "index")
    public String pageIndex() {
        return SysUtil.view("index");
    }

    @GetMapping(SysConstant.VIEW_PREFIX + "404")
    public String error404() {
        return SysUtil.view("error/404");
    }

    @GetMapping(SysConstant.VIEW_PREFIX + "403")
    public String error403() {
        return SysUtil.view("error/403");
    }

    @GetMapping(SysConstant.VIEW_PREFIX + "500")
    public String error500() {
        return SysUtil.view("error/500");
    }

    private void resolveUserModel(String username, Model model, Boolean transform) {
        User user = userService.findByName(username);
        model.addAttribute("user", user);
        if (transform) {
            String ssex = user.getSex();
            if (User.SEX_MALE.equals(ssex)) user.setSex("男");
            else if (User.SEX_FEMALE.equals(ssex)) user.setSex("女");
            else user.setSex("保密");
        }
        if (user.getLastLoginTime() != null)
            model.addAttribute("lastLoginTime", DateUtil.getDateFormat(user.getLastLoginTime(), DateUtil.FULL_TIME_SPLIT_PATTERN));
    }
    
    @GetMapping(SysConstant.VIEW_PREFIX + "system/dict")
    @RequiresPermissions("dict:view")
    public String systemDict() {
        return SysUtil.view("system/dict/dict");
    }
    
    @GetMapping(SysConstant.VIEW_PREFIX + "system/dictitem/{dictId}")
    @RequiresPermissions("dictitem:view")
    public String systemDictItem(@PathVariable Long dictId, Model model) {
    	model.addAttribute("dictId", dictId);
        return SysUtil.view("system/dict/dictItem");
    }
}
