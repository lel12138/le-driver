package com.wule.pojo;

import java.io.Serializable;

public class WholeDingdan {

    private String ID;
    private String telephone;
    private String driver_telephone;
    private String jiedan_time;
    private String final_time;
    private String final_price;
    private String zhuangtai;

    private String city;
    private String send_time;
    private String qidian;
    private String zhongdian;

    private String fangan;
    private String distance;
    private String use_time;
    private String price;

    private String kanjia;
    private String why_kanjia;
    private String tijia;
    private String why_tijia;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDriver_telephone() {
        return driver_telephone;
    }

    public void setDriver_telephone(String driver_telephone) {
        this.driver_telephone = driver_telephone;
    }

    public String getJiedan_time() {
        return jiedan_time;
    }

    public void setJiedan_time(String jiedan_time) {
        this.jiedan_time = jiedan_time;
    }

    public String getFinal_time() {
        return final_time;
    }

    public void setFinal_time(String final_time) {
        this.final_time = final_time;
    }

    public String getFinal_price() {
        return final_price;
    }

    public void setFinal_price(String final_price) {
        this.final_price = final_price;
    }

    public String getZhuangtai() {
        return zhuangtai;
    }

    public void setZhuangtai(String zhuangtai) {
        this.zhuangtai = zhuangtai;
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

    public String getZhongdian() {
        return zhongdian;
    }

    public void setZhongdian(String zhongdian) {
        this.zhongdian = zhongdian;
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

    public String getKanjia() {
        return kanjia;
    }

    public void setKanjia(String kanjia) {
        this.kanjia = kanjia;
    }

    public String getWhy_kanjia() {
        return why_kanjia;
    }

    public void setWhy_kanjia(String why_kanjia) {
        this.why_kanjia = why_kanjia;
    }

    public String getTijia() {
        return tijia;
    }

    public void setTijia(String tijia) {
        this.tijia = tijia;
    }

    public String getWhy_tijia() {
        return why_tijia;
    }

    public void setWhy_tijia(String why_tijia) {
        this.why_tijia = why_tijia;
    }

    public WholeDingdan(String ID, String telephone, String driver_telephone, String jiedan_time, String final_time, String final_price, String zhuangtai, String city, String send_time, String qidian, String zhongdian, String fangan, String distance, String use_time, String price, String kanjia, String why_kanjia, String tijia, String why_tijia) {
        this.ID = ID;
        this.telephone = telephone;
        this.driver_telephone = driver_telephone;
        this.jiedan_time = jiedan_time;
        this.final_time = final_time;
        this.final_price = final_price;
        this.zhuangtai = zhuangtai;
        this.city = city;
        this.send_time = send_time;
        this.qidian = qidian;
        this.zhongdian = zhongdian;
        this.fangan = fangan;
        this.distance = distance;
        this.use_time = use_time;
        this.price = price;
        this.kanjia = kanjia;
        this.why_kanjia = why_kanjia;
        this.tijia = tijia;
        this.why_tijia = why_tijia;
    }

    public WholeDingdan(DingdanInfo dingdanInfo, TaskInfo taskInfo) {
        this.ID = dingdanInfo.getID();
        this.telephone = dingdanInfo.getTelephone();
        this.driver_telephone = dingdanInfo.getDriver_telephone();
        this.jiedan_time = dingdanInfo.getJiedan_time();
        this.final_time = dingdanInfo.getFinal_time();
        this.final_price = dingdanInfo.getFinal_price();
        this.zhuangtai = dingdanInfo.getZhuangtai();
        this.kanjia=dingdanInfo.getKanjia();
        this.why_kanjia=dingdanInfo.getWhy_kanjia();
        this.tijia=dingdanInfo.getTijia();
        this.why_tijia=dingdanInfo.getWhy_tijia();
        this.city = taskInfo.getCity();
        this.send_time = taskInfo.getSend_time();
        this.qidian = taskInfo.getQidian();
        this.zhongdian = taskInfo.getZhongdian();
        this.fangan = taskInfo.getFangan();
        this.distance = taskInfo.getDistance();
        this.use_time = taskInfo.getUse_time();
        this.price = taskInfo.getPrice();
    }

    public WholeDingdan(TaskInfo taskInfo) {
        this.ID = taskInfo.getID();
        this.telephone = taskInfo.getTelephone();
        this.driver_telephone = "等待接单";
        this.jiedan_time = "";
        this.final_time = "";
        this.final_price = "";
        this.zhuangtai = taskInfo.getZhuangtai();
        this.kanjia="";
        this.why_kanjia="";
        this.tijia="";
        this.why_tijia="";
        this.city = taskInfo.getCity();
        this.send_time = taskInfo.getSend_time();
        this.qidian = taskInfo.getQidian();
        this.zhongdian = taskInfo.getZhongdian();
        this.fangan = taskInfo.getFangan();
        this.distance = taskInfo.getDistance();
        this.use_time = taskInfo.getUse_time();
        this.price = taskInfo.getPrice();
    }
}
