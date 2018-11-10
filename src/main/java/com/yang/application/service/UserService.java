package com.yang.application.service;


import com.yang.mapper.UserMapper;
import com.yang.model.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class UserService implements ApplicationContextAware {

//    @Autowired
    private UserMapper userMapper;

    public User findUserById(Long id ) {

        return userMapper.selectById(id);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        userMapper = (UserMapper) applicationContext.getBean("userMapper");
    }
}
