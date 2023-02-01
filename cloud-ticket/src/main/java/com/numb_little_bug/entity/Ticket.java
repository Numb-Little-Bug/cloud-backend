package com.numb_little_bug.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Ticket {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String teller;

    private String operator;

    private String notice;
}
