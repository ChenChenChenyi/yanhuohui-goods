package com.chenyi.yanhuohui.goods.mapper;

import com.chenyi.yanhuohui.goods.goodsentity.GoodsInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GoodsInfoMapper {
    //查询
    public List<GoodsInfo> queryAll();
    //添加数据
    public int add(GoodsInfo goodsInfo);
    //根据用户名查询数据
    public GoodsInfo queryByName(String username);
}
