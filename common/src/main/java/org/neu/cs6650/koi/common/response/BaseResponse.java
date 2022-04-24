package org.neu.cs6650.koi.common.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseResponse implements Serializable {

    private int status = 200;

    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
