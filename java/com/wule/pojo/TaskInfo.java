package com.wule.pojo;

import java.io.Serializable;

public class TaskInfo  implements Serializable {
    private static final long serialVersionUID=1L;

    private String ID;
    private String telephone;
    private String city;
    private String send_time;
    private String qidian;
    private String qidian_lng;
    private String qidian_lat;
    private String zhongdian;
    private String zhongdian_lng;
    private String zhongdian_lat;
    private String fangan;
    private String distance;
    private String use_time;
    private String price;
    private String zhuangtai;

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public String getSend_time() {
        return send_time;
    }

    public void setSend_time(String send_time) {
        this.send_time = send_time;
    }


    public String getQidian() {
        return qidian;
    }

    public void setQidian(String qidian) {
        this.qidian = qidian;
    }

    public String getQidian_lng() {
        return qidian_lng;
    }

    public void setQidian_lng(String qidian_lng) {
        this.qidian_lng = qidian_lng;
    }

    public String getQidian_lat() {
        return qidian_lat;
    }

    public void setQidian_lat(String qidian_lat) {
        this.qidian_lat = qidian_lat;
    }

    public String getZhongdian() {
        return zhongdian;
    }

    public void setZhongdian(String zhongdian) {
        this.zhongdian = zhongdian;
    }

    public String getZhongdian_lng() {
        return zhongdian_lng;
    }

    public void setZhongdian_lng(String zhongdian_lng) {
        this.zhongdian_lng = zhongdian_lng;
    }

    public String getZhongdian_lat() {
        return zhongdian_lat;
    }

    public void setZhongdian_lat(String zhongdian_lat) {
        this.zhongdian_lat = zhongdian_lat;
    }

    public String getFangan() {
        return fangan;
    }

    public void setFangan(String fangan) {
        this.fangan = fangan;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getUse_time() {
        return use_time;
    }

    public void setUse_time(String use_time) {
        this.use_time = use_time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public TaskInfo() {
        super();
    }


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getZhuangtai() {
        return zhuangtai;
    }

    public void setZhuangtai(String zhuangtai) {
        this.zhuangtai = zhuangtai;
    }

    public TaskInfo(String ID, String telephone, String city, String send_time, String qidian, String qidian_lng, String qidian_lat, String zhongdian, String zhongdian_lng, String zhongdian_lat, String fangan, String distance, String use_time, String price, String zhuangtai) {
        this.ID = ID;
        this.telephone = telephone;
        this.city = city;
        this.send_time = send_time;
        this.qidian = qidian;
        this.qidian_lng = qidian_lng;
        this.qidian_lat = qidian_lat;
        this.zhongdian = zhongdian;
        this.zhongdian_lng = zhongdian_lng;
        this.zhongdian_lat = zhongdian_lat;
        this.fangan = fangan;
        this.distance = distance;
        this.use_time = use_time;
        this.price = price;
        this.zhuangtai = zhuangtai;
    }

    @Override
    public String toString() {
        return "TaskInfo{" +
                "ID='" + ID + '\'' +
                ", telephone='" + telephone + '\'' +
                ", city='" + city + '\'' +
                ", send_time='" + send_time + '\'' +
                ", qidian='" + qidian + '\'' +
                ", qidian_lng='" + qidian_lng + '\'' +
                ", qidian_lat='" + qidian_lat + '\'' +
                ", zhongdian='" + zhongdian + '\'' +
                ", zhongdian_lng='" + zhongdian_lng + '\'' +
                ", zhongdian_lat='" + zhongdian_lat + '\'' +
                ", fangan='" + fangan + '\'' +
                ", distance='" + distance + '\'' +
                ", use_time='" + use_time + '\'' +
                ", price='" + price + '\'' +
                ", zhuangtai='" + zhuangtai + '\'' +
                '}';
    }
}
