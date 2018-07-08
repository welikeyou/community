package com.java.control;
import com.java.model.Community;
import com.java.model.Create_Apply;
import com.java.model.Database;
import com.java.model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public Community[] show(ResultSet rs) {
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
        }
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

        return communities;
    }

    public Community[] viewCommnuities() {

        ResultSet rs = database.viewCommunities();
        Community[] communities = show(rs);
        return communities;
    }


    public void applyCommunity(String studentID, String communityID) {
        database.applyCommunity(studentID, communityID);
    }
    public void applyEstablishCommunity(String stuID, String comName, String type, String reason,String plan ,String advantage, String contact)
    {
        database.applyForEstablishCommunity(stuID, comName, type, reason, plan, advantage, contact);
    }

    public boolean checkRegister(String stuName, String stuPwd) {
        ResultSet rs = database.stuQuery(stuName);
        try {
            if (rs.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        database.stuInsert(stuName, stuPwd);
        return false;
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
            student.setBirthday(rs.getString("birthday"));
            student.setAddress(rs.getString("address"));
            student.setHobby(rs.getString("hobby"));
            student.setSlogan(rs.getString("slogan"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;

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
        database.stuinfoUpdate(student.getId(),student.getName(),student.getSex(),student.getContact(),student.getSchool(),student.getBirthday(),student.getAddress(),student.getHobby(),student.getSlogan());
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
                    String type=rs.getString("type");
                    String slogan_in=rs.getString("slogan_in");
                    String slogan_out=rs.getString("slogan_out");
                    String reason=rs.getString("reason");
                    String plan=rs.getString("plan");
                    String advantage=rs.getString("advantage");
                    String contact=rs.getString("contact");
                    String time=rs.getString("time");
                    String state=rs.getString("state");
                    String studentName=rs.getString("studentName");
                    Create_Apply create_Apply=new Create_Apply();
                    create_Apply.setAssociationName(associationName);
                   create_Apply.setStudentNum(studentNum);
                    create_Apply.setType(type);
                   create_Apply.setSlogan_in(slogan_in);
                   create_Apply.setSlogan_out(slogan_out);
                    create_Apply.setReason(reason);
                   create_Apply.setPlan(plan);
                    create_Apply.setAdvantage(advantage);
                  create_Apply.setContact(contact);
                   create_Apply.setTime(time);
                    create_Apply.setState(state);
                    create_Apply.setStudentName(studentName);
                    create_Apply.setResult("");
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
        u.setResult("");
        m.setStudentName("u");
        m.setAssociationName("y");
        m.setState("UNCHECKED");
        m.setResult("");
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

        //测试（一直到System.out.println)
       /* List<Create_Apply> checkedAssociations=new ArrayList<Create_Apply>();
        Create_Apply u=new Create_Apply();
        u.setStudentName("a");
        u.setAssociationName("m");
        u.setState("passed");
        u.setResult("");
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
}
