package com.chenyi.yanhuohui.goods.goodsspecparam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 规格参数组下的参数名 Repository
 *
 * @author chenyi
 */
public interface GoodsSpecParamRepository extends JpaSpecificationExecutor<GoodsSpecParam>, JpaRepository<GoodsSpecParam, Long> {

    @Query(value = "select * from goods_spec_param where category_id = ?1 and name = ?2 and del_flag=0",nativeQuery = true)
    List<GoodsSpecParam> findByCategoryIdAndName(Long categoryId, String name);
    @Query(value = "select * from goods_spec_param where category_id = ?1 and del_flag=0",nativeQuery = true)
    List<GoodsSpecParam> findByCategoryId(Long categoryId);
}