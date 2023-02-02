package com.numb_little_bug.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Ticket {
    /**
     * 操作票id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 操作票名称
     */
    private String name;

    /**
     * 操作票唱票人
     */
    private String teller;

    /**
     * 操作票执行人
     */
    private String operator;

    /**
     * 操作票注意事项
     */
    private String notice;

    /**
     * 操作票对应的现场侧id
     */
    private Integer siteId;

    /**
     * 操作开始时间
     */
    private Date startTime;

    /**
     * 操作截止时间
     */
    private Date endTime;

    /**
     * 操作票发布人
     */
    private String publisher;

    /**
     * 备注
     */
    private String remark;

    /**
     * 操作票状态: 0-已发布, 未执行, 1-正在执行, 2-已完成, 3-已取消
     */
    private Integer status;
}

