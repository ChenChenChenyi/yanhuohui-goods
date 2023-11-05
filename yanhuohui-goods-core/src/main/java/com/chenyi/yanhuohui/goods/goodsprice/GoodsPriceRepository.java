package com.chenyi.yanhuohui.goods.goodsprice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 商品价格表 Repository
 *
 * @author chenyi
 */
public interface GoodsPriceRepository extends JpaSpecificationExecutor<GoodsPrice>, JpaRepository<GoodsPrice, Long> {
}