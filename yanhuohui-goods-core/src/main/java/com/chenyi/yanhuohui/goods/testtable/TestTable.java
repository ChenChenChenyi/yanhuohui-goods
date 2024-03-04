//package com.chenyi.yanhuohui.goods.testtable;
//
//import lombok.Data;
//import org.hibernate.annotations.GenericGenerator;
//import javax.persistence.*;
//import java.util.Date;
//
///**
// * test_table
// *
// * @author chenyi
// */
//@Data
//@Entity
//@Table(name = "test_table")
//public class TestTable {
//
//    /**
//     * id
//     */
//    @Id
//    @GeneratedValue(generator = "snowflake")
//    @GenericGenerator(name = "snowflake", strategy = "cn.fufeii.generator.config.JpaSnowflakeIdGenerator")
//    @Column
//    private Long id;
//
//    /**
//     * key1
//     */
//    @Column
//    private String key1;
//
//    /**
//     * key2
//     */
//    @Column
//    private String key2;
//
//    /**
//     * del_flag
//     */
//    @Column
//    private Integer delFlag;
//
//    /**
//     * create_time
//     */
//    @Column
//    private Date createTime;
//
//    /**
//     * update_time
//     */
//    @Column
//    private Date updateTime;
//
//}