package com.hongguo.cloud.service;

import com.hongguo.cloud.entities.Pay;

import java.util.List;

public interface PayService {

    int add(Pay pay);

    int update(Pay pay);

    int delete(Integer id);

    Pay queryById(Integer id);

    List<Pay> queryAll();
}
