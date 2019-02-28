package com.java.servlets;

import com.java.control.Databaseco;
import com.java.model.Community;
import com.java.model.Join_Apply;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class Update_Join extends HttpServlet {
    Databaseco databaseco=new Databaseco();
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

    //approve state->passed
    protected void approveJoin(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
       // System.out.print("pass1");
		/*this.getServletContext().setAttribute("methodSelectByUpdate","reviewCreate_Apply");*/
        String studentNum=request.getParameter("studentNum");
        String associationNum=request.getParameter("associationNum");
        databaseco.addJoin(studentNum,associationNum);
        result(request,response);


    }

    //refuse state->failed
    protected void refuseJoin(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
      //  System.out.println("refuse1");
        String studentNum=request.getParameter("studentNum");
        String associationNum=request.getParameter("associationNum");
        databaseco.failJoin(studentNum,associationNum);
       /* RequestDispatcher view=request.getRequestDispatcher("list");
        view.forward(request, response);*/
        result(request,response);
    }

    protected void deleteJoin(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
       // System.out.println("delete1");
        String studentNum=request.getParameter("studentNum");
        String associationNum=request.getParameter("associationNum");
        databaseco.deleteJoin(studentNum,associationNum);
       /* RequestDispatcher view=request.getRequestDispatcher("list");
        view.forward(request, response);*/
       result(request,response);

    }

    //批量拒绝
    protected  void refuseJoinForBox(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
      //  System.out.println("批量refuse1");
        String[] selected=request.getParameterValues("box1");
        String[] info;
        String studentNum;
        String associationNum;
        if(selected==null){
            System.out.println("无选中的对象");
        }else{
            for(int i=0;i<selected.length;i++){
                info=request.getParameterValues(selected[i]);
                studentNum=info[0];
                associationNum=info[1];
                databaseco.failJoin(studentNum,associationNum);
            }
        }
       /* RequestDispatcher view=request.getRequestDispatcher("list");
        view.forward(request,response);*/
       result(request,response);
    }

    //批量同意
    protected void approveJoinForBox(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
      //  System.out.println("批量approve1");
        String[] selected=request.getParameterValues("box1");
        String[] info;
        String studentNum;
        String associationNum;
        if(selected==null){
            System.out.println("无选中的对象");
        }else{
            for(int i=0;i<selected.length;i++){
                info=request.getParameterValues(selected[i]);
                studentNum=info[0];
                associationNum=info[1];
                databaseco.addJoin(studentNum,associationNum);
            }
        }
       /* RequestDispatcher view=request.getRequestDispatcher("list");
        view.forward(request,response);*/
       result(request,response);
    }

    //批量删除
    protected void deleteJoinForBox(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
       // System.out.println("批量delete1");
        String[] selected=request.getParameterValues("box2");
        String[] info;
        String studentNum;
        String associationNum;
        if(selected==null){
            System.out.println("无选中的对象");
        }else{
            for(int i=0;i<selected.length;i++){
                info=request.getParameterValues(selected[i]);
                studentNum=info[0];
                associationNum=info[1];
               databaseco.deleteJoin(studentNum,associationNum);
            }
        }
        result(request,response);
      /*  RequestDispatcher view=request.getRequestDispatcher("list");
        view.forward(request,response);*/
}

    protected void result(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        Community community = (Community)request.getSession().getAttribute("community");
        List<Join_Apply> unchecked=databaseco.getUncheckedJoin(community.getComID());
        List<Join_Apply>checked=databaseco.getCheckedJoin(community.getComID());
        request.setAttribute("unchecked", unchecked);
        request.setAttribute("checked", checked);
        RequestDispatcher view=request.getRequestDispatcher("/checkapply/checkapply.jsp");
        view.forward(request, response);
    /*response.getWriter().println(" <script>\n" +
            "                location.href = \"/checkapply.jsp\";\n" +
            "            </script>");*/
    }
}
