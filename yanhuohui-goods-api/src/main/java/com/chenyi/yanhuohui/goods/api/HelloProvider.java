package com.chenyi.yanhuohui.goods.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "yanhuohui-goods",contextId = "HelloProvider")
public interface HelloProvider {

    @GetMapping("/hello")
    ResponseEntity hello();
}
