package com.numb_little_bug.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Operation {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String type;

    private String description;

    private String notice;

    private String next;

    private Integer ticketId;
}
