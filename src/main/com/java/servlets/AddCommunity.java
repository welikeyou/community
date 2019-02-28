package com.java.servlets;


/*
功能:申请加入社团的处理
创建者:黄友明
修改者:黄友明
 */
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.java.control.Databaseco;
import com.java.model.Activity;
import com.java.model.Community;
import com.java.model.Recruitment;
import com.java.model.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class AddCommunity extends HttpServlet {
    public Databaseco databaseco = new Databaseco();
    public Student student    = new Student();
    public int page;
    public int pagecount;
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException
    {

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

    protected void viewCommunities(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
    {
       page = 1;
        pagecount = databaseco.getRecPageCount();
        Recruitment[] recruitments = databaseco.viewRecruitments(page);
        request.getSession().setAttribute("recruitments",recruitments);
        RequestDispatcher view = request.getRequestDispatcher("/AddCommunity/viewCommunities.jsp");
        view.forward(request,response);
    }
    protected void viewDetail(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
    {
        String id = request.getParameter("retit");
        Recruitment recruitment = databaseco.getRecruitmentByTittle(id);
        Community community = databaseco.getComByID(recruitment.getAssociationNum());

        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("recruitment",gson.toJsonTree(recruitment));
        jsonObject.add("community",gson.toJsonTree(community));

        response.getWriter().println(jsonObject.toString());


    }
    protected void applyCommunity(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
    {
       /*String stuID = (String)request.getSession().getAttribute("stuID");*/
       /*if(true) {*/
       String id = request.getParameter("retit");
        Recruitment recruitment = databaseco.getRecruitmentByTittle(id);
        Community community = databaseco.getComByID(recruitment.getAssociationNum());
        request.getSession().setAttribute("comID",community.getComID());
       //   request.setAttribute("comID",recruitment.getAssociationNum());
           // databaseco.applyCommunity(stuID,commID);
           //Student student = (Student) request.getSession().getAttribute("loginStudent") ;
          // HashMap<String,String> personalAssociations=databaseco.getPersonalAssociation(student.getId());
          // request.setAttribute("personalAssociations", personalAssociations);
           RequestDispatcher view = request.getRequestDispatcher("/AddCommunity/applyCommunity.jsp");
           view.forward(request, response);
      /* }
       else
       {
          *//* String message = " login first please";
           response.getWriter().println(" <script charset=\"UTF-8\">\n" +
                   "\t\t\t alert(\""+message+"\")\n" +
                   "\t\t " +
                   "</script>");*//*
           try {
               Thread.sleep(2000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           response.sendRedirect("/mainpages/login.jsp");
       }*/
    }
    protected void addCommunity(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
    {
       // String stuID = (String)request.getSession().getAttribute("stuID");
        Student student = (Student)request.getSession().getAttribute("loginStudent") ;
        String commID =(String) request.getSession().getAttribute("comID");
        String department = request.getParameter("department");
        String hobby = request.getParameter("hobby");
        String reason = request.getParameter("reason");
        String contact = request.getParameter("contact");
      //  String stuRe = request.getParameter("stuRe");
        databaseco.applyCommunity(student.getId(),commID,department,hobby,reason,contact);
        String message = "你的申请已提交,请等待处理";
        //message = new String(message.getBytes("UTF-8"),"UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(" <script charset=\"UTF-8\">\n" +
                "\t\t\t alert(\""+message+"\")\n" +
                "\t\t " +
                "</script>");
        response.getWriter().println("<script language=\"JavaScript\">\n" +
                "    function Tomain() {\n" +
                "        location.href = \"/mainpages/afterlogin.jsp\";\n" +
                "    }\n" +
                "    setTimeout(Tomain(),300);\n" +
                "</script>");

    }
    protected void applyActivity(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
    {
        String title = request.getParameter("retit");
        request.getSession().setAttribute("retit",title);
        RequestDispatcher view = request.getRequestDispatcher("/AddCommunity/join.jsp");
        view.forward(request, response);
    }
    protected void addActivity(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
    {
       String title = (String) request.getSession().getAttribute("retit");
        Student student = (Student) request.getSession().getAttribute("loginStudent") ;
        String studentNum = student.getId();
        String grade = request.getParameter("grade");
        String hobby =  request.getParameter("hobby");
        String reason = request.getParameter("reason");
        String contact = request.getParameter("contact");
        Activity activity = databaseco.getActivityByTitle(title);
        String associationName = activity.getAssociationName();
        databaseco.addapplyforactivity(studentNum,grade,title,associationName,hobby,reason);
        response.sendRedirect("/mainpages/afterlogin.jsp");

    }
    protected void viewActDe(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
    {
        String id = request.getParameter("retit");
        Activity activity = databaseco.getActivityByTitle(id);
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("activity",gson.toJsonTree(activity));
        response.getWriter().println(jsonObject.toString());

    }
    protected  void pagedown(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
    {
       page--;
        changepage(request,response);
    }
    protected  void pageup(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
    {

        page++;
        changepage(request,response);

    }
   private void changepage(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
    {
        //pagecount = databaseco.getRecPageCount();
        if(page < 1)
            page =1;
        if(page >pagecount)
            page = pagecount;
        Recruitment[] recruitments = databaseco.viewRecruitments(page);
        request.getSession().setAttribute("recruitments",recruitments);
        RequestDispatcher view = request.getRequestDispatcher("/AddCommunity/viewCommunities.jsp");
        view.forward(request,response);
    }
    protected void checkComName(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
    {

    }
    /*protected void viewapllies(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
    {
        String stuID = (String)request.getSession().getAttribute("stuID");
        RequestDispatcher view = request.getRequestDispatcher("/AddCommunity/viewapplies.jsp");
        view.forward(request,response);

    }*/
}
