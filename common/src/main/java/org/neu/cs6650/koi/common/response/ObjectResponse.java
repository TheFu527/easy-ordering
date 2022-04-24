package org.neu.cs6650.koi.common.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class ObjectResponse<T> extends BaseResponse implements Serializable {

    private T data;
}
