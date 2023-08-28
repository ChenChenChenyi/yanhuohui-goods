package com.chenyi.yanhuohui.goods.jdgoods;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.chenyi.yanhuohui.goods.bean.dto.JDGetSellPriceDTO;
import com.chenyi.yanhuohui.goods.bean.dto.JDResponseDTO;
import com.chenyi.yanhuohui.goods.jd.JDCategoriesResponseDTO;
import com.chenyi.yanhuohui.goods.jd.JDGoodsDetailResponseDTO;
import com.chenyi.yanhuohui.goods.jd.JDSkuImgResponseDTO;
import com.chenyi.yanhuohui.goods.jd.SimilarSkuResponseDTO;
import com.chenyi.yanhuohui.goods.jdgoods.request.GetSellPriceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JDGoodsService {
    @Value("${jd.url.prefix}")
    private String jdUrlPrefix;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JDAccessTokenService jdAccessTokenService;

    @Autowired
    private JDRestTemplate jdRestTemplate;

    /**
     * 获取京东商品价格
     */
    public List<JDGetSellPriceDTO> getSellPrice(GetSellPriceRequest getSellPriceRequest){
        String token = "";
        getSellPriceRequest.setToken(token);
        return null;
    }

    /**
     * 查询商品图片
     */
    public Map<String, List<JDSkuImgResponseDTO>> getJdSkuImg(List<String> skuList){
        //请求URL
        String requestUrl = jdUrlPrefix + "/api/product/skuImage";
        //构建请求参数
        HashMap<String, String> map= new HashMap<>();
        String skus = String.join(",",skuList);
        //最高支持100个商品
        map.put("sku", skus);
        map.put("token", jdAccessTokenService.getAccessToken());
        //执行post请求，并响应返回字符串
        String response = jdRestTemplate.request(requestUrl, HttpMethod.POST,map, String.class);
        return JSONObject.parseObject(response, new TypeReference<Map<String, List<JDSkuImgResponseDTO>>>(){});
    }

    public JDCategoriesResponseDTO getCategores(Integer pageNo, Integer pageSize){
        //请求URL
        String requestUrl = jdUrlPrefix + "/api/product/getCategorys";
        //构建请求参数
        HashMap<String, String> map= new HashMap<>();
        map.put("token", jdAccessTokenService.getAccessToken());
        map.put("pageNo",pageNo.toString());
        map.put("pageSize",pageSize.toString());
        return jdRestTemplate.request(requestUrl, HttpMethod.POST,map, JDCategoriesResponseDTO.class);
    }

    /**
     *
     * @param skuId 商品ID
     * @param queryExts 商品查询的扩展字段
     */
    public JDGoodsDetailResponseDTO getGoodsDetail(String skuId){
        //请求URL
        String requestUrl = jdUrlPrefix + "/api/product/getDetail";
        //构建请求参数
        HashMap<String, String> map= new HashMap<>();
        map.put("token", jdAccessTokenService.getAccessToken());
        map.put("sku",skuId);
        map.put("queryExts", "categoryAttrs");
        return jdRestTemplate.request(requestUrl, HttpMethod.POST,map, JDGoodsDetailResponseDTO.class);
    }

    public List<SimilarSkuResponseDTO> getSimilarSku(String skuId){
        //请求URL
        String requestUrl = jdUrlPrefix + "/api/product/getSimilarSku";
        //构建请求参数
        HashMap<String, String> map= new HashMap<>();
        map.put("token", jdAccessTokenService.getAccessToken());
        map.put("skuId",skuId);
        String result = jdRestTemplate.request(requestUrl, HttpMethod.POST,map, String.class);
        return JSONObject.parseArray(result,SimilarSkuResponseDTO.class);
    }
}
