package com.yesmoon.pojo;

public class SenceCode {
    private Integer id;

    private String senceCodeJson;

    public SenceCode(Integer id, String senceCodeJson) {
        this.id = id;
        this.senceCodeJson = senceCodeJson;
    }

    public SenceCode() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSenceCodeJson() {
        return senceCodeJson;
    }

    public void setSenceCodeJson(String senceCodeJson) {
        this.senceCodeJson = senceCodeJson == null ? null : senceCodeJson.trim();
    }
}