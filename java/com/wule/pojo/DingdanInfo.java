package com.wule.pojo;

import java.io.Serializable;

public class DingdanInfo implements Serializable {
    private String ID;
    private String telephone;
    private String driver_telephone;
    private String jiedan_time;
    private String final_time;
    private String final_price;
    private String zhuangtai;
    private String kanjia;
    private String why_kanjia;
    private String tijia;
    private String why_tijia;

    public DingdanInfo() {

    }

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

    public DingdanInfo(String ID, String telephone, String driver_telephone, String jiedan_time, String final_time, String final_price, String zhuangtai, String kanjia, String why_kanjia, String tijia, String why_tijia) {
        this.ID = ID;
        this.telephone = telephone;
        this.driver_telephone = driver_telephone;
        this.jiedan_time = jiedan_time;
        this.final_time = final_time;
        this.final_price = final_price;
        this.zhuangtai = zhuangtai;
        this.kanjia = kanjia;
        this.why_kanjia = why_kanjia;
        this.tijia = tijia;
        this.why_tijia = why_tijia;
    }

    @Override
    public String toString() {
        return "DingdanInfo{" +
                "ID='" + ID + '\'' +
                ", telephone='" + telephone + '\'' +
                ", driver_telephone='" + driver_telephone + '\'' +
                ", jiedan_time='" + jiedan_time + '\'' +
                ", final_time='" + final_time + '\'' +
                ", final_price='" + final_price + '\'' +
                ", zhuangtai='" + zhuangtai + '\'' +
                ", kanjia='" + kanjia + '\'' +
                ", why_kanjia='" + why_kanjia + '\'' +
                ", tijia='" + tijia + '\'' +
                ", why_tijia='" + why_tijia + '\'' +
                '}';
    }
}
