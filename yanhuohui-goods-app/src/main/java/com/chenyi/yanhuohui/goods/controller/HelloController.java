package com.chenyi.yanhuohui.goods.controller;

import com.chenyi.yanhuohui.customer.provider.CustomerQueryProvider;
import com.chenyi.yanhuohui.goods.api.HelloProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController implements HelloProvider {

    @Autowired
    private CustomerQueryProvider customerQueryProvider;



    @Override
    public ResponseEntity hello(){
        System.out.println("hello goods!");
        //customerQueryProvider.hello();
        return new ResponseEntity <> ("SUCCESS!", HttpStatus.OK);
    }
}
