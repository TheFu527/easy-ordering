package org.neu.cs6650.koi.common.exception;

import org.neu.cs6650.koi.common.enums.RspStatusEnum;

public class DefaultException extends RuntimeException {

    private RspStatusEnum rspStatusEnum;

    public DefaultException(String message, Throwable cause) {
        super(message, cause);
    }

    public DefaultException(RspStatusEnum rspStatusEnum) {
        super(rspStatusEnum.getMessage());
        this.rspStatusEnum = rspStatusEnum;
    }

    public DefaultException(RspStatusEnum rspStatusEnum, Throwable cause) {
        super(rspStatusEnum.getMessage(), cause);
        this.rspStatusEnum = rspStatusEnum;
    }

    public RspStatusEnum getRspStatusEnum() {
        return rspStatusEnum;
    }

    public void setRspStatusEnum(RspStatusEnum rspStatusEnum) {
        this.rspStatusEnum = rspStatusEnum;
    }
}
