package com.chenyi.yanhuohui.goods.goodsEntity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GoodsInfoDTO {

    private Integer thirdSkuId;

    private String detail;

    private BigDecimal price;
 }
