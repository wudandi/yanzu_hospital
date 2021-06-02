package com.yanzu.yygh.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @auther 吴彦祖
 * @date 2021/6/2
 */
@Data
public class HospSetQueryVo {

    @ApiModelProperty("医院名称")
    private String hospname;

    @ApiModelProperty("医院编号")
    private String hoscode;
}
