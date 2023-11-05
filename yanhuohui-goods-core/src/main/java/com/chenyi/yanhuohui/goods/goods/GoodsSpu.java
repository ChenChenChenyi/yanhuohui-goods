package com.chenyi.yanhuohui.goods.goods;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * spu表描述的是一个抽象性的商品
 *
 * @author chenyi
 */
@Data
@Entity
@Table(name = "goods_spu")
public class GoodsSpu {

    /**
     * SPU Id
     */
    @Id
    private Long spuId;

    /**
     * 商品标题
     */
    @Column
    private String spuName;

    /**
     * 副标题 (一般是促销信息)
     */
    @Column
    private String spuSubName;

    /**
     * 3级分类Id
     */
    @Column
    private Long cid3;

    /**
     * 品牌Id (商品所属的品牌)
     */
    @Column
    private Long brandId;

    /**
     * 是否上架 (0-下架，1-上架)
     */
    @Column
    private Integer saleable;

    /**
     * 店铺ID
     */
    @Column
    private Long storeId;

    /**
     * 删除标志
     */
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