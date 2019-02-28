package com.java.model;

public class Task {
    private String taskNum;
    private String clubNum;
    private String clubName;
    private String senderNum;
    private String senderName;
    private String title;
    private String content;
    private String ddl;
    private String sendTime;
    private String state;
    private String filePath;
    private String result;

    public void setResult(){
        String s="{'taskNum':'"+taskNum+"','content':'"+content+"','filePath':'"+filePath+"'}";
        this.result=s;
    }
    public String getResult(){
        return result;
    }
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(String taskNum) {
        this.taskNum = taskNum;
    }

    public String getClubNum() {
        return clubNum;
    }

    public void setClubNum(String clubNum) {
        this.clubNum = clubNum;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getSenderNum() {
        return senderNum;
    }

    public void setSenderNum(String senderNum) {
        this.senderNum = senderNum;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDdl() {
        return ddl;
    }

    public void setDdl(String ddl) {
        this.ddl = ddl;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }




}
