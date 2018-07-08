package com.java.model;
/*
功能:申请表类
创建者:刘诗滢
修改者:刘诗滢
 */



public class Create_Apply {
	private String studentNum;
	private String associationName;
	private String type;
	private String studentName;
	private String slogan_in;
	private String slogan_out;
	private String reason;
	private String plan;
	private String advantage;
	private String contact;
	private String time;
	private String state;
	private String result;


	public void setAdvantage(String advantage) {
		this.advantage = advantage;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public void setResult(String result) {
		String s = "{'reason':'" + reason + "','plan':'" + plan + "','advantage':'" + advantage + "','contact':'" + contact + "','studentNum':'" + studentNum + "','associationName':'" + associationName + "','type':'" + type +
				"','studentName':'" + studentName + "','slogan_in':'" + slogan_in + "','slogan_out':'" + slogan_out + "','time':'" +
				time + "','state':'" + state + "'}" ;

		this.result = s;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setSlogan_in(String slogan_in) {
		this.slogan_in = slogan_in;
	}

	public void setSlogan_out(String slogan_out) {
		this.slogan_out = slogan_out;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}

	public void setAssociationName(String associationName) {
		this.associationName = associationName;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAssociationName() {
		return associationName;
	}

	public String getStudentNum() {
		return studentNum;
	}

	public String getStudentName() {
		return studentName;
	}

	public String getTime() {
		return time;
	}

	public String getSlogan_out() {
		return slogan_out;
	}

	public String getSlogan_in() {
		return slogan_in;
	}

	public String getUnique() {
		return studentNum + associationName;
	}

	public String getType() {
		return type;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public String getPlan() {
		return plan;
	}

	public String getResult() {

		return result;
	}

	public String getReason() {
		return reason;
	}

	public String getContact() {
		return contact;
	}

	public String getAdvantage() {
		return advantage;

	}
}




