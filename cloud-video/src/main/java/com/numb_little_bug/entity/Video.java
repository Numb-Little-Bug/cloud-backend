package com.numb_little_bug.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
public class Video {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String url;

    private Integer site_id;
}
