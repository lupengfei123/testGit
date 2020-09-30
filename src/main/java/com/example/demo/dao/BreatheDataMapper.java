package com.example.demo.dao;

import com.example.demo.entity.BreatheData;

public interface BreatheDataMapper {
    int insert(BreatheData record);

    int insertSelective(BreatheData record);
}