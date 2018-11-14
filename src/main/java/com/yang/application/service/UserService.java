package com.yang.application.service;


import com.yang.mapper.UserMapper;
import com.yang.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService{

    @Autowired
    private UserMapper userMapper;

    public User findUserById(Long id ) {

        return userMapper.selectById(id);
    }

}
