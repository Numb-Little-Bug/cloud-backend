package com.numb_little_bug.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.context.annotation.Primary;

import javax.annotation.RegEx;
import javax.annotation.sql.DataSourceDefinition;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @Length(min = 1, message = "姓名不能为空")
    private String name;

    @Length(min = 6, max = 16, message = "密码长度必须在6-16位之间")
    private String password;

    @Pattern(regexp = "^1[3|4|5|7|8][0-9]\\d{8}$", message = "手机号格式不正确")
    private String tel;

    private IDCard id_card;

    private Date created_time;

    @Pattern(regexp = "^(site|dispatch)$", message = "角色只能是site或者dispatch")
    private String role;
}
