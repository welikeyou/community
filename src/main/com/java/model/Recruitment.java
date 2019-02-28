package com.java.model;

public class Recruitment {
    private String tittle;
    private String introduction;
    private String propaganda;
    private String demand;
    private String examine;
    private String time;
    private String postcard;
    private String publisher;
    private String contact;
    private String associationNum;
    public void check()
    {
        if(null == postcard)
            postcard = "/pic/lemon.jpg";
    }
    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getPropaganda() {
        return propaganda;
    }

    public void setPropaganda(String propaganda) {
        this.propaganda = propaganda;
    }

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    public String getExamine() {
        return examine;
    }

    public void setExamine(String examine) {
        this.examine = examine;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPostcard() {
        return postcard;
    }

    public void setPostcard(String postcard) {
        this.postcard = postcard;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAssociationNum() {
        return associationNum;
    }

    public void setAssociationNum(String associationNum) {
        this.associationNum = associationNum;
    }
}
