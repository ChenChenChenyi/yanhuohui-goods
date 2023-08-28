package com.chenyi.yanhuohui.goods.goodscate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class GoodsCategory {
    @Id
    @Column(name = "cate_id")
    private Integer cateId;

    @Column(name = "cate_name")
    private String cateName;

    @Column(name = "cate_parent_id")
    private Integer cateParentId;

    @Column(name = "cate_img")
    private String cateImg;

    @Column(name = "cate_path")
    private String catePath;

    /**
     * 0:一级分类；1：二级分类；2：三级分类
     */
    @Column(name = "cate_level")
    private Integer cateLevel;

    @Column(name = "sort")
    private Integer sort;

    @Column(name = "del_flag")
    private Integer delFlag;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;
}
