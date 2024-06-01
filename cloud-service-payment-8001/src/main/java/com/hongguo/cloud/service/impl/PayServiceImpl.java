package com.hongguo.cloud.service.impl;

import com.hongguo.cloud.entities.Pay;
import com.hongguo.cloud.mapper.PayMapper;
import com.hongguo.cloud.service.PayService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayServiceImpl implements PayService {

    @Resource
    private PayMapper payMapper;

    @Override
    public int add(Pay pay) {
        return payMapper.insertSelective(pay);
    }

    @Override
    public int update(Pay pay) {
        return payMapper.updateByPrimaryKey(pay);
    }

    @Override
    public int delete(Integer id) {
        return payMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Pay queryById(Integer id) {
        return payMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Pay> queryAll() {
        return payMapper.selectAll();
    }
}
