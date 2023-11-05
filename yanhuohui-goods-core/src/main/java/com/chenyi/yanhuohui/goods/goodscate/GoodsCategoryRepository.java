package com.chenyi.yanhuohui.goods.goodscate;

import com.chenyi.yanhuohui.goods.goodsimage.GoodsImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

@Service
public interface GoodsCategoryRepository extends JpaRepository<GoodsCategory,Long>, JpaSpecificationExecutor<GoodsCategory> {
}
