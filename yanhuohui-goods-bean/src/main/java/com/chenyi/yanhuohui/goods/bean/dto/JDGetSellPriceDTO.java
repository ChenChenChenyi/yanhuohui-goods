package com.chenyi.yanhuohui.goods.bean.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class JDGetSellPriceDTO {

    /**
     * 商品编号
     */
    private String skuId;

    /**
     * 划线价
     */
    private BigDecimal jdPrice;

    /**
     * 售卖价
     */
    private BigDecimal price;

    private BigDecimal marketPrice;

    private String tax;

    private BigDecimal taxPrice;

    private BigDecimal nakedPrice;
}
