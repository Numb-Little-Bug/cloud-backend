package com.numb_little_bug.entity;

import java.util.Date;
import java.util.Objects;

public class User {
    private Integer id;
    private String name;
    private String password;
    private String tel;

    private IDCard id_card;

    private Date create_time;

    private String role;

    public User() {

    }

    public User(Integer id, String name, String password, String tel, IDCard id_card, Date create_time, String role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.tel = tel;
        this.id_card = id_card;
        this.create_time = create_time;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public IDCard getId_card() {
        return id_card;
    }

    public void setId_card(IDCard id_card) {
        this.id_card = id_card;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isDispatch(){
        return "dispatch".equals(role);
    }

    public boolean isSite() {
        return "site".equals(role);
    }

    public boolean isBoth(){
        return "both".equals(role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", tel='" + tel + '\'' +
                ", id_card=" + id_card +
                ", create_time=" + create_time +
                ", role='" + role + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getName(), user.getName()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getTel(), user.getTel()) && Objects.equals(getId_card(), user.getId_card()) && Objects.equals(getCreate_time(), user.getCreate_time()) && Objects.equals(getRole(), user.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPassword(), getTel(), getId_card(), getCreate_time(), getRole());
    }
}
