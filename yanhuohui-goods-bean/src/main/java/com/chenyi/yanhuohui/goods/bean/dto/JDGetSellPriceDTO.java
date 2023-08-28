package com.chenyi.yanhuohui.goods.bean.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JDGetSellPriceDTO {

    /**
     * 商品编号
     */
    private String skuId;

    private String jdPrice;

    private String price;

    private String marketPrice;

    private String tax;

    private String taxPrice;

    private String nakedPrice;
}
