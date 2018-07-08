package com.java.servlets;
/*
功能: 对页面上方导航栏及其部分相关逻辑的处理
创建者:黄友明
修改者:黄友明,刘诗滢
 */
import com.java.control.Databaseco;
import com.java.model.Community;
import com.java.model.Create_Apply;
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
import java.util.List;

public class mainpage extends HttpServlet {
    public Databaseco databaseco = new Databaseco();
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException
    {
        //get which method to be used
        request.setCharacterEncoding("UTF-8");
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

        Community[] communities = databaseco.viewCommnuities();

        request.getSession().setAttribute("communities",communities);
        response.sendRedirect("/AddCommunity/viewCommunities.jsp");
    }
    protected void viewActivities(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException
    {
       viewCommunities(request,response);
    }
    protected void viewMyCommunities(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException
    {
        Student student = (Student) request.getSession().getAttribute("loginStudent") ;
        HashMap<String,String> personalAssociations=databaseco.getPersonalAssociation(student.getId());
        request.setAttribute("personalAssociations", personalAssociations);
        RequestDispatcher view = request.getRequestDispatcher("/myCommunities/myCommunities.jsp");
        view.forward(request, response);
    }
    protected void viewMyImformation(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException
    {
        RequestDispatcher view = request.getRequestDispatcher("/myImformation/myImformation.jsp");
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
        databaseco.applyEstablishCommunity(student.getId(),
                request.getParameter("applyName"),
                request.getParameter("applyType"),
                request.getParameter("applyReason"),
                request.getParameter("applyPlan"),
                request.getParameter("advantages"),
                student.getContact()
        );
        response.sendRedirect("/mainpages/afterlogin.jsp");

    }
}
