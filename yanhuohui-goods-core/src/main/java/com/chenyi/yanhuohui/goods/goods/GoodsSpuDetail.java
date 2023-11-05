package com.chenyi.yanhuohui.goods.goods;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * goods_spu_detail
 *
 * @author 陈义
 */
@Data
@Entity
@Table(name = "goods_spu_detail")
public class GoodsSpuDetail {

    /**
     * SPU Id
     */
    @Id
    private Long spuId;

    /**
     * 商品描述信息
     */
    @Column
    private String description;

    /**
     * 通用规格键值对 (json格式)
     */
    @Column
    private String genericSpec;

    /**
     * 特有规格可选值 (json格式)
     */
    @Column
    private String specialSpec;

    /**
     * 删除标志
     */
    @Column
    private Integer delFlag;

    /**
     * create_time
     */
    @Column
    private LocalDateTime createTime;

    /**
     * update_time
     */
    @Column
    private LocalDateTime updateTime;

}