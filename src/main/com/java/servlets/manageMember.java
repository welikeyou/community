package com.java.servlets;/*
日期：2018/7/8
执行人：李兰
 */
import com.java.control.Databaseco;
import com.java.model.Community;
import com.java.model.Member;
import com.java.model.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class manageMember extends HttpServlet {

    public Databaseco databaseco = new Databaseco();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        //get which method to be used
        String methodName = request.getParameter("method");
        System.out.println(methodName+"方法名");
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
    }
        //以上为通用代码



 /*   protected void showMemberInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           //String clubNum=request.getParameter("clubNum");
          // String stuNum=request.getParameter("stuNum");
           String isShow="yes";
           String stuNum="1";
           System.out.println(stuNum+"这是获取的学生编号");
           Student memberInfo=databaseco.getStuByStuID(stuNum);
           request.setAttribute("memberInfo",memberInfo);
           request.setAttribute("isShow",isShow);
           request.getRequestDispatcher("/MemberManage/memberguanli.jsp").forward(request,response);
    }*/
    protected void deleteMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Community club=(Community)request.getSession().getAttribute("community");
        String stuNums=request.getParameter("stuNum");
        String []stuNum=stuNums.split(",");
        for(int i=0;i<stuNum.length;i++) {
            databaseco.deleteMember(club.getComID(), stuNum[i]);
        }
        List<Member> buZhang=databaseco.getAllBuZhang(club.getComID());
        List<Member> fuBu=databaseco.getAllFuBu(club.getComID());
        List<Member>  buWei=databaseco.getAllBuWei(club.getComID());
        request.setAttribute("allBuZhang",buZhang);
        request.setAttribute("allFuBu",fuBu);
        request.setAttribute("allBuWei",buWei);
        request.getRequestDispatcher("/MemberManage/memberguanli.jsp").forward(request,response);
    }
    protected void toBuZhang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Community club=(Community)request.getSession().getAttribute("community");
        String stuNums=request.getParameter("stuNum");
        String []stuNum=stuNums.split(",");
        for(int j=0;j<stuNum.length;j++)
        {
            System.out.println(stuNum[j]);
        }
        for(int i=0;i<stuNum.length;i++) {
            databaseco.allToBuZhang(club.getComID(), stuNum[i]);
        }
        List<Member> buZhang=databaseco.getAllBuZhang(club.getComID());
        List<Member> fuBu=databaseco.getAllFuBu(club.getComID());
        List<Member>  buWei=databaseco.getAllBuWei(club.getComID());
        request.setAttribute("allBuZhang",buZhang);
        request.setAttribute("allFuBu",fuBu);
        request.setAttribute("allBuWei",buWei);
        request.getRequestDispatcher("/MemberManage/memberguanli.jsp").forward(request,response);
    }
    protected void toFuBu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Community club=(Community)request.getSession().getAttribute("community");
        String stuNums=request.getParameter("stuNum");
        String []stuNum=stuNums.split(",");
        for(int i=0;i<stuNum.length;i++) {
            databaseco.allToFuBu(club.getComID(), stuNum[i]);
        }
        List<Member> buZhang=databaseco.getAllBuZhang(club.getComID());
        List<Member> fuBu=databaseco.getAllFuBu(club.getComID());
        List<Member>  buWei=databaseco.getAllBuWei(club.getComID());
        request.setAttribute("allBuZhang",buZhang);
        request.setAttribute("allFuBu",fuBu);
        request.setAttribute("allBuWei",buWei);
        request.getRequestDispatcher("/MemberManage/memberguanli.jsp").forward(request,response);
    }
    protected void toBuWei(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Community club=(Community)request.getSession().getAttribute("community");
        String stuNums=request.getParameter("stuNum");
        String []stuNum=stuNums.split(",");
        for(int i=0;i<stuNum.length;i++) {
            databaseco.allToBuWei(club.getComID(), stuNum[i]);
        }
        List<Member> buZhang=databaseco.getAllBuZhang(club.getComID());
        List<Member> fuBu=databaseco.getAllFuBu(club.getComID());
        List<Member>  buWei=databaseco.getAllBuWei(club.getComID());
        request.setAttribute("allBuZhang",buZhang);
        request.setAttribute("allFuBu",fuBu);
        request.setAttribute("allBuWei",buWei);
        request.getRequestDispatcher("/MemberManage/memberguanli.jsp").forward(request,response);
    }
}
