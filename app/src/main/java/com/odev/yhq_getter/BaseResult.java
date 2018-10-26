package com.odev.yhq_getter;

public class BaseResult {
    private String code;
    private String subCodeMsg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSubCodeMsg() {
        return subCodeMsg;
    }

    public void setSubCodeMsg(String subCodeMsg) {
        this.subCodeMsg = subCodeMsg;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "code='" + code + '\'' +
                ", subCodeMsg='" + subCodeMsg + '\'' +
                '}';
    }
}
