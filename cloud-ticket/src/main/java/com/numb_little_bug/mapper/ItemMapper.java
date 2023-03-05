package com.numb_little_bug.mapper;

import com.numb_little_bug.entity.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ItemMapper {
    // 添加item
    @Select("insert into item (id ,line ,col, name, type, status, deviceId) values (#{id}, #{line}, #{col}, #{name}, #{type}, #{status}, #{deviceId})")
    void addItem(Item item);

    // 从item表查找strap
    @Select("select * from item where type = #{type} and deviceId = #{deviceId}")
    Item[] queryComponentByTypeAndDeviceId(String type, Integer deviceId);

    // 根据deviceId查找items
    @Select("select * from item where deviceId = #{deviceId}")
    Item[] queryItemByDeviceId(Integer deviceId);

    // 根据id查找item
    @Select("select * from item where id = #{id}")
    Item queryItemById(Integer id);
}
