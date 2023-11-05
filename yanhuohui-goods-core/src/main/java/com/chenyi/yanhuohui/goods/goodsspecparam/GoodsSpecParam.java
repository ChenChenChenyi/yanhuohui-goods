package com.chenyi.yanhuohui.goods.goodsspecparam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 规格参数组下的参数名
 *
 * @author 陈义
 */
@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "goods_spec_param")
public class GoodsSpecParam {

    /**
     * 参数Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long specParamId;

    /**
     * 商品分类Id (参数所属的商品分类)
     */
    @Column
    private Long categoryId;

    /**
     * 规则组Id (参数所属的规格组)
     */
    @Column
    private Long groupId;

    /**
     * 参数名
     */
    @Column
    private String name;

    /**
     * 是否是数字类型参数 (true或false)
     */
    @Column
    private Integer numerical;

    /**
     * 数字类型参数的单位 (非数字类型可以为空)
     */
    @Column
    private String unit;

    /**
     * 是否是SKU通用规格 (0：销售规格，1：通用规格)
     */
    @Column
    private Integer generic;

    /**
     * 是否用于搜索过滤 (true或false)
     */
    @Column
    private Integer searching;

    /**
     * 区间 (数值类型参数的预设区间值，如果需要搜索，则添加分段间隔值，如CPU频率间隔：0.5-1.0)
     */
    @Column
    private String segments;

    /**
     * 排序
     */
    @Column
    private Integer sort;

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