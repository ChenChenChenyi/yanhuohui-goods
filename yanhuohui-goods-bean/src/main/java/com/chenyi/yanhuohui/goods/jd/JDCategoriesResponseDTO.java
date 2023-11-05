package com.chenyi.yanhuohui.goods.jd;

import lombok.Data;

import java.util.List;

@Data
public class JDCategoriesResponseDTO {
    /**
     * 总条数
     */
    private Integer totalRows;

    /**
     * 当前页数
     */
    private Integer pageNo;

    /**
     * 每条页数
     */
    private Integer pageSize;

    /**
     * 分类信息列表
     */
    private List<JDCategoryVO> categorys;

}
