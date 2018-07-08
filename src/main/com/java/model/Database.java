package com.java.model;
/*
功能:数据库基础逻辑
创建者:佟光辉
修改者:佟光辉
 */


import java.sql.*;

public class Database {

    public static  Connection conn = null;
    public static Statement stmt = null;
    public static ResultSet rs = null;

    public String sql = null;
    static
    {
       String url = null;
       String user = null;
       String password = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver"); //加载mysq驱动
        } catch (ClassNotFoundException e) {
            System.out.println("驱动加载错误");
            e.printStackTrace();//打印出错详细信息
        }
        try {
            url = "jdbc:oracle:thin:@localhost:1521:oracle";//简单写法：url = "jdbc:myqsl://localhost/test(数据库名)? user=root(用户)&password=yqs2602555(密码)";
            user = "user_01";
            password = "123";
            conn = (Connection) DriverManager.getConnection(url, user, password);
            System.out.println("数据库链接成功");
            stmt = (Statement) conn.createStatement();


        } catch (SQLException e) {
            System.out.println("数据库链接错误");
            e.printStackTrace();

        }

    }
   /*
   函数名 view
   参数 无
   功能: 获得社团图片,社团名称,社团招新标语,社团等级,并按照社团等级降序排列
   返回值: 先判断rs中除标题外是否有值,有值则返回rs,没有值则返回null
    */
    public ResultSet viewCommunities()
    {

         sql = "select * from association  order by levell DESC ";
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    /*
        函数名 applyCommunity
        参数 studentID是用户id, communityID是社团id
        功能 根据用户id和社团id将用户对该社团的申请信息写入数据库,其余未提供信息如时间请自行补充
        返回值 无
         */
    public void applyCommunity(String studentID, String communityID) {
        String sql = "insert into join_apply(studentnum,associationnum) values('" + studentID + "','" + communityID + "')";
        try {
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
/*
功能:在申请建立社团表中插入一条数据
参数:所需信息
返回值:无

 */
    public void applyForEstablishCommunity(String stuID, String comName, String type, String reason,String plan ,String advantage, String contact)
    {
        sql = "insert into create_apply(studentNum,associationName,type,reason,plan,advantage,contact) " +
                "values("+"'"+stuID+"',"+"'"+comName+"',"+"'"+type+"',"+"'"+reason+"',"+"'"+plan+"',"+"'"+advantage+"',"+"'"+contact+"'"+")";
        try {
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }






    /*
    函数名 view
    参数 studentNum学号名（stu_choose_association表中）
    功能: 根据学号studentNum获得社团编号(associationNum) 社团名称(associationName)
    返回值: rs
    */
    public ResultSet selectFromStuAssociation(String studentNum) {
        ResultSet rs = null;
        String sql = "select a.associationnum, a.associationname\n" +
                "from stu_choose_association sca, association a\n" +
                "where a.studentnum='" + studentNum + "' or sca.studentnum='" + studentNum + "' and sca.associationnum=a.associationnum";
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /*函数名 selectUncheckedAssociations
    参数 无
    功能 获得所有state为unchecked的信息，包括create_apply表中所有内容和studentNum对应的studentName
    返回值: 返回rs
    */
    public ResultSet selectUncheckedAssociations() {
        ResultSet rs = null;
        String sql = "select s.studentname ,ca.*\n" +
                "from create_apply ca,student s\n" +
                "where ca.state='unchecked' and ca.studentnum=s.studentnum";
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /*
    函数名 selectCheckedAssociations
    参数 无
    功能 获得所有state为Passed和failed的信息，包括create_apply表中所有内容和studentNum对应的studentName
    返回值: 先判断rs中除标题外是否有值,有值则返回rs,没有值则返回null
     */
    public ResultSet selectCheckedAssociations() {
        ResultSet rs = null;
        String sql = "select s.studentname ,ca.*\n" +
                "from create_apply ca,student s\n" +
                "where ca.state in ('passed','failed') and ca.studentnum=s.studentnum";
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /*
    函数名 updateStatePass
    参数  studentNum,associationName （Create_apply表中的申请编号）
    功能 根据studentNum,associationName将它的state变为passed
    返回值 无
    */
    public void updateStatePass(String studentNum, String associationName) {
        String sql = "update create_apply\n" +
                "set state='passed'\n" +
                "where studentNum='" + studentNum + "' and associationname='" + associationName + "'";
        try {
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
   函数名 updateStateFail
   参数  studentNum,associationName （Create_apply表中的申请编号）
   功能 根据studentNum,associationName将它的state变为failed
   返回值 无
   */
    public void updateStateFail(String studentNum, String associationName) {
        String sql = "update create_apply\n" +
                "set state='failed'\n" +
                "where studentNum='" + studentNum + "' and associationname='" + associationName + "'";
        try {
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    函数名 deleteCreate_Apply
    参数 studentNum,associationName（Create_Apply表中）
    功能 根据studentNum,associationName删除Create_apply表中所有的信息
    返回值 无
    */
    public void deleteCreate_Apply(String studentNum, String associationName) {
        String sql = "delete from create_apply\n" +
                "where studentnum='" + studentNum + "' and associationname='" + associationName + "'";
        try {
            stmt.executeQuery(sql);
        } catch (SQLException e) {
        }
    }






    /*
        函数名：stuQuery（String account）
        参数：account 是账号信息，对应studentNum
        功能描述：通过给定的账号查询出个人信息
        */
    public ResultSet stuQuery(String account){
        ResultSet rs=null;
        String sql="select * from student where studentnum="+account+"";
        try {
            rs=stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  rs;
    }
    /*
    函数名：stuInsert(String account, String password)
    参数：account为账号，password为密码
    功能描述：用于注册时插入信息
    */
    public void stuInsert(String account, String password){
        String sql="insert into student(studentnum,password) values('" + account + "','" + password + "')";
        try {
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    函数名：stuinfoUpdate（String account, String password, String studentname,String sex, String school,int year,int month,int day, String address,String hobby,String slogan )
    参数：
    功能描述：用于个人信息的修改
    */
    public void stuinfoUpdate(String account, String studentname,String sex,String contact, String school,String date, String address,String hobby,String slogan){
        //String date=year+"-"+month+"-"+day;
        String sql="update student set studentname='"+studentname+"',sex='"+sex+"',contact='"+contact+"',school='"+school+"',birthday="+"to_date('"+date+"','YYYY-MM-DD'),address='"+address+"',hobby='"+hobby+"',slogan='"+slogan+"' where studentnum='"+account+"'";
        try {
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void stupwUpdate(String account,String password){
        String sql="update student set password='"+password+"'where studentnum='"+account+"'";
        try {
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    函数名：stuDelete(String account)
    参数：account为用户账号
    功能描述：删除账号
    */
    public void stuDelete(String account){
        String sql="delete from student where studentnum='"+account+"'";
        System.out.println(sql);
        try {
            stmt.executeQuery(sql);
            System.out.println("ok");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
