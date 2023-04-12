package com.justin.user.entity.enums;

public enum ResultCode {
    SUCCESS("0000", "SUCCESS");

    ResultCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    public ResultCode setCode(String code) {
        this.code = code;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public ResultCode setDesc(String desc) {
        this.desc = desc;
        return this;
    }
}
