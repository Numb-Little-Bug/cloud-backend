package com.numb_little_bug.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestHeader;

@Data
@NoArgsConstructor
public class Site {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;

    private String video1;

    private String video2;
}
