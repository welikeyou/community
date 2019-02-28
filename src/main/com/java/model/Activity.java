package com.java.model;

public class Activity {
    private String title;
    private String associationName;
    private String time;
    private String address;
    private String introduction;
    private String demand;
    private String registrationTime;
    private String publisher;
    private String contact;
    private String postcard;

    public void setPostcard(String postcard) {
        this.postcard = postcard;
    }

    public String getPostcard() {
        return postcard;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setAssociationName(String associationName) {
        this.associationName = associationName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public String getRegistrationTime() {
        return registrationTime;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getIntroduction() {
        return introduction;
    }

    public String getDemand() {
        return demand;
    }

    public String getContact() {
        return contact;
    }

    public String getAddress() {
        return address;
    }

    public String getAssociationName() {
        return associationName;
    }

    public void setRegistrationTime(String registrationTime) {
        this.registrationTime = registrationTime;
    }



    public void setTitle(String title) {
        this.title = title;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
