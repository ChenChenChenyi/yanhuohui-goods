package com.chenyi.yanhuohui.goods.goodsentity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GoodsDTO {

    private Integer thirdSpuId;

    private BigDecimal price;

    private Integer cateId;

    private String param;

    private String detail;
}
