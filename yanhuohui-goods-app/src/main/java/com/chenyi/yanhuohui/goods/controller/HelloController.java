package com.chenyi.yanhuohui.goods.controller;

import com.chenyi.yanhuohui.goods.dto.MerchantDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLOutput;

@RestController
public class HelloController {

    @GetMapping(value="/hello")
    public void hello(){
        System.out.println("Hello chenyi!");
    }
}
