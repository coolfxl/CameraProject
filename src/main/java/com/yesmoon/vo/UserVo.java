package com.yesmoon.vo;

import com.yesmoon.pojo.SenceCode;

public class UserVo {

    private Integer id;

    private String username;

    private String password;

    private String cameraList;

    private Integer role;

    private String token;

    private SenceCode senceCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCameraList() {
        return cameraList;
    }

    public void setCameraList(String cameraList) {
        this.cameraList = cameraList;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public SenceCode getSenceCode() {
        return senceCode;
    }

    public void setSenceCode(SenceCode senceCode) {
        this.senceCode = senceCode;
    }
}
