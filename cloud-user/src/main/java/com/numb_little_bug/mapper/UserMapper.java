package com.numb_little_bug.mapper;

import com.numb_little_bug.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    //查询所有用户
    @Select("select * from user")
    List<User> queryAllUser();

    //根据id查询用户
    @Select("select * from user where id = #{id}")
    User queryUserById(Long id);

    //根据用户名查询用户
    @Select("select * from user where name = #{name}")
    User queryUserByName(String name);

    //根据手机号查询用户
    @Select("select * from user where phone = #{tel}")
    User queryUserByTel(String tel);

    //添加用户
    @Select("insert into user (name, password, phone, email, id_card_number) values (#{name}, #{password}, #{phone}, #{email}, #{id_card_number})")
    void addUser(User user);
}
