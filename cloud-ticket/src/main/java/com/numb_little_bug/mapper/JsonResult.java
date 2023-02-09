package com.numb_little_bug.mapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JsonResult {
    /*
     * 状态码
     * 0: success
     */
    private Integer code;

    /*
     * 返回的数据，可以是任意类型
     */
    private Object result;

    /*
     * 响应消息
     */
    private String message;

    /*
     * 响应类型：success, failed
     */
    private String type;
}
