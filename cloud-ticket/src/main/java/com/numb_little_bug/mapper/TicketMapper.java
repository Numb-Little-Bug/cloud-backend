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

    @Select("select * from ticket where tellerId = #{tellerId}")
    Ticket[] queryTicketByTeller(Integer tellerId);

    @Select("select * from ticket where operatorId = #{operatorId}")
    Ticket[] queryTicketByOperator(Integer operatorId);

    @Select("select * from ticket where publisherId = #{publisherId}")
    Ticket[] queryTicketByPublisher(Integer publisherId);

    @Select("select * from ticket where status = #{status}")
    Ticket[] queryTicketByStatus(String status);

    @Select("insert into ticket(name, tellerId, operatorId, notice, siteId, startTime, endTime, publisherId, remark, status) values(#{name}, #{tellerId}, #{operatorId}, #{notice}, #{siteId}, #{startTime}, #{endTime}, #{publisherId}, #{remark}, #{status})")
    void addTicket(Ticket ticket);

    @Select("delete from ticket where id = #{id}")
    void deleteTicket(Integer id);

    @Select("update ticket set name = #{name}, tellerId = #{tellerId}, operatorId = #{operatorId}, notice = #{notice}, siteId = #{siteId}, startTime = #{startTime}, endTime = #{endTime}, publisherId = #{publisherId}, remark = #{remark}, status = #{status} where id = #{id}")
    void updateTicket(Ticket ticket);

    @Select("select * from ticket")
    Ticket[] queryAllTicket();

    @Select("update ticket set status = #{status} where id = #{id}")
    void updateTicketStatus(String status, Integer id);

    @Select("update ticket set name = #{name} where id = #{id}")
    void updateTicketName(String name, Integer id);

    @Select("update ticket set tellerId = #{teller} where id = #{id}")
    void updateTicketTeller(Integer teller, Integer id);

    @Select("update ticket set operatorId = #{operator} where id = #{id}")
    void updateTicketOperator(Integer operator, Integer id);

    @Select("update ticket set notice = #{notice} where id = #{id}")
    void updateTicketNotice(String notice, Integer id);

    @Select("update ticket set siteId = #{siteId} where id = #{id}")
    void updateTicketSiteId(Integer siteId, Integer id);

    @Select("update ticket set startTime = #{startTime}, endTime = #{endTime} where id = #{id}")
    void updateTicketTime(String startTime, String endTime, Integer id);

    @Select("update ticket set remark = #{remark} where id = #{id}")
    void updateTicketRemark(String remark, Integer id);

}
