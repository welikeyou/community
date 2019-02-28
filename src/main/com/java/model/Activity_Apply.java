package com.java.model;

public class Activity_Apply {
    private String associationName;
    private String associationNum;
    private String title;
    private String studentNum;
    private String studentName;
    private String sex;
    private String birthday;
    private String school;
    private String reason;
    private String hobby;
    private String contact;
    private String time;
    private  String state;
    private String result;

    private String unique;
    private String associations;//以字符串的

    public String getAssociationName() {
        return associationName;
    }

    public void setAssociationName(String associationName) {
        this.associationName = associationName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUnique() {
        return title+studentName;
    }

    public String getResult() {
        result="{'studentNum':'"+studentNum+"','studentName':'"+studentName+"','associationName':'"+associationName+"','sex':'"+sex+
                "','birthday':'"+birthday+"','school':'"+school+"','reason':'"+reason+"','state':'"+state+"','hobby':'"+hobby+
                "','associations':'"+associations+"','contact':'"+contact+"','title':'"+title+"','time':'"+time+"'}";

        return result;
    }

    public String getAssociationNum() {
        return associationNum;
    }

    public void setAssociationNum(String associationNum) {
        this.associationNum = associationNum;
    }

    public String getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getAssociations() {
        return associations;
    }

    public void setAssociations(String associations) {
        this.associations = associations;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }




}
