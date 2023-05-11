package com.chenyi.yanhuohui.goods.goodsentity;

import java.util.List;

public interface GoodsInfoService {
    //查询
    public List<GoodsInfo> queryAll();
    //添加数据
    public int add(GoodsInfo goodsInfo);
    //根据用户名查询数据
    public GoodsInfo queryByName(String goodsInfoName);
}
