package com.java.model;

public class Join_Apply {
    private String studentNum;
    private String studentName;
    private String sex;
    private String school;
    private String birthday;
    private String department;
    private String reason;
    private String hobby;
    public String associationNum;
    private String contact;
    private String time;
    private  String state;
    private String result;
    private String associations;//以字符串的形式:s1+s2
    private String unique;

    public String getUnique(){
        return studentNum+associationNum;
    }

    public String getResult(){
        result="{'studentNum':'"+studentNum+"','studentName':'"+studentName+"','sex':'"+sex+
                "','birthday':'"+birthday+"','school':'"+school+"','department':'"+department+"','reason':'"+reason+"','hobby':'"+hobby+
                "','associations':'"+associations+"','associationNum':'"+associationNum+"','contact':'"+contact+"','time':'"+time+"'}";
        return result;}

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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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

    public String getAssociationNum() {
        return associationNum;
    }

    public void setAssociationNum(String associationNum) {
        this.associationNum = associationNum;
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

    public String getAssociations() {
        return associations;
    }

    public void setAssociations(String associations) {
        this.associations = associations;
    }
}
