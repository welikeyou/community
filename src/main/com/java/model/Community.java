package com.java.model;
/*
功能:社团类
创建者:黄友明
修改者:黄友明,李兰
 */
public class Community {
    private String comID;
    private String comName;
    private String chiefID;
    private String type;
    private String level;
    private String amount;
    private String money;
   // private String picSrc;
   // private String slogan_in;
  //  private String slogan_out;
   //新添字段
    private String aim;
    private String members;
    private String biefInfo;

    private String headPicture;
    private String backgroundPicture ;
    private String title1;
    private String content1;
    private String title2;
    private String content2 ;

    public void check()
    {
        if(null == title1)
            title1 = "标题";
        if(null == backgroundPicture)
            backgroundPicture = "/pic/defaultbg.png";
        if(null == title2)
            title2 = "标题";
        if(null == headPicture)
            headPicture = "标题";
    }

    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public String getContent1() {
        return content1;
    }

    public void setContent1(String content1) {
        this.content1 = content1;
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public String getBackgroundPicture() {
        return backgroundPicture;
    }

    public void setBackgroundPicture(String backgroundPicture) {
        this.backgroundPicture = backgroundPicture;
    }

    public String getHeadPicture() {
        return headPicture;
    }

    public void setHeadPicture(String headPicture) {
        this.headPicture = headPicture;
    }


    public String getBiefInfo() {
        return biefInfo;
    }

    public void setBiefInfo(String biefInfo) {
        this.biefInfo = biefInfo;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public String getAim() {
        return aim;
    }

    public void setAim(String aim) {
        this.aim = aim;
    }

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

    /*public String getPicSrc() {
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
    }*/
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
