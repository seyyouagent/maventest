package com.kxl.Dto;

/**
 * Created by Administrator on 2019/1/25.
 */
public class UserDto {
    private String uname;

    private String password;

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