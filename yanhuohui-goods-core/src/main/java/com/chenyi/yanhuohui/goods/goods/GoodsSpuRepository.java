package com.chenyi.yanhuohui.goods.goods;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * spu表描述的是一个抽象性的商品 Repository
 *
 * @author chenyi
 */
public interface GoodsSpuRepository extends JpaSpecificationExecutor<GoodsSpu>, JpaRepository<GoodsSpu, Long> {
}