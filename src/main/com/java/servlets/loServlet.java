package com.java.servlets;
/*
功能:注册/登录的处理
创建者:李兰
修改者:黄友明
 */

import com.java.control.Databaseco;
import com.java.model.Database;
import com.java.model.Student;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class loServlet extends HttpServlet {
   // ResultSet rs=null;
   // DBTest DB = new DBTest();//用于连接数据库
     // Database DB=new Database();
    // ArrayList<User> suserlist;用于保存查询数据
    // String sqlsearch="select * from student";用于编写查询语句
public Databaseco databaseco = new Databaseco();







    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        //get which method to be used
        String methodName = request.getParameter("method");
        try {
            Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);//获得方法
            method.setAccessible(true);
            method.invoke(this, request, response);//执行方法
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //以上为通用代码
    }
    protected void register(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
       // System.out.println("调用了register方法");
        //需要获取的数据是用户电话号码（账号），密码
        String account = (String) request.getParameter("jaccount");//account是传过来的用户账号name属性
       // System.out.println(account+"accout");
        String password=(String)request.getParameter("jpassword");

        // 验证原表中是否已存在相同的账号,存在则返回true，不存在则返回false
         if(databaseco.checkRegister(account,password))
       // if(account=="123")
        {
            response.getWriter().println("账号已存在");
        }
        else
        {
           // response.getWriter().println("false");
        }

    }
