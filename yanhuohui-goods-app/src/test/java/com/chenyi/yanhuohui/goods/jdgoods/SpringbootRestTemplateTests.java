package com.chenyi.yanhuohui.goods.jdgoods;

import com.alibaba.fastjson.JSONObject;
import com.chenyi.yanhuohui.goods.bean.dto.JDGetSellPriceDTO;
import com.chenyi.yanhuohui.goods.bean.dto.JDResponseDTO;
import com.chenyi.yanhuohui.goods.goodsimage.GoodsImage;
import com.chenyi.yanhuohui.goods.goodsimage.GoodsImageService;
import com.chenyi.yanhuohui.goods.jd.JDGoodsDetailResponseDTO;
import com.chenyi.yanhuohui.goods.jd.SimilarSkuResponseDTO;
import com.chenyi.yanhuohui.goods.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringbootRestTemplateTests {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JDAccessTokenService jdAccessTokenService;

    @Autowired
    private JDGoodsService jdGoodsService;

    @Test
    public void getJDGoodsSellPrice() {
        String requestUrl = "https://bizapi.jd.com/api/price/getSellPrice";

        //构建json请求参数
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("token", "xPwLXX09ntkUzoxgF1AEqtBzL");
        map.add("sku", "100028025335");
        //设置请求头请求格式
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //创建请求实体
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
        //执行post请求，并响应返回字符串
        ResponseEntity<JDResponseDTO> resText = restTemplate.postForEntity(requestUrl, entity, JDResponseDTO.class);
        JDGetSellPriceDTO jdGetSellPriceDTO = JSONObject.parseObject((String) resText.getBody().getResult(),JDGetSellPriceDTO.class);
        System.out.println(resText);
    }

    @Test
    public void getJDAccessToken(){
        String token = jdAccessTokenService.getAccessToken();
    }

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void getRedsiKey(){
        redisUtil.set("chenyi","28");
    }

    @Test
    public void getJDImage(){
        List<String> skuList = new ArrayList<>();
        skuList.add("8289496");
        //jdGoodsService.getCategores();
    }

    @Autowired
    private GoodsImageService goodsImageService;

    @Test
    public void testJPA(){
        List<Integer> skuList = new ArrayList<>();
        skuList.add(222);
        List<GoodsImage> result = goodsImageService.getBySkuId(skuList);
        System.out.println(result);
    }

    @Autowired
    private JDGoodsImportService jdGoodsImportService;
    @Test
    public void testImageImport(){
        List<String> skuList = new ArrayList<>();
        skuList.add("8289496");
        skuList.add("8355057");
        skuList.add("100005891866");
        jdGoodsImportService.importGoodsImage(skuList);
    }

    @Test
    public void getGoodsDetail(){
        List<SimilarSkuResponseDTO> responseDTOS = jdGoodsService.getSimilarSku("8355057");
    }
}
