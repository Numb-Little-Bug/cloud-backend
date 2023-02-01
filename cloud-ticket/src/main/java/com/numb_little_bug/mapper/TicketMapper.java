package com.numb_little_bug.mapper;

import com.numb_little_bug.entity.Ticket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TicketMapper {
    @Select("select * from ticket where id = #{id}")
    Ticket queryTicketById(Integer id);

    @Select("select * from ticket where name = #{name}")
    Ticket queryTicketByName(String name);

    @Select("select * from ticket where teller = #{teller}")
    Ticket[] queryTicketByTeller(String teller);

    @Select("insert into ticket (id, name, teller, notice) values (#{id}, #{name}, #{teller}, #{notice})")
    void addTicket(Ticket ticket);

    @Select("delete from ticket where id = #{id}")
    void deleteTicket(Integer id);

    @Select("update ticket set name = #{name}, teller = #{teller}, notice = #{notice} where id = #{id}")
    void updateTicket(Ticket ticket);

    @Select("select * from ticket")
    Ticket[] queryAllTicket();
}
