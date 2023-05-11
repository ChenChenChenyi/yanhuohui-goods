package com.chenyi.yanhuohui.goods.goodsentity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author 陈义
 */
@Data
public class TestVO implements Serializable {

    private String id;

    private List<InnerVO> innerVO;
}
