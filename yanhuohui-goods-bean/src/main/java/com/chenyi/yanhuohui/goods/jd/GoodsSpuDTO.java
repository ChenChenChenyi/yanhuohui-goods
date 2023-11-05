package com.chenyi.yanhuohui.goods.jd;

import lombok.Data;

@Data
public class GoodsSpuDTO {

    private Long spuId;

    /**
     * 三级ID
     */
    private Long cateId;

    private String spuName;

}
