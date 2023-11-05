package com.chenyi.yanhuohui.goods.jd;

import lombok.Data;

import java.util.List;

@Data
public class SimilarSkuResponseDTO {
    /**
     * 维度
     */
    private Integer dim;

    /**
     * 销售名称
     */
    private String saleName;

    /**
     * 商品销售标签
     */
    private List<SaleAttrDTO> saleAttrList;
}
