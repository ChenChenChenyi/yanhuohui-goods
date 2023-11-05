package com.chenyi.yanhuohui.goods.goods;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * sku详情表
 *
 * @author chenyi
 */
@Data
@Entity
@Table(name = "goods_sku_detail")
public class GoodsSkuDetail {

    /**
     * skuID
     */
    @Id
    private Long skuId;

    /**
     * 一般属性值，一些随意填写的K-V值
     */
    @Column
    private String generalParam;

    /**
     * 商品详情长页，存储HTML
     */
    @Column
    private String goodsInfoDetail;

    /**
     * 商品清单
     */
    @Column
    private String packingList;

    /**
     * 售后服务
     */
    @Column
    private String afterService;

    /**
     * 商品类型（0：实体商品，1：虚拟商品）
     */
    @Column
    private Integer type;

    @Column
    private Integer delFlag;

    /**
     * 添加时间
     */
    @Column
    private LocalDateTime createTime;

    /**
     * 最后修改时间
     */
    @Column
    private LocalDateTime updateTime;

}