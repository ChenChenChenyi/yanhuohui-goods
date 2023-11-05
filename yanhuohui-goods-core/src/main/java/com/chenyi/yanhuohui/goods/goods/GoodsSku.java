package com.chenyi.yanhuohui.goods.goods;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * sku表,该表表示具体的商品实体
 *
 * @author chenyi
 */
@Data
@Entity
@Table(name = "goods_sku")
public class GoodsSku {

    /**
     * SKU Id
     */
    @Id
    private Long skuId;

    /**
     * SPU Id
     */
    @Column
    private Long spuId;

    /**
     * 商品标题
     */
    @Column
    private String skuName;

    /**
     * 商品副标题
     */
    @Column
    private String skuSubName;

    /**
     * 库存
     */
    @Column
    private Integer stock;

    /**
     * 特有规格参数在SPU规格模板中对应的下标组合(如1_0_0)
     */
    @Column
    private String indexes;

    /**
     * SKU的特有规格参数键值对 (json格式，反序列化时请使用linkedHashMap，保证有序)
     */
    @Column
    private String ownSpec;

    /**
     * 是否可售 (0-无效，1-有效)
     */
    @Column
    private Integer saleable;

    /**
     * 删除标志
     */
    @Column
    private Integer delFlag;

    /**
     * 销售数量
     */
    @Column
    private Long salesNum;

    /**
     * 评论数量
     */
    @Column
    private Long evaluateNum;

    /**
     * 收藏数量
     */
    @Column
    private Long collectNum;

    /**
     * 店铺ID
     */
    @Column
    private Long storeId;

    /**
     * 商品类型（0：实体商品，1：虚拟商品）
     */
    @Column
    private Integer type;

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