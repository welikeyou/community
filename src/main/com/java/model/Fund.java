package com.java.model;

public class Fund {
    public String associationNum;
    public String person;
    public double income;
    public double cost;
   /* public double formerMoney;//收入支出之前的余额
    public double laterMoney;//计算之后的余额*/
    public double currentMoney;//当前余额
    public double previousMoney;//历史余额
    public String date;
    public String remark;
    public String order;//主键

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public double getCurrentMoney() {
        return currentMoney;
    }

    public void setCurrentMoney(String currentMoney) {
        this.currentMoney = Double.valueOf(currentMoney).doubleValue();
    }

    public double getPreviousMoney() {
        return previousMoney;
    }

    public void setPreviousMoney(String previousMoney) {
        this.previousMoney =Double.valueOf(previousMoney).doubleValue();
    }
    /*public double getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = Double.valueOf(money).doubleValue();
    }*/

    public String getAssociationNum() {
        return associationNum;
    }

    public void setAssociationNum(String associationNum) {
        this.associationNum = associationNum;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = Double.valueOf(income).doubleValue();
    }

    public double getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = Double.valueOf(cost).doubleValue();
    }

   /* public void setFormerMoney(String formerMoney) {
        this.formerMoney = Double.valueOf(formerMoney).doubleValue();
    }

    public double getLaterMoney() {

        laterMoney=formerMoney+income-cost;
        return laterMoney;
    }
*/

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getResult(){
        String s= "{'day':'" + date + "','income':'" + income + "','outcome':'" + cost+ "','rest':'" + previousMoney+ "'}" ;
        return s;
    }
}
