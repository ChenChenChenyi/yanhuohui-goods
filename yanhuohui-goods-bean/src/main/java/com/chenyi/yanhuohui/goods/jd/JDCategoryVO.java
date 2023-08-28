package com.chenyi.yanhuohui.goods.jd;

import lombok.Data;

@Data
public class JDCategoryVO {
    /**
     * 分类ID
     */
    private Integer catId;

    /**
     * 父分类ID
     */
    private Integer parentId;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 0:一级分类；1：二级分类；2：三级分类
     */
    private Integer catClass;
}
