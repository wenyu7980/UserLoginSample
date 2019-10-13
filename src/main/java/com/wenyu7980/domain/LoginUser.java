package com.wenyu7980.domain;

public class LoginUser {
    private String username;
    private String password;
    private String mobile;
    private String code;

    public LoginUser() {
    }

    public LoginUser(String username, String password, String mobile,
            String code) {
        this.username = username;
        this.password = password;
        this.mobile = mobile;
        this.code = code;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
