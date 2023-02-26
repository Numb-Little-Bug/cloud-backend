package com.numb_little_bug.mapper;

import com.numb_little_bug.entity.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ItemMapper {

    // 添加item
    @Select("insert into item (id ,line ,col, name, type, status, deviceId) values (#{id}, #{line}, #{col}, #{name}, #{type}, #{status}, #{deviceId})")
    void addItem(Item item);
}
