package com.numb_little_bug.controller;

import com.numb_little_bug.entity.IDCard;
import com.numb_little_bug.entity.User;
import com.numb_little_bug.mapper.UserMapper;
import com.numb_little_bug.mapper.IDCardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
public class UserController {
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @GetMapping(value="/user/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userMapper.queryUserById(id);
    }

    @GetMapping(value="/user/{username}")
    public User getUserByUsername(@PathVariable("username") String username) {
        return userMapper.queryUserByUsername(username);
    }

    @GetMapping(value="/user/{tel}")
    public User getUserByTel(@PathVariable("tel") String tel) {
        return userMapper.queryUserByTel(tel);
    }

    //接受前端用户注册表单
    @PostMapping(value="/user/register")
    public String register(User user) {
        userMapper.addUser(user);
        return "Register";
    }
}
