package com.chenyi.yanhuohui.goods.controller;

import com.chenyi.yanhuohui.goods.jdgoods.JDGoodsImportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class GoodsImportController {

    @Autowired
    private JDGoodsImportService jdGoodsImportService;
    @GetMapping(value="/import-jd-categoryies")
    public String importJDGoodsCategories(){
        jdGoodsImportService.importGoodsCategories();
        return "请求发送成功！";
    }
}
