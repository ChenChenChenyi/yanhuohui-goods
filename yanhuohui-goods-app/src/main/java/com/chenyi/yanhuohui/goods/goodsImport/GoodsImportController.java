package com.chenyi.yanhuohui.goods.goodsImport;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.UUID;

@RestController
@Slf4j
public class GoodsImportController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/hello")
    public void hello(){
        String uuid = UUID.randomUUID().toString();
        uuid = StringUtils.replace(uuid,"-","");
        System.out.println(uuid);
    }

}
