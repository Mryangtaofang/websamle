package com.yang.application.service;


import com.yang.mapper.UserMapper;
import com.yang.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findUserById(Long id ) {
        return userMapper.selectById(id);
    }
}
