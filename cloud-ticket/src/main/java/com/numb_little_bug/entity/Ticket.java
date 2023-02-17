package com.numb_little_bug.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Data
@NoArgsConstructor
@Getter
@Setter
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
    private Integer tellerId;

    /**
     * 操作票执行人id
     */
    private Integer operatorId;

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
    private Integer publisherId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 操作票状态: 0-已发布, 未执行, 1-正在执行, 2-已完成, 3-已取消
     */
    private Integer status;

    /**
     * 操作票对应的设备类型，用id表示
     */
    private Integer deviceTypeId;
}

