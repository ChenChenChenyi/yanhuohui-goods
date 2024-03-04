package com.chenyi.yanhuohui.goods;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;

import java.net.InetAddress;

/**
 * @author 陈义
 */
@SpringBootApplication
@Slf4j
@EnableAsync
@EnableFeignClients(basePackages = {"com.chenyi.yanhuohui.*.api","com.chenyi.yanhuohui.*.provider"})
@EnableDiscoveryClient
public class GoodsServiceApplication {
    public static void main(String[] args) throws Exception {
        Environment environment = SpringApplication.run(GoodsServiceApplication.class,args).getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = environment.getProperty("server.port");
        String property = environment.getProperty("server.servlet.context-path");
        String path = property == null ? "" :  property;
        System.out.println(
                "\n\t" +
                        "----------------------------------------------------------\n\t" +
                        "Application GOODS is running! Access URLs:\n\t" +
                        "Local: \t\thttp://localhost:" + port + path + "/\n\t" +
                        "External: \thttp://" + ip + ":" + port + path + "/\n\t" +
                        "------------------------------------------------------------");
    }
}
