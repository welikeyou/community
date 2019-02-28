package com.java.model;

import com.google.gson.Gson;


public class Create_Apply {
	private String studentNum;
	private String associationName;
	private String studentName;
	private String sex;
	private String birthday;
	private String school;
	private String aim;
	private String plan;
	private String contact;
	private String time;

	private String member1;
	private String briefintro1;
	private String member2;
    private String briefintro2;
	private String member3;
	private String briefintro3;
	private String member4;
	private String briefintro4;
	private String member5;
	private String briefintro5;
	private String state;
	private String result;

	private String unique;


	public String getUnique(){
		return associationName+studentNum;
	}
	public String getResult() {
		result="{'studentNum':'" + studentNum + "','associationName':'" + associationName + "','studentName':'" + studentName + "','sex':'" + sex + "','birthday':'" +birthday + "','school':'" + school + "','aim':'" + aim+
				"','plan':'" +plan + "','contact':'" + contact + "','time':'" + time+ "','state':'" + state+"','member1':'" +
				member1 +"','member3':'" + member3+"','member4':'" + member4+"','member5':'" + member5  + "','briefintro1':'" + briefintro1+  "','briefintro5':'" + briefintro5+"','briefintro4':'" + briefintro4+ "','briefintro3':'" + briefintro3+  "','briefintro2':'" + briefintro2+"','member2':'" + member2 + "'}";
		return result;
	}


	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}



	public String getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}

	public String getAssociationName() {
		return associationName;
	}

	public void setAssociationName(String associationName) {
		this.associationName = associationName;
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

	public String getAim() {
		return aim;
	}

	public void setAim(String reason) {
		this.aim =aim;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
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

	public String getMember1() {
		return member1;
	}

	public void setMember1(String member1) {
		this.member1 = member1;
	}

	public String getBriefintro1() {
		return briefintro1;
	}

	public void setBriefintro1(String briefintro1) {
		this.briefintro1 = briefintro1;
	}

	public String getMember2() {
		return member2;
	}

	public void setMember2(String member2) {
		this.member2 = member2;
	}

	public String getBriefintro2() {
		return briefintro2;
	}

	public void setBriefintro2(String briefintro2) {
		this.briefintro2 = briefintro2;
	}

	public String getMember3() {
		return member3;
	}

	public void setMember3(String member3) {
		this.member3 = member3;
	}

	public String getBriefintro3() {
		return briefintro3;
	}

	public void setBriefintro3(String briefintro3) {
		this.briefintro3 = briefintro3;
	}

	public String getMember4() {
		return member4;
	}

	public void setMember4(String member4) {
		this.member4 = member4;
	}

	public String getBriefintro4() {
		return briefintro4;
	}

	public void setBriefintro4(String briefintro4) {
		this.briefintro4 = briefintro4;
	}

	public String getMember5() {
		return member5;
	}

	public void setMember5(String member5) {
		this.member5 = member5;
	}

	public String getBriefintro5() {
		return briefintro5;
	}

	public void setBriefintro5(String briefintro5) {
		this.briefintro5 = briefintro5;
	}
}




