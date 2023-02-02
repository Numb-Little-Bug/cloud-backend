package com.numb_little_bug.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Operation {
    /**
     * 操作id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 操作类型
     */
    private String type;

    /**
     * 操作描述
     */
    private String description;

    /**
     * 操作注意事项
     */
    private String notice;

    /**
     * 本次操作步骤的次序
     */
    private Integer stepNumber;

    /**
     * 本次操作所属操作票的id
     */
    private Integer ticketId;
}
