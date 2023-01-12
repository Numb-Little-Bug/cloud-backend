package com.numb_little_bug.entity;

import java.util.Date;
import java.util.Objects;

public class IDCard {
    private String number;

    private String name;

    private String address;

    private Date birthday;

    private Boolean gender;

    private String photo;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "IDCard{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", birthday=" + birthday +
                ", gender=" + gender +
                ", photo='" + photo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IDCard)) return false;
        IDCard idCard = (IDCard) o;
        return Objects.equals(getNumber(), idCard.getNumber()) && Objects.equals(getName(), idCard.getName()) && Objects.equals(getAddress(), idCard.getAddress()) && Objects.equals(getBirthday(), idCard.getBirthday()) && Objects.equals(getGender(), idCard.getGender()) && Objects.equals(getPhoto(), idCard.getPhoto());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getName(), getAddress(), getBirthday(), getGender(), getPhoto());
    }
}
