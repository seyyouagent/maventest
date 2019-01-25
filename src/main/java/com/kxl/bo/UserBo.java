package com.kxl.bo;

/**
 * Created by Administrator on 2019/1/25.
 */
public class UserBo {

    private String id;

    private String userName;

    private String password;

    private String salt;

    private Integer locked;

    private String account;

    public void setId(String id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getId() {

        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public Integer getLocked() {
        return locked;
    }

    public String getAccount() {
        return account;
    }

    @Override
    public String toString() {
        return "UserBo{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", locked=" + locked +
                ", account='" + account + '\'' +
                '}';
    }
}
