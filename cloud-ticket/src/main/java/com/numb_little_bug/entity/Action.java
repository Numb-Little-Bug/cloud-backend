package com.numb_little_bug.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.IdType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Action {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String action;

    private String statue;

    private Integer ItemId;
}
