package com.kxl.Dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2019/1/25.
 */
@ApiModel(value = "用户DTO", description = "登录条件入参")
public class UserDto {
    @ApiModelProperty("用户名")
    private String uname;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("token")
    private String token;

    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUname() {

        return uname;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
