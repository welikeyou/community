package com.java.servlets;
/*
功能: 对页面上方导航栏及其部分相关逻辑的处理
创建者:黄友明
修改者:黄友明,刘诗滢
 */
import com.java.control.Databaseco;
import com.java.model.*;
/*import com.sun.xml.internal.ws.api.pipe.ServerTubeAssemblerContext;*/

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

public class mainpage extends HttpServlet {
    public Databaseco databaseco = new Databaseco();
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException
    {
        //get which method to be used
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String methodName = request.getParameter("method");

        try
        {
            Method method = getClass().getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);//获得方法
            method.setAccessible(true);
            method.invoke(this,request,response);//执行方法
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //以上为通用代码
    }
    protected void viewCommunities(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException
    {

      // Recruitment[] recruitments = databaseco.viewRecruitments(1);
       // request.getSession().setAttribute("recruitments",recruitments);
      //  response.sendRedirect("/AddCommunity/viewCommunities.jsp");
        request.setAttribute("method","viewCommunities");
        RequestDispatcher view = request.getRequestDispatcher("/AddCommunity.do");
        view.forward(request, response);
    }
    protected void viewbeCommunities(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException
    {

          Recruitment[] recruitments = databaseco.viewRecruitments(1);
        // request.getSession().setAttribute("recruitments",recruitments);
        //  response.sendRedirect("/AddCommunity/viewCommunities.jsp");
       // request.setAttribute("method","viewCommunities");
        request.setAttribute("recruitments",recruitments);
        RequestDispatcher view = request.getRequestDispatcher("/mainpages/viewCommunities.jsp");
        view.forward(request, response);
    }
    protected void viewbeActivities(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException
    {
        List<Activity> activities = databaseco.viewActivities();
        request.setAttribute("activities",activities);
        RequestDispatcher view = request.getRequestDispatcher("/mainpages/activity.jsp");
        view.forward(request, response);
    }

    protected void viewActivities(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException
    {
      List<Activity> activities = databaseco.viewActivities();
      request.setAttribute("activities",activities);
      RequestDispatcher view = request.getRequestDispatcher("/AddCommunity/activity.jsp");
      view.forward(request, response);
    }
    protected void viewMyCommunities(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException
    {
        Student student = (Student) request.getSession().getAttribute("loginStudent") ;
        HashMap<String,String> personalAssociations=databaseco.getPersonalAssociation(student.getId());
        request.setAttribute("personalAssociations", personalAssociations);
        HashMap<String, String> personalCreate = databaseco.getPersonalCreate(student.getId());
        request.getSession().setAttribute("personalCreate", personalCreate);
        HashMap<String, String> personalManage = databaseco.getPersonalManage(student.getId());
        request.setAttribute("personalManage", personalManage);
        RequestDispatcher view = request.getRequestDispatcher("/myCommunities/myCommunities.jsp");
        view.forward(request, response);
    }
    protected void showTask(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException
    {
      //  request.setAttribute("method","showTask");
        RequestDispatcher view = request.getRequestDispatcher("/receiveTask.do");
        view.forward(request, response);
    }
    protected void reviewCreate_Apply(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        //System.out.println("=====reviewCreate_Apply");
        List<Create_Apply> unchecked=databaseco.getUncheckedAssociations();
        List<Create_Apply>checked=databaseco.getCheckedAssociations();
        request.getSession().setAttribute("unchecked", unchecked);
        request.getSession().setAttribute("checked", checked);
        /*HttpSession session = request.getSession();
        session.setAttribute("list", unchecked);*/
        RequestDispatcher view=request.getRequestDispatcher("/mainpages/review.jsp");
        view.forward(request, response);
    }
    protected void estCom(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException {
       // Student student = (Student) request.getSession().getAttribute("loginStudent") ;
        RequestDispatcher view=request.getRequestDispatcher("/myCommunities/establish.jsp");
        view.forward(request, response);

    }
    protected void AplEstCom(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException {
        Student student = (Student) request.getSession().getAttribute("loginStudent") ;
        String  studentNum = student.getId();
        String associationName = request.getParameter("name");
        String  level = request.getParameter("level");
        String introduction = request.getParameter("introdution");
        String advantage = "有理想";
        String contact = request.getParameter("contact");
        String  member1 = request.getParameter("member1");
        String briefintro1 = request.getParameter("briefintro1");
        String  member2 = request.getParameter("member2");
        String briefintro2 = request.getParameter("briefintro2");
        String  member3 = request.getParameter("member3");
        String briefintro3 = request.getParameter("briefintro3");
        String  member4 = request.getParameter("member4");
        String briefintro4 = request.getParameter("briefintro4");
        String  member5 = request.getParameter("member5");
        String briefintro5 = request.getParameter("briefintro5");
        String plan = request.getParameter("plan");
        String address = request.getParameter("address");
        String logo = request.getParameter("logo");
        String  aim = request.getParameter("aim");
        String type = request.getParameter("type");
        databaseco.applyEstablishCommunity(studentNum,associationName,level,type,aim,logo,address,plan,introduction,advantage,contact,member1,briefintro1,member2,briefintro2,briefintro3,briefintro3,member4,briefintro4,member5,briefintro5);
        response.sendRedirect("/mainpages/afterlogin.jsp");
    }
    protected  void getComMain(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
    {
        String comID = request.getParameter("comID");
        Community community = databaseco.getComByID(comID);
        Student student = (Student) request.getSession().getAttribute("loginStudent") ;
        String  studentNum = student.getId();
        String pos = databaseco.getposition(studentNum,comID);
        request.getSession().setAttribute("pos",pos);
        request.getSession().setAttribute("community",community);
        List<ComMainPage> communityPageInfo = databaseco.getComMainPageByComID(comID);
        request.getSession().setAttribute("communityPageInfo",communityPageInfo);
        response.sendRedirect("/mainpages/shouye.jsp");
    }

    protected void fenpeirenwu(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
    {
        Community community = (Community) request.getSession().getAttribute("community");
      Student[][] students =  databaseco.getStuByPos(community.getComID());
      Student president = students[0][0];
      Student[] vices = students[1];
        Student[] commons = students[2];
        request.setAttribute("president",president);
        request.setAttribute("vices",vices);
        request.setAttribute("commons",commons);
        RequestDispatcher view=request.getRequestDispatcher("/assignment/assignment.jsp");
        view.forward(request, response);
    }
    protected void showMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Community club=(Community)request.getSession().getAttribute("community");
       List<Member> buZhang=databaseco.getAllBuZhang(club.getComID());
        List<Member> fuBu=databaseco.getAllFuBu(club.getComID());
        List<Member> buWei=databaseco.getAllBuWei(club.getComID());
        request.setAttribute("allBuZhang",buZhang);
        request.setAttribute("allFuBu",fuBu);
        request.setAttribute("allBuWei",buWei);
        request.getRequestDispatcher("/MemberManage/memberguanli.jsp").forward(request,response);
    }
    //接收来自界面的社团信息并更改
    protected void showClubInfo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       /* Community club=(Community)request.getSession().getAttribute("community");
        Community clubInfo=databaseco.getClubByNum(club.getComID());
        request.getSession().setAttribute("community",clubInfo);*/
        response.sendRedirect("/ClubInfo/information.jsp");
    }
    protected void reviewJoin_Apply(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
        //String associationNum= (String) request.getSession().getAttribute("associationNum");
        Community community = (Community)request.getSession().getAttribute("community");
        List<Join_Apply> unchecked=databaseco.getUncheckedJoin(community.getComID());
        List<Join_Apply>checked=databaseco.getCheckedJoin(community.getComID());
        request.setAttribute("unchecked", unchecked);
        request.setAttribute("checked", checked);
        RequestDispatcher view=request.getRequestDispatcher("/checkapply/checkapply.jsp");
        view.forward(request, response);

    }
    protected void reviewFunds(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
        // String associationNum=(String)request.getSession().getAttribute("associationNum");
        Community community = (Community)request.getSession().getAttribute("community");
        List<Fund> fundInfo=databaseco.getFundInfo(community.getComID());
        //double money=databaseco.getAssociationMoney(associationNum);//获得当前余额
        //test
        double money=0;

        request.setAttribute("money",money);
        request.setAttribute("fundInfo",fundInfo);
        RequestDispatcher view=request.getRequestDispatcher("/money/funds.jsp");
        view.forward(request,response);

    }
    //活动审核显示
    protected void reviewActivity_Join(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
        Community community = (Community)request.getSession().getAttribute("community");
        String associaitonNum= community.getComID();
        List<Activity_Apply>checked=databaseco.getCheckedAc(associaitonNum) ;
        List<Activity_Apply>unchecked=databaseco.getUncheckedAc(associaitonNum);
        request.setAttribute("checkedAcJoin",checked);
        request.setAttribute("uncheckedAcJoin",unchecked);
        RequestDispatcher view=request.getRequestDispatcher("/checkapply/checkJoin.jsp");
        view.forward(request,response);
    }
    protected void exit(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException
    {
        Community community = (Community)request.getSession().getAttribute("community");
        Student student = (Student) request.getSession().getAttribute("loginStudent") ;
      String studentNum =student.getId();
      String associationNum = community.getComID();
      String associationName = community.getComName();
        databaseco.exitAssociation(studentNum,associationNum,associationName);
        response.sendRedirect("/mainpages/afterlogin.jsp");
    }
}
