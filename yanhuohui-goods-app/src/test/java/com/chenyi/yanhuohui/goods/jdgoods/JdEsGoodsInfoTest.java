package com.chenyi.yanhuohui.goods.jdgoods;

import cn.hutool.extra.pinyin.engine.pinyin4j.Pinyin4jEngine;
import com.chenyi.yanhuohui.goods.elastic.jdgoods.JdEsGoodsInfo;
import com.chenyi.yanhuohui.goods.elastic.jdgoods.JdEsGoodsInfoRepo;
import com.chenyi.yanhuohui.goods.jd.JDGoodsDetailResponseDTO;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
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

    @Test
    public void testAddGoodsInfo(){
        List<String> list = Stream.of("2134277", "4936419", "100013001088","100021416603","100015295314","100017362630","1231907","7368601","100007949895","4962018","100009515509").collect(Collectors.toList());
        list.forEach(skuID->{
            JDGoodsDetailResponseDTO responseDTO = jdGoodsService.getGoodsDetail(skuID);
            JdEsGoodsInfo jdEsGoodsInfo = new JdEsGoodsInfo();
            jdEsGoodsInfo.setGoodsInfoId(skuID);
            jdEsGoodsInfo.setGoodsInfoName(responseDTO.getName());
            jdEsGoodsInfo.setPrice(BigDecimal.valueOf(9.12));
            String cateId = responseDTO.getCategory().split("|")[2];
            jdEsGoodsInfo.setCateId(Long.valueOf(cateId));
            jdEsGoodsInfo.setBrandName(responseDTO.getBrandName());
            jdEsGoodsInfo.setBrandPinyin(this.getPinYin(responseDTO.getBrandName()));
//        jdEsGoodsInfo.setCreateTime(LocalDateTime.now());
//        jdEsGoodsInfo.setUpdateTime(LocalDateTime.now());
            jdEsGoodsInfoRepo.save(jdEsGoodsInfo);
        });

        JDGoodsDetailResponseDTO responseDTO = jdGoodsService.getGoodsDetail("206892");
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
        Pageable pageable = Pag
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.matchPhraseQuery("goodsInfoName","辣条"));
        boolQuery.must(QueryBuilders.eq)
        builder.withQuery(boolQuery);
        builder.
        List<JdEsGoodsInfo> result = elasticsearchTemplate.queryForList(builder.build(), JdEsGoodsInfo.class);
    }
}
