package com.chenyi.yanhuohui.goods.goodsimage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class GoodsImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "spu_id")
    private Long spuId;

    @Column(name = "sku_id")
    private Long skuId;

    @Column(name = "image_type")
    private Integer imageType;

    @Column(name = "external_url")
    private String externalUrl;

    @Column(name = "local_url")
    private String localUrl;

    @Column(name = "del_flag")
    private Integer delFlag;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;
}
