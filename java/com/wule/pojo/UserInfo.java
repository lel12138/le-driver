package com.wule.pojo;

import java.io.Serializable;

public class UserInfo implements Serializable {
    private static final long serialVersionUID=1L;

    private String telephone;
    private String password;
    private String is_driver;
    private String name;
    private String ID_card_number;
    private String money;
    private String jinji;
    private String location;
    private String car_number;
    private String car_type;
    private String score;

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIs_driver() {
        return is_driver;
    }

    public void setIs_driver(String is_driver) {
        this.is_driver = is_driver;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID_card_number() {
        return ID_card_number;
    }

    public void setID_card_number(String ID_card_number) {
        this.ID_card_number = ID_card_number;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getJinji() {
        return jinji;
    }

    public void setJinji(String jinji) {
        this.jinji = jinji;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCar_number() {
        return car_number;
    }

    public void setCar_number(String car_number) {
        this.car_number = car_number;
    }

    public String getCar_type() {
        return car_type;
    }

    public void setCar_type(String car_type) {
        this.car_type = car_type;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public UserInfo() {
        super();
    }

    public UserInfo(String telephone, String password, String is_driver, String name, String ID_card_number, String money, String jinji, String location, String car_number, String car_type, String score) {
        this.telephone = telephone;
        this.password = password;
        this.is_driver = is_driver;
        this.name = name;
        this.ID_card_number = ID_card_number;
        this.money = money;
        this.jinji = jinji;
        this.location = location;
        this.car_number = car_number;
        this.car_type = car_type;
        this.score = score;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "telephone='" + telephone + '\'' +
                ", password='" + password + '\'' +
                ", is_driver='" + is_driver + '\'' +
                ", name='" + name + '\'' +
                ", ID_card_number='" + ID_card_number + '\'' +
                ", money='" + money + '\'' +
                ", jinji='" + jinji + '\'' +
                ", location='" + location + '\'' +
                ", car_number='" + car_number + '\'' +
                ", car_type='" + car_type + '\'' +
                ", score='" + score + '\'' +
                '}';
    }
}
