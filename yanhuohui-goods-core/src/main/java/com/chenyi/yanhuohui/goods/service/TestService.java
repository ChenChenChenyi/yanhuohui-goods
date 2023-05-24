package com.chenyi.yanhuohui.goods.service;

import com.chenyi.yanhuohui.goods.api.TestServiceApi;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class TestService implements TestServiceApi {

    public void hello(){
        System.out.println("Hello");
    }
}
