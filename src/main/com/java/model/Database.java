package com.java.model;

import java.sql.*;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Database {
    static Connection conn = null;
    static Statement stmt = null;
    public String sql = null;

    //连接数据库
    static {
        String url = null;
        String user = null;
        String password = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver"); //加载oracle驱动
        } catch (ClassNotFoundException e) {
            System.out.println("驱动加载错误");
            e.printStackTrace();//打印出错详细信息
        }
        try {
            url = "jdbc:oracle:thin:@localhost:1521:orcl";
            //"jdbc:mysql://localhost/orcl?user=scott&password=123456&useUnicode=true&&characterEncoding=gb2312&autoReconnect = true";
            // 简单写法：url = "jdbc:myqsl://localhost/test(数据库名)? user=root(用户)&password=yqs2602555(密码)";
            user = "scott";
            password = "123456";
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            System.out.print("数据库连接成功\n");
        } catch (SQLException e) {
            System.out.println("数据库链接错误");
            e.printStackTrace();
        }
    }

    /*
    函数名 applyCommunity
    参数 studentID是用户id, communityID是社团id
    功能 根据用户id和社团id将用户对该社团的申请信息写入数据库,其余未提供信息如时间请自行补充
    返回值 无
     */
    public void applyCommunity(String studentID, String communityID, String deparment, String hobby, String reason, String contact) {
        String sql = "insert into join_apply(studentnum,associationnum,department,hobby,reason,contact) values('" + studentID + "'," + communityID + ",'" + deparment + "','" + hobby + "','" + reason + "','" + contact + "')";
        try {
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    函数名：stuQuery（String account）
    参数：account 是账号信息，对应studentNum
    功能描述：通过给定的账号查询出个人信息
    */
    public ResultSet stuQuery(String account) {
        ResultSet rs = null;
        String sql = "select * from student where studentnum='" + account + "'";
        try {
            //  System.out.println(sql);
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /*
    函数名：stuinfoUpdate（String account, String password, String studentname,String sex, String school,int year,int month,int day, String address,String hobby,String slogan )
    参数：
    功能描述：用于个人信息的修改
    */
    public void stuinfoUpdate(String account, String studentname, String logo, String sex, String contact, String school, String date, String address, String hobby, String slogan) {
        //String date=year+"-"+month+"-"+day;
        String sql = "update student set studentname='" + studentname + "',sex='" + sex + "',contact='" + contact + "',school='" + school + "',birthday=" + "to_date('" + date + "','YYYY-MM-DD'),address='" + address + "',hobby='" + hobby + "',slogan='" + slogan + slogan + "',logo='" + logo + "' where studentnum='" + account + "'";
        try {
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    参数:无
    功能:查询所有的活动信息
    返回值:
     */
    public ResultSet viewActivities() {
        ResultSet rs = null;
        String sql = "select * from activity";
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /*
    参数:无
    功能:查询该活动的所有信息
    返回值:
    */
    public ResultSet getActivitiesByTitle(String title) {
        ResultSet rs = null;
        String sql = "select * from activity where title='" + title + "'";
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
        String sql = "select s.* ,ca.*\n" +
                "from create_apply ca,student s\n" +
                "where ca.state in ('passed','failed') and ca.studentnum=s.studentnum";
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void stupwUpdate(String account, String password) {
        String sql = "update student set password='" + password + "'where studentnum='" + account + "'";
        try {
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    函数名：stuInsert(String account, String password)
    参数：account为账号，password为密码
    功能描述：用于注册时插入信息
    */
    public void stuInsert(String account, String password) {
        String sql = "insert into student(studentnum,password) values('" + account + "','" + password + "')";
        try {
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    函数名：stuUpdate（String account, String password, String studentname,String sex, String school,int year,int month,int day, String address,String hobby,String slogan )
    参数：
    功能描述：用于个人信息的修改
    */
    public void stuUpdate(String account, String password, String studentname, String sex, String contact, String school, String year, String month, String day, String address, String hobby, String slogan) {
        String date = year + "-" + month + "-" + day;
        String sql = "update student set password='" + password + "',studentname='" + studentname + "',sex='" + sex + "',contact='" + contact + "',school='" + school + "',birthday=" + "to_date('" + date + "','YYYY-MM-DD'),address='" + address + "',hobby='" + hobby + "',slogan='" + slogan + "' where studentnum='" + account + "'";
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
    public void stuDelete(String account) {
        String sql = "delete from student where studentnum='" + account + "'";
        System.out.println(sql);
        try {
            stmt.executeQuery(sql);
            //  System.out.println("ok");
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
        String sql = "select sca.associationnum, a.associationname\n" +
                "from stu_choose_association sca, association a\n" +
                "where sca.studentnum='" + studentNum + "' and sca.associationnum=a.associationnum";
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
        String sql = "select s.* ,ca.*\n" +
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
    public ResultSet selectcheckedAssociations() {
        ResultSet rs = null;
        String sql = "select s.* ,ca.*\n" +
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
        String sql2 = "insert into  stu_choose_association(studentnum,associationnum,department,position) values ('" + studentNum + "',(select associationNum from association where studentnum='" + studentNum + "' and associationname='" + associationName + "'),'a','部长')";
        try {
            //System.out.println(sql2);
            stmt.executeQuery(sql2);
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
    参数:表的各个数据
    功能,向recruitment中插入一条数据
    返回值:无
    */
    public void addRecruitment(String title, String introduction, String propaganda, String demand, String examine, String time, String postcard, String publisher, String contact, String associationNum) {
        String sql = "insert into recruitment values ('" + title + "','" + introduction + "','" + propaganda + "','" + demand + "','" + examine + "','" + time + "','" + postcard + "','" + publisher + "','" + contact + "'," + associationNum + ")";
        try {
            System.out.println(sql);
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    参数:表的各个数据
    功能,向activity中插入一条数据
    返回值:无
    */
    public void addActivity(String title, String associationName, String time, String address, String introduction, String demand, String registrationTime, String publisher, String contact, String postcard) {
        String sql = "insert into activity values('" + title + "','" + associationName + "','" + time + "','" + address + "','" + introduction + "','" + demand + "','" + registrationTime + "','" + publisher + "','" + contact + "','" + postcard + "')";
        try {
            System.out.println(sql);
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    参数:社团id和学生职位
    功能:查询该社团该职位的所有学生
    返回值:学生姓名和学生id
    */
    public ResultSet getStuByPosition(String associationNum, String Position) {
        ResultSet rs = null;
        String sql = "select s.studentname ,s.studentnum\n" +
                "from stu_choose_association sca, student s\n" +
                "where sca.associationnum=" + associationNum + " and sca.position='" + Position + "' and sca.studentnum=s.studentnum";
        try {
            // System.out.println(sql);
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /*
    参数:表中各参数
    功能:插入一条数据到message
    返回值:无
    */
    public void addTask(String associationNum, String content, String sender, String receiver, String DDL, String filePath) {
        String sql = "insert into message values ('" + associationNum + "','" + content + "','" + sender + "','" + receiver + "','" + DDL + "','" + filePath + "')";
        try {
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    参数:无
    功能:查找所有的招新要求,按社团等级降序排列
    返回值:各个属性
    */
    public ResultSet viewRecruitment() {
        ResultSet rs = null;
        String sql = "select r.*\n" +
                "from recruitment r, association a\n" +
                "where r.associationnum=a.associationnum\n" +
                "order by a.level_ desc";
        try {
            System.out.println(sql);
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /*
    函数名 viewDetail
    参数 communityID是社团ID
    功能 根据社团id获得社团所有属性
    返回值: 先判断rs中除标题外是否有值,有值则返回rs,没有值则返回null
    */
    public ResultSet viewcompage(String communityID) {
        ResultSet rs = null;
        String sql = "select * from association where associationnum=" + communityID + "";
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /*
    参数 title
    功能 根据title获取招新的所有信息
    返回值: rs
    */
    public ResultSet getRecruitmentByTittle(String title) {
        ResultSet resultSet = null;
        String sql = "select * from recruitment where title='" + title + "'";
        try {
            resultSet = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    /*
    参数 社团ID
    功能 根据社团ID获取社团主页面上所有标题和内容
    返回值: rs
     */
    public ResultSet getCommunityMainInfo(String communityID) {
        ResultSet resultSet = null;
        String sql = "select title,content from page where associationnum=" + communityID + "";
        try {
            resultSet = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    /////////////////////////////////////////////////////
 /*
    参数:你自己看
    功能:插条数据到page
    返回值:无
    */
    public void addCommunityMainInfo(String communityID, String title, String contact) {
        String sql = "insert into page values(" + communityID + ",'" + title + "','" + contact + "')";
        try {
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    参数:你自己看
    功能:更新数据到page,只有content是要更新的
    返回值:无
    */
    public void deleteCommunityMainInfo(String communityID, String title) {
        String sql = "delete from page  where associationnum=" + communityID + " and title='" + title + "'";
        try {
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    参数:社团编号,标题1,内容1,标题2,内容2,背景图片,标题图片
    功能:更新相应的数据
    返回值:无
    */
    public void updateCommunityinfos(String communityID, String title1, String content1, String title2, String content2, String bgPic, String titlePic) {
        String sql = "update association set title1='" + title1 + "',content1='" + content1 + "',title2='" + title2 + "',content2='" + content2 + "',backgroundpicture='" + bgPic + "',headpicture='" + titlePic + "' where associationnum=" + communityID;
        try {
            System.out.println(sql);
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
   函数名：clubQuery（String associationNum)
   参数：associationNum是社团编号
   功能描述：通过给定社团编号获得社团名称，类型，级别，宗旨，成员，简述
   */
    public ResultSet clubQuery(String associationNum) {
        ResultSet rs = null;
        String sql = "select a.*,s.*\n" +
                "from association a,student s, stu_choose_association sca\n" +
                "where a.associationnum='" + associationNum + "' and a.associationnum=sca.associationnum and sca.studentnum=s.studentnum";
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;

    }


    /*
    函数名：clubUpdate（String associationNum，String associationName，String type, int level, String aim,String members,String biefInfo )
    参数：
    功能描述：根据社团编号对社团信息的修改
    */
    public void clubUpdate(String associationNum, String associationName, String type, String level, String aim, String members, String biefInfo) {
        String sql = "update association set associationName='" + associationName + "',type_='" + type + "',level_='" + level + "',aim='" + aim + "',membersdetail='" + members + "',introduction='" + biefInfo + "' where associationnum='" + associationNum + "'";
        try {
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /*
    函数名：clubDelete(String associationNum)
    参数：
    功能描述：删除社团，删除社团与学生的联系，但不能删除学生信息
    */
    public void clubDelete(String associationNum) {
        String sql = "delete from stu_choose_association where associationNum='" + associationNum + "'";
        System.out.println(sql);
        try {
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql2 = "delete from association where associationNum='" + associationNum + "'";
        System.out.println(sql2);
        try {
            stmt.executeQuery(sql2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    函数名：getBuZhang()
    参数：无
    功能描述：获取所有职位为部长的成员包括社团编号，个人账号，个人姓名，性别，所属部门
    */
    public ResultSet getBuZhang(String associationNum) {
        ResultSet rs = null;
        String sql = "select sca.associationnum,sca.studentnum,s.studentname,s.sex,sca.department\n" +
                "from stu_choose_association sca,student s\n" +
                "where sca.associationnum=" + associationNum + " and sca.position='部长' and sca.studentnum=s.studentnum";
        try {
            System.out.println(sql);
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /*
    函数名：getFuBu（）
    参数：
    功能描述：获取所有职位为副部的成员包括社团编号，个人账号，个人姓名，性别，所属部门
    */
    public ResultSet getFuBu(String associationNum) {
        ResultSet rs = null;
        String sql = "select sca.associationnum,sca.studentnum,s.studentname,s.sex,sca.department\n" +
                "from stu_choose_association sca,student s\n" +
                "where sca.associationnum=" + associationNum + " and sca.position='副部长' and sca.studentnum=s.studentnum";
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /*
    函数名：getBuWei()
    参数：
    功能描述：获取所有职位为部委的成员包括社团编号，个人账号，个人姓名，性别，所属部门
    */
    public ResultSet getBuWei(String associationNum) {
        ResultSet rs = null;
        String sql = "select sca.associationnum,sca.studentnum,s.studentname,s.sex,sca.department\n" +
                "from stu_choose_association sca,student s\n" +
                "where sca.associationnum=" + associationNum + " and sca.position='普通成员' and sca.studentnum=s.studentnum";
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /*
    函数名：toBuZhang(String clubNum,String studentNum)
    参数：社团编号和学生编号
    功能描述：将职位信息都改为部长
    */
    public void toBuZhang(String clubNum, String studentNum) {
        String sql = "update stu_choose_association set position='部长' where associationnum='" + clubNum + "' and studentnum='" + studentNum + "'";
        try {
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    函数名：toFuBu(String clubNum,String studentNum)
    参数：社团编号和学生编号
    功能描述：将职位信息都改为副部
    */
    public void toFuBu(String clubNum, String studentNum) {
        String sql = "update stu_choose_association set position='副部长' where associationnum='" + clubNum + "' and studentnum='" + studentNum + "'";
        try {
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    函数名：toBuWei(String clubNum,String studentNum)
    参数：社团编号和学生编号
    功能描述：将职位信息都改为部委
    */
    public void toBuWei(String clubNum, String studentNum) {
        String sql = "update stu_choose_association set position='普通成员' where associationnum='" + clubNum + "' and studentnum='" + studentNum + "'";
        try {
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    函数名：deleteMem(String clubNum,String studentNum )
    参数：社团编号和学生编号
    功能描述：删除社团成员表信息，不删除学生表信息
    */
    public void deleteMem(String clubNum, String studentNum) {
        String sql = "delete from stu_choose_association where associationnum='" + clubNum + "' and studentnum='" + studentNum + "'";
        try {
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /*
    * 函数名：selevtUncheckedJoin
    * 参数：associaitionNum(社团编号)
    * 功能：获得join_apply和student表中state=unchecked的信息：包括如图
    */
    public ResultSet seleteUncheckedJoin(String associationNum) {
        ResultSet rs = null;
        String sql = "select s.*,ja.*\n" +
                "from join_apply ja,student s\n" +
                "where ja.associationNum=" + associationNum + " and ja.state='unchecked' and ja.studentnum=s.studentnum";
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /*
    * 函数名：selevtUncheckedJoin
    * 参数：associaitionNum(社团编号)
    * 功能：获得join_apply和student中state=passed or failed的信息：包括如图
    */
    public ResultSet seleteCheckedJoin(String associationNum) {
        ResultSet rs = null;
       String sql = "select s.*,ja.*\n" +
                "from join_apply ja,student s\n" +
                "where ja.associationNum=" + associationNum + " and ja.state in ('passed','failed') and ja.studentnum=s.studentnum";
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /*
    * 函数名：joinStatePass
    * 参数：associaitionNum(社团编号) studentNum(学号) （表join_apply里）
    * 功能：将参数所对应的state改为passed.
    */
    public void joinStatePass(String studentNum, String associationNum) {
        String sql = "update join_apply set state='passed' \n" +
                "where ASSOCIATIONNUM=" + associationNum + " and STUDENTNUM=" + studentNum + "";
        try {
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /*
    * 函数名：joinStateFail
    * 参数：associaitionNum(社团编号) studentNum(学号) （表join_apply里）
    * 功能：将参数所对应的state改为failed.
    */
    public void joinStateFail(String studentNum, String associationNum) {
        String sql = "update join_apply set state='failed' \n" +
                "where ASSOCIATIONNUM=" + associationNum + " and STUDENTNUM=" + studentNum + "";
        try {
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    * 函数名：deleteJoin_Apply
    * 参数：associaitionNum(社团编号) studentNum(学号) （表join_apply里）
    * 功能：删除参数对应信息
    */
    public void deleteJoin_Apply(String studentNum, String associatioNum) {
        String sql = "delete from join_apply where studentnum='" + studentNum + "' and associationnum=" + associatioNum + "";
        try {
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    * 函数名：selectFunds
    * 参数：associaitionNum(社团编号) （表bill）
    * 功能：获得表中所有信息+association表中对应的money
    */
    public ResultSet selectFunds(String associationNum) {
        ResultSet rs = null;
        String sql = "select b.*,a.money\n" +
                "from bill b, association a\n" +
                "where b.associationnum=" + associationNum + " and b.associationnum=a.associationnum";
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /*
    * 函数名：addFundsInfo
    * 参数：如下
    * 功能：添加到表bill（currentMoney对应previousMoney（历史余额））
    */
    public void addFundsInfo(String associationnum, String person, String cost, String income, String currenMoney, String date, String remark) {
        String sql = "insert into bill values(nvl((select max(order_) from bill),0)+1,'" + associationnum + "',to_date('" + date + "','YYYY-MM-DD'),'" + income + "','" + cost + "','" + currenMoney + "','" + person + "','" + remark + "')";
        try {
            System.out.println(sql);
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    * 函数名：getMoney
    * 参数：associationNum
    * 功能：获得association表中的Money,associationNum
    */
    public ResultSet getMoney(String associationNum) {
        ResultSet rs = null;
        String sql = "select money from association where associationnum='" + associationNum + "'";
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }


    /*
    函数名：getTasks(String stuNum,String state)
    参数：stuNum学生编号,state任务状态
    功能描述：通过stuNum和state获得taskNum;clubNum;clubName;senderNum; senderName;title;content;ddl;sendTime; state; filePath;
    */
    public ResultSet getTasks(String stuNum, String state) {
        ResultSet rs = null;
        String sql = "select m.messageNum,a.associationNum,a.associationName,s.studentnum,s.studentname,m.title,m.content,m.ddl,m.publishtime,sm.state,m.filepath\n" +
                "from stu_message sm,message m,association a,student s\n" +
                "where sm.receiver='" + stuNum + "' and sm.state='" + state + "' and sm.messageNum=m.messageNum and m.associationnum=a.associationnum and m.sender=s.studentnum";
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /*
    函数名：toFinished(String taskNum,String studentNum)
    参数：taskNum是任务编号
    功能描述：把任务编号为taskNum的状态改为已完成，对应4
    */
    public void toFinished(String taskNum, String studentNum) {
        String sql = "update stu_message set state='4' where receiver='" + studentNum + "' and messageNum='" + taskNum + "'";
        try {
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
函数名：changeState(String stuNum)
参数：stuNum是学生编号
功能描述：把学生编号为stuNum的状态根据当前时间和ddl更改状态，超过ddl则更改为超时，对应3，距离ddl少于等于三天改为1
*/
    public void changeState(String stuNum) {
        ResultSet rs = null;
        String sql = "select m.MESSAGENUM,m.ddl,sm.state\n" +
                "from stu_message sm, message m\n" +
                "where sm.receiver='" + stuNum + "' and sm.messageNum=m.messageNum";
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (rs.next()) {
                String messageNum = rs.getString(1);
                String ddltime = rs.getString(2);
                String state = rs.getString(3);
                String currenttime = null;
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date currentdate = null;
                Date ddl = null;
                Date date = new Date();
                try {
                    ddl = dateFormat.parse(ddltime);
                    currenttime = dateFormat.format(date);
                    currentdate = dateFormat.parse(currenttime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (!state.equals("4") && ddl.before(currentdate)) {
                    Statement st = null;
                    st = conn.createStatement();
                    String sql2 = "update stu_message set state='3' where messageNum='" + messageNum + "' and receiver='" + stuNum + "'";
                    st.executeQuery(sql2);
                } else if (!state.equals("4") && (ddl.getTime() - currentdate.getTime()) < 3 * 24 * 60 * 60 * 1000) {
                    Statement st = null;
                    st = conn.createStatement();
                    String sql3 = "update stu_message set state='1' where messageNum='" + messageNum + "' and receiver='" + stuNum + "'";
                    st.executeQuery(sql3);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    * 函数名：seleteCheckedAc
    * 参数：associationNum
    * 功能：获得activity、activity_apply、student表中state=checked的以上内容
    *
    */
    public ResultSet seleteCheckedAc(String associationNum) {
        ResultSet rs = null;
        String sql = "select s.*,a.*,ac.*,aa.*\n" +
                "from student s,association a,activity ac,activity_apply aa\n" +
                "where aa.state in('passed','failed') and aa.associationname=(select associationname from association where associationnum= " + associationNum + ") and aa.associationname=ac.associationname and aa.associationname=a.associationname and aa.studentnum=s.studentnum";
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /*
    * 函数名：seleteUncheckedAc
    * 参数：associationNum
    * 功能：获得activity、act 223332222222ivity_apply、student表中state=unchecked的以上内容
    */
    public ResultSet seleteUncheckedAc(String associationNum) {
        ResultSet rs = null;
        String sql = "select s.*,a.*,ac.*,aa.*\n" +
                "from student s,association a,activity ac,activity_apply aa\n" +
                "where aa.state='unchecked'  and aa.associationname=(select associationname from association where associationnum='" + associationNum + "') and aa.associationname=ac.associationname and aa.associationname=a.associationname and aa.studentnum=s.studentnum";
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /*
    * 函数名：updateActPass
    * 参数：studentNum  title
    * 功能：将activity_apply表里对应的state值该为passed
    */
    public void updateActPass(String studentNum, String title, String associationName) {
        String sql = "update activity_apply set state='passed' where studentnum='" + studentNum + "' and title='" + title + "' and associationname='" + associationName + "'";
        try {
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    * 函数名：updateActFail
    * 参数：studentNum  title
    * 功能：将activity_apply表里对应的state值该为failed
    */
    public void updateActFail(String studentNum, String title, String associationName) {
        String sql = "update activity_apply set state='failed' where studentnum='" + studentNum + "' and title='" + title + "' and associationname='" + associationName + "'";
        try {
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    * 函数名：deleteAct
    * 参数：studentNum  title
    * 功能：删除activity_apply表里的参数对应的信息
    */
    public void deleteAct(String studentNum, String title, String associationName) {
        String sql = "delete from activity_apply where studentnum='" + studentNum + "' and title='" + title + "' and associationname='" + associationName + "'";
        try {
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
   函数名：deleteTask(String stuNum)
   参数：stuNum是学生编号
   功能描述：当任务变成已完成或超时开始计时，时间达到30天从数据库删除该任务
   */
    public void deleteTask(String stuNum) {
        ResultSet rs = null;
        String sql = "select ddl,messagenum \n" +
                "from message\n" +
                "where messagenum in(select messagenum from stu_message where state in('3','4') and receiver='" + stuNum + "' )";
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (rs.next()) {
                String ddltime = rs.getString(1);
                String messagenum = rs.getString(2);
                String currenttime = null;
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date currentdate = null;
                Date ddl = null;
                Date date = new Date();
                try {
                    ddl = dateFormat.parse(ddltime);
                    currenttime = dateFormat.format(date);
                    currentdate = dateFormat.parse(currenttime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                System.out.println(currentdate);
                System.out.println(ddl);
                if ((currentdate.getTime() - ddl.getTime()) > 30 * 24 * 60 * 60 * 1000) {
                    Statement st = null;
                    st = conn.createStatement();
                    String sql2 = "delete from message where messagenum='" + messagenum + "'";
                    st.executeQuery(sql2);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    函数名 selectPersonalCreate  //获得我创建的社团
    参数 studentNum,
    功能 根据studentNum,获得stu_choose_association表中的associationName和associationNum
    返回值
    */
    public ResultSet selectPersonalCreate(String studentNum) {
        ResultSet rs = null;
        String sql = "select a.associationname,a.associationnum\n" +
                "from stu_choose_association sca, association a\n" +
                "where sca.studentnum='" + studentNum + "'  and sca.position='部长' and sca.associationnum=a.associationnum";
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }


    /*
     函数名 selectPersonalManage //获得我管理的社团
     参数 studentNum,
     功能 根据studentNum,获得stu_choose_association表中的associationName和associationNum;
     返回值 无
     */
    public ResultSet selectPersonalManage(String studentNum) {
        ResultSet rs = null;
        String sql = "select a.associationname,a.associationnum\n" +
                "from stu_choose_association sca, association a\n" +
                "where sca.studentnum='" + studentNum + "'  and sca.position in ('部长','副部') and sca.associationnum=a.associationnum";
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }


    /*
    * 函数名：deleteInStuChooseAc
    * 参数：studentNum  associationName
    * 功能：删除stu_choose_activity表里的参数对应的信息
    */
    public void deleteInStuChooseAc(String studentNum, String associationName) {
        String sql = "delete from stu_choose_activity where studentnum='" + studentNum + "' and associationname='" + associationName + "'";
        try {
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    * 函数名：deleteInAct_Apply
    * 参数：studentNum  associationName
    * 功能：删除activity_apply表里的参数对应的信息
    */
    public void deleteInAct_Apply(String studentNum, String associationName) {
        String sql = "delete from activity apply where studentnum='" + studentNum + "' and associationname='" + associationName + "'";
        try {
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    * 函数名：deleteInStuChoAsso
    * 参数：studentNum  associationNum
    * 功能：删除stu_choose_association表里的参数对应的信息
    */
    public void deleteInStuChoAsso(String studentNum, String associationNum) {
        String sql = "delete from stu_choose_association where studentnum='" + studentNum + "' and associationnum='" + associationNum + "'";
        try {
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    * 函数名：deleteInAssociation
    * 参数：studentNum  associationNum
    * 功能：删除Association表里的参数对应的信息
    */
    public void deleteInAssociation(String studentNum, String associationNum) {
        String sql = "delete from association where associationnum='" + associationNum + "' and studentnum='" + studentNum + "'";
        try {
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    功能:在申请加入社团活动表中插入一条数据
    参数:所需信息
    返回值:无
    */
    public void addapplyforactivity(String studentNum, String grade, String title, String associationName, String hobby, String reason) {
        String sql = "insert into activity_apply(studentnum,grade,title,associationname,hobby,reason) values('" + studentNum + "','" + grade + "','" + title + "','" + associationName + "','" + hobby + "','" + reason + "')";
        try {
            System.out.println(sql);
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
    public void applyForEstablishCommunity(String studentNum, String associationName, String level, String type, String aim, String logo, String address, String Plan, String introduction, String advantage, String contact, String member1, String briefintro1, String member2, String briefintro2, String member3, String briefintro3, String member4, String briefintro4, String member5, String briefintro5) {
        String sql = null;
        sql = "INSERT INTO CREATE_APPLY (STUDENTNUM, ASSOCIATIONNAME, LEVEL_, TYPE, AIM, LOGO, ADDRESS, PLAN_, INTRODUCTION, ADVANTAGE, CONTACT, MEMBER1, BRIEFINTRO1, MEMBER2, BRIEFINTRO2, MEMBER3, BRIEFINTRO3, MEMBER4, BRIEFINTRO4, MEMBER5, BRIEFINTRO5) VALUES ('" + studentNum + "', '" + associationName + "', '" + level + "', '" + type + "', '" + aim + "', '" + logo + "', '" + address + "', '" + Plan + "', '" + introduction + "', '" + advantage + "', '" + contact + "', '" + member1 + "', '" + briefintro1 + "', '" + member2 + "', '" + briefintro2 + "', '" + member3 + "', '" + briefintro3 + "', '" + member4 + "', '" + briefintro4 + "', '" + member5 + "', '" + briefintro5 + "')";
        try {
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    功能:在message和stu_message中插入数据
    参数:所需信息
    返回值:无
    */
    public void addAssignment(String associationNum, String title, String content, String sender, String DDL, String filepath, String[] receivers) {
        String sql = "insert into message(messagenum,associationnum,title,content,sender,ddl,filepath) values(nvl((select max(messageNum) from message),0)+1,'" + associationNum + "','" + title + "','" + content + "','" + sender + "',to_date('" + DDL + "','YYYY-MM-DD'),'" + filepath + "')";
        System.out.println(sql);
        try {
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (String a : receivers) {
            Statement st = null;
            try {
                st = conn.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String sql2 = "insert into stu_message(messagenum,receiver) values((select messagenum from message where associationnum='" + associationNum + "' and title='" + title + "' and rownum=1),'" + a + "')";
            try {
                st.executeQuery(sql2);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }


    public ResultSet getposition(String studentnum, String associationnum) {
        ResultSet rs = null;
        String sql = "select * from stu_choose_association where studentnum='" + studentnum + "' and associationnum= " + associationnum + "  and position='普通成员'";
        try {
            System.out.println(sql);
          rs =   stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}