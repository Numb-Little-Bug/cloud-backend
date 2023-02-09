package com.numb_little_bug.controller;

import com.numb_little_bug.entity.IDCard;
import com.numb_little_bug.entity.User;
import com.numb_little_bug.mapper.UserMapper;
import com.numb_little_bug.mapper.IDCardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IDCardController {
    private final IDCardMapper idCardMapper;

    @Autowired
    public IDCardController(IDCardMapper idCardMapper) {
        this.idCardMapper = idCardMapper;
    }

    @GetMapping(value="/id_card/{number}")
    public IDCard getIDCardByNumber(@PathVariable("number") String number) {
        return idCardMapper.queryIDCardByNumber(number);
    }

    @GetMapping(value="/id_card/{name}")
    public IDCard getIDCardByName(@PathVariable("name") String name) {
        return idCardMapper.queryIDCardByName(name);
    }
}