protected void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
{
    //获取表单传过来的数据
    String account = request.getParameter("jaccount");
    String password = request.getParameter("jpassword");
    String yanzhengpw  =  databaseco.getPwdByStuName(account);
    if(yanzhengpw.equals(password.trim()))
    {

        //可以实现界面跳转
        //request.setAttribute("accounttologin", account);//传递了账号信息
        Student student = databaseco.getStuByStuID(account);
        request.getSession().setAttribute("loginStudent",student);
        //response.getWriter().println("登录成功");
        //request.getRequestDispatcher("").forward(request, response);
       // response.sendRedirect("/mainpages/afterlogin.jsp");
    }
    else
    {
        response.getWriter().println("密码错误");
        //提示密码输入错误
        //发送提示信息到login页面
       // String warn2="account or password error";
      //  returnwarn(warn2,"/mainpages/login.jsp",request,response);
    }

}
protected void showselfInfo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
{
    /*String accounttoInfo=(String)request.getParameter("accountflogin");
    try{
        rs=DB.stuQuery(accounttoInfo);//Query为用户表的查询函数
        //将查询结果传输到information界面
        while(rs.next()) {
            String nametoInfo = rs.getString("studentname");
            String sextoInfo = rs.getString("sex");
            String schooltoInfo = rs.getString("school");
            String birthdaytoInfo = rs.getString("birthday");
            String addresstoInfo = rs.getString("address");
            String hobbytoInfo = rs.getString("hobby");
            String slogantoInfo = rs.getString("slogan");
            request.setAttribute("accounttoInfo", accounttoInfo);//传递了账号信息
            request.setAttribute("nametoInfo", nametoInfo);//传递了昵称信息
            request.setAttribute("sextoInfo", sextoInfo);
            request.setAttribute("schooltoInfo", schooltoInfo);
            request.setAttribute("birthdaytoInfo", birthdaytoInfo);
            request.setAttribute("addresstoInfo", addresstoInfo);
            request.setAttribute("hobbytoInfo", hobbytoInfo);
            request.setAttribute("slogantoInfo", slogantoInfo);
            request.getRequestDispatcher("/SelfInfo/personal.jsp").forward(request, response);
        }
    }
    catch (SQLException e) {
        System.out.println("数据操作错误");
        e.printStackTrace();
    }*/
}
protected void changeIm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
{
Student student = (Student) request.getSession().getAttribute("loginStudent");

student.setName(request.getParameter("Infoname"));
student.setSex(request.getParameter("Infosex"));
student.setContact(request.getParameter("Infocontact"));
student.setSchool( request.getParameter("Infoschool"));
student.setBirthday(request.getParameter("Infobirthday"));
student.setAddress( request.getParameter("Infoaddress"));
student.setHobby(request.getParameter("Infohobby"));
student.setSlogan(request.getParameter("Infoslogan"));
student.setLogo((request.getParameter("infologo")));
request.getSession().setAttribute("loginStudent",student);
databaseco.UpdateStu(student);
/*
// 获取从information得到的更改后数据
    String accountfInfo = request.getParameter("Infoaccount");
    String namefInfo = request.getParameter("Infoname");
    String sexfInfo = request.getParameter("Infosex");
    String contactfInfo = request.getParameter("Infocontact");
    String schoolfInfo = request.getParameter("Infoschool");
    String birthdayfInfo = request.getParameter("Infobirthday");
    String addressfInfo = request.getParameter("Infoaddress");
    String hobbyfInfo = request.getParameter("Infohobby");
    String sloganfInfo = request.getParameter("Infoslogan");
    System.out.println(accountfInfo+"更改了数据库");
    //其他数据
    //对输入数据合法性的验证
    //code is here
    String password="00000";
    String year="1998";
    String month="01";
    String day="25";
    DB.stuinfoUpdate(accountfInfo,namefInfo,sexfInfo,contactfInfo,schoolfInfo,year,month,day,addressfInfo,hobbyfInfo,sloganfInfo);//数据库更新函数
    System.out.println("更改了数据库");
    try {
        rs = DB.stuQuery(accountfInfo);

        while(rs.next())
        {
            String nametoInfo = rs.getString("studentname");
            String sextoInfo = rs.getString("sex");
            String contacttoInfo = rs.getString("contact");
            String schooltoInfo = rs.getString("school");
            String birthdaytoInfo = rs.getString("birthday");
            String addresstoInfo = rs.getString("address");
            String hobbytoInfo = rs.getString("hobby");
            String slogantoInfo = rs.getString("slogan");
            System.out.println(contacttoInfo);
            request.setAttribute("accounttoInfo", accountfInfo);//传递了账号信息
            request.setAttribute("nametoInfo", nametoInfo);//传递了昵称信息
            request.setAttribute("sextoInfo", sextoInfo);
            request.setAttribute("contacttoInfo", contacttoInfo);
            request.setAttribute("schooltoInfo", schooltoInfo);
            request.setAttribute("birthdaytoInfo", birthdaytoInfo);
            request.setAttribute("addresstoInfo", addresstoInfo);
            request.setAttribute("hobbytoInfo", hobbytoInfo);
            request.setAttribute("slogantoInfo", slogantoInfo);
        }

    }
    catch (SQLException e) {
        System.out.println("数据操作错误");
        e.printStackTrace();
    }
*/

   // System.out.print(methodName + "is information" + "\n");
   // request.getRequestDispatcher("/SelfInfo/personal.jsp").forward(request, response);

   response.sendRedirect("/myImformation/myImformation.jsp");
}
    protected void changepwd(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String oldpassword=request.getParameter("joldpassword");
        String newpassword1 = request.getParameter("jpassword");
        Student student = (Student) request.getSession().getAttribute("loginStudent");
        String yanzhengpw = databaseco.getPwdByStuID(student.getId());
        if (oldpassword.trim().equals(yanzhengpw)) {
            databaseco.UpdatePwdByStuID(student.getId(), newpassword1);
            response.getWriter().println("密码修改成功");
        }
        else
        {
            response.getWriter().println("密码修改失败");
        }
    }


   /* try {
        rs=DB.stuQuery(pwaccount);
        while(rs.next())
        {
            String yanzhengpw=rs.getString("password");

            if(oldpassword.trim().equals(yanzhengpw))//确保括号中不会为空值
            {
                System.out.println(yanzhengpw+"yangzhengpwxiugai");
                if(newpassword1.trim().length() < 6)
                {
                    warn="password should not be less than 6!";
                    returnwarn(warn,"/SelfInfo/personal.jsp",request,response);

                }
                else if(newpassword1.trim().length()>18)
                {
                    warn="password should not be more than 18!";
                    returnwarn(warn,"/SelfInfo/personal.jsp",request,response);
                }
                else if(newpassword1.trim().equals(newpassword2.trim()))
                {
                    //更新密码
                    DB.stupwUpdate(pwaccount,newpassword1);;//Update为更新函数
                    warn="password has been changed successfully!";
                    try {
                        rs = DB.stuQuery(pwaccount);

                        while(rs.next())
                        {
                            String nametoInfo = rs.getString("studentname");
                            String sextoInfo = rs.getString("sex");
                            String contacttoInfo = rs.getString("contact");
                            String schooltoInfo = rs.getString("school");
                            String birthdaytoInfo = rs.getString("birthday");
                            String addresstoInfo = rs.getString("address");
                            String hobbytoInfo = rs.getString("hobby");
                            String slogantoInfo = rs.getString("slogan");
                            System.out.println(contacttoInfo);
                            request.setAttribute("accounttoInfo", pwaccount);//传递了账号信息
                            request.setAttribute("nametoInfo", nametoInfo);//传递了昵称信息
                            request.setAttribute("sextoInfo", sextoInfo);
                            request.setAttribute("contacttoInfo", contacttoInfo);
                            request.setAttribute("schooltoInfo", schooltoInfo);
                            request.setAttribute("birthdaytoInfo", birthdaytoInfo);
                            request.setAttribute("addresstoInfo", addresstoInfo);
                            request.setAttribute("hobbytoInfo", hobbytoInfo);
                            request.setAttribute("slogantoInfo", slogantoInfo);
                            request.getRequestDispatcher("/SelfInfo/personal.jsp").forward(request, response);
                        }

                    }
                    catch (SQLException e) {
                        System.out.println("数据操作错误");
                        e.printStackTrace();
                    }

                    returnwarn(warn,"/SelfInfo/personal.jsp",request,response);
                }
                else
                {
                    warn="the two password you typed do not match!";
                    returnwarn(warn,"/SelfInfo/personal.jsp",request,response);
                }

            }
            else
            {
                //提示密码输入错误
                //发送提示信息到login页面
                warn="original password error!";
                request.getRequestDispatcher("/SelfInfo/personal.jsp").forward(request, response);

            }
        }*/

    /*}
    catch (SQLException e) {
        System.out.println("数据操作错误");
        e.printStackTrace();
    }*/



       /* String methodName = request.getParameter("do");

        if (methodName.equals("register"))//针对注册提交表单的处理
        {
            //添加操作

            //需要获取的数据是用户电话号码（账号），密码
            String account = (String) request.getParameter("regaccount");//account是传过来的用户账号name属性
            String password1 = (String) request.getParameter("regpassword1");
            String password2 = (String) request.getParameter("regpassword2");
            System.out.println(account+"zhuce");
            System.out.println(password1+"zhuce");

            //验证密码是否符合要求,即密码长度在6~18位，是否包含中文
            //code
            String warn;
            Pattern pat = Pattern.compile("[\u4e00-\u9fa5]");
            Matcher matcher = pat.matcher(password1);
            if (matcher.find()) {
                warn = "the password can't contain Chinese!";
                System.out.println(warn);
                returnwarn(warn,"/SelfInfo/login.jsp",request,response);
                //returnwarn为自定义函数
            }
            else if (password1.length() < 6) {
                warn = "密码不能少于6位";
                returnwarn(warn,"/SelfInfo/login.jsp",request,response);
            } else if (password1.length() > 18) {
                warn = "密码不能多于18位";
                returnwarn(warn,"/SelfInfo/login.jsp",request,response);
            }
            else if(!password2.equals(password1))
            {
                warn = "两次输入的密码不一致";
                returnwarn(warn,"/SelfInfo/login.jsp",request,response);

            }
            // 验证原表中是否已存在相同的账号
            try{
                rs= DB.stuQuery(account);
                if(rs.next())
                {

                    //发送提示信息到register页面，想
                    warn="the same account already exists";
                    System.out.println(warn);
                    returnwarn(warn,"/SelfInfo/login.jsp",request,response);

                }
                else {
                    //插入
                    DB.stuInsert(account,password1);//数据库插入函数
                    System.out.print(methodName + "is add" + "\n");
                    //插入成功后跳转到登陆界面，想
                    request.getRequestDispatcher("/SelfInfo/login.jsp").forward(request, response);

                }
            }
            catch (SQLException e) {
                System.out.println("数据操作错误");
                e.printStackTrace();
            }



        }


        if (methodName.equals("login"))//针对登录表单的处理
        {


            //获取表单传过来的数据
            String account = (String)request.getParameter("account");
            String password = (String)request.getParameter("password");
            System.out.println(account);
            System.out.println(password);
            try{
                rs = DB.stuQuery(account);

                while(rs.next()) {
                   String yanzhengpw = rs.getString("password");
                   System.out.println(yanzhengpw);
                    if(yanzhengpw.equals(password.trim()))
                    {
                        //可以实现界面跳转
                        request.setAttribute("accounttologin", account);//传递了账号信息
                        request.getRequestDispatcher("/SelfInfo/mine.jsp").forward(request, response);

                    }
                    else
                    {
                        //提示密码输入错误
                        //发送提示信息到login页面
                        String warn2="account or password error";
                        returnwarn(warn2,"/SelfInfo/login.jsp",request,response);
                    }
                }
            }
            catch (SQLException e) {
                System.out.println("数据操作错误");
                e.printStackTrace();
            }
        }
        //李兰 2018/7/4
        if (methodName.equals("showselfInfo"))//接收来自主界面信息,并以该账号为依据查询，将查询结果传给information界面
        {

            String accounttoInfo=(String)request.getParameter("accountflogin");
            try{
                rs=DB.stuQuery(accounttoInfo);//Query为用户表的查询函数
                //将查询结果传输到information界面
                while(rs.next()) {
                    String nametoInfo = rs.getString("studentname");
                    String sextoInfo = rs.getString("sex");
                    String schooltoInfo = rs.getString("school");
                    String birthdaytoInfo = rs.getString("birthday");
                    String addresstoInfo = rs.getString("address");
                    String hobbytoInfo = rs.getString("hobby");
                    String slogantoInfo = rs.getString("slogan");
                    request.setAttribute("accounttoInfo", accounttoInfo);//传递了账号信息
                    request.setAttribute("nametoInfo", nametoInfo);//传递了昵称信息
                    request.setAttribute("sextoInfo", sextoInfo);
                    request.setAttribute("schooltoInfo", schooltoInfo);
                    request.setAttribute("birthdaytoInfo", birthdaytoInfo);
                    request.setAttribute("addresstoInfo", addresstoInfo);
                    request.setAttribute("hobbytoInfo", hobbytoInfo);
                    request.setAttribute("slogantoInfo", slogantoInfo);
                    request.getRequestDispatcher("/SelfInfo/personal.jsp").forward(request, response);
                }
            }
            catch (SQLException e) {
                System.out.println("数据操作错误");
                e.printStackTrace();
            }

        }


        //可修改信息，性别、密码
        //由于密码修改需要输入原密码，所以将密码修改与其他基本信息的修改分开
        if (methodName.equals("information"))//针对个人基本信息修改的处理
        {
            // 获取从information得到的更改后数据
            String accountfInfo = request.getParameter("Infoaccount");
            String namefInfo = request.getParameter("Infoname");
            String sexfInfo = request.getParameter("Infosex");
            String contactfInfo = request.getParameter("Infocontact");
            String schoolfInfo = request.getParameter("Infoschool");
            String birthdayfInfo = request.getParameter("Infobirthday");
            String addressfInfo = request.getParameter("Infoaddress");
            String hobbyfInfo = request.getParameter("Infohobby");
            String sloganfInfo = request.getParameter("Infoslogan");
            System.out.println(accountfInfo+"更改了数据库");
            //其他数据
            //对输入数据合法性的验证
            //code is here
            String password="00000";
            String year="1998";
            String month="01";
            String day="25";
            DB.stuinfoUpdate(accountfInfo,namefInfo,sexfInfo,contactfInfo,schoolfInfo,year,month,day,addressfInfo,hobbyfInfo,sloganfInfo);//数据库更新函数
            System.out.println("更改了数据库");
            try {
                rs = DB.stuQuery(accountfInfo);

                while(rs.next())
                {
                    String nametoInfo = rs.getString("studentname");
                    String sextoInfo = rs.getString("sex");
                    String contacttoInfo = rs.getString("contact");
                    String schooltoInfo = rs.getString("school");
                    String birthdaytoInfo = rs.getString("birthday");
                    String addresstoInfo = rs.getString("address");
                    String hobbytoInfo = rs.getString("hobby");
                    String slogantoInfo = rs.getString("slogan");
                    System.out.println(contacttoInfo);
                    request.setAttribute("accounttoInfo", accountfInfo);//传递了账号信息
                    request.setAttribute("nametoInfo", nametoInfo);//传递了昵称信息
                    request.setAttribute("sextoInfo", sextoInfo);
                    request.setAttribute("contacttoInfo", contacttoInfo);
                    request.setAttribute("schooltoInfo", schooltoInfo);
                    request.setAttribute("birthdaytoInfo", birthdaytoInfo);
                    request.setAttribute("addresstoInfo", addresstoInfo);
                    request.setAttribute("hobbytoInfo", hobbytoInfo);
                    request.setAttribute("slogantoInfo", slogantoInfo);
                }

            }
            catch (SQLException e) {
                System.out.println("数据操作错误");
                e.printStackTrace();
            }

            System.out.print(methodName + "is information" + "\n");
           request.getRequestDispatcher("/SelfInfo/personal.jsp").forward(request, response);
     }

      if(methodName.equals("changepw"))//针对密码的修改
        {
            String warn;
            //修改密码时需要同时显示账号、需要输入原始密码、需要输入新密码、需要确认新密码
            String pwaccount=request.getParameter("pwaccount");//account是传过来的用户账号name属性
            String oldpassword=request.getParameter("oldpassword");
            String newpassword1=request.getParameter("newpassword1");
            String newpassword2=request.getParameter("newpassword2");
            try {
                rs=DB.stuQuery(pwaccount);
              while(rs.next())
              {
                  String yanzhengpw=rs.getString("password");

                if(oldpassword.trim().equals(yanzhengpw))//确保括号中不会为空值
                {
                    System.out.println(yanzhengpw+"yangzhengpwxiugai");
                    if(newpassword1.trim().length() < 6)
                    {
                        warn="password should not be less than 6!";
                        returnwarn(warn,"/SelfInfo/personal.jsp",request,response);

                    }
                    else if(newpassword1.trim().length()>18)
                    {
                        warn="password should not be more than 18!";
                        returnwarn(warn,"/SelfInfo/personal.jsp",request,response);
                    }
                    else if(newpassword1.trim().equals(newpassword2.trim()))
                    {
                        //更新密码
                        DB.stupwUpdate(pwaccount,newpassword1);;//Update为更新函数
                        warn="password has been changed successfully!";
                        try {
                            rs = DB.stuQuery(pwaccount);

                            while(rs.next())
                            {
                                String nametoInfo = rs.getString("studentname");
                                String sextoInfo = rs.getString("sex");
                                String contacttoInfo = rs.getString("contact");
                                String schooltoInfo = rs.getString("school");
                                String birthdaytoInfo = rs.getString("birthday");
                                String addresstoInfo = rs.getString("address");
                                String hobbytoInfo = rs.getString("hobby");
                                String slogantoInfo = rs.getString("slogan");
                                System.out.println(contacttoInfo);
                                request.setAttribute("accounttoInfo", pwaccount);//传递了账号信息
                                request.setAttribute("nametoInfo", nametoInfo);//传递了昵称信息
                                request.setAttribute("sextoInfo", sextoInfo);
                                request.setAttribute("contacttoInfo", contacttoInfo);
                                request.setAttribute("schooltoInfo", schooltoInfo);
                                request.setAttribute("birthdaytoInfo", birthdaytoInfo);
                                request.setAttribute("addresstoInfo", addresstoInfo);
                                request.setAttribute("hobbytoInfo", hobbytoInfo);
                                request.setAttribute("slogantoInfo", slogantoInfo);
                                request.getRequestDispatcher("/SelfInfo/personal.jsp").forward(request, response);
                            }

                        }
                        catch (SQLException e) {
                            System.out.println("数据操作错误");
                            e.printStackTrace();
                        }

                        returnwarn(warn,"/SelfInfo/personal.jsp",request,response);
                    }
                    else
                    {
                        warn="the two password you typed do not match!";
                        returnwarn(warn,"/SelfInfo/personal.jsp",request,response);
                    }

                }
            else
                {
                    //提示密码输入错误
                    //发送提示信息到login页面
                    warn="original password error!";
                    request.getRequestDispatcher("/SelfInfo/personal.jsp").forward(request, response);

                }
              }

            }
            catch (SQLException e) {
                System.out.println("数据操作错误");
                e.printStackTrace();
            }



        }*/






    public void returnwarn(String warn,String target,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.getWriter().println("<script language=\"JavaScript\">\n" +
                "            function Tomain() {\n"+
                "            location.href = \""+target+"\"; \n" +
                "            }\n" +
                "            setTimeout(Tomain(),300);\n" +
                " alert(\""+warn+"\");</script>");
    }
    }



