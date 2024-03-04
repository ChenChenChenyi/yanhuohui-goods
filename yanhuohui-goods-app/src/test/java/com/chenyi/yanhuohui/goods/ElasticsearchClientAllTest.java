package com.chenyi.yanhuohui.goods;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.chenyi.yanhuohui.goods.elastic.jdgoods.JdEsGoodsInfo;
import com.chenyi.yanhuohui.goods.elastic.jdgoods.JdEsGoodsInfoBackup;
import com.chenyi.yanhuohui.goods.elastic.jdgoods.JdEsGoodsInfoRepo;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void termTest(){
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(new Criteria())
                .build();

        List<JdEsGoodsInfoBackup> movies = elasticsearchTemplate.queryForList(searchQuery, JdEsGoodsInfoBackup.class);

        System.out.println(JSON.toJSONString(movies));
    }






}
