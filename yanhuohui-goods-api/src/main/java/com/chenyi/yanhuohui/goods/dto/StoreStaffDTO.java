package com.chenyi.yanhuohui.goods.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value="StoreStaffDTO", description="")
public class StoreStaffDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "门店标识")
    private Long storeId;

    @ApiModelProperty(value = "员工标识")
    private Long staffId;


}
