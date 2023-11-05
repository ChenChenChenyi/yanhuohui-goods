package com.chenyi.yanhuohui.goods.jdgoods;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.common.util.Md5Utils;
import com.chenyi.yanhuohui.common.base.entity.BaseResponse;
import com.chenyi.yanhuohui.goods.bean.dto.JDGetSellPriceDTO;
import com.chenyi.yanhuohui.goods.bean.dto.JDResponseDTO;
import com.chenyi.yanhuohui.goods.jd.AccessTokenResponseDTO;
import com.chenyi.yanhuohui.goods.jd.RefreshTokenResponseDTO;
import com.chenyi.yanhuohui.goods.utils.MD5Utils;
import com.chenyi.yanhuohui.goods.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class JDAccessTokenService {

    @Value("${jd.url.prefix}")
    private String jdUrlPrefix;

    @Value("${jd.username}")
    private String jdUserName;

    @Value("${jd.userpassword}")
    private String userPassword;

    @Value("${jd.clientId}")
    private String jdClientId;

    @Value("${jd.clientSecret}")
    private String clientSecret;

    private static final String accessTokenKey = "access_token";

    private static final String refreshTokenKey = "refresh_token";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JDRestTemplate jdRestTemplate;

    @Autowired
    private RedisUtil redisUtil;

    public String getAccessToken(){
        //请求URL
        String requestUrl = jdUrlPrefix + "/oauth2/accessToken";

        //先从缓存中取值
        if(redisUtil.hasKey(accessTokenKey)){
            log.info("当前用户{}的accessToken未失效！",jdUserName);
            return redisUtil.get(accessTokenKey);
        }
        if(redisUtil.hasKey(refreshTokenKey)){
            log.info("当前用户{}的accessToken已失效,刷新获取新的token！",jdUserName);
            return this.refreshToken(redisUtil.get(refreshTokenKey));
        }

        //构建请求参数
        HashMap<String, String> map= new HashMap<>();
        map.put("grant_type", "access_token");
        map.put("client_id", jdClientId);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = df.format(LocalDateTime.now());
        map.put("timestamp", timestamp);
        map.put("username",jdUserName);
        String password = DigestUtils.md5Hex(userPassword).toLowerCase();
        map.put("password",password);
        String signString = clientSecret + timestamp + jdClientId + jdUserName + password + "access_token" + clientSecret;
        String sign = DigestUtils.md5Hex(signString).toUpperCase();
        map.put("sign",sign);
        //执行post请求，并响应返回字符串
        AccessTokenResponseDTO accessTokenResponseDTO = jdRestTemplate.request(requestUrl, HttpMethod.POST,map,AccessTokenResponseDTO.class);
        //存入缓存
        redisUtil.set(accessTokenKey,accessTokenResponseDTO.getAccess_token());
        redisUtil.expire(accessTokenKey,accessTokenResponseDTO.getExpires_in(), TimeUnit.SECONDS);
        redisUtil.set(refreshTokenKey,accessTokenResponseDTO.getRefresh_token());
        redisUtil.expire(refreshTokenKey,accessTokenResponseDTO.getRefresh_token_expires() - Instant.now().getEpochSecond(),TimeUnit.SECONDS);
        return accessTokenResponseDTO.getAccess_token();
    }

    public String refreshToken(String refreshToken){
        //请求URL
        String requestUrl = jdUrlPrefix + "/oauth2/refreshToken";
        //构建请求参数
        HashMap<String, String> map= new HashMap<>();
        map.put("refresh_token", refreshToken);
        map.put("client_id", jdClientId);
        map.put("client_secret", clientSecret);
        //执行post请求，并响应返回字符串
        RefreshTokenResponseDTO refreshTokenResponseDTO = jdRestTemplate.request(requestUrl, HttpMethod.POST,map, RefreshTokenResponseDTO.class);
        redisUtil.set(accessTokenKey,refreshTokenResponseDTO.getAccess_token());
        redisUtil.expire(accessTokenKey,refreshTokenResponseDTO.getExpires_in(), TimeUnit.SECONDS);
        redisUtil.set(refreshTokenKey,refreshTokenResponseDTO.getRefresh_token());
        redisUtil.expire(refreshTokenKey,refreshTokenResponseDTO.getRefresh_token_expires() - Instant.now().getEpochSecond(),TimeUnit.SECONDS);
        return refreshTokenResponseDTO.getAccess_token();
    }
}
