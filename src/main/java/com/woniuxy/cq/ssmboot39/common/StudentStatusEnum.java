package com.woniuxy.cq.ssmboot39.common;

public enum StudentStatusEnum {

    GUEST("GUEST", "游客"),

    REGISTERED("REGISTERED", "已报名"),

    IN_SCHOOL("IN_SCHOOL", "在读"),

    GRADUATED("GRADUATED", "已就业"),

    SUSPENSION("SUSPENSION", "休学"),

    DROP_OUT("DROP_OUT", "辍学");

    private String code;

    private String message;

    StudentStatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
