package com.numb_little_bug.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JsonResult {
    private Integer code;

    private Object result;
    private String message;

    private String type;
}
