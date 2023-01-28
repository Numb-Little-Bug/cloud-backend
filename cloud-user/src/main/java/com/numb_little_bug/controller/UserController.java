package com.numb_little_bug.controller;

import com.numb_little_bug.config.EncryptionWithKeyConfig;
import com.numb_little_bug.entity.IDCard;
import com.numb_little_bug.entity.User;
import com.numb_little_bug.mapper.UserMapper;
import com.numb_little_bug.mapper.IDCardMapper;
import com.numb_little_bug.utils.EncryptionWithKey;
import com.numb_little_bug.utils.JsonResult;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;
import java.util.*;

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

    @GetMapping(value="/user")
    public JsonResult getUserById(@RequestHeader("Authorization") String token) {
        String tel = EncryptionWithKey.decrypt(token, EncryptionWithKeyConfig.KEY);
        System.out.println(tel);
        return new JsonResult(0, userMapper.queryUserByTel(tel), "获取用户信息成功", "success");
    }

    @PostMapping(value="/user/{tel}")
    public JsonResult login(@PathVariable("tel") String tel, @RequestBody User userParam) {
        User user = userMapper.queryUserByTel(tel);
        if (user == null) {
            return new JsonResult(400, null, "用户不存在", "failed");
        }
        if (user.getPassword().equals(userParam.getPassword())) {
            // 根据手机号加密生成jwt
            String jwt = EncryptionWithKey.encrypt(tel, EncryptionWithKeyConfig.KEY);
            System.out.println(jwt);
            Map<String, String> map = new HashMap<>();
            map.put("token", jwt);
            return new JsonResult(0, map, "登录成功", "success");
        } else {
            return new JsonResult(400, null, "密码错误", "failed");
        }

    }

    // 接受前端用户注册表单
    @PostMapping(value="/user")
    public JsonResult register(@Valid @RequestBody User user, BindingResult bindingResult){
        // 判断数据格式是否有错误
        if (bindingResult.hasErrors()) {
            return new JsonResult(400, null, bindingResult.getFieldError().getDefaultMessage().toString(), "failed");
        }

        // 判断用户是否已经注册
        User existingUser = userMapper.queryUserByTel(user.getTel());
        if (existingUser != null) {
            return new JsonResult(400,null, "该手机号已经被注册", "failed");
        }
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        //java mysql 时区问题，北京时间比mysql时间少8小时，所以我们加上14小时
        //calendar.add(Calendar.HOUR, 14);
        Date time = calendar.getTime();
        user.setCreated_time(time);
        try{
            userMapper.addUser(user);
        } catch (Exception e){
            // 返回json格式包含状态码的错误信息
            JsonResult jr = new JsonResult();
            jr.setCode(410);
            jr.setMessage("注册失败, "+e.getMessage());
            jr.setType("failed");
            return jr;
        }
        // 返回json格式包含状态码的成功信息
        JsonResult jr = new JsonResult();
        jr.setCode(0);
        jr.setMessage("注册成功");
        jr.setType("success");
        JsonResult result = new JsonResult();
        result.setCode(0);
        result.setMessage("注册成功");
        result.setType("success");
        result.setResult(user);
        jr.setResult(result);
        return jr;
    }

    // 注销用户
    @DeleteMapping(value="/user/{id}")
    public JsonResult deleteUser(@PathVariable("id") Long id) {
        // 判断用户是否存在
        User existingUser = userMapper.queryUserById(id);
        if (existingUser == null) {
            return new JsonResult(404, null, "该用户不存在", "failed");
        }
        try{
            userMapper.deleteUser(id);
        } catch (Exception e){
            // 返回json格式包含状态码的错误信息
            JsonResult jr = new JsonResult();
            jr.setCode(410);
            jr.setMessage("注销失败, "+e.getMessage());
            jr.setType("failed");
            return jr;
        }
        // 返回json格式包含状态码的成功信息
        JsonResult jr = new JsonResult();
        jr.setCode(0);
        jr.setMessage("注销成功");
        jr.setType("success");
        return jr;
    }

    // 用户登出
    @GetMapping(value="/user/logout")
    public JsonResult logout(@RequestHeader("Authorization") String token) {
        if(token != null){
            return new JsonResult(0, null, "登出成功", "success");
        }
        return new JsonResult(400, null, "登出失败", "failed");
    }
}
