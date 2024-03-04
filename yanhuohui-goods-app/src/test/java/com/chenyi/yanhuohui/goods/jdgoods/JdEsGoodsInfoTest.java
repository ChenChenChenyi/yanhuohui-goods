package com.chenyi.yanhuohui.goods.jdgoods;

import cn.hutool.extra.pinyin.engine.pinyin4j.Pinyin4jEngine;
import com.chenyi.yanhuohui.goods.bean.dto.JDGetSellPriceDTO;
import com.chenyi.yanhuohui.goods.constant.EsConstants;
import com.chenyi.yanhuohui.goods.elastic.jdgoods.JdEsGoodsInfo;
import com.chenyi.yanhuohui.goods.elastic.jdgoods.JdEsGoodsInfoRepo;
import com.chenyi.yanhuohui.goods.jd.JDGoodsDetailResponseDTO;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.data.elasticsearch.core.query.UpdateQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JdEsGoodsInfoTest {

    @Autowired
    private JdEsGoodsInfoRepo jdEsGoodsInfoRepo;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private JDGoodsService jdGoodsService;


    //新增es商品数据
    @Test
    public void testAddGoodsInfo(){
        List<String> list = Stream.of("2134277", "4936419", "100013001088","100021416603","100015295314","100017362630","1231907","7368601","100007949895","4962018","100009515509").collect(Collectors.toList());
        List<JDGetSellPriceDTO> priceDTOS = jdGoodsService.getSellPrice(list);
        Map<String,JDGetSellPriceDTO> skuIDAndPriceDTO = priceDTOS.stream().collect(Collectors.toMap(JDGetSellPriceDTO::getSkuId, Function.identity()));
        Set<String> validSkuId = skuIDAndPriceDTO.keySet();
        validSkuId.forEach(skuID->{
            JDGoodsDetailResponseDTO responseDTO = jdGoodsService.getGoodsDetail(skuID);
            JdEsGoodsInfo jdEsGoodsInfo = new JdEsGoodsInfo();
            jdEsGoodsInfo.setGoodsInfoId(skuID);
            jdEsGoodsInfo.setGoodsInfoName(responseDTO.getName());
            jdEsGoodsInfo.setPrice(skuIDAndPriceDTO.get(skuID).getPrice());
            String cateId = responseDTO.getCategory().split(";")[2];
            jdEsGoodsInfo.setCateId(Long.valueOf(cateId));
            jdEsGoodsInfo.setBrandName(responseDTO.getBrandName());
            jdEsGoodsInfo.setBrandPinyin(this.getPinYin(responseDTO.getBrandName()));
            jdEsGoodsInfo.setCreateTime(LocalDateTime.now());
            jdEsGoodsInfo.setChannelSource(1);
            jdEsGoodsInfoRepo.save(jdEsGoodsInfo);
        });

        JDGoodsDetailResponseDTO responseDTO = jdGoodsService.getGoodsDetail("206892");
    }

    //修改商品ES数据
    @Test
    public void testUpdateGoodsInfo(){
        Map<String, Object> params = new HashMap<>();
        params.put("channelSource", 2);
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.doc(params);

        UpdateQueryBuilder updateQueryBuilder = new UpdateQueryBuilder();
        updateQueryBuilder.withId("100015295314");
        updateQueryBuilder.withUpdateRequest(updateRequest);
        updateQueryBuilder.withClass(JdEsGoodsInfo.class);

        UpdateQuery updateQuery = updateQueryBuilder.build();
        UpdateResponse updateResponse = elasticsearchTemplate.update(updateQuery);
    }

    public String getPinYin(String name){
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 大小写
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不加声调
        format.setVCharType(HanyuPinyinVCharType.WITH_V);// 'ü' 使用 "v" 代替
        Pinyin4jEngine engine = new Pinyin4jEngine(format);
        return engine.getPinyin(name, "");
    }

    @Test
    public void getPinYinTest(){
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 大小写
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不加声调
        format.setVCharType(HanyuPinyinVCharType.WITH_V);// 'ü' 使用 "v" 代替
        Pinyin4jEngine engine = new Pinyin4jEngine(format);
        System.out.println(engine.getPinyin("陈义", ""));
    }

    @Test
    public void query_by_title(){
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        //boolQuery.must(QueryBuilders.matchPhraseQuery("goodsInfoName","辣条"));
        //boolQuery.must(QueryBuilders.eq)
        //确定搜索表
        builder.withIndices(EsConstants.DOC_GOODS_INFO);

        //确定搜索条件
        boolQuery.must(QueryBuilders.matchPhraseQuery("goodsInfoName","良品铺子"));
        builder.withQuery(boolQuery);

        //确定分页条件
        //builder.withPageable();

        //确定

        List<JdEsGoodsInfo> result = elasticsearchTemplate.queryForList(builder.build(), JdEsGoodsInfo.class);
    }
}
