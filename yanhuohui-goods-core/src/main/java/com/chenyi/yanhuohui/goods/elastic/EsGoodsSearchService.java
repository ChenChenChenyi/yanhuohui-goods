package com.chenyi.yanhuohui.goods.elastic;

import com.chenyi.yanhuohui.goods.api.EsGoodsSearchRequest;
import com.chenyi.yanhuohui.goods.api.EsGoodsSearchResponse;
import com.chenyi.yanhuohui.goods.elastic.jdgoods.EsGoodsSearchHolder;
import org.springframework.stereotype.Service;

@Service
public class EsGoodsSearchService {
    /**
    * Description: 包含一些基础的查询参数
    * date: 2024/3/5 16:35
    * author: ChenYi
    * source: yanhuohui
    */
    public EsGoodsSearchHolder wrapBasicSearchParam(EsGoodsSearchHolder request){
        request.setAddedFlag(1);
        return request;
    }

    public EsGoodsSearchHolder wrapSortParam(EsGoodsSearchHolder request){

        return request;
    }

    public EsGoodsSearchHolder wrapAggrParam(EsGoodsSearchHolder request){

        return request;
    }

    public EsGoodsSearchResponse basicSearchByParam(EsGoodsSearchRequest esGoodsSearchRequest){

    }
}
