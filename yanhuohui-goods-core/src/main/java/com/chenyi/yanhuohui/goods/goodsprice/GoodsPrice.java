package com.chenyi.yanhuohui.goods.goodsprice;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 商品价格表
 *
 * @author 陈义
 */
@Data
@Entity
@Table(name = "goods_price")
public class GoodsPrice {

    /**
     * 商品SKUID
     */
    @Id
    private Long skuId;

    /**
     * 渠道价
     */
    @Column
    private BigDecimal channelPrice;

    /**
     * 划线价
     */
    @Column
    private BigDecimal linePrice;

    /**
     * 建议零售价
     */
    @Column
    private BigDecimal marketPrice;

    /**
     * 集中采购价格
     */
    @Column
    private BigDecimal centralizedPrice;

    /**
     * 会员价格
     */
    @Column
    private BigDecimal vipPrice;

    /**
     * 结算价格
     */
    @Column
    private BigDecimal settlePrice;

    /**
     * 删除标志
     */
    @Column
    private Integer delFlag;

    /**
     * 创建时间
     */
    @Column
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Column
    private LocalDateTime updateTime;

}