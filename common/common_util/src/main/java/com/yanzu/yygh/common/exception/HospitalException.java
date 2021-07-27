package com.yanzu.yygh.common.exception;

import com.yanzu.yygh.common.result.ResultCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @auther 吴彦祖
 * @date 2021/6/4
 */
@Data
@ApiModel("自定义异常")
public class HospitalException extends RuntimeException{

    @ApiModelProperty("异常代码")
    private Integer code;

    public HospitalException(String message,Integer code){
        super(message);
        this.code = code;
    }

    public HospitalException(ResultCodeEnum resultCodeEnum){
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "HospitalException{" +
                "code=" + code +
                ",message=" + this.getMessage() +
                '}';
    }
}
