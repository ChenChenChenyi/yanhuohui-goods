package com.chenyi.yanhuohui.goods;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author 陈义
 */
@SpringBootApplication
@Slf4j
@EnableAsync
public class GoodsServiceApplication {


    public static void main(String[] args) {
        Environment env = SpringApplication
                .run(GoodsServiceApplication.class,args).getEnvironment();
    }
}
