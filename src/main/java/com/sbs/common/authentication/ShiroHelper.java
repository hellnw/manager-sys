package com.sbs.common.authentication;

import org.apache.shiro.authz.AuthorizationInfo;

import com.sbs.common.annotation.Helper;

@Helper
public class ShiroHelper extends ShiroRealm {

    /**
     * 获取当前用户的角色和权限集合
     *
     * @return AuthorizationInfo
     */
    public AuthorizationInfo getCurrentuserAuthorizationInfo() {
        return super.doGetAuthorizationInfo(null);
    }
}
