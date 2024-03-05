package com.chenyi.yanhuohui.goods.api;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
* Description: 商品查询的请求体
* date: 2024/3/5 16:07
* author: ChenYi
* source: yanhuohui
*/

@Data
@Slf4j
public class EsGoodsSearchRequest {
    //基础查询参数
    private int pageSize;
    private int pageNum;

    //搜索框输入的关键字
    private String keyword;

    private String goodsInfoId;

    private String goodsInfoName;

    private BigDecimal price;

    private Long cateId;

    private String brandName;

    private String brandPinyin;

    private LocalDateTime createTime;

    private int channelSource;

    //上架标志
    private int addedFlag;


}
