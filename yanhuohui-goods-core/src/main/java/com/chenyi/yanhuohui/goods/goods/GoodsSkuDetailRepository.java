package com.chenyi.yanhuohui.goods.goods;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * sku详情表 Repository
 *
 * @author chenyi
 */
public interface GoodsSkuDetailRepository extends JpaSpecificationExecutor<GoodsSkuDetail>, JpaRepository<GoodsSkuDetail, Long> {
}