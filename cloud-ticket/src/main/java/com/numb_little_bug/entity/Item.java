package com.numb_little_bug.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    /**
     * 项目id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer line;

    private Integer col;

    private String status;

    private String type;

    private Integer deviceId;
}
