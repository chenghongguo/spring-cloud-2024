package com.hongguo.cloud.response;

import com.hongguo.cloud.response.enums.ReturnCodeEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReturnData<T> {

    private String code;
    private String message;
    private T data;
    private long timestamp;

    public ReturnData(String code, String message) {
        this(code, message, null);
    }

    public ReturnData(ReturnCodeEnum codeEnum) {
        this(codeEnum.getCode(), codeEnum.getMessage());
    }

    public ReturnData(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> ReturnData<T> success(T data) {
        return new ReturnData<>(ReturnCodeEnum.RC200.getCode(), ReturnCodeEnum.RC200.getMessage(), data);
    }

    public static <T> ReturnData<T> fail(ReturnCodeEnum codeEnum) {
        return fail(codeEnum.getCode());
    }

    public static <T> ReturnData<T> fail(String code) {
        ReturnCodeEnum codeEnum = ReturnCodeEnum.getByCode(code);
        return fail(codeEnum.getCode(), codeEnum.getMessage());
    }

    public static <T> ReturnData<T> fail(String code, String message) {
        return new ReturnData<>(code, message);
    }
}
