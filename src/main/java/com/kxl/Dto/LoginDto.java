package com.kxl.Dto;

/**
 * Created by Administrator on 2019/1/25.
 */
public class LoginDto {
    private String uname;

    private String password;

    private String verificationCode;

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

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
