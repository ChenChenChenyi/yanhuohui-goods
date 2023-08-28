package com.chenyi.yanhuohui.goods.jd;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JDSkuImgResponseDTO {
    private Long id;

    private Long skuId;

    private String path;

    /**
     * 创建时间
     */
    private LocalDateTime created;

    /**
     * 更新时间
     */
    private LocalDateTime modified;

    /**
     * 0:不可用；1：可用
     */
    private Integer yn;

    /**
     * 是否主图，1：是；0：否
     */
    private Integer isPrimary;

    /**
     * 排序
     */
    private Integer orderSort;

    /**
     * 位置
     */
    private Integer position;

    /**
     * 类型，0：方图；1：长图（服装）
     */
    private Integer type;

    /**
     * 特征
     */
    private String features;
}
