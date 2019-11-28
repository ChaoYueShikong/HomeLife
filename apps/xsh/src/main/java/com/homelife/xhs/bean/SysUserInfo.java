package com.homelife.xhs.bean;

/**
 * 功能：
 * 作者：HDM
 * 创建时间：2019/10/8 17:43
 */
public class SysUserInfo {
    private String accountid;
    private String authtoken;
    private String ver;

    public SysUserInfo() {
    }

    public SysUserInfo(String accountid, String authtoken, String ver) {
        this.accountid = accountid;
        this.authtoken = authtoken;
        this.ver = ver;
    }

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }
}
