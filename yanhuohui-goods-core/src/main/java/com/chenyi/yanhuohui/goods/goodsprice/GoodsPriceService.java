package com.chenyi.yanhuohui.goods.goodsprice;


import com.chenyi.yanhuohui.common.base.exception.YhhRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 商品价格表
 * CRUD GoodsPrice Service
 *
 * @author chenyi
 */
@Service
public class GoodsPriceService {

    @Autowired
    private GoodsPriceRepository goodsPriceRepository;

    /**
     * 列表查询
     */
    public List<GoodsPrice> selectList(Specification<GoodsPrice> spec) {
        return goodsPriceRepository.findAll(spec);
    }

    /**
     * 分页查询
     */
    public Page<GoodsPrice> selectPage(Specification<GoodsPrice> spec, Pageable pageable) {
        return goodsPriceRepository.findAll(spec, pageable);
    }

    /**
     * 通过ID获取一个可能存在的实体
     */
    public Optional<GoodsPrice> selectByIdOptional(Long id) {
        return goodsPriceRepository.findById(id);
    }

    /**
     * 通过ID获取一个存在的实体
     */
    public GoodsPrice selectById(Long id) {
        return goodsPriceRepository.findById(id).orElseThrow(() -> new YhhRuntimeException());
    }

    /**
     * 通过条件获取一个可能存在的实体
     */
    public Optional<GoodsPrice> selectOneOptional(Specification<GoodsPrice> spec) {
        return goodsPriceRepository.findOne(spec);
    }

    /**
     * 通过条件获取一个存在的实体
     */
    public GoodsPrice selectOne(Specification<GoodsPrice> spec) {
        return goodsPriceRepository.findOne(spec).orElseThrow(() -> new YhhRuntimeException());
    }

    /**
     * 统计个数
     */
    public long count(Specification<GoodsPrice> spec) {
        return goodsPriceRepository.count(spec);
    }

    /**
     * 是否存在
     */
    public boolean exist(Specification<GoodsPrice> spec) {
        return goodsPriceRepository.count(spec) > 0;
    }

    /**
     * 插入实体
     */
    public GoodsPrice insert(GoodsPrice entity) {
        return goodsPriceRepository.saveAndFlush(entity);
    }

    /**
     * 更新实体
     */
    public GoodsPrice updateById(GoodsPrice entity) {
        return goodsPriceRepository.saveAndFlush(entity);
    }

    /**
     * 删除实体
     */
    public void deleteById(Long id) {
        goodsPriceRepository.deleteById(id);
    }

    public void saveAll(List<GoodsPrice> goodsPrices){
        goodsPrices.forEach(goodsPrice -> {
            goodsPrice.setDelFlag(0);
            goodsPrice.setCreateTime(LocalDateTime.now());
            goodsPrice.setUpdateTime(LocalDateTime.now());
        });
        goodsPriceRepository.saveAll(goodsPrices);
    }


}