package com.chenyi.yanhuohui.goods.goodsimage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class GoodsImageService {


    @Autowired
    private GoodsImageRepository goodsImageRepository;

    public List<GoodsImage> getBySkuId(List<Integer> skuIds){
        return goodsImageRepository.findBySkuIds(skuIds);
    }

    @Transactional
    public void saveGoodsImages(List<GoodsImage> goodsImageList){
        goodsImageRepository.saveAll(goodsImageList);
    }
}
