package com.yanzu.yygh.common.result;

import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 全局统一返回结果类
 * @auther 吴彦祖
 * @date 2021/6/2
 */
@Data
@ApiModel("全局统一返回结果")
public class Result<T> {

    @ApiModelProperty("返回代码")
    private Integer code;

    @ApiModelProperty("返回消息")
    private String message;

    @ApiModelProperty("返回数据")
    private T data;

    public Result(){}

    public static <T> Result<T> build(T data){
        Result<T> result = new Result<T>();
        if (data != null){
            result.setData(data);
        }
        return result;
    }

    public static <T> Result<T> build(T body, ResultCodeEnum resultCodeEnum){
        Result<T> result = build(body);
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        return result;
    }

    public static <T> Result<T> build(Integer code,String message){
        Result<T> result = build(null);
        result.setMessage(message);
        result.setCode(code);
        return result;
    }

    public static <T> Result<T> ok(){
        return Result.ok(null);
    }

    public static <T> Result<T> ok(T data){
        Result<T> result = build(data);
        return build(data,ResultCodeEnum.SUCCESS);
    }

    public static <T> Result<T> fail(){
        return Result.fail();
    }

    public static <T> Result<T> fail(T data){
        Result<T> result = build(data);
        return build(data,ResultCodeEnum.FAIL);
    }

    public Result<T> message(String msg){
        this.setMessage(msg);
        return this;
    }

    public Result<T> code(Integer code){
        this.setCode(code);
        return this;
    }

    public boolean isOk(){
        if(this.getCode().intValue() == ResultCodeEnum.SUCCESS.getCode().intValue()){
            return true;
        }
        return false;
    }
}
