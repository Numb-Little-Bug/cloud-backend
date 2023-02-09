package com.numb_little_bug.mapper;

import com.numb_little_bug.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    // 查询所有用户
    @Select("select * from user")
    List<User> queryAllUser();

    @Select("select * from user where role = #{role}")
    List<User> queryUserByRole(String role);

    // 根据id查询用户
    @Select("select * from user where id = #{id}")
    User queryUserById(Integer id);

    // 根据用户名查询用户
    @Select("select * from user where name = #{name}")
    User queryUserByName(String name);

    // 根据手机号查询用户
    @Select("select * from user where tel = #{tel}")
    User queryUserByTel(String tel);

    // 添加用户
    @Select("insert into user (id, name, password, tel, created_time, role) values (#{id}, #{name}, #{password}, #{tel}, #{created_time}, #{role})")
    void addUser(User user);

    // 根据id删除用户
    @Select("delete from user where id = #{id}")
    void deleteUser(Integer id);
}
