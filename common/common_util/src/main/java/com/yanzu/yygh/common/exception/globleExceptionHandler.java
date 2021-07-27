package com.yanzu.yygh.common.exception;

import com.yanzu.yygh.common.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @auther 吴彦祖
 * @date 2021/6/4
 */
@RestControllerAdvice
public class globleExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result error(Exception e){
        e.printStackTrace();
        return Result.fail();
    }

    @ExceptionHandler(HospitalException.class)
    public Result error(HospitalException hospitalException){
        hospitalException.printStackTrace();
        return Result.fail();
    }
}
