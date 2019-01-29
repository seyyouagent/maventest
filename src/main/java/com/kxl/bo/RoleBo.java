package com.kxl.bo;

import java.util.Date;

/**
 * Created by Administrator on 2019/1/28.
 */
public class RoleBo {

    private Integer roleId;

    private String roleName;

    private Date version;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Date getVersion() {
        return version;
    }

    public void setVersion(Date version) {
        this.version = version;
    }
}
