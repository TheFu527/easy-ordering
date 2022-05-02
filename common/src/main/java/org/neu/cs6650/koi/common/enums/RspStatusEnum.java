package org.neu.cs6650.koi.common.enums;

public enum RspStatusEnum {

    SUCCESS(200, "SUCCESS"),

    FAIL(400, "FAIL"),

    EXCEPTION(500, "EXCEPTION"),

    NON_EXIST(400, "USER NOT EXIST"),

    NOT_MATCH(400, "WRONG PASSWORD"),

    EXISTED_USER(400, "USER ALREADY EXIST"),
    STOCK_LOW(400, "NOT ENOUGH STOCK"),

    MONEY_SHORT(400, "NOT ENOUGH MONEY");

    private int code;

    private String message;

    RspStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
