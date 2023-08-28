package com.chenyi.yanhuohui.goods.goodsimage;

import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GoodsImageRepository extends JpaRepository<GoodsImage,Long>, JpaSpecificationExecutor<GoodsImage> {
    @Query(value = "select * from goods_image where sku_id in ?1 and del_flag=0",nativeQuery = true)
    List<GoodsImage> findBySkuIds(List<Integer> skuIds);
}
