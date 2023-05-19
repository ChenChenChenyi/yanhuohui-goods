package com.chenyi.yanhuohui.goods;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

/**
 * @author 陈义
 */
@SpringBootApplication
@Slf4j
@EnableDubbo
@MapperScan("com.chenyi.yanhuohui.goods") //设置mapper接口的扫描包
public class GoodsServiceApplication {
    public static void main(String[] args) {
        Environment env = SpringApplication
                .run(GoodsServiceApplication.class,args).getEnvironment();
    }
}
