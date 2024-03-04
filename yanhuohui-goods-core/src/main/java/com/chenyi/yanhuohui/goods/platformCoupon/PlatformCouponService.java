//package com.chenyi.yanhuohui.goods.platformCoupon;
//
//import cn.fufeii.generator.entity.PlatformCoupon;
//import cn.fufeii.generator.service.PlatformCouponService;
//import cn.fufeii.generator.service.crud.CrudPlatformCouponService;
//import cn.fufeii.generator.model.vo.request.PlatformCouponRequest;
//import cn.fufeii.generator.util.BeanCopierUtil;
//import cn.fufeii.generator.model.vo.response.PlatformCouponResponse;
//import cn.fufeii.generator.util.SpecUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.domain.Specification;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///**
// * 平台优惠券 Service Impl
// * 业务实现
// *
// * @author chenyi
// */
//@Service
//public class PlatformCouponService {
//
//    @Autowired
//    private CrudPlatformCouponService crudPlatformCouponService;
//
//    /**
//     * 分页查询
//     */
//    public Page<PlatformCouponResponse> page(PlatformCouponRequest request, Pageable pageable) {
//        Specification<PlatformCoupon> spec = SpecUtil.<PlatformCoupon>and().build();
//        Page<PlatformCoupon> selectPage = crudPlatformCouponService.selectPage(spec, pageable);
//        // 组装response对象返回
//        List<PlatformCoupon> content = selectPage.getContent();
//        // 建议使用setter，字段类型问题能在编译期发现
//        List<PlatformCouponResponse> contentResp = BeanCopierUtil.copy(content, PlatformCouponResponse.class);
//        return new PageImpl<>(contentResp, pageable, selectPage.getTotalElements());
//    }
//
//    /**
//     * 获取
//     */
//    public PlatformCouponResponse info(Long id) {
//        PlatformCoupon platformCoupon = crudPlatformCouponService.selectById(id);
//        PlatformCouponResponse platformCouponResponse = new PlatformCouponResponse();
//        // 建议使用setter，字段类型问题能在编译期发现
//        BeanCopierUtil.copy(platformCoupon, platformCouponResponse);
//        return platformCouponResponse;
//    }
//
//    /**
//     * 保存
//     */
//    public void create(PlatformCouponRequest request) {
//        PlatformCoupon platformCoupon = new PlatformCoupon();
//        // 建议使用setter，字段类型问题能在编译期发现
//        BeanCopierUtil.copy(request, platformCoupon);
//        crudPlatformCouponService.insert(platformCoupon);
//    }
//
//    /**
//     * 更新
//     */
//    public void modify(PlatformCouponRequest request) {
//        PlatformCoupon platformCoupon = new PlatformCoupon();
//        // 建议使用setter，字段类型问题能在编译期发现
//        BeanCopierUtil.copy(request, platformCoupon);
//        crudPlatformCouponService.updateById(platformCoupon);
//    }
//
//    /**
//     * 删除
//     */
//    public void remove(Long id) {
//        crudPlatformCouponService.deleteById(id);
//    }
//
//}