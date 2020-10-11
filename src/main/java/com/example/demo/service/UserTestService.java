package com.example.demo.service;

import com.example.demo.dao.UserTestMapper;
import com.example.demo.entity.UserTest;
import com.example.demo.exception.TooFrequentException;
import com.example.demo.service.impl.UserTestServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class UserTestService implements UserTestServiceImpl {
    @Resource
    private UserTestMapper userTestMapper;
    @Override
    public List<UserTest> detail() throws Exception {
//        if("ss".equals("ss"))
//        throw new TooFrequentException("haha");
        return userTestMapper.getList();
    }
}
