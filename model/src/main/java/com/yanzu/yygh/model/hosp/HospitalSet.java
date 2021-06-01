package com.yanzu.yygh.model.hosp;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yanzu.yygh.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @auther 吴彦祖
 * @date 2021/6/1
 */
@Data
@ApiModel("医院设置")
@TableName("hospital_set")
public class HospitalSet extends BaseEntity {
    private static final long serialVersionUID=1L;

    @TableField("hosname")
    @ApiModelProperty("医院名称")
    private String hosname;

    @ApiModelProperty(value = "医院编号")
    @TableField("hoscode")
    private String hoscode;

    @ApiModelProperty(value = "api基础路径")
    @TableField("api_url")
    private String apiUrl;

    @ApiModelProperty(value = "签名秘钥")
    @TableField("sign_key")
    private String signKey;

    @ApiModelProperty("联系人")
    @TableField("contact_name")
    private String contactName;

    @ApiModelProperty("联系人手机")
    @TableField("contact_phone")
    private String contactPhone;

    @ApiModelProperty("状态")
    @TableField("status")
    private Integer status;
}
