package com.chenyi.yanhuohui.goods.configuration;

import com.alibaba.fastjson.JSON;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StopWatch;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * RestTemplate集成okhttp3
 */
@Configuration
@Slf4j
public class RestTemplateConfig {
    /**
     * 创建http请求工厂
     *
     * 实例化OkHttp3
     *
     * @return
     */
    @Bean
    public OkHttp3ClientHttpRequestFactory httpRequestFactory() {
        OkHttp3ClientHttpRequestFactory requestFactory = new OkHttp3ClientHttpRequestFactory();
        requestFactory.setConnectTimeout(5000);
        requestFactory.setReadTimeout(8000);
        requestFactory.setWriteTimeout(8000);
        return requestFactory;
    }

    /**
     * 实例化RestTemplate模板
     *
     * @return
     */
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate(httpRequestFactory());
        // 添加拦截器
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        MyRequestInterceptor myRequestInterceptor = new MyRequestInterceptor();
        interceptors.add(myRequestInterceptor);
        restTemplate.setInterceptors(interceptors);
        // 中文乱码，主要是 StringHttpMessageConverter的默认编码为ISO导致的
        List<HttpMessageConverter<?>> list = restTemplate.getMessageConverters();
        for (HttpMessageConverter converter : list) {
            if (converter instanceof StringHttpMessageConverter) {
                ((StringHttpMessageConverter) converter).setDefaultCharset(StandardCharsets.UTF_8);
                List<MediaType> mediaTypes = new ArrayList<>();
                mediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
                ((StringHttpMessageConverter) converter).setSupportedMediaTypes(mediaTypes);
            }
        }

        return restTemplate;
    }


    /**
     * 自定义的拦截器
     *
     * 主要打印日志
     *
     */
    class MyRequestInterceptor implements ClientHttpRequestInterceptor {
        @Override
        public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] body, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
            //记录请求开始时间
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            //执行请求
            ClientHttpResponse response = clientHttpRequestExecution.execute(httpRequest, body);
            //执行完毕后，这里要创建备份，要不然会报io提前关闭的异常错误
            ClientHttpResponse responseCopy = new BufferingClientHttpResponseWrapper(response);
            //记录请求结束时间
            stopWatch.stop();
            //获取响应内容
            StringBuilder resBody = new StringBuilder();
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(responseCopy.getBody(),
                    Charset.forName("UTF-8")))) {
                String line = bufferedReader.readLine();
                while (line != null) {
                    resBody.append(line);
                    line = bufferedReader.readLine();
                }
            }

            //获取请求内容
            String reqBody = "";
            //这里可以自定义想打印什么方法或者请求的内容，如下只打印post请求的日志，因为这时候才会带上body
            if (httpRequest.getHeaders().getContentType() != null && "POST".equals(httpRequest.getMethod().name())) {
                String requestBody = new String(body, Charset.forName("UTF-8"));
//                String contentType = httpRequest.getHeaders().getContentType().toString();
                //这里可以对contentType做一些判断，针对其格式化请求体的内容，如"application/x-www-form-urlencoded"格式会附带一些boundary(分割线)的内容
                reqBody = requestBody;

            }

            //打印日志的格式可以自定义
            log.info(JSON.toJSONString(RestLog.builder().costTime(stopWatch.getLastTaskTimeMillis()).headers(httpRequest.getHeaders()).method(httpRequest.getMethodValue())
                    .reqUrl(httpRequest.getURI().toString()).reqBody(reqBody).resBody(resBody.toString()).resStatus(responseCopy.getRawStatusCode()).build()));

            return responseCopy;
        }
    }


    /**
     * 响应内容备份
     */
    final class BufferingClientHttpResponseWrapper implements ClientHttpResponse {

        private final ClientHttpResponse response;

        private byte[] body;


        BufferingClientHttpResponseWrapper(ClientHttpResponse response) {
            this.response = response;
        }


        @Override
        public HttpStatus getStatusCode() throws IOException {
            return this.response.getStatusCode();
        }

        @Override
        public int getRawStatusCode() throws IOException {
            return this.response.getRawStatusCode();
        }

        @Override
        public String getStatusText() throws IOException {
            return this.response.getStatusText();
        }

        @Override
        public HttpHeaders getHeaders() {
            return this.response.getHeaders();
        }

        @Override
        public ByteArrayInputStream getBody() throws IOException {
            if (this.body == null) {
                this.body = StreamUtils.copyToByteArray(this.response.getBody());
            }
            return new ByteArrayInputStream(this.body);
        }

        @Override
        public void close() {
            this.response.close();
        }
    }

    /**
     * 定义日志的字段
     */
    @Data
    @Builder
    private static class RestLog {
        private String reqUrl;
        private String method;
        private HttpHeaders headers;
        private String reqBody;
        private String resBody;
        private long costTime;
        private int resStatus;
    }
}
