package com.chenyi.yanhuohui.goods.jdgoods.request;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
@Data
@Builder
public class GetSellPriceRequest {

    /**
     * access token
     */
    private String token;

    /**
     * 商品sku，请以逗号分割。例如121212,121223（最高支持100个商品）
     */
    private String sku;

    /**
     * 英文半角分割的枚举值，枚举值不同出参不同，Price:大客户默认价，marketPrice市场价...
     */
    private String queryExts;
}
