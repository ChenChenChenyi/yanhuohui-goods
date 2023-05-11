package com.chenyi.yanhuohui.goods.goodsentity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsInfo {

    private String goodsInfoId;

    private String goodsInfoName;

    //private BigDecimal salePrice;
}
