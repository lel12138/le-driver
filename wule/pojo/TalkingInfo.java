package com.wule.pojo;

import java.io.Serializable;

public class TalkingInfo implements Serializable {

    private String talkingID;
    private String ID;
    private String typee;
    private String valuee;
    private String timee;
    private String see;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTypee() {
        return typee;
    }

    public void setTypee(String typee) {
        this.typee = typee;
    }

    public String getValuee() {
        return valuee;
    }

    public void setValuee(String valuee) {
        this.valuee = valuee;
    }

    public String getTimee() {
        return timee;
    }

    public void setTimee(String timee) {
        this.timee = timee;
    }

    public String getTalkingID() {
        return talkingID;
    }

    public void setTalkingID(String talkingID) {
        this.talkingID = talkingID;
    }

    public String getSee() {
        return see;
    }

    public void setSee(String see) {
        this.see = see;
    }

    public TalkingInfo(String talkingID, String ID, String typee, String valuee, String timee, String see) {
        this.talkingID = talkingID;
        this.ID = ID;
        this.typee = typee;
        this.valuee = valuee;
        this.timee = timee;
        this.see = see;
    }

    @Override
    public String toString() {
        return "TalkingInfo{" +
                "talkingID='" + talkingID + '\'' +
                ", ID='" + ID + '\'' +
                ", typee='" + typee + '\'' +
                ", valuee='" + valuee + '\'' +
                ", timee='" + timee + '\'' +
                ", see='" + see + '\'' +
                '}';
    }
}
