package com.chenyi.yanhuohui.goods.controller;

import cn.hutool.core.bean.BeanUtil;
import com.chenyi.yanhuohui.common.base.entity.BaseResponse;
import com.chenyi.yanhuohui.goods.api.EsGoodsSearchRequest;
import com.chenyi.yanhuohui.goods.api.EsGoodsSearchResponse;
import com.chenyi.yanhuohui.goods.elastic.EsGoodsSearchService;
import com.chenyi.yanhuohui.goods.elastic.jdgoods.EsGoodsSearchHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
* Description: 商品搜索的相关接口
* date: 2024/3/5 16:03
* author: ChenYi
* source: yanhuohui
*/

@RestController
@RequestMapping(value = "/goods-search")
public class GoodsSearchController {

    @Autowired
    private EsGoodsSearchService esGoodsSearchService;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
    * Description: 通过ES搜索商品
    * date: 2024/3/5 16:12
    * author: ChenYi
    * source: yanhuohui
    */
    public BaseResponse goodsSearch(@Valid @RequestBody EsGoodsSearchRequest esGoodsSearchRequest){
        //校验数据

        //转换搜索对象
        EsGoodsSearchHolder esGoodsSearchHolder = BeanUtil.copyProperties(esGoodsSearchRequest, EsGoodsSearchHolder.class);
        //根据业务新增一些搜索条件

        //添加基础搜索条件
        esGoodsSearchService.wrapBasicSearchParam(esGoodsSearchHolder);
        //添加排序
        esGoodsSearchService.wrapSortParam(esGoodsSearchHolder);
        //添加聚合
        esGoodsSearchService.wrapAggrParam(esGoodsSearchHolder);

        SearchQuery searchQuery = esGoodsSearchHolder.getSearchCriterialFromParams();
        EsGoodsSearchResponse response = elasticsearchTemplate.query(searchQuery,
                esResponse->{
        })

        return BaseResponse.SUCCESSFUL();

    }

}
