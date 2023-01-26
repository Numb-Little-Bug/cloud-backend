package com.numb_little_bug.controller;

import com.numb_little_bug.entity.IDCard;
import com.numb_little_bug.entity.User;
import com.numb_little_bug.mapper.UserMapper;
import com.numb_little_bug.mapper.IDCardMapper;
import com.numb_little_bug.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@RestController
public class UserController {
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @GetMapping(value="/user/all")
    public List<User> getAllUser() {
        return userMapper.queryAllUser();
    }

    @GetMapping(value="/user/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userMapper.queryUserById(id);
    }

    //接受前端用户注册表单
    @PostMapping(value="/user/register")
    public JsonResult register(@RequestBody User user){
        try{
            userMapper.addUser(user);
        } catch (Exception e){
            //返回json格式包含状态码的错误信息
            JsonResult jr = new JsonResult();
            jr.setCode(500);
            jr.setMsg("注册失败");
            return jr;
        }
        //返回json格式包含状态码的成功信息
        JsonResult jr = new JsonResult();
        jr.setCode(0);
        jr.setMsg("注册成功");
        return jr;
    }
}
