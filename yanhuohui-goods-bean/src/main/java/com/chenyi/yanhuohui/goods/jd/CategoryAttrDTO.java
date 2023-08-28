package com.chenyi.yanhuohui.goods.jd;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class CategoryAttrDTO {

    private String attrName;

    private String attRemark;

    private Set<String> vals;

    private Integer sort;
}
