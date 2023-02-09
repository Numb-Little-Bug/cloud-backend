package com.numb_little_bug.mapper;

import com.numb_little_bug.entity.IDCard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IDCardMapper {
    //查询所有IDCard
    @Select("select * from id_card")
    List<IDCard> queryAllIDCard();

    //根据number查询IDCard
    @Select("select * from id_card where number = #{number}")
    IDCard queryIDCardByNumber(String number);

    //根据name查询IDCard
    @Select("select * from id_card where name = #{name}")
    IDCard queryIDCardByName(String name);
}
