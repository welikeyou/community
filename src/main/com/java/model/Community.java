package com.java.model;
/*
功能:社团类
创建者:黄友明
修改者:黄友明
 */
public class Community {
    private String comID;
    private String comName;
    private String chiefID;
    private String type;
    private String level;
    private String amount;
    private String money;
    private String picSrc;
    private String slogan_in;
    private String slogan_out;

    public String getComID() {
        return comID;
    }

    public void setComID(String comID) {
        this.comID = comID;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getChiefID() {
        return chiefID;
    }

    public void setChiefID(String chiefID) {
        this.chiefID = chiefID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getPicSrc() {
        return picSrc;
    }

    public void setPicSrc(String picSrc) {
        this.picSrc = picSrc;
    }

    public String getSlogan_in() {
        return slogan_in;
    }

    public void setSlogan_in(String slogan_in) {
        this.slogan_in = slogan_in;
    }

    public String getSlogan_out() {
        return slogan_out;
    }

    public void setSlogan_out(String slogan_out) {
        this.slogan_out = slogan_out;
    }
    /*
create table association(
associationNum	VARCHAR2(16) not null primary key,
associationName	VARCHAR2(64) not null,
studentNum	VARCHAR2(16) not null references student (studentNum),
typee	VARCHAR2(64) not null,
levell	number(4) default 0,
amount	NUMBER(5) default 1,
money	VARCHAR2(16) default 0,
picture  VARCHAR2(64),
slogan_in	 VARCHAR2(128) ,
slogan_out	VARCHAR2(128)
)

 */

}
