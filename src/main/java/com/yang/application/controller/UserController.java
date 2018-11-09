package com.yang.application.controller;


import com.yang.application.service.UserService;
import com.yang.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * RestController的意思就是controller里面的方法都以json格式输出
 */
@RestController
public class UserController  {

    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    public String index() {
        User user = userService.findUserById(1L);
        return user.getUserName();
    }
}
