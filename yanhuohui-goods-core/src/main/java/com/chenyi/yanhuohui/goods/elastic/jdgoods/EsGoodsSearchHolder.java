package com.chenyi.yanhuohui.goods.elastic.jdgoods;

import com.chenyi.yanhuohui.goods.constant.EsConstants;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Slf4j
public class EsGoodsSearchHolder {
    //基础查询参数
    private int pageSize;
    private int pageNum;

    private List<AbstractAggregationBuilder> aggs = new ArrayList<>();

    private List<SortBuilder> sorts = new ArrayList<>();

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

    private QueryBuilder getQueryCriterialFromParams(){

    }

    public SearchQuery getSearchCriterialFromParams(){
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        builder.withIndices(EsConstants.DOC_GOODS_INFO_BACKUP);

        builder.withQuery(this.getQueryCriterialFromParams());

        builder.withPageable();

        builder.withFilter();
        if(!CollectionUtils.isEmpty(sorts)){
            sorts.forEach(builder::withSort);
        }
        if(!CollectionUtils.isEmpty(aggs)){
            aggs.forEach(builder::addAggregation);
        }

        builder.withMinScore();

        //折叠

        NativeSearchQuery build = builder.build();

        log.info("搜索条件：{}",build.getQuery().toString());
        log.info("聚合条件：{}",build.getAggregations().toString());
        log.info("排序条件：{}",build.getElasticsearchSorts().toString());

        return build;

    }

    private
}
