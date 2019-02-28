package com.java.control;
import com.java.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
/*
功能: 该文件是数据库逻辑处理与Servlet的中间处理文件
创建者:黄友明
修改者:黄友明,李兰,刘诗滢
 */

public class Databaseco {
    public Database database = new Database();

    public int getRows(ResultSet rs)
    {
        int i = 0;
        try {
            rs.next();
            while (rs.next())
                i++;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;

    }
    /*public Community[] show(ResultSet rs) {
        int n_length =8;
        Community[] communities = new Community[n_length];

        try {


            for(int i = 0; i < n_length  && rs.next(); i++) {
                communities[i] = new Community();
               // buffer.append(rs.getString("associationNum"));
              //  String associationNum = new String(buffer);
                //buffer.delete(0, buffer.length());
              //  buffer.append(rs.getString("picture"));
               // String picSrc = new String(buffer);
              //  buffer.delete(0, buffer.length());
                communities[i].setComID(rs.getString("associationNum"));
                communities[i].setPicSrc(rs.getString("picture"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();


                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
        // String  result=new String(buffer);


        /*String[] res = result.split("/n");
        String[] rest = new String[2];
        String[][] re =  new String[n_length][2];
        for(int i = 0;i<n_length;i++)
        {
            rest = res[i].split("/t");
            re[i][0] = rest[0];
            re[i][1] = rest[1];
        }*/

   /*     return communities;
    }*/

    /*public Community[] viewCommnuities() {

        ResultSet rs = database.viewCommunities();
        Community[] communities = show(rs);
        return communities;
    }*/


    public void applyCommunity(String studentID, String communityID,String department,String hobby,String reason,String contact) {
        database.applyCommunity(studentID, communityID,department,hobby,reason,contact);
    }
   public void applyEstablishCommunity(String studentNum,String associationName, String level, String type, String aim, String logo, String address, String Plan, String introduction, String advantage, String   contact,   String     member1, String     briefintro1, String    member2, String    briefintro2,String   member3, String    briefintro3, String    member4, String   briefintro4, String      member5, String    briefintro5)
    {
        database.applyForEstablishCommunity(studentNum, associationName, level, type, aim, logo, address, Plan, introduction, advantage, contact, member1, briefintro1, member2, briefintro2,member3, briefintro3, member4, briefintro4, member5, briefintro5);
    }

    public boolean checkRegister(String stuName, String stuPwd) {
        ResultSet rs = database.stuQuery(stuName);
        int i = 0;
       // int j = 0;
        try {
            while (rs.next())
               i++;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //System.out.print(i);
        if(i<1) {
            database.stuInsert(stuName, stuPwd);
            return false;
        }
        else
            return true;
    }

    public String getPwdByStuName(String stuName) {
        ResultSet rs = database.stuQuery(stuName);
        StringBuffer stringBuffer = new StringBuffer();
        try {
            rs.next();
            stringBuffer.append(rs.getString("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String pwd = new String(stringBuffer);
        return pwd;
    }
    public Student getStuByStuID(String stuID)
    {
     ResultSet rs = database.stuQuery(stuID);
     Student student = new Student();
        try {
            rs.next();
            student.setId(rs.getString("studentNum"));
            student.setName(rs.getString("studentName"));
            student.setPassword(rs.getString("password"));
            student.setSex(rs.getString("sex"));
            student.setContact(rs.getString("contact"));
            student.setSchool(rs.getString("school"));
            String birthday0=rs.getString("birthday");
            if(birthday0==null||birthday0.equals(""))
            {
                student.setBirthday(birthday0);
            }
            else{
                String []birthdays=birthday0.split(" ");
                String birthday=birthdays[0];
                student.setBirthday(birthday);
            }
            student.setAddress(rs.getString("address"));
            student.setHobby(rs.getString("hobby"));
            student.setSlogan(rs.getString("slogan"));
           // student.setLogo(rs.getString("logo"));
            student.check();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public HashMap<String,String> getPersonalCreate(String studentNum){
        HashMap<String,String> personalCreate=new HashMap<>();
        try {
            ResultSet rs1=database.selectPersonalCreate(studentNum);
            if(rs1==null)return null;
            String pANum=null;
            while(rs1.next())
            {
                pANum=rs1.getString("associationNum");
                String pAName = null;
                pAName=rs1.getString("associationName");
                personalCreate.put(pANum,pAName);
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        //personalCreate.put("1001","动漫社");
        return personalCreate;
    }


    //获得个人所管理的社团
    public HashMap<String,String> getPersonalManage(String studentNum) {
        HashMap<String, String> personalManage = new HashMap<>();
        try {
            ResultSet rs1 = database.selectPersonalManage(studentNum);
            if (rs1 == null) return null;
            String pANum = null;
            while (rs1.next()) {
                pANum = rs1.getString("associationNum");
                String pAName = null;
                pAName = rs1.getString("associationName");
                personalManage.put(pANum, pAName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
            //personalManage.put("1001","国学社");
            return personalManage;
        }

    public String getPwdByStuID (String stuID){
       ResultSet rs = database.stuQuery(stuID);
       String pwd = new String();
        try {
            rs.next();
            pwd = rs.getString("password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pwd;
    }
    public void UpdatePwdByStuID(String stuID,String pwd)
    {
        database.stupwUpdate(stuID, pwd);
    }
    public void UpdateStu(Student student)
    {
        //String account, String studentname,String sex,String contact, String school,String date, String address,String hobby,String slogan
        database.stuinfoUpdate(student.getId(),student.getName(),student.getLogo(),student.getSex(),student.getContact(),student.getSchool(),student.getBirthday(),student.getAddress(),student.getHobby(),student.getSlogan());
    }

    public Community getClubByNum(String clubNum){
        Community clubInfo=new Community();
        ResultSet rs=database.clubQuery(clubNum);
        try {
            while (rs.next()) {
                clubInfo.setComID(clubNum);
                clubInfo.setComName(rs.getString("associationName"));
                clubInfo.setType(rs.getString("type_"));
                clubInfo.setLevel(rs.getString("level_"));
                clubInfo.setAim(rs.getString("aim"));
                clubInfo.setMembers(rs.getString("membersdetail"));
                clubInfo.setBiefInfo(rs.getString("introduction"));


            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        //测试数据
      /* clubInfo.setComID("123");
       clubInfo.setComName("ll");
       clubInfo.setType("体育");
       clubInfo.setLevel("校级");
       clubInfo.setAim("随便");
       clubInfo.setMembers("部长");
       clubInfo.setBiefInfo("555");*/
        return clubInfo;
    }

    public void UpdateClub(Community clubInfo)
    {
        database.clubUpdate(clubInfo.getComID(),clubInfo.getComName(),clubInfo.getType(),clubInfo.getLevel(),clubInfo.getAim(),clubInfo.getMembers(),clubInfo.getBiefInfo());
    }


    //针对成员管理
    public List<Member> getAllBuZhang(String clubNum){
        List<Member> allBuZhang=new ArrayList<>();
        ResultSet rs=database.getBuZhang(clubNum);
        try{
            while(rs.next())
            {

                //依次为社团编号，个人账号，个人姓名，性别，所属部门
                Member buZhang=new Member();
                buZhang.setClubNum(rs.getString("associationnum"));
                buZhang.setStuNum(rs.getString("studentnum"));
                buZhang.setStudentName(rs.getString("studentname"));
                buZhang.setSex(rs.getString("sex"));
                buZhang.setBuMen(rs.getString("department"));

                String stuNum=rs.getString("studentnum");
                ResultSet rs1=database.stuQuery(stuNum);
                if(rs1.next())
                {//String id,String name,String sex,String contact, String school,String birthday,String address,String hobby,String slogan

                    String id=rs1.getString("studentNum");
                    String name=rs1.getString("studentName");
                    String sex=rs1.getString("sex");
                    String contact=rs1.getString("contact");
                    String school=rs1.getString("school");
                    String birthday=rs1.getString("birthday");
                    String address=rs1.getString("address");
                    String hobby=rs1.getString("hobby");
                    String slogan=rs1.getString("slogan");
                    buZhang.setStuDetail(id, name, sex, contact,  school, birthday, address, hobby, slogan );
                    buZhang.setResult();
                }
                allBuZhang.add(buZhang);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

        //测试代码
    /*  for(int i=0;i<4;i++ )
        {
            Member buZhang=new Member();
            buZhang.setClubNum("123");
            buZhang.setStuNum("1");
            buZhang.setStudentName("ll");
            buZhang.setSex("m");
            buZhang.setBuMen("外联部");
            buZhang.setStuDetail("1","ll","m","123","wwwwwww","01-25","武汉","跑步","诚实守信");
            buZhang.setResult();
            System.out.println(buZhang.getStuDetail()+"学生姓名");
            allBuZhang.add(buZhang);

        }*/
        return allBuZhang;
    }

    public List<Member> getAllFuBu(String clubNum){
        List<Member> allFuBu=new ArrayList<>();
        ResultSet rs=database.getFuBu(clubNum);
        try{
            while(rs.next())
            {
                Member fuBu=new Member();
                fuBu.setClubNum(rs.getString("associationnum"));
                fuBu.setStuNum(rs.getString("studentnum"));
                fuBu.setStudentName(rs.getString("studentname"));
                fuBu.setSex(rs.getString("sex"));
                fuBu.setBuMen(rs.getString("department"));

                String stuNum=rs.getString("studentnum");
                ResultSet rs1=database.stuQuery(stuNum);
                if(rs1.next())
                {//String id,String name,String sex,String contact, String school,String birthday,String address,String hobby,String slogan

                    String id=rs1.getString("studentNum");
                    String name=rs1.getString("studentName");
                    String sex=rs1.getString("sex");
                    String contact=rs1.getString("contact");
                    String school=rs1.getString("school");
                    String birthday=rs1.getString("birthday");
                    String address=rs1.getString("address");
                    String hobby=rs1.getString("hobby");
                    String slogan=rs1.getString("slogan");
                    fuBu.setStuDetail(id, name, sex, contact,  school, birthday, address, hobby, slogan );
                    fuBu.setResult();
                }
                allFuBu.add(fuBu);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        //测试代码
       /* for(int i=0;i<4;i++ )
        {
            Member buZhang=new Member();
            buZhang.setClubNum("123");
            buZhang.setStuNum("1");
            buZhang.setStudentName("ll");
            buZhang.setSex("m");
            buZhang.setBuMen("实践部");
            buZhang.setStuDetail("1","ll","m","123","wwwwwww","01-25","武汉","跑步","诚实守信");
            buZhang.setResult();
            allFuBu.add(buZhang);

        }*/
        return allFuBu;
    }

    public List<Member> getAllBuWei(String clubNum){
        List<Member> allBuWei=new ArrayList<>();
        ResultSet rs=database.getBuWei(clubNum);
        try{
            while(rs.next())
            {
                Member buWei=new Member();
                buWei.setClubNum(rs.getString("associationnum"));
                buWei.setStuNum(rs.getString("studentnum"));
                buWei.setStudentName(rs.getString("studentname"));
                buWei.setSex(rs.getString("sex"));
                buWei.setBuMen(rs.getString("department"));

                String stuNum=rs.getString("studentnum");
                ResultSet rs1=database.stuQuery(stuNum);
                if(rs1.next())
                {//String id,String name,String sex,String contact, String school,String birthday,String address,String hobby,String slogan

                    String id=rs1.getString("studentNum");
                    String name=rs1.getString("studentName");
                    String sex=rs1.getString("sex");
                    String contact=rs1.getString("contact");
                    String school=rs1.getString("school");
                    String birthday=rs1.getString("birthday");
                    String address=rs1.getString("address");
                    String hobby=rs1.getString("hobby");
                    String slogan=rs1.getString("slogan");
                    buWei.setStuDetail(id, name, sex, contact,  school, birthday, address, hobby, slogan );
                    buWei.setResult();
                }

                allBuWei.add(buWei);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        //测试代码
       /* for(int i=0;i<8;i++ )
        {
            Member buZhang=new Member();
            buZhang.setClubNum("123");
            buZhang.setStuNum("1");
            buZhang.setStudentName("ll");
            buZhang.setSex("m");
            buZhang.setBuMen("实践部");
            allBuWei.add(buZhang);
            buZhang.setStuDetail("1","ll","m","123","wwwwwww","01-25","武汉","跑步","诚实守信");
            buZhang.setResult();

        }*/
        return allBuWei;
    }

    public void allToBuZhang(String clubNum,String stuNum)
    {
        database.toBuZhang(clubNum,stuNum);
    }
    public void allToFuBu(String clubNum,String stuNum)
    {
        database.toFuBu(clubNum,stuNum);
    }

    public void allToBuWei(String clubNum,String stuNum)
    {
        database.toBuWei(clubNum,stuNum);
    }

    public void deleteMember(String clubNum,String studentNum )
    {
        database.deleteMem(clubNum,studentNum);
    }

    public HashMap<String,String> getPersonalAssociation(String studentNum){
        HashMap<String,String> personalAssociation=new HashMap<>();
        try {
            ResultSet rs1=database.selectFromStuAssociation(studentNum);
            if(rs1==null)return null;
            String pANum=null;
            while(rs1.next())
            {
                pANum=rs1.getString("associationNum");
                String pAName = null;
                pAName=rs1.getString("associationName");
                personalAssociation.put(pANum,pAName);
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return personalAssociation;
    }

    //审核社团
    //根据具体的sql语句获得申请创建的社团
    public List<Create_Apply> getApplyAssociations(ResultSet rs){
        List<Create_Apply> applyAssociations=new ArrayList<>();
        if(rs==null)applyAssociations=null;
        else {
            try {
                while(rs.next()) {

                    String studentNum=rs.getString("studentNum");
                    String associationName=rs.getString("associationName");
                    String studentName=rs.getString("studentName");
                    String sex=rs.getString("sex");
                    String birthday=rs.getString("birthday");
                    String school=rs.getString("school");
                    String aim=rs.getString("aim");
                    String plan=rs.getString("plan_");
                    String contact=rs.getString("contact");
                    String time=rs.getString("time_");
                    String state=rs.getString("state");
                    String member1=rs.getString("member1");
                    String member2=rs.getString("member2");
                    String member3=rs.getString("member3");
                    String member4=rs.getString("member4");
                    String member5=rs.getString("member5");

                    String briefintro1=rs.getString("briefintro1");
                    String briefintro2=rs.getString("briefintro2");
                    String briefintro3=rs.getString("briefintro3");
                    String briefintro4=rs.getString("briefintro4");
                    String briefintro5=rs.getString("briefintro5");
                    Create_Apply create_Apply=new Create_Apply();
                    create_Apply.setAssociationName(associationName);
                    create_Apply.setStudentNum(studentNum);
                    create_Apply.setStudentName(studentName);
                    create_Apply.setSex(sex);
                    create_Apply.setBirthday(birthday);
                    create_Apply.setSchool(school);
                    create_Apply.setAim(aim);
                    create_Apply.setPlan(plan);
                    create_Apply.setContact(contact);
                    create_Apply.setTime(time);
                    create_Apply.setState(state);

                    create_Apply.setMember1(member1);
                    create_Apply.setMember2(member2);
                    create_Apply.setMember3(member3);
                    create_Apply.setMember4(member4);
                    create_Apply.setMember5(member5);

                    create_Apply.setBriefintro1(briefintro1);
                    create_Apply.setBriefintro2(briefintro2);
                    create_Apply.setBriefintro3(briefintro3);
                    create_Apply.setBriefintro4(briefintro4);
                    create_Apply.setBriefintro5(briefintro5);


                    //System.out.println("====result:"+create_Apply.getResult());
                    applyAssociations.add(create_Apply);}
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return applyAssociations;
    }

    //获取未审核的
    public List<Create_Apply> getUncheckedAssociations() {

        //实际代码
		List<Create_Apply> uncheckedAssociations;
		ResultSet rs=database.selectUncheckedAssociations();
		uncheckedAssociations=getApplyAssociations(rs);

        //测试(此处开始一直到System.out.println)
       /* List<Create_Apply> uncheckedAssociations=new ArrayList<Create_Apply>();
        Create_Apply u=new Create_Apply();
        Create_Apply m=new Create_Apply();
        u.setStudentName("x");
        u.setAssociationName("v");
        u.setState("UNCHECKED");

        m.setStudentName("n");
        m.setAssociationName("y  dfrg");
        m.setTime("2017-9-10 23:45:09");
        m.setPlan("我的计划");

        m.setState("UNCHECKED");
        uncheckedAssociations.add(u);
        uncheckedAssociations.add(m);
        System.out.println(u.getResult());*/

        return uncheckedAssociations;
    }

    //获取审核过的
    public List<Create_Apply>getCheckedAssociations(){

        //实际代码
		List<Create_Apply> checkedAssociations;
		ResultSet rs=database.selectCheckedAssociations();
		checkedAssociations=getApplyAssociations(rs);

       /* //测试（一直到System.out.println)
        List<Create_Apply> checkedAssociations=new ArrayList<Create_Apply>();
        Create_Apply u=new Create_Apply();
        u.setStudentName("a");
        u.setAssociationName("");
        u.setState("passed");
        checkedAssociations.add(u);
        System.out.println("m"+u.getResult()+u.getStudentName()+u.getAssociationName());*/
        return checkedAssociations;
    }

    //审核通过后1.state->passed 2.加入到Associations表中
    public void addAssociations(String studentNum,String associaitionName) {
        database.updateStatePass(studentNum,associaitionName);
        //加入表
    }

    //审核未通过，更改状态
    public void fail(String studentNum,String associaitionName) {
        database.updateStateFail(studentNum,associaitionName);

    }

    //删除
    public void delete(String studentNum,String associaitionName) {
        database.deleteCreate_Apply(studentNum,associaitionName);

    }

    /*//获取审核申请学生的详细信息
    public List<Join_Apply>getApplyInfo(ResultSet rs) {
        List<Join_Apply> list = new ArrayList<>();
        if (rs == null) list = null;
        else {
            try {
                Join_Apply one = new Join_Apply();
                while (rs.next()) {
                    String associationNum=rs.getString("associationNum");
                    String studentNum = rs.getString("studentNum");
                    String studetnName = rs.getString("studentName");
                    String sex = rs.getString("sex");
                    String birthday = rs.getString("birthday");
                    String reason = rs.getString("reason");
                    String hobby = rs.getString("hobby");
                    String contact = rs.getString("contact");
                    String time = rs.getString("time");
                    String state = rs.getString("state");
                    String associations = "";

                    //获得已加入的社团
                    HashMap<String, String> getAssociations = getPersonalAssociation(studentNum);
                    if (getAssociations == null) associations = "";
                    else {
                        Iterator<Map.Entry<String, String>> iterator = getAssociations.entrySet().iterator();
                        while (iterator.hasNext()) {
                            Map.Entry<String, String> entry = iterator.next();
                            String association = (String) entry.getValue();
                            associations += association;
                        }
                    }
                    one.setStudentNum(studentNum);
                    one.setStudentName(studetnName);
                    one.setSex(sex);
                    one.setBirthday(birthday);
                    one.setReason(reason);
                    one.setHobby(hobby);
                    one.setAssociations(associations);
                    one.setContact(contact);
                    one.setTime(time);
                    one.setState(state);
                    one.setResult(" ");
                    one.setAssociationNum(associationNum);

                    list.add(one);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }return list;
    }*/

    /*//获取未审核学生信息
    public List<Join_Apply> getUncheckedJoin(String associationNum) {
        List<Join_Apply> unchecked=new ArrayList<>();
        ResultSet rs=database.seleteUncheckedJoin(associationNum);
        unchecked=getApplyInfo(rs);


        //测试
        *//*Join_Apply m=new Join_Apply();
        Join_Apply n=new Join_Apply();
        m.setStudentNum("1001");
        m.setStudentName("Jone");
        m.setTime("2001-8-8 24:30:56");
        m.setHobby("kkxksljas");
        m.setAssociationNum("1");
        m.setResult("");
        m.setAssociationNum("1");

        n.setStudentNum("1006");
        n.setStudentName("Lily");
        n.setTime("2009-8-8 24:30:56");
        n.setHobby("kkxadsksljas");
        n.setResult("");
        unchecked.add(m);
        unchecked.add(n);*//*
        return unchecked;
    }

    //获取已审核学生信息
    public List<Join_Apply> getCheckedJoin(String associationNum) {
        List<Join_Apply> checked=new ArrayList<>();
    ResultSet rs=database.seleteCheckedJoin(associationNum);
        checked=getApplyInfo(rs);


        *//*Join_Apply m=new Join_Apply();
        Join_Apply n=new Join_Apply();
        m.setStudentNum("1004");
        m.setStudentName("haha");
        m.setTime("2068-8-8 24:30:56");
        m.setHobby("kkxksljas");
        m.setAssociationNum("1");
        m.setResult("");

        m.setAssociationNum("1");
        n.setStudentNum("1002");
        n.setStudentName("Sam");
        n.setTime("20309-8-8 24:30:56");
        n.setHobby("kkxadsksljas");
        n.setResult("");
        checked.add(m);
        checked.add(n);*//*
        return checked;
    }



    public void failJoin(String studentNum, String associationNum) {
        database.joinStateFail(studentNum,associationNum);
        System.out.println("refuse2");
    }



    public void deleteJoin(String studentNum, String associationNum) {
        database.deleteJoin_Apply(studentNum,associationNum);
        System.out.println("delete2");
    }

    //state->pass
    public void addJoin(String studentNum, String associationNum) {
        database.joinStatePass(studentNum,associationNum);
        System.out.println("pass2");
    }*/

    //Fund管理
    public List<Fund> getFundInfo(String associationNum){
        List<Fund> fund=new ArrayList<>();
        ResultSet rs=database.selectFunds(associationNum);
        if(rs==null)fund=null;
        else{
          try{

                while(rs.next()){
                    Fund one=new Fund();
                    String person=rs.getString("person");
                    String income=rs.getString("income");
                    String cost=rs.getString("cost");
                    String CurrentMoney=rs.getString("money");
                    String previousMoney=rs.getString("historyMoney");
                    String date=rs.getString("date_");
                    String remark=rs.getString("remark_");
                    String order=rs.getString("order_");

                    one.setAssociationNum(associationNum);
                    one.setPerson(person);
                    one.setIncome(income);
                    one.setCost(cost);
                    one.setDate(date);
                    one.setRemark(remark);
                    one.setCurrentMoney(CurrentMoney);
                    one.setPreviousMoney(previousMoney);
                    one.setOrder(order);
                    fund.add(one);
                }
          }catch(Exception e){
              e.printStackTrace();
          }
        }

        /*Fund m=new Fund();
        m.setPerson("Jone");
        m.setDate("2008");
        m.setCost("60");
        m.setCurrentMoney("100");
        m.setPreviousMoney("200");
        m.setOrder("1");
        fund.add(m);*/
        return fund;
    }

    public void addFunds(String associationNum, String person, String cost, String income, String currentMoney, String date, String remark) {

        database.addFundsInfo(associationNum,person,cost,income,currentMoney,date,remark);
        /*database.updateAssociationMoney(associationNum,currentMoney);*/
    }

    public double getAssociationMoney(String associationNum) {
        double money=0;
        String getMoney;
        ResultSet rs=database.getMoney(associationNum);
        try{
            while(rs.next()) {
                getMoney = rs.getString("money");
                money = Double.valueOf(getMoney).doubleValue();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return money;
    }


    //修改负责人
   /* public void updatePerson(String order, String value) {
        database.updateBillPerson(order,value);
    }

    public void updateRemark(String order, String remark) {
        database.updateBillRemark(order,remark);
    }

    public void updateCost(String order, String value, String currentMoney,String associationNum) {
        //修改bill中的cost和previousMoney
        database.updateBillCost(order,value,currentMoney);
        //修改association表里的money
        database.updateAssociationMoney(associationNum,currentMoney);

    }

    public void updateIncome(String order, String value, String currentMoney, String associationNum) {
        //修改bill中的Income和previousMoney
        database.updateBillIncome(order,value,currentMoney);
        //修改association表里的money
        database.updateAssociationMoney(associationNum,currentMoney);
    }

    public void updateDate(String order, String value) {
        database.updateBillDate(order,value);
    }*/
    public void releaseRecruitment(Recruitment recruitment)
    {
        database.addRecruitment(recruitment.getTittle(),recruitment.getIntroduction(),recruitment.getPropaganda(),recruitment.getDemand(),recruitment.getExamine(),recruitment.getTime(),recruitment.getPostcard(),recruitment.getPublisher(),recruitment.getContact(),recruitment.getAssociationNum());
    }
    public void releaseActivity(String title, String associationName, String time, String address, String introduction, String  demand, String registrationTime, String  publisher,String contact,String postcard)
    {
        database.addActivity(title, associationName,postcard, time, address, introduction, demand, registrationTime, publisher, contact );
    }
    public Student[][] getStuByPos(String comID)
    {
        Student[][] students = new Student[3][];
        students[0] = new Student[1];

        students[1] = new Student[10];
        students[2] = new Student[10];
        students[0][0] = new Student();
        ResultSet rs0 =   database.getStuByPosition(comID,"部长");


        try {
        while (rs0.next()) {

                students[0][0].setId(rs0.getString("studentnum"));
                students[0][0].setName(rs0.getString("studentname"));
    }
            ResultSet rs1 =   database.getStuByPosition(comID,"副部长");
            int  i = 0;
            while (rs1.next())
            {
                if(i == 10)
                    break;
                students[0][i] = new Student();
                students[0][i].setId(rs1.getString("studentnum"));
                students[0][i].setName(rs1.getString("studentname"));
                i++;
            }

             i = 0;
            ResultSet rs2 =   database.getStuByPosition(comID,"普通成员");
            while (rs2.next())
            {
                if(i == 10)
                    break;
                students[0][i].setId(rs2.getString("studentnum"));
                students[0][i].setName(rs2.getString("studentname"));
                i++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
    public Recruitment[] viewRecruitments(int page)
    {
        page--;
       ResultSet rs =  database.viewRecruitment();
        Recruitment[] recruitments = new Recruitment[10];
        int  i = 0;
        int j = 0;
        try {
            while (rs.next())
            {
                if(j <page * 10) {
                    j++;
                    continue;
                }
                if(10 == i)
                    break;
                recruitments[i] = new Recruitment();
                recruitments[i].setTittle(rs.getString("title"));
                recruitments[i].setPostcard(rs.getString("postcard"));
                recruitments[i].setTime(rs.getString("time"));
                recruitments[i].setAssociationNum(rs.getString("associationNum"));
                recruitments[i].setPublisher(rs.getString("publisher"));
                recruitments[i].setContact(rs.getString("contact"));
                recruitments[i].setExamine(rs.getString("examine"));
                recruitments[i].setPropaganda(rs.getString("propaganda"));
                recruitments[i].setIntroduction(rs.getString("introduction"));
                recruitments[i].setDemand(rs.getString("demand"));
                recruitments[i].check();
                i++;
            }

           // return recruitments;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recruitments;
    }
    public int getRecPageCount()
    {
        ResultSet rs = database.viewRecruitment();
        int pagecount = 1;
        int infos = -1;
        try {
            while (rs.next())
            {
                infos++;
                if(10 == infos)
                {
                    pagecount++;
                    infos=0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pagecount;
    }
    public Community getComByID(String comID)  {
     ResultSet rs =  database.viewcompage(comID);
      Community community = new Community();

        try {
            while (rs.next())
            {
                community.setComID(comID);
                community.setComName(rs.getString("associationName"));
                community.setChiefID(rs.getString("studentNum"));
                community.setType(rs.getString("type_"));
                community.setLevel(rs.getString("level_"));
                community.setAmount(rs.getString("amount"));
                community.setMoney(rs.getString("money"));
                community.setAim(rs.getString("aim"));
                community.setBiefInfo(rs.getString("introduction"));
                community.setMembers(rs.getString("membersdetail"));
                community.setHeadPicture(rs.getString("headPicture"));
                community.setBackgroundPicture(rs.getString("backgroundPicture"));
                community.setTitle1(rs.getString("title1"));
                community.setContent1(rs.getString("content1"));
                community.setTitle2(rs.getString("title2"));
                community.setContent2(rs.getString("content2"));
                community.check();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*community.setType("type");
        community.setLevel("level");*/
        return community;
    }
    public Recruitment getRecruitmentByTittle(String tittle)
    {
     ResultSet rs =  database.getRecruitmentByTittle(tittle);
      Recruitment recruitment =  new Recruitment();
        try {
            while (rs.next())
            {
                recruitment.setTittle(tittle);
                recruitment.setPostcard(rs.getString("postcard"));
                recruitment.setTime(rs.getString("time"));
                recruitment.setAssociationNum(rs.getString("associationNum"));
                recruitment.setPublisher(rs.getString("publisher"));
                recruitment.setContact(rs.getString("contact"));
                recruitment.setExamine(rs.getString("examine"));
                recruitment.setPropaganda(rs.getString("propaganda"));
                recruitment.setIntroduction(rs.getString("introduction"));
                recruitment.setDemand(rs.getString("demand"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recruitment;
    }
   public List<ComMainPage> getComMainPageByComID(String comID)
   {
       List<ComMainPage> list = new ArrayList<>();
       ResultSet rs = database.getCommunityMainInfo(comID);
       try {
           while (rs.next())
           {
               ComMainPage comMainPage = new ComMainPage();
               comMainPage.setComID(comID);
               comMainPage.setTittle(rs.getString("title"));
               comMainPage.setContact(rs.getString("content"));
               list.add(comMainPage);
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return  list;
   }
   public void addComMainPageInfo(String communityID,String tittle,String contact)
   {
       database.addCommunityMainInfo(communityID, tittle, contact);
   }
    public void deleteComMainPageInfo(String communityID,String tittle)
    {
        database.deleteCommunityMainInfo(communityID, tittle);
    }
    public void updateCommunityInfo(String communityID,String tittle1,String contact1,String tittle2,String contact2,String bgPic,String tittlePic)
    {
        database.updateCommunityinfos(communityID, tittle1, contact1, tittle2, contact2, bgPic, tittlePic);
    }
    public List<Activity> viewActivities()
    {
        ResultSet rs = database.viewActivities();
        List<Activity> list = new ArrayList<>();
        try {
            while (rs.next())
            {
                Activity activity = new Activity();
                activity.setAddress(rs.getString("address")  );
                activity.setAssociationName(rs.getString("associationName")  );
                activity.setContact(rs.getString("contact")  );
                activity.setDemand(rs.getString("demand")  );
                activity.setIntroduction(rs.getString("introduction")  );
                activity.setPublisher(rs.getString("publisher")  );
                activity.setTitle(rs.getString("title")  );
                activity.setTime(rs.getString("time")  );
                activity.setPostcard(rs.getString("name"));
                activity.setRegistrationTime(rs.getString("registrationTime")  );
                list.add(activity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public Activity getActivityByTitle(String title)
    {
        ResultSet rs = database.getActivitiesByTitle(title);
        Activity activity = new Activity();
        try {
            while (rs.next())
            {

                activity.setAddress(rs.getString("address")  );
                activity.setAssociationName(rs.getString("associationName")  );
                activity.setContact(rs.getString("contact")  );
                activity.setDemand(rs.getString("demand")  );
                activity.setIntroduction(rs.getString("introduction")  );
                activity.setPublisher(rs.getString("publisher")  );
                activity.setPostcard(rs.getString("name"));
                activity.setTitle(title );
                activity.setTime(rs.getString("time")  );
                activity.setRegistrationTime(rs.getString("registrationTime")  );

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activity;
    }
    public void addapplyforactivity(String studentNum, String grade, String title, String associationName, String hobby, String  reason)
    {
        database.addapplyforactivity(studentNum, grade, title, associationName, hobby, reason);
    }
    public void addAssignment(String associationNum,String title,String content, String sender,String DDL,String filepath,String[] receivers)
    { database.addAssignment(associationNum, title, content, sender, DDL, filepath, receivers);
    }
    //审核活动详细信息
    public List<Activity_Apply> getActivityApplies(ResultSet rs) {
        List<Activity_Apply> activity_applies = new ArrayList<>();
        if (rs == null) activity_applies = null;
        else {
            try {
                Activity_Apply one = new Activity_Apply();
                while (rs.next()) {
                    String associationName=rs.getString("associationName");
                    String title=rs.getString("title");
                    String associationNum = rs.getString("associationNum");
                    String studentNum = rs.getString("studentNum");
                    String studetnName = rs.getString("studentName");
                    String sex = rs.getString("sex");
                    String birthday = rs.getString("birthday");
                    String school = rs.getString("school");
                    String reason = rs.getString("reason");
                    String hobby = rs.getString("hobby");
                    String contact = rs.getString("contact");
                    String time = rs.getString("time");
                    String state = rs.getString("state");
                    String associations = "";

                    //获得已加入的社团
                    HashMap<String, String> getAssociations = getPersonalAssociation(studentNum);
                    if (getAssociations == null) associations = "";
                    else {
                        Iterator<Map.Entry<String, String>> iterator = getAssociations.entrySet().iterator();
                        while (iterator.hasNext()) {
                            Map.Entry<String, String> entry = iterator.next();
                            String association = (String) entry.getValue();
                            associations += association;
                        }
                    }
                    one.setTitle(title);
                    one.setAssociationNum(associationNum);
                    one.setStudentNum(studentNum);
                    one.setStudentName(studetnName);
                    one.setSex(sex);
                    one.setBirthday(birthday);
                    one.setSchool(school);
                    one.setReason(reason);
                    one.setHobby(hobby);
                    one.setAssociations(associations);
                    one.setContact(contact);
                    one.setTime(time);
                    one.setState(state);
                    one.setAssociationName(associationName);

                    activity_applies.add(one);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return activity_applies;
    }

    public List<Activity_Apply>getCheckedAc(String associationNum){
        List<Activity_Apply> checked = new ArrayList<>();
        ResultSet rs=database.seleteCheckedAc(associationNum);
        checked=getActivityApplies(rs);

        return checked;
    }

    public List<Activity_Apply>getUncheckedAc(String associationNum){
        List<Activity_Apply> unchecked = new ArrayList<>();
      ResultSet rs=database.seleteUncheckedAc(associationNum);
        unchecked=getActivityApplies(rs);

        return unchecked;
    }

    public void approveAct(String studentNum, String title,String associationName) {
        database.updateActPass(studentNum,title,associationName);
    }

    public void failAct(String studentNum, String title,String associationName) {
        database.updateActFail(studentNum,title,associationName);
    }

    public void deleteAct(String studentNum, String title,String associationName) {
        database.deleteAct(studentNum,title,associationName);
    }
    public void exitAssociation(String studentNum, String associationNum,String associationName) {
        database.deleteInAssociation(studentNum,associationNum);
        database.deleteInStuChoAsso(studentNum,associationNum);
        database.deleteInAct_Apply(studentNum,associationName);
        database.deleteInStuChooseAc(studentNum,associationName);
    }
    //获取审核申请学生的详细信息
    public List<Join_Apply> getApplyInfo(ResultSet rs) {
        List<Join_Apply> list = new ArrayList<>();
        if (rs == null) list = null;
        else {
            try {
                Join_Apply one = new Join_Apply();
                while (rs.next()) {
                    String associationNum = rs.getString("associationNum");
                    String studentNum = rs.getString("studentNum");
                    String studetnName = rs.getString("studentName");
                    String sex = rs.getString("sex");
                    String birthday = rs.getString("birthday");
                    String school=rs.getString("school");
                    String department=rs.getString("department");
                    String reason = rs.getString("reason");
                    String hobby = rs.getString("hobby");
                    String contact = rs.getString("contact");
                    String time = rs.getString("time");
                    String state = rs.getString("state");
                    String associations = "";

                    //获得已加入的社团
                    HashMap<String, String> getAssociations = getPersonalAssociation(studentNum);
                    if (getAssociations == null) associations = "";
                    else {
                        Iterator<Map.Entry<String, String>> iterator = getAssociations.entrySet().iterator();
                        while (iterator.hasNext()) {
                            Map.Entry<String, String> entry = iterator.next();
                            String association = (String) entry.getValue();
                            associations += association;
                        }
                    }
                    one.setStudentNum(studentNum);
                    one.setStudentName(studetnName);
                    one.setSex(sex);
                    one.setBirthday(birthday);
                    one.setSchool(school);
                    one.setDepartment(department);
                    one.setReason(reason);
                    one.setHobby(hobby);
                    one.setAssociations(associations);
                    one.setContact(contact);
                    one.setTime(time);
                    one.setState(state);
                    one.setAssociationNum(associationNum);

                    list.add(one);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return list;
    }

    //获取未审核学生信息
    public List<Join_Apply> getUncheckedJoin(String associationNum) {
        List<Join_Apply> unchecked = new ArrayList<>();
       ResultSet rs=database.seleteUncheckedJoin(associationNum);
        unchecked=getApplyInfo(rs);


       /* //测试
        Join_Apply m = new Join_Apply();
        Join_Apply n = new Join_Apply();
        m.setStudentNum("1001");
        m.setStudentName("Jone");
        m.setTime("2001-8-8 24:30:56");
        m.setHobby("kkxksljas");
        m.setAssociationNum("1");

        m.setAssociationNum("1");

        n.setStudentNum("1006");
        n.setStudentName("Lily");
        n.setTime("2009-8-8 24:30:56");
        n.setHobby("kkxadsksljas");

        unchecked.add(m);
        unchecked.add(n);*/
        return unchecked;
    }

    //获取已审核学生信息
    public List<Join_Apply> getCheckedJoin(String associationNum) {
        List<Join_Apply> checked = new ArrayList<>();
     ResultSet rs=database.seleteCheckedJoin(associationNum);
        checked=getApplyInfo(rs);


        /*Join_Apply m = new Join_Apply();
        Join_Apply n = new Join_Apply();
        m.setStudentNum("1004");
        m.setStudentName("haha");
        m.setTime("2068-8-8 24:30:56");
        m.setHobby("kkxksljas");
        m.setAssociationNum("1");


        m.setAssociationNum("1");
        n.setStudentNum("1002");
        n.setStudentName("Sam");
        n.setTime("20309-8-8 24:30:56");
        n.setHobby("kkxadsksljas");

        checked.add(m);
        checked.add(n);*/
        return checked;
    }


    public void failJoin(String studentNum, String associationNum) {
        database.joinStateFail(studentNum, associationNum);
        System.out.println("refuse2");
    }


    public void deleteJoin(String studentNum, String associationNum) {
        database.deleteJoin_Apply(studentNum, associationNum);
        System.out.println("delete2");
    }

    //state->pass
    public void addJoin(String studentNum, String associationNum) {
        database.joinStatePass(studentNum, associationNum);
        System.out.println("pass2");
    }
    //7/17 新添代码 李兰
    public List<Task> getUrgentTask(String stuNum)
    {
        List<Task> urgentTasks=new ArrayList<>();
        ResultSet rs=database.getTasks(stuNum,"1");
        try{
            while(rs.next())
            {
                Task task=new Task();
                task.setTaskNum(rs.getString(1));
                task.setClubNum(rs.getString(2));
                task.setTaskNum(rs.getString("messageNum"));
                task.setClubName(rs.getString("associationNum"));
                task.setSenderName(rs.getString("studentname"));
                task.setTitle(rs.getString("title"));
                task.setDdl(rs.getString("ddl"));
                task.setSendTime(rs.getString("publishtime"));
                task.setContent(rs.getString("content"));
                task.setFilePath(rs.getString("filepath"));
                task.setState("未完成");//紧急
                task.setResult();
                urgentTasks.add(task);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
     /*  for(int i=0;i<4;i++)
       {
           Task task=new Task();
           task.setTaskNum("1");
           task.setClubNum("123");
           task.setClubName("可乐社团");
           task.setSenderNum("123");
           task.setSenderName("张三");
           task.setTitle("参加讲座");
           task.setDdl("2018-8-9");
           task.setSendTime("2018-6-20");
           task.setContent("hoonojoiojoi");
           task.setFilePath("jljl");
           task.setState("待完成");//紧急
           task.setResult();
           urgentTasks.add(task);
       }*/
        return urgentTasks;
    }

    public List<Task> getDoingTask(String stuNum)
    {
        List<Task> doingTasks=new ArrayList<>();
        ResultSet rs=database.getTasks(stuNum,"2");
        try{
            while(rs.next())
            {
                Task task=new Task();
                task.setTaskNum(rs.getString("messageNum"));
                task.setClubName(rs.getString("associationNum"));
                task.setSenderName(rs.getString("studentname"));
                task.setTitle(rs.getString("title"));
                task.setDdl(rs.getString("ddl"));
                task.setSendTime(rs.getString("publishtime"));
                task.setContent(rs.getString("content"));
                task.setFilePath(rs.getString("filepath"));
                task.setState("未完成");
                task.setResult();
                doingTasks.add(task);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

       /* for(int i=0;i<4;i++)
        {
            Task task=new Task();
            task.setTaskNum("1");
            task.setClubNum("123");
            task.setClubName("可乐社团");
            task.setSenderNum("123");
            task.setSenderName("张三");
            task.setTitle("参加讲座");
            task.setDdl("2018-8-9");
            task.setSendTime("2018-6-20");
            task.setContent("hoonojoiojoi");
            task.setFilePath("jljl");
            task.setState("待完成");//紧急
            task.setResult();
            doingTasks.add(task);
        }*/
        return doingTasks;
    }

    public List<Task> getOvertimeTask(String stuNum)
    {
        List<Task> overtimeTasks=new ArrayList<>();
        ResultSet rs=database.getTasks(stuNum,"3");
        try{
            while(rs.next())
            {
                // m.messageNum,a.associationNum,a.associationName,s.studentnum,s.studentname,m.title,m.content,m.ddl,m.publishtime,sm.state,m.filepath
                Task task=new Task();
                task.setTaskNum(rs.getString("messageNum"));
                task.setClubName(rs.getString("associationNum"));
                task.setSenderName(rs.getString("studentname"));
                task.setTitle(rs.getString("title"));
                task.setDdl(rs.getString("ddl"));
                task.setSendTime(rs.getString("publishtime"));
                task.setContent(rs.getString("content"));
                task.setFilePath(rs.getString("filepath"));
                task.setState("超时");
                task.setResult();
                overtimeTasks.add(task);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        //测试代码
      /*  for(int i=0;i<4;i++)
        {
            Task task=new Task();
            task.setTaskNum("1");
            task.setClubNum("123");
            task.setClubName("可乐社团");
            task.setSenderNum("123");
            task.setSenderName("张三");
            task.setTitle("参加讲座");
            task.setDdl("2018-8-9");
            task.setSendTime("2018-6-20");
            task.setContent("hoonojoiojoi");
            task.setFilePath("jljl");
            task.setState("超时");//紧急
            task.setResult();
            overtimeTasks.add(task);
        }*/
        return overtimeTasks;
    }

    public List<Task> getFinishedTask(String stuNum)
    {
        List<Task> finishedTasks=new ArrayList<>();
        ResultSet rs=database.getTasks(stuNum,"4");
        try{
            while(rs.next())
            {
                Task task=new Task();
                task.setTaskNum(rs.getString("messageNum"));
                task.setClubName(rs.getString("associationNum"));
                task.setSenderName(rs.getString("studentname"));
                task.setTitle(rs.getString("title"));
                task.setDdl(rs.getString("ddl"));
                task.setSendTime(rs.getString("publishtime"));
                task.setContent(rs.getString("content"));
                task.setFilePath(rs.getString("filepath"));
                task.setState("已完成");
                task.setResult();
                finishedTasks.add(task);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        //测试代码
       /* for(int i=0;i<4;i++)
        {
            Task task=new Task();
            task.setTaskNum("1");
            task.setClubNum("123");
            task.setClubName("可乐社团");
            task.setSenderNum("123");
            task.setSenderName("张三");
            task.setTitle("参加讲座");
            task.setDdl("2018-8-9");
            task.setSendTime("2018-6-20");
            task.setContent("hoonojoiojoi");
            task.setFilePath("jljl");
            task.setState("已完成");
            task.setResult();
            finishedTasks.add(task);
        }*/
        return finishedTasks;
    }

    public void toFinishedBytaskNum(String taskNum,String stuNum){
        database.toFinished(taskNum,stuNum);
    }

    public void chgState(String stuNum){
        database.changeState(stuNum);
    }
    public void deleTask(String stuNum){database.deleteTask(stuNum);}
    public String getposition(String studentnum, String associationnum)
    {
       ResultSet rs =  database.getposition(studentnum, associationnum);

        try {
            rs.next();
            while (rs.next())
            {

                return "common";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "not";
    }
}
