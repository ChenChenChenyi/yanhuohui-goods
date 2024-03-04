//package com.chenyi.yanhuohui.goods.platformCoupon;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.domain.Specification;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
///**
// * 平台优惠券
// * CRUD PlatformCoupon Service
// *
// * @author chenyi
// */
//@Service
//public class CrudPlatformCouponService {
//
//    @Autowired
//    private PlatformCouponRepository platformCouponRepository;
//
//    /**
//     * 列表查询
//     */
//    public List<PlatformCoupon> selectList(Specification<PlatformCoupon> spec) {
//        return platformCouponRepository.findAll(spec);
//    }
//
//    /**
//     * 分页查询
//     */
//    public Page<PlatformCoupon> selectPage(Specification<PlatformCoupon> spec, Pageable pageable) {
//        return platformCouponRepository.findAll(spec, pageable);
//    }
//
//    /**
//     * 通过ID获取一个可能存在的实体
//     */
//    public Optional<PlatformCoupon> selectByIdOptional(Long id) {
//        return platformCouponRepository.findById(id);
//    }
//
//    /**
//     * 通过ID获取一个存在的实体
//     */
//    public PlatformCoupon selectById(Long id) {
//        return platformCouponRepository.findById(id).orElseThrow(NullPointerException::new);
//    }
//
//    /**
//     * 通过条件获取一个可能存在的实体
//     */
//    public Optional<PlatformCoupon> selectOneOptional(Specification<PlatformCoupon> spec) {
//        return platformCouponRepository.findOne(spec);
//    }
//
//    /**
//     * 通过条件获取一个存在的实体
//     */
//    public PlatformCoupon selectOne(Specification<PlatformCoupon> spec) {
//        return platformCouponRepository.findOne(spec).orElseThrow(NullPointerException::new);
//    }
//
//    /**
//     * 统计个数
//     */
//    public long count(Specification<PlatformCoupon> spec) {
//        return platformCouponRepository.count(spec);
//    }
//
//    /**
//     * 是否存在
//     */
//    public boolean exist(Specification<PlatformCoupon> spec) {
//        return platformCouponRepository.count(spec) > 0;
//    }
//
//    /**
//     * 插入实体
//     */
//    public PlatformCoupon insert(PlatformCoupon entity) {
//        return platformCouponRepository.saveAndFlush(entity);
//    }
//
//    /**
//     * 更新实体
//     */
//    public PlatformCoupon updateById(PlatformCoupon entity) {
//        return platformCouponRepository.saveAndFlush(entity);
//    }
//
//    /**
//     * 删除实体
//     */
//    public void deleteById(Long id) {
//        platformCouponRepository.deleteById(id);
//    }
//
//
//    // ----------------------------------------------------------------------------------- //
//    // -------------------------------- 下面是CRUD的扩展 ----------------------------------- //
//    // ----------------------------------------------------------------------------------- //
//
//
//}