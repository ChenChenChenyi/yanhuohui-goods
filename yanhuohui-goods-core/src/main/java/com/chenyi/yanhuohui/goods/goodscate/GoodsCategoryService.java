package com.chenyi.yanhuohui.goods.goodscate;

import com.chenyi.yanhuohui.goods.jd.JDCategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsCategoryService {

    @Autowired
    private GoodsCategoryRepository goodsCategoryRepository;
    

    @Transactional
    public void saveJDCategoryVO(List<JDCategoryVO> jdCategoryVOS){
        List<GoodsCategory> categories = new ArrayList<>();
        jdCategoryVOS.forEach(jdCategoryVO -> {
            GoodsCategory goodsCategory = new GoodsCategory();
            goodsCategory.setCateId(jdCategoryVO.getCatId());
            goodsCategory.setCateName(jdCategoryVO.getName());
            goodsCategory.setCateLevel(jdCategoryVO.getCatClass());
            goodsCategory.setCateParentId(jdCategoryVO.getParentId());
            goodsCategory.setDelFlag(0);
            goodsCategory.setCreateTime(LocalDateTime.now());
            goodsCategory.setUpdateTime(LocalDateTime.now());
            categories.add(goodsCategory);
        });
        goodsCategoryRepository.saveAll(categories);
    }
}
