package com.chenyi.yanhuohui.goods.goodsentity;

import com.chenyi.yanhuohui.goods.mapper.GoodsInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsInfoServiceImpl implements GoodsInfoService{
    @Autowired
    GoodsInfoMapper userLoginMapper;
    @Override
    public List<GoodsInfo> queryAll() {
        return userLoginMapper.queryAll();
    }

    @Override
    public int add(GoodsInfo userLogin) {
        return userLoginMapper.add(userLogin);
    }

    @Override
    public GoodsInfo queryByName(String username) {
        return userLoginMapper.queryByName(username);
    }
}
