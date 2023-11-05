package com.chenyi.yanhuohui.goods.goods;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * goods_spu_detail Repository
 *
 * @author chenyi
 */
public interface GoodsSpuDetailRepository extends JpaSpecificationExecutor<GoodsSpuDetail>, JpaRepository<GoodsSpuDetail, Long> {
}