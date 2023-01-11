package com.numb_little_bug.controller;


import com.numb_little_bug.entity.SmartData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmartDocTestController {

    /**
     * 这是一个smart-doc测试接口
     * @return SmartData
     */
    @GetMapping("/smart")
    public SmartData test() {
        return new SmartData("Tom", 24);
    }
}
