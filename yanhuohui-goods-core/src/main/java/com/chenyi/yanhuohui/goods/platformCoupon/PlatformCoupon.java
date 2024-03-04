//package com.chenyi.yanhuohui.goods.platformCoupon;
//
//import lombok.Data;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Table;
//import java.util.Date;
//
///**
// * 平台优惠券
// *
// * @author 陈义
// */
//@Data
//@Entity
//@Table(name = "platform_coupon")
//public class PlatformCoupon {
//
//    /**
//     * 优惠券ID
//
//     */
//    @Column
//    private long couponId;
//
//    /**
//     * 优惠券码
//     */
//    @Column
//    private String couponCode;
//
//    /**
//     * 活动ID
//     */
//    @Column
//    private String activityId;
//
//    /**
//     * 领取人ID
//     */
//    @Column
//    private String customerId;
//
//    /**
//     * 领取时间
//     */
//    @Column
//    private Date acquireTime;
//
//    /**
//     * 使用时间
//     */
//    @Column
//    private Date useTime;
//
//    /**
//     * 使用状态
//     */
//    @Column
//    private Integer useStatus;
//
//    /**
//     * 开始时间
//     */
//    @Column
//    private Date startTime;
//
//    /**
//     * 结束时间
//     */
//    @Column
//    private Date endTime;
//
//    /**
//     * 创建时间
//     */
//    @Column
//    private Date createTime;
//
//    /**
//     * 更新时间
//     */
//    @Column
//    private Date updateTime;
//
//    /**
//     * 删除标注
//     */
//    @Column
//    private Integer delFlag;
//
//}