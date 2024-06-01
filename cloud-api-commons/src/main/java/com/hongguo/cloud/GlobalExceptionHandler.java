package com.hongguo.cloud;

import com.hongguo.cloud.response.ReturnData;
import com.hongguo.cloud.response.enums.ReturnCodeEnum;

//@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(RuntimeException.class)
    public ReturnData<String> runtimeException(Exception e) {
        return ReturnData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
    }

//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ReturnData<String> exception(Exception e) {
        return ReturnData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
    }
}
