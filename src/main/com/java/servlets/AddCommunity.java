package com.java.servlets;


/*
功能:申请加入社团的处理
创建者:黄友明
修改者:黄友明
 */
import com.java.control.Databaseco;
import com.java.model.Community;
import com.java.model.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AddCommunity extends HttpServlet {
    public Databaseco databaseco = new Databaseco();
    public Student student    = new Student();
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException
    {
       /* request.getSession().setAttribute("stu",student);
        response.sendRedirect("/mainpages/afterlogin.jsp");*/
        //get which method to be used
        String methodName = request.getParameter("method");
        System.out.println("name:"+methodName);
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
        /*String[][] communities = databaseco.viewCommnuities();
        request.setAttribute("communities",communities);*/
       Community[] communities = databaseco.viewCommnuities();

        request.getSession().setAttribute("communities",communities);
        RequestDispatcher view = request.getRequestDispatcher("/AddCommunity/viewCommunities.jsp");
        view.forward(request,response);
    }
    /*protected void viewDetail(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
    {
        String id = request.getParameter("comID");
       String[] viewDetail =  databaseco.viewDetail(id);
       request.setAttribute("viewDetail",viewDetail);
        RequestDispatcher view = request.getRequestDispatcher("/AddCommunity/viewdetail.jsp");
        view.forward(request,response);

    }*/
    protected void applyCommunity(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
    {
       /*String stuID = (String)request.getSession().getAttribute("stuID");*/
       if(true) {
           //String commID = request.getParameter("comID");
           // databaseco.applyCommunity(stuID,commID);
           RequestDispatcher view = request.getRequestDispatcher("/AddCommunity/applyCommunity.jsp");
           view.forward(request, response);
       }
       else
       {
          /* String message = " login first please";
           response.getWriter().println(" <script charset=\"UTF-8\">\n" +
                   "\t\t\t alert(\""+message+"\")\n" +
                   "\t\t " +
                   "</script>");*/
           try {
               Thread.sleep(2000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           response.sendRedirect("/mainpages/login.jsp");
       }
    }
    protected void addCommunity(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
    {
        String stuID = (String)request.getSession().getAttribute("stuID");
        String commID = request.getParameter("comID");
        databaseco.applyCommunity(stuID,commID);
        String message = " are you ok";
        //message = new String(message.getBytes("UTF-8"),"UTF-8");
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
        /*RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
        view.forward(request,response);*/
    }
    /*protected void viewapllies(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
    {
        String stuID = (String)request.getSession().getAttribute("stuID");
        RequestDispatcher view = request.getRequestDispatcher("/AddCommunity/viewapplies.jsp");
        view.forward(request,response);

    }*/
}
