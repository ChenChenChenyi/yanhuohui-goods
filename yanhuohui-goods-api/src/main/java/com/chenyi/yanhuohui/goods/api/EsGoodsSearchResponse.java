package com.chenyi.yanhuohui.goods.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chenyi.yanhuohui.goods.jd.JDCategoryVO;
import com.chenyi.yanhuohui.goods.jd.JdEsGoodsInfoVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EsGoodsSearchResponse {

    private Page<JdEsGoodsInfoVO> esGoodsInfoVOPage = new Page<JdEsGoodsInfoVO>();

    private List<JDCategoryVO> categoryVOList = new ArrayList<>();

    private List<String> brandName = new ArrayList<>();
}
