package com.java.servlets;/*
日期：2018/7/8
执行人：李兰
 */
import com.java.control.Databaseco;
import com.java.model.Community;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class clubChange extends HttpServlet {

    public Databaseco databaseco = new Databaseco();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
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


    protected void changeInfo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        Community clubInfo=(Community) request.getSession().getAttribute("community");
        String clubNum=request.getParameter("clubNum");
        String clubName=request.getParameter("clubName1");
        String clubType=request.getParameter("clubType1");
        String clubLevel=request.getParameter("clubLevel1");
        String clubAim=request.getParameter("clubAim1");
        String clubMember=request.getParameter("clubMember1");
        String clubBiefInfo=request.getParameter("clubBiefInfo1");
        clubInfo.setComID(clubNum);
        clubInfo.setComName(clubName);
        clubInfo.setType(clubType);
        clubInfo.setLevel(clubLevel);
        clubInfo.setAim(clubAim);
        clubInfo.setMembers(clubMember);
        clubInfo.setBiefInfo(clubBiefInfo);
        databaseco.UpdateClub(clubInfo);
        request.getSession().setAttribute("community",clubInfo);
        response.sendRedirect("/ClubInfo/information.jsp");
    }

}
