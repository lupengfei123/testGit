package com.example.demo.dao;

import com.example.demo.entity.UserTest;

import java.util.List;

public interface UserTestMapper {
    int deleteByPrimaryKey(String userId);

    int insert(UserTest record);

    int insertSelective(UserTest record);

    UserTest selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(UserTest record);

    int updateByPrimaryKey(UserTest record);

    List<UserTest> getList ();
}