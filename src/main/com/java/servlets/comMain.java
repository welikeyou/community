package com.java.servlets;

import com.java.control.Databaseco;
import com.java.model.ComMainPage;
import com.java.model.Community;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class comMain extends HttpServlet {
    public Databaseco databaseco = new Databaseco();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        response.setHeader("content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

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
    protected void updatecommaininfo(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException
    {
        String tittle = request.getParameter("title");
        //String contact = request.getParameter("contact");
        Community community = (Community)request.getSession().getAttribute("community");
        String comID = community.getComID();
        databaseco.deleteComMainPageInfo(community.getComID(),tittle);
      //  List<ComMainPage> communityPageInfo = databaseco.getComMainPageByComID(comID);
       // request.getSession().setAttribute("communityPageInfo",communityPageInfo);
        response.getWriter().println("已修改");
    }
    protected void addcominfo(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException
    {
        String tittle = request.getParameter("tittle");
        String contact = request.getParameter("contact");
        Community community = (Community)request.getSession().getAttribute("community");
        String comID = community.getComID();
        databaseco.addComMainPageInfo(community.getComID(),tittle,contact);
        List<ComMainPage> communityPageInfo = databaseco.getComMainPageByComID(comID);
        request.getSession().setAttribute("communityPageInfo",communityPageInfo);
        response.getWriter().println("已增加");
    }
    protected void updateinfo(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException
    {
        String bgpic = request.getParameter("bgpic");
        String tipic = request.getParameter("tit");
       // String tit = request.getParameter("tit");
        String tit1 = request.getParameter("tit1");
        String con1 = request.getParameter("con1");
        String tit2 = request.getParameter("tit2");
        String con2 = request.getParameter("con2");
        Community community  = (Community)request.getSession().getAttribute("community");
        String comid = community.getComID();
        System.out.println(comid);
        databaseco.updateCommunityInfo(comid,tit1,con1,tit2,con2,bgpic,tipic);
         community = databaseco.getComByID(comid);
         request.getSession().setAttribute("community",community);
        response.sendRedirect("/mainpages/shouye.jsp");
    }
    protected void tdelete(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException
    {
        String title = request.getParameter("title");
        Community community  = (Community)request.getSession().getAttribute("community");
        String comid = community.getComID();
        databaseco.deleteComMainPageInfo(comid,title);
        List<ComMainPage> communityPageInfo = databaseco.getComMainPageByComID(comid);
        request.getSession().setAttribute("communityPageInfo",communityPageInfo);
        response.getWriter().println("已删除");
    }
}
