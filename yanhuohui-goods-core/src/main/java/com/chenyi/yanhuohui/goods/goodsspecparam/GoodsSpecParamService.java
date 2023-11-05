package com.chenyi.yanhuohui.goods.goodsspecparam;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class GoodsSpecParamService {

    @Autowired
    private GoodsSpecParamRepository goodsSpecParamRepository;
    @Transactional
    public List<GoodsSpecParam> saveSpecParams(List<GoodsSpecParam> goodsSpecParams){
        goodsSpecParams.forEach(goodsSpecParam -> {
            goodsSpecParam.setDelFlag(0);
//            goodsSpecParam.setCreateTime(LocalDateTime.now());
//            goodsSpecParam.setUpdateTime(LocalDateTime.now());
        });
        return goodsSpecParamRepository.saveAll(goodsSpecParams);
    }

    public List<GoodsSpecParam> findByCategoryIdAndName(Long cateId,String name){
        return goodsSpecParamRepository.findByCategoryIdAndName(cateId,name);
    }

    public List<GoodsSpecParam> findByCategoryId(Long cateId){
        return goodsSpecParamRepository.findByCategoryId(cateId);
    }
}
