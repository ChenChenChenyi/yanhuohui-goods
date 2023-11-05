package com.chenyi.yanhuohui.goods.goods;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class GoodsSkuService {

    @Autowired
    private GoodsSkuRepository goodsSkuRepository;

    @Autowired
    private GoodsSkuDetailRepository goodsSkuDetailRepository;

    public List<GoodsSku> findBySkuIds(List<String> skuIds){
        return goodsSkuRepository.findBySkuIds(skuIds);
    }

    public void saveGoodsSku(GoodsSku goodsSku){
        goodsSkuRepository.save(goodsSku);
    }

    public void saveGoodsSkuDetail(GoodsSkuDetail goodsSkuDetail){
        goodsSkuDetailRepository.save(goodsSkuDetail);
    }
}
