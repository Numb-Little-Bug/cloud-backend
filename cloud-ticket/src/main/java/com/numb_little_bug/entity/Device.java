package com.numb_little_bug.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Data
@NoArgsConstructor
@Getter
@Setter
public class Device {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer siteId;
}
