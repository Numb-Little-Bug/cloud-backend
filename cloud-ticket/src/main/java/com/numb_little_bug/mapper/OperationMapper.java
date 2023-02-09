package com.numb_little_bug.mapper;

import com.numb_little_bug.entity.Operation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OperationMapper {
    @Select("select * from operation where id = #{id}")
    Operation queryOperationById(Integer id);

    @Select("select * from operation where type = #{type}")
    Operation queryOperationByType(String type);

    @Select("select * from operation where description = #{description}")
    Operation queryOperationByDescription(String description);

    @Select("select * from operation where stepNumber = #{stepNumber}")
    Operation queryOperationByStepNumber(Integer stepNumber);

    @Select("select * from operation where ticketId = #{ticketId}")
    Operation[] queryOperationByTicketId(Integer ticketId);

    @Select("insert into operation (id, type, description, notice, stepNumber, ticketId) values (#{id}, #{type}, #{description}, #{notice}, #{stepNumber}, #{ticketId})")
    void addOperation(Operation operation);

    @Select("delete from operation where id = #{id}")
    void deleteOperation(Integer id);

    @Select("update operation set type = #{type}, description = #{description}, notice = #{notice}, stepNumber = #{stepNumber}, ticketId = #{ticketId} where id = #{id}")
    void updateOperation(Operation operation);

    @Select("select * from operation")
    Operation[] queryAllOperation();
}
