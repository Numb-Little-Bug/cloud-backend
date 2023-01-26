package com.numb_little_bug.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
public class IDCard {
    private String number;

    private String name;

    private String address;

    private Date birthday;

    private Boolean gender;

    private String photo;
}
