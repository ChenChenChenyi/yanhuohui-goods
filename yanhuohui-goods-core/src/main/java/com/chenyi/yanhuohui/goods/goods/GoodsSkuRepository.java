package com.chenyi.yanhuohui.goods.goods;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * sku表,该表表示具体的商品实体 Repository
 *
 * @author 陈义
 */
public interface GoodsSkuRepository extends JpaSpecificationExecutor<GoodsSku>, JpaRepository<GoodsSku, Long> {
    @Query(value = "select * from goods_sku where sku_id in ?1 and del_flag=0",nativeQuery = true)
    List<GoodsSku> findBySkuIds(List<String> skuIds);
}