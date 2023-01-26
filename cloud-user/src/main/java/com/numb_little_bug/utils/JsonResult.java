package com.numb_little_bug.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JsonResult {
    private Object obj;
    private String msg;
    private Integer code;
}
