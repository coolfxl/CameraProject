package com.yesmoon.pojo;

public class Camera {
    private Integer id;

    private String cid;

    private String ip;

    private Integer port;

    private String cpassword;

    private String user;

    public Camera(Integer id, String cid, String ip, Integer port, String cpassword, String user) {
        this.id = id;
        this.cid = cid;
        this.ip = ip;
        this.port = port;
        this.cpassword = cpassword;
        this.user = user;
    }

    public Camera() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getCpassword() {
        return cpassword;
    }

    public void setCpassword(String cpassword) {
        this.cpassword = cpassword == null ? null : cpassword.trim();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user == null ? null : user.trim();
    }
}