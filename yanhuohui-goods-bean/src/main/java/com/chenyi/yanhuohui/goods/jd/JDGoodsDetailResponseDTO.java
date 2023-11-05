package com.chenyi.yanhuohui.goods.jd;

import lombok.Data;

import java.util.List;

@Data
public class JDGoodsDetailResponseDTO {

    /**
     * 商品编号
     */
    private String sku;

    private String name;

    /**
     * 售卖单位
     */
    private String  saleUnit;

    private String weight;

    private String productArea;

    /**
     * 售卖清单
     */
    private String wareQD;

    /**
     * 主图
     */
    private String imagePath;

    /**
     * 规格参数
     */
    private String param;

    /**
     * 状态
     */
    private String state;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 商品分类
     */
    private String category;

    /**
     * 商品详情页大字段
     */
    private String introduction;

    /**
     * 类目属性
     */
    private List<CategoryAttrDTO> categoryAttrs;
}
