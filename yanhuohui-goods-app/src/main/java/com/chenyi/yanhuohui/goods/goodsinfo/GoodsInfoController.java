package com.chenyi.yanhuohui.goods.goodsinfo;

import com.chenyi.yanhuohui.goods.goodsentity.GoodsInfo;
import com.chenyi.yanhuohui.goods.goodsentity.GoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goodsInfo")
public class GoodsInfoController {
    @Autowired
    GoodsInfoService goodsInfoService;

    @GetMapping("/query-all")
    public List<GoodsInfo> queryAll(){
        return goodsInfoService.queryAll();
    }

    @PostMapping("/query-by-name")
    public GoodsInfo queryByName(@RequestParam String goodsInfoName){
        return goodsInfoService.queryByName(goodsInfoName);
    }

}
