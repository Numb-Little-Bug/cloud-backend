package com.numb_little_bug.mapper;

import com.numb_little_bug.entity.Ticket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TicketMapper {
    @Select("select * from ticket where id = #{id}")
    Ticket queryTicketById(Integer id);

    @Select("select * from ticket where siteId = #{siteId}")
    Ticket[] queryTicketBySiteId(Integer siteId);

    @Select("select * from ticket where name = #{name}")
    Ticket queryTicketByName(String name);

    @Select("select * from ticket where teller = #{teller}")
    Ticket[] queryTicketByTeller(String teller);

    @Select("select * from ticket where operator = #{operator}")
    Ticket[] queryTicketByOperator(String operator);

    @Select("select * from ticket where publisher = #{publisher}")
    Ticket[] queryTicketByPublisher(String publisher);

    @Select("select * from ticket where status = #{status}")
    Ticket[] queryTicketByStatus(String status);

    @Select("insert into ticket(name, teller, operator, notice, siteId, startTime, endTime, publisher, remark, status) values(#{name}, #{teller}, #{operator}, #{notice}, #{siteId}, #{startTime}, #{endTime}, #{publisher}, #{remark}, #{status})")
    void addTicket(Ticket ticket);

    @Select("delete from ticket where id = #{id}")
    void deleteTicket(Integer id);

    @Select("update ticket set name = #{name}, teller = #{teller}, operator = #{operator}, notice = #{notice}, siteId = #{siteId}, startTime = #{startTime}, endTime = #{endTime}, publisher = #{publisher}, remark = #{remark}, status = #{status} where id = #{id}")
    void updateTicket(Ticket ticket);

    @Select("select * from ticket")
    Ticket[] queryAllTicket();

    @Select("update ticket set status = #{status} where id = #{id}")
    void updateTicketStatus(String status, Integer id);

    @Select("update ticket set name = #{name} where id = #{id}")
    void updateTicketName(String name, Integer id);

    @Select("update ticket set teller = #{teller} where id = #{id}")
    void updateTicketTeller(String teller, Integer id);

    @Select("update ticket set operator = #{operator} where id = #{id}")
    void updateTicketOperator(String operator, Integer id);

    @Select("update ticket set notice = #{notice} where id = #{id}")
    void updateTicketNotice(String notice, Integer id);

    @Select("update ticket set siteId = #{siteId} where id = #{id}")
    void updateTicketSiteId(Integer siteId, Integer id);

    @Select("update ticket set startTime = #{startTime}, endTime = #{endTime} where id = #{id}")
    void updateTicketTime(String startTime, String endTime, Integer id);

    @Select("update ticket set remark = #{remark} where id = #{id}")
    void updateTicketRemark(String remark, Integer id);

}
