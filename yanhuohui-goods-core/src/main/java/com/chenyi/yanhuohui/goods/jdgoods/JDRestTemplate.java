package com.chenyi.yanhuohui.goods.jdgoods;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.chenyi.yanhuohui.common.base.entity.CommonErrorCode;
import com.chenyi.yanhuohui.common.base.exception.SbcRuntimeException;
import com.chenyi.yanhuohui.goods.bean.dto.JDResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JDRestTemplate {

    @Autowired
    private RestTemplate restTemplate;

    public <T> T request(String url, HttpMethod httpMethod, HashMap<String,String> paramMap, Class<T> clazz){
        log.info("京东接口请求开始，请求URL：{}，请求参数：{}。",url,paramMap.toString());
        //设置Http的Header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        //设置访问参数
       MultiValueMap<String, String> multiValueMap= new LinkedMultiValueMap<String, String>();
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            multiValueMap.put(entry.getKey(), Arrays.asList(entry.getValue()));
        }
        //设置访问的Entity
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(multiValueMap, headers);

        //发送请求
        String resText = restTemplate.postForObject(url,entity,String.class);
        JDResponseDTO<T> jdResponseDTO = JSONObject.parseObject(resText, new TypeReference<JDResponseDTO<T>>(clazz){});
        if(!jdResponseDTO.isSuccess()){
            log.error("调用京东失败，调用结果：{}",jdResponseDTO.toString());
            throw new SbcRuntimeException(CommonErrorCode.FAILED);
        }else {
            return jdResponseDTO.getResult();
        }
    }
}
