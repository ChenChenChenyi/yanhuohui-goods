package com.chenyi.yanhuohui.goods;

import cn.hutool.core.bean.BeanUtil;
import com.chenyi.yanhuohui.goods.constant.EsConstants;
import com.chenyi.yanhuohui.goods.elastic.jdgoods.JdEsGoodsInfo;
import com.chenyi.yanhuohui.goods.elastic.jdgoods.JdEsGoodsInfoBackup;
import com.chenyi.yanhuohui.goods.elastic.jdgoods.JdEsGoodsInfoRepo;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ResultsExtractor;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ElasticsearchClientAllTest {


    @Autowired
    private RestHighLevelClient restHighLevelClient;//这个client有点难用，还不如用下面这个
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private JdEsGoodsInfoRepo jdEsGoodsInfoRepo;

    @Test
    public void createIndexAndMapping() {
        //测试用的JdEsGoodsInfo类的索引名字为jdesgoodsinfo_backup
        if (elasticsearchTemplate.indexExists("jdesgoodsinfo_backup")){
            log.info("索引已存在");
            return;
        }
        boolean index = elasticsearchTemplate.createIndex(JdEsGoodsInfo.class);
        boolean mapping = elasticsearchTemplate.putMapping(JdEsGoodsInfo.class);
        //boolean indexAndMapping = elasticsearchSaveController.createIndexAndMapping(Student.class);
    }

    @Test
    public void deleteIndex(){
        elasticsearchTemplate.deleteIndex("JdEsGoodsInfo.class");
    }

    @Test
    public void saveDocment(){
        long sum = elasticsearchTemplate.count(new CriteriaQuery(new Criteria()),JdEsGoodsInfo.class);
        int pageSize = 10;
        int pageNum = (int)(sum%pageSize==0?sum/pageSize:(sum/pageSize+1));
        for(int i = 0;i<pageNum;i++){
            List<IndexQuery> indexQueryList = new ArrayList<>();
            CriteriaQuery criteriaQuery = new CriteriaQuery(new Criteria())
                    .setPageable(PageRequest.of(i, pageSize));
            Page<JdEsGoodsInfo> jdEsGoodsInfoPage = elasticsearchTemplate.queryForPage(criteriaQuery, JdEsGoodsInfo.class);
            jdEsGoodsInfoPage.getContent().forEach(jdEsGoodsInfo -> {
                JdEsGoodsInfoBackup jdEsGoodsInfoBackup = BeanUtil.copyProperties(jdEsGoodsInfo, JdEsGoodsInfoBackup.class);
                IndexQuery indexQuery = new IndexQueryBuilder()
                        .withObject(jdEsGoodsInfoBackup)
                        .build();
                indexQueryList.add(indexQuery);
            });
            elasticsearchTemplate.bulkIndex(indexQueryList);
        }
    }

    /**
     * 根据 id 单个删除
     *
     * @author liuqiuyi
     * @date 2021/4/29 20:30
     */
    @Test
    public void deleteByIdTest(){
        String delete = elasticsearchTemplate.delete(JdEsGoodsInfoBackup.class, "79R2HXkBm5pjjA5okt3o");
        System.out.println(delete);
    }

    /**
     * 批量删除
     *
     * @author liuqiuyi
     * @date 2021/5/6 15:37
     */
    @Test
    public void batchDeleteByIdsTest(){
        CriteriaQuery criteriaQuery = new CriteriaQuery(new Criteria());
        List<String> ids = new ArrayList<>();
        ids.add("sdfsf");
        criteriaQuery.setIds(ids);
        elasticsearchTemplate.delete(criteriaQuery, JdEsGoodsInfoBackup.class);
    }

    /**
    * Description: 普通查询
    * date: 2024/3/5 15:08
    * author: ChenYi
    * source: yanhuohui
    */
    @Test
    public void queryTest(){
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        //确定搜索表
        builder.withIndices(EsConstants.DOC_GOODS_INFO_BACKUP);
        //确定搜索条件
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        //boolQuery.must(QueryBuilders.matchPhraseQuery("goodsInfoName","牛奶"));
        List<Integer> sources = Arrays.asList(2,2);
        boolQuery.must(QueryBuilders.termsQuery("channelSource",sources.stream().distinct().collect(Collectors.toList())));
        builder.withQuery(boolQuery);

        builder.withFilter(QueryBuilders.termQuery("channelSource",2));
        //确定分页条件
        builder.withPageable(PageRequest.of(0,10));
        //确定排序条件
        //builder.withSort(Sort.);

        List<JdEsGoodsInfoBackup> result = elasticsearchTemplate.queryForList(builder.build(), JdEsGoodsInfoBackup.class);
    }

    /**
    * Description: 聚合查询
    * date: 2024/3/5 15:09
    * author: ChenYi
    * source: yanhuohui
    */
    @Test
    public void aggrTest(){
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        //确定搜索表
        builder.withIndices(EsConstants.DOC_GOODS_INFO_BACKUP);
        //确定过滤条件
        builder.withFilter(QueryBuilders.termQuery("channelSource",1));

        //确定聚合条件
        TermsAggregationBuilder vcb= AggregationBuilders.terms("price_term").field("price");
        builder.addAggregation(vcb);

        Aggregations aggregations = elasticsearchTemplate.query(builder.build(), new ResultsExtractor<Aggregations>() {
            @Override
            public Aggregations extract(SearchResponse response) {
                return response.getAggregations();
            }
        });
        //打印查询条件
        log.info(builder.build().getAggregations().toString());
    }




}
