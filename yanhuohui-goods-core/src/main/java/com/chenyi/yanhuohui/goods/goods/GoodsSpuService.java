package com.chenyi.yanhuohui.goods.goods;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class GoodsSpuService {

    @Autowired
    private GoodsSpuRepository goodsSpuRepository;

    @Autowired
    private GoodsSpuDetailRepository goodsSpuDetailRepository;
    @Transactional
    public void saveGoodsSpu(GoodsSpu goodsSpu){
        goodsSpuRepository.save(goodsSpu);
    }
    @Transactional
    public void saveGoodsSpuDetail(GoodsSpuDetail goodsSpuDetail){
        goodsSpuDetailRepository.save(goodsSpuDetail);
    }
}
