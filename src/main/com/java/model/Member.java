package com.java.model;

public class Member {
    private String studentNum;
    private String clubNum;
    private String studentName;
    private String sex;
    private String buMen;
    public Student stuDetail;
    private String result;

    public Member(){
        stuDetail=new Student();
    }
    public void setStuDetail(String id,String name,String sex,String contact, String school,String birthday,String address,String hobby,String slogan) {
        this.stuDetail.setId(id);
        this.stuDetail.setName(name);
        this.stuDetail.setSex(sex);
        this.stuDetail.setContact(contact);
        this.stuDetail.setSchool(school);
        this.stuDetail.setBirthday(birthday);
        this.stuDetail.setAddress(address);
        this.stuDetail.setHobby(hobby);
        this.stuDetail.setSlogan(slogan);

    }
   public String  getStuDetail()
   {
       return stuDetail.getName();
   }

    public void setResult()
    {
        System.out.println(stuDetail.getName()+"在member里的学生姓名");
     String s="{'stuNum':'"+stuDetail.getId()+"','stuName':'"+stuDetail.getName()+"','stuSex':'"+stuDetail.getSex()+"','stuContact':'"+stuDetail.getContact()+"'," +
                "'stuSchool':'"+stuDetail.getSchool()+"','stuBirthday':'"+stuDetail.getBirthday()+"','stuAddress':'"+stuDetail.getAddress()+"','stuHobby':'"+stuDetail.getHobby()+"'," +
                "'stuSlogan':'"+stuDetail.getSlogan()+"'}";


        this.result=s;
    }
    public String getResult()
    {
        System.out.println(result);
        return result;

    }

    public void setStuNum(String stuNum)
    {
        studentNum=stuNum;
    }

    public String getStudentNum()
    {
        return studentNum;
    }

    public void setClubNum(String cluNum)
    {
        clubNum=cluNum;
    }

    public String getClubNum()
    {
        return clubNum;
    }

    public void setStudentName(String stuName)
    {
        studentName=stuName;
    }

    public String getStudentName()
    {
        return studentName;
    }

    public void setSex(String Sex)
    {
        sex=Sex;
    }

    public String getSex()
    {
        return sex;
    }

    public void setBuMen(String BuMen)
    {
       buMen=BuMen;
    }

    public String getBuMen()
    {
        return buMen;
    }
}
