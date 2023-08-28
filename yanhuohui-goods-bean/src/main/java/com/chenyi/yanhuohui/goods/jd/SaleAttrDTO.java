package com.chenyi.yanhuohui.goods.jd;

import lombok.Data;

import java.util.Set;

@Data
public class SaleAttrDTO {
    /**
     * 标签图片地址
     */
    private String imagePath;

    /**
     * 标签名称
     */
    private String saleValue;

    /**
     * 当前标签下的同类商品skuID
     */
    private Set<Long> skuIds;
}
